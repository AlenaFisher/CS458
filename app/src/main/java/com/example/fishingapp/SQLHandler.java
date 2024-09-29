/**
 * @author  Alena Fisher
 * 9/28/2024
 */
package com.example.fishingapp;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//import com.example.fishingapp.Model.ListModel; !!!!!!!!!!!!!!!
import java.util.ArrayList;
import java.util.List;

public class SQLHandler extends SQLiteOpenHelper {
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

    private static final String CREATE_TABLE = "CREATE TABLE " + LIST_TABLE + "(" + ID + " INTEGER PRIMARY KEY, " + FIRST_NAME + " TEXT, " + LAST_NAME + " TEXT, " +
            USER_NAME + " TEXT, " + PASSWORD + " TEXT, " + EMAIL_ADDRESS + " TEXT, " + PHONE_NUMBER + " TEXT," + DATE_OF_BIRTH + " TEXT" + ")";

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
        ContentValues cv = new ContentValues();
        cv.put(FIRST_NAME, user.getFirstName());
        cv.put(LAST_NAME, user.getLastName());
        cv.put(USER_NAME, user.getUserName());
        cv.put(PASSWORD, user.getPassword());
        cv.put(EMAIL_ADDRESS, user.getEmailAddress());
        cv.put(PHONE_NUMBER, user.getPhoneNumber());
        cv.put(DATE_OF_BIRTH, user.getDateOfBirth());

        sqlDatabase.insert(LIST_TABLE, null, cv);
    }

    @SuppressLint("Range")
    public List<User> getAllUsers() {
        List<User> listOfUsers = new ArrayList<>();
        Cursor cur = null;

        try {
            cur = sqlDatabase.query(LIST_TABLE, null, null, null, null, null, null);
            if(cur != null && cur.moveToFirst()) {
                do {
                    User user = new User(cur.getInt(cur.getColumnIndex(ID)),
                            cur.getString(cur.getColumnIndex(FIRST_NAME)),
                            cur.getString(cur.getColumnIndex(LAST_NAME)),
                            cur.getString(cur.getColumnIndex(USER_NAME)),
                            cur.getString(cur.getColumnIndex(PASSWORD)),
                            cur.getString(cur.getColumnIndex(EMAIL_ADDRESS)),
                            cur.getString(cur.getColumnIndex(PHONE_NUMBER)),
                            cur.getString(cur.getColumnIndex(DATE_OF_BIRTH)));
                    listOfUsers.add(user);
                } while(cur.moveToNext());
            }
        } finally {
            if (cur != null) {
                cur.close();
            }
        }
        return listOfUsers;
    }

    public void updateUser(User user) {
        ContentValues cv = new ContentValues();
        cv.put(FIRST_NAME, user.getFirstName());
        cv.put(LAST_NAME, user.getLastName());
        cv.put(USER_NAME, user.getUserName());
        cv.put(PASSWORD, user.getPassword());
        cv.put(EMAIL_ADDRESS, user.getEmailAddress());
        cv.put(PHONE_NUMBER, user.getPhoneNumber());
        cv.put(DATE_OF_BIRTH, user.getDateOfBirth());

        sqlDatabase.update(LIST_TABLE, cv, ID + "=?", new String[] {String.valueOf(user.getId())});
    }

    public void deleteUser(int id) {
        sqlDatabase.delete(LIST_TABLE, ID + "=?", new String[] {String.valueOf(id)});
    }
}
