/**
 * @author  Alena Fisher
 * Created on 9/28/2024
 * Last updated on 9/30/2024
 */

package com.example.fishingapp;
// Importing necessary libraries
import android.content.Context;
import java.util.List;

public class DatabaseHandler extends SQLHandler {
    /**
     * This file is responsible for the addition, updating, and removal of users
     * from the database
     * @param context
     */

    public DatabaseHandler(Context context) {
        /**
         * This method creates a new instance of the database
         */
        super(context);
    }

    public void insertUser(User user) {
        /**
         * This method is responsible for handling the user that is passed
         * as a parameter and placing it in the database
         *
         * @param The user to be placed into the database
         */
        super.insertUser(user);
    }

    public List<User> getAllUsers() {
        /**
         * This method is responsible for retrieving the users from the database
         */
        return super.getAllUsers();
    }

    public void updateUser(User user) {
        /**
         * This method is responsible for updating the user
         * in the database
         */
        super.updateUser(user);
    }

    public void deleteUser(int id) {
        /**
         * This method is responsible for deleting a user from the database
         */
        super.deleteUser(id);
    }

}
