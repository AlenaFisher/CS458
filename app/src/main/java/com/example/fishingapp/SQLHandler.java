/**
 * @author  Alena Fisher
 * Created on 9/28/2024
 * Last updated on 9/30/2024
 */

// Importing all necessary libraries
package com.example.fishingapp;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SQLHandler extends SQLiteOpenHelper {
    /**
     * This is the main entry point of this file.
     * There are methods that aid in the addition, viewing, updating, and
     * deletion of users from the database
     * @param   a list of possible arguments passed by the user
     */

    // Declaring and initializing all variables for the user table
    private static final int VERSION = 1; // Version of the database
    private static final String NAME = "userDatabase"; // Name of the database
    public static final String LIST_TABLE = "users"; // Name of the table
    public static final String ID = "id";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String USER_NAME = "user_name";
    public static final String PASSWORD = "password";
    public static final String EMAIL_ADDRESS = "email_address";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String DATE_OF_BIRTH = "date_of_birth";

    //Declaring and initializing all variables for the catch table
    public static final String CATCH_TABLE = "catches";
    public static final String PRIMARY_KEY_HOLDER = "key_holder";// Name of the table
    public static final String FISH_TYPE = "fish_type";
    public static final String CATCH_ID = "catch_id";
    public static final String LENGTH = "length";
    public static final String WEIGHT = "weight";
    public static final String BAIT_USED = "bait_used";
    public static final String LOCATION = "Location";


    // Creating the user table for the database
    private static final String CREATE_TABLE = "CREATE TABLE " + LIST_TABLE + "(" + ID + " INTEGER PRIMARY KEY, " + FIRST_NAME + " TEXT, " + LAST_NAME + " TEXT, " +
            USER_NAME + " TEXT, " + PASSWORD + " TEXT, " + EMAIL_ADDRESS + " TEXT, " + PHONE_NUMBER + " TEXT," + DATE_OF_BIRTH + " TEXT" + ")";

    //Creating table for catches
    private static final String CREATE_CATCH_TABLE = "CREATE TABLE " + CATCH_TABLE + "(" + PRIMARY_KEY_HOLDER + " INTEGER PRIMARY KEY, " + CATCH_ID + " INTEGER, " + FISH_TYPE + " TEXT, " + LENGTH + " TEXT, " +
            WEIGHT + " TEXT, " + BAIT_USED + " TEXT, " + LOCATION + " TEXT" + ")";

    // Declaring the database
    private SQLiteDatabase sqlDatabase;

    public SQLHandler(Context context) {
        /**
         * This method is responsible for initializing the database
         * with the name and version
         */
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqlDatabase) {
        /**
         * This method is responsible for creating the database
         */
        sqlDatabase.execSQL(CREATE_CATCH_TABLE);
        sqlDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqlDatabase, int oldVersion, int newVersion) {
        /**
         * This method is responsible for removes the existing table if it exists
         * and creates a new table
         */
        sqlDatabase.execSQL("DROP TABLE IF EXISTS " + LIST_TABLE);
        sqlDatabase.execSQL("DROP TABLE IF EXISTS " + CATCH_TABLE);
        onCreate(sqlDatabase);
    }

    public void openDatabase() {
        /**
         * This method is responsible for opening the database and
         * writing the operations
         */
        sqlDatabase = this.getWritableDatabase();
    }

    public void insertUser(User user) {
        /**
         * This method is responsible for placing a user into the database
         * @param The user to be placed into the database
         */

        // Creating the content value for the information to be placed in
        ContentValues cv = new ContentValues();

        // Placing the user information into the content value
        cv.put(FIRST_NAME, user.getFirstName());
        cv.put(LAST_NAME, user.getLastName());
        cv.put(USER_NAME, user.getUserName());
        cv.put(PASSWORD, user.getPassword());
        cv.put(EMAIL_ADDRESS, user.getEmailAddress());
        cv.put(PHONE_NUMBER, user.getPhoneNumber());
        cv.put(DATE_OF_BIRTH, user.getDateOfBirth());

        // Placing the user into the database
        sqlDatabase.insert(LIST_TABLE, null, cv);
    }

    @SuppressLint("Range")
    public void insertCatch(Catch fishCatch){

        /**
         * This method is responsible for placing a catch into the database
         * @param The catch to be placed into the database
         */

        // Creating the content value for the information to be placed in
        ContentValues cv = new ContentValues();

        // Placing the user information into the content value
        cv.put(CATCH_ID, fishCatch.getId());
        cv.put(FISH_TYPE, fishCatch.getTitle());
        cv.put(LENGTH, fishCatch.getLength());
        cv.put(WEIGHT, fishCatch.getWeight());
        cv.put(BAIT_USED, fishCatch.getBaitUsed());
        cv.put(LOCATION, fishCatch.getLocation());

        // Placing the user into the database
        sqlDatabase.insert(CATCH_TABLE, null, cv);

    }

    @SuppressLint("Range")
    public List<User> getAllUsers() {
        /**
         * This method is responsible for gather all users from the database
         * and placing them into a list
         */

        // Creating an array list for the users
        List<User> listOfUsers = new ArrayList<>();
        // Creating a cursor to iterate across the database
        Cursor cur = null;
        // Starting the process
        sqlDatabase.beginTransaction();

        // try-catch sequence
        try {
            // Getting all users from the database
            cur = sqlDatabase.query(LIST_TABLE, null, null, null, null, null, null);
            if(cur != null && cur.moveToFirst()) { // Checking if the cursor is null and moving it
                do {
                    // Creating a new user and placing the information into the list as the cursor iterates
                    User user = new User(cur.getInt(cur.getColumnIndex(ID)),
                            cur.getString(cur.getColumnIndex(FIRST_NAME)),
                            cur.getString(cur.getColumnIndex(LAST_NAME)),
                            cur.getString(cur.getColumnIndex(USER_NAME)),
                            cur.getString(cur.getColumnIndex(PASSWORD)),
                            cur.getString(cur.getColumnIndex(EMAIL_ADDRESS)),
                            cur.getString(cur.getColumnIndex(PHONE_NUMBER)),
                            cur.getString(cur.getColumnIndex(DATE_OF_BIRTH)));
                    // Adding the user and their information to the list
                    listOfUsers.add(user);
                } while(cur.moveToNext());
            }
        } finally {
            sqlDatabase.endTransaction();
            if (cur != null) {
                cur.close(); // Closing the cursor if it is null
            }
        }
        // Returning the list of users
        return listOfUsers;
    }


    /**
     * This method gets the catches for a specific user and stores them in a linked list
     * @param userID
     * @return
     */
    @SuppressLint("Range")
    public LinkedList<Catch> getUserCatches(int userID) {

        //creating linked list
        LinkedList<Catch> listOfCatches = new LinkedList<>();

        //initializing query and database as readable
        String selectQuery = "SELECT * FROM " + CATCH_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        //initializing cursor
        Cursor cur = db.rawQuery(selectQuery,  null);

        if(cur != null && cur.moveToFirst()) {
            do {
                // Creating a catch and placing the information into the linked list as the cursor iterates
                if (cur.getInt(cur.getColumnIndex(CATCH_ID)) == userID) {
                    Catch fishCatch = new Catch(
                            cur.getInt(cur.getColumnIndex(CATCH_ID)),
                            cur.getString(cur.getColumnIndex(FISH_TYPE)),
                            cur.getString(cur.getColumnIndex(LOCATION)),
                            cur.getString(cur.getColumnIndex(WEIGHT)),
                            cur.getString(cur.getColumnIndex(BAIT_USED)),
                            cur.getString(cur.getColumnIndex(LENGTH)));
                    int uniqueID = cur.getInt(cur.getColumnIndex((PRIMARY_KEY_HOLDER)));
                    fishCatch.setUnique_ID(uniqueID);

                    // Adding the catch and their information to the list
                    listOfCatches.add(fishCatch);
                }
            } while (cur.moveToNext());
        }
        cur.close();
        db.close();

        return listOfCatches;
    }

    public void updateUser(User user) {
        /**
         * This method is responsible for updating the information of a user
         * in the database
         * @param A user that is already in the database
         */

        // Creating a content value to place the user into
        ContentValues cv = new ContentValues();

        // Placing the user information into the content value
        cv.put(FIRST_NAME, user.getFirstName());
        cv.put(LAST_NAME, user.getLastName());
        cv.put(USER_NAME, user.getUserName());
        cv.put(PASSWORD, user.getPassword());
        cv.put(EMAIL_ADDRESS, user.getEmailAddress());
        cv.put(PHONE_NUMBER, user.getPhoneNumber());
        cv.put(DATE_OF_BIRTH, user.getDateOfBirth());

        // Updating the database
        sqlDatabase.update(LIST_TABLE, cv, ID + "=?", new String[] {String.valueOf(user.getId())});
    }

    /**
     * This method updates a catch when a user edits
     * @param fishCatch
     */
    public void updateCatch(Catch fishCatch) {

        ContentValues cv = new ContentValues();

        // Placing the catch information into the content value
        cv.put(FISH_TYPE, fishCatch.getTitle());
        cv.put(LENGTH, fishCatch.getLength());
        cv.put(WEIGHT, fishCatch.getWeight());
        cv.put(BAIT_USED, fishCatch.getBaitUsed());
        cv.put(LOCATION, fishCatch.getLocation());

        // Updating the database
        sqlDatabase.update(CATCH_TABLE, cv, PRIMARY_KEY_HOLDER + "=?",new String[]{String.valueOf(fishCatch.getUnique_ID())});
    }

    public void deleteUser(int id) {
        /**
         * This method is responsible for deleting a user from the database
         * @param The ID of a user to be deleted from the database
         */
        sqlDatabase.delete(LIST_TABLE, ID + "=?", new String[] {String.valueOf(id)});
    }

    /**
     * This method deletes a catch
     * @param id
     */
    public void deleteCatch(int id) {

        sqlDatabase.delete(CATCH_TABLE, PRIMARY_KEY_HOLDER + "=?", new String[] {String.valueOf(id)});
    }

    /**
     * Method to search through the database to see if a user requested username is already taken.
     * @param userName
     * @return
     */
    public boolean searchUsers(String userName){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " + LIST_TABLE + " WHERE " + USER_NAME + " = ? ", new String[] {userName});
        if(cursor.getCount() > 0) return false;
        else return true;
    }


}
