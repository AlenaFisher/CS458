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
import java.util.List;

public class SQLHandler extends SQLiteOpenHelper {
    /**
     * This is the main entry point of this file.
     * There are methods that aid in the addition, viewing, updating, and
     * deletion of users from the database
     * @param   a list of possible arguments passed by the user
     */

    // Declaring and initializing all variables for the table
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

    // Creating the table for the database
    private static final String CREATE_TABLE = "CREATE TABLE " + LIST_TABLE + "(" + ID + " INTEGER PRIMARY KEY, " + FIRST_NAME + " TEXT, " + LAST_NAME + " TEXT, " +
            USER_NAME + " TEXT, " + PASSWORD + " TEXT, " + EMAIL_ADDRESS + " TEXT, " + PHONE_NUMBER + " TEXT," + DATE_OF_BIRTH + " TEXT" + ")";

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
        sqlDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqlDatabase, int oldVersion, int newVersion) {
        /**
         * This method is responsible for removes the existing table if it exists
         * and creates a new table
         */
        sqlDatabase.execSQL("DROP TABLE IF EXISTS " + LIST_TABLE);
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

    public void deleteUser(int id) {
        /**
         * This method is responsible for deleting a user from the database
         * @param The ID of a user to be deleted from the database
         */
        sqlDatabase.delete(LIST_TABLE, ID + "=?", new String[] {String.valueOf(id)});
    }
}
