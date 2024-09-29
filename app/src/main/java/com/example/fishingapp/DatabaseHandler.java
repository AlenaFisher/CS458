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

public class DatabaseHandler extends SQLHandler {
    public DatabaseHandler(Context context) {
        super(context);
    }

    public void insertUser(User user) {
        super.insertUser(user);
    }

    public List<User> getAllUsers() {
        return super.getAllUsers();
    }

    public void updateUser(User user) {
        super.updateUser(user);
    }

    public void deleteUser(int id) {
        super.deleteUser(id);
    }

}
