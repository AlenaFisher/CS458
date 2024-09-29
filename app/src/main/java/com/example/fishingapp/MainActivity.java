package com.example.fishingapp;

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
import java.util.Random;

/**
 * @author Benaiah Gingrich
 * @date 09/29/2024
 */

public class MainActivity extends AppCompatActivity {

    /**
     * Creating dummy accounts and a list as a fake database for progress purposes
     */
    User DAlonzo = new User(1, "Devon", "Alonzo", "DAlonzo", "MightyMouse", "MightyMouse@short-king.com", "5758675309", "01/01/2024");
    User AFisher = new User(2, "Alena", "Fisher", "AFisher", "RheaRhipley", "Commander_Coder@in-charge.net", "5758675309", "01/01/2003");
    User BGingrich = new User(3, "Benaiah", "Gingrich", "BGingrich", "Himbo1", "empty_headed@comedycentral.com", "5758675309", "01/01/1804");
    ArrayList<User> fakeUserDataBase=new ArrayList<>();


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

    }

    /**
     * This method takes the input from a user in EditText fields then changes that input from EditText to string.
     * The method then iterates through the fake database checking user given inputs versus accounts in "database".
     * If inputs are valid, user is directed to a new page.
     * If inputs are invalid, user is given a warning.
     * @param view
     */
    public void onLoginButtonClick(View view){
        EditText userNameInput=findViewById(R.id.userNameTxt);
        String userNameInputString = userNameInput.getText().toString();
        EditText userPasswordInput=findViewById(R.id.passwordTxt);
        String userPasswordInputString = userPasswordInput.getText().toString();
        boolean correctInformation = false;

        for(int i=0; i<fakeUserDataBase.size(); i++){
            if(userNameInputString.equals(fakeUserDataBase.get(i).getUserName()) && userPasswordInputString.equals(fakeUserDataBase.get(i).getPassword())){
                correctInformation = true;
//                changeView();
                setContentView(R.layout.successful_login_temporary_page);
            }

            }
        if(!correctInformation){
            AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Incorrect Username or Password");
            builder.setPositiveButton("Ok", (dialog, which) -> dialog.cancel());
            AlertDialog incorrectInformation = builder.create();
            incorrectInformation.show();
        }
    }

    /**
     * Method to create an alert dialog box to receive user input in various fields to create a new user account.
     * Method checks that the user password was entered correctly. If not, an error dialog box is displayed and the account creation box closes.
     * If user passwords match, method passes user input to a method to create a new user object that is stored in the fake database.
     * The user can then login from the login page with their username and password.
      * @param view
     */
    public void onCreateAccountButtonClick(View view){

        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Create Account");

        final View account_information_view = getLayoutInflater().inflate(R.layout.account_creation_information_page, null);
        builder.setView(account_information_view);
        EditText firstName=account_information_view.findViewById(R.id.FirstName);
        EditText lastName=account_information_view.findViewById(R.id.LastName);
        EditText emailAddress=account_information_view.findViewById(R.id.EmailAddress);
        EditText phoneNumber=account_information_view.findViewById(R.id.PhoneNumber);
        EditText dateOfBirth=account_information_view.findViewById(R.id.DateOfBirth);
        EditText userName=account_information_view.findViewById(R.id.UserName);
        EditText password=account_information_view.findViewById(R.id.Password);
        EditText confirmPassword=account_information_view.findViewById(R.id.ConfirmPassword);

        builder.setPositiveButton("Create Account", (dialog, which) -> {
            String inputPassword = password.getText().toString();
            String inputConfirmPassword = confirmPassword.getText().toString();
            if(inputPassword.equals(inputConfirmPassword)){
                sendDialogDataToAccountCreation(firstName.getText().toString(),
                        lastName.getText().toString(),
                        emailAddress.getText().toString(),
                        phoneNumber.getText().toString(),
                        dateOfBirth.getText().toString(),
                        userName.getText().toString(),
                        inputPassword);
            }
            else{

                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setTitle("Error!");
                builder1.setMessage("Passwords Do Not Match");

                builder1.setPositiveButton("OK", (dialog1, which1) ->dialog1.dismiss());

                AlertDialog dialog1=builder1.create();
                dialog1.show();
            }

        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Method that receives user input for all fields required to create a new user object, creates the user object, and stores it in the fake database.
      * @param firstName the user input first name
     * @param lastName the user input last name
     * @param emailAddress the user input email address
     * @param phoneNumber the user input phone number
     * @param dateOfBirth the user input date of birth
     * @param userName the user input username
     * @param password the user input password
     */
    public void sendDialogDataToAccountCreation(String firstName, String lastName, String emailAddress, String phoneNumber, String dateOfBirth, String userName, String password){
        Random rand = new Random();
        User newUser = new User(rand.nextInt(25), firstName, lastName, userName, password, emailAddress, phoneNumber, dateOfBirth);
        fakeUserDataBase.add(newUser);
    }


}