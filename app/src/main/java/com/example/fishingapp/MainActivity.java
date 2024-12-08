package com.example.fishingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Benaiah Gingrich
 * @date 12/04//2024
 */

public class MainActivity extends AppCompatActivity {

    /**
     * Creating dummy accounts and a list as a fake database for progress purposes
     */
    User DAlonzo = new User(1, "Devon", "Alonzo", "DAlonzo", "MightyMouse", "MightyMouse@short-king.com", "5758675309", "01/01/2024");
    User AFisher = new User(2, "Alena", "Fisher", "AFisher", "RheaRhipley", "Commander_Coder@in-charge.net", "5758675309", "01/01/2003");
    User BGingrich = new User(3, "Benaiah", "Gingrich", "BGingrich", "Himbo1", "empty_headed@comedycentral.com", "5758675309", "01/01/1804");
    ArrayList<User> fakeUserDataBase = new ArrayList<>();
    DatabaseHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        fakeUserDataBase.add(DAlonzo);
        fakeUserDataBase.add(AFisher);
        fakeUserDataBase.add(BGingrich);
        handler = new DatabaseHandler(this);
        handler.openDatabase();
        printUserNames();
    }

    /**
     * Test method to check stored values in the database.
     */
    public void printUserNames() {
        List<User> userList = handler.getAllUsers();
        for (int i = 0; i < userList.size(); i++) {
            System.out.println(userList.get(i).getUserName());
        }
        handler.openDatabase();
        boolean uh = handler.searchUsers("BGingrich");
        System.out.println(uh);
    }

    /**
     * This method takes the input from a user in EditText fields then changes that input from EditText to string.
     * The method then iterates through the database checking user given inputs versus accounts in database.
     * If inputs are valid, user is directed to a new page.
     * If inputs are invalid, user is given a warning.
     *
     * @param view
     */
    public void onLoginButtonClick(View view) {
        EditText userNameInput = findViewById(R.id.userNameTxt);
        String userNameInputString = userNameInput.getText().toString();
        EditText userPasswordInput = findViewById(R.id.passwordTxt);
        String userPasswordInputString = userPasswordInput.getText().toString();
        boolean correctInformation = false;
        List<User> listOfUsers = handler.getAllUsers();

        for (int i = 0; i < listOfUsers.size(); i++) {
            if (userNameInputString.equals(listOfUsers.get(i).getUserName()) && userPasswordInputString.equals(listOfUsers.get(i).getPassword())) {
                int id = listOfUsers.get(i).getId();

                SharedPreferences sharedPref = getSharedPreferences("MyUsers", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("username", userNameInput.getText().toString());
                editor.apply();
                correctInformation = true;

                setContentView(R.layout.successful_login_temporary_page);
                Intent intent = new Intent(MainActivity.this, FragmentActivity.class);

                //creating a bundle and passing the current users ID to the next activity
                Bundle bundle = new Bundle();
                bundle.putInt("Id", id);
                intent.putExtras(bundle);

                startActivity(intent);
                finish();
                break;
            }

        }
        if (!correctInformation) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Incorrect Username or Password");
            builder.setPositiveButton("Ok", (dialog, which) -> dialog.cancel());
            AlertDialog incorrectInformation = builder.create();
            incorrectInformation.show();
        }
    }

    /**
     * Method to create an alert dialog box to receive user input in various fields to create a new user account.
     * Method checks that the user password was entered correctly. If not, an error dialog box is displayed and the account creation box closes.
     * Method checks that the user requested user name is not already stored in the database, if it is an error is displayed.
     * If user passwords match and the username is not already taken, method passes user input to a method to create a new user object that is stored in the fake database.
     * The user can then login from the login page with their username and password.
     *
     * @param view
     */
    public void onCreateAccountButtonClick(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Create Account");

        final View account_information_view = getLayoutInflater().inflate(R.layout.account_creation_information_page, null);
        builder.setView(account_information_view);
        EditText firstName = account_information_view.findViewById(R.id.FirstName);
        EditText lastName = account_information_view.findViewById(R.id.LastName);
        EditText emailAddress = account_information_view.findViewById(R.id.EmailAddress);
        EditText phoneNumber = account_information_view.findViewById(R.id.PhoneNumber);
        EditText dateOfBirth = account_information_view.findViewById(R.id.DateOfBirth);
        EditText userName = account_information_view.findViewById(R.id.UserName);
        EditText password = account_information_view.findViewById(R.id.Password);
        EditText confirmPassword = account_information_view.findViewById(R.id.ConfirmPassword);

        builder.setPositiveButton("Create Account", (dialog, which) -> {
            String inputPassword = password.getText().toString();
            String inputConfirmPassword = confirmPassword.getText().toString();
            String inputUserName = userName.getText().toString();
            boolean userNameTaken = handler.searchUsers(inputUserName);
            boolean passwordMatch = inputPassword.equals(inputConfirmPassword);

            if (userNameTaken && passwordMatch) {
                sendDialogDataToAccountCreation(firstName.getText().toString(),
                        lastName.getText().toString(),
                        emailAddress.getText().toString(),
                        phoneNumber.getText().toString(),
                        dateOfBirth.getText().toString(),
                        userName.getText().toString(),
                        inputPassword);
            } else if (!passwordMatch) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setTitle("Error!");
                builder1.setMessage("Passwords Do Not Match");

                builder1.setPositiveButton("OK", (dialog1, which1) -> dialog1.dismiss());

                AlertDialog dialog1 = builder1.create();
                dialog1.show();
            } else if (!userNameTaken) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setTitle("Error!");
                builder1.setMessage("User Name Taken");

                builder1.setPositiveButton("OK", (dialog1, which1) -> dialog1.dismiss());

                AlertDialog dialog1 = builder1.create();
                dialog1.show();
            }

        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Method that receives user input for all fields required to create a new user object, creates the user object, and passes it to the DatabaseHandler
     * to be stored in the Database.
     *
     * @param firstName    the user input first name
     * @param lastName     the user input last name
     * @param emailAddress the user input email address
     * @param phoneNumber  the user input phone number
     * @param dateOfBirth  the user input date of birth
     * @param userName     the user input username
     * @param password     the user input password
     */
    public void sendDialogDataToAccountCreation(String firstName, String lastName, String emailAddress, String phoneNumber, String dateOfBirth, String userName, String password) {
        Random rand = new Random();
        User newUser = new User(rand.nextInt(25), firstName, lastName, userName, password, emailAddress, phoneNumber, dateOfBirth);
        handler.openDatabase();
        handler.insertUser(newUser);
        setContentView(R.layout.successful_login_temporary_page);
        Intent intent = new Intent(MainActivity.this, FragmentActivity.class);
        startActivity(intent);
        finish();
//        fakeUserDataBase.add(newUser);
    }

}

