package com.example.fishingapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * @author Benaiah Gingrich
 * @date 12/03/2024
 */
public class ProfileFragment extends Fragment {
    TextView firstName;
    TextView lastName;
    TextView email;
    TextView phoneNumber;
    TextView birthdate;
    TextView userName;
    TextView password;
    DatabaseHandler userHandler;
    User loggedUser;
    String loggedName;
    EditText firstNameEdit;
    EditText lastNameEdit;
    EditText emailAddressEdit;
    EditText phoneNumberEdit;
    EditText dateOfBirthEdit;
    EditText userNameEdit;
    EditText passwordEdit;
    EditText confirmPasswordEdit;
    Button editButton;
    Button logout;

    /**
     * The Create View method inflates the fragment, opens the database, initializes the text fields,
     * initializes a button to edit profile information and one to logout, then sets the values of the
     * text fields.
     * Method also retrieves the username of the logged in user from saved preferences for reference by
     * other methods.
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inf = inflater.inflate(R.layout.fragment_profile, container, false);
        Context context = getActivity().getApplicationContext();
        SharedPreferences shared = getActivity().getSharedPreferences("MyUsers", context.MODE_PRIVATE);
        loggedName = shared.getString("username", "");
//        loggedName = getArguments().getString("userName");
        userHandler = new DatabaseHandler(getActivity());
        userHandler.openDatabase();
        initDatabaseHelper();
        firstName = inf.findViewById(R.id.StoredFirstName);
        lastName = inf.findViewById(R.id.StoredLastName);
        email = inf.findViewById(R.id.StoredEmail);
        phoneNumber = inf.findViewById(R.id.StoredPhone);
        birthdate = inf.findViewById(R.id.StoredBirthdate);
        userName = inf.findViewById(R.id.StoredUserName);
        password = inf.findViewById(R.id.StoredPassword);
        editButton = inf.findViewById(R.id.EditProfile);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEditProfileButtonClick(view);
            }
        });
        logout = (Button) inf.findViewById(R.id.Logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logoutIntent = new Intent(getContext(), MainActivity.class);
                startActivity(logoutIntent);
            }
        });
        setValues();
        return inf;
    }

    /**
     * The onResume method is used to refresh the profile text fields with updates user profile information
     * when an edit is made.
     */
    @Override
    public void onResume(){
        super.onResume();
        setValues();
    }

    /**
     * Method to check if the database has been opened and, if not, opens the database.
     */
    public void initDatabaseHelper(){
        if(userHandler == null){
            userHandler = new DatabaseHandler(getActivity());
        }
    }

    /**
     * Method to get the correct user information from the database based on the username
     * that was stored in Saved Preferences upon login.
     * Method then sets the values of the text fields to correct user information.
     */
    public void setValues(){
        System.out.println("LOOK FOR THIS LINE");
        System.out.println(loggedName);
        List<User> userList = userHandler.getAllUsers();
        for(int i = 0; i < userList.size(); i++){
            if(userList.get(i).getUserName().equals(loggedName)){
                loggedUser=userList.get(i);
                break;
            }
        }
        firstName.setText(loggedUser.getFirstName());
        lastName.setText(loggedUser.getLastName());
        email.setText(loggedUser.getEmailAddress());
        phoneNumber.setText(loggedUser.getPhoneNumber());
        birthdate.setText(loggedUser.getDateOfBirth());
        userName.setText(loggedUser.getUserName());
        password.setText(loggedUser.getPassword());
    }

    /**
     * Upon click of the Edit Profile button, method spawns a dialog box to receive user input
     * of new profile information.
     * Method requires that all profile information be entered, not just areas that are to be changed.
     * If a field is left blank, an error is displayed and no changes are made.
     * Method also performs checks to confirm no mistakes in passwords were typed, and that, if a new
     * username is requested, the username isn't already taken.
     * If no errors are present, the method passes the new information to the sendDialogDataToUserEdit.
     * @param view
     */
    public void onEditProfileButtonClick(View view){
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        builder.setTitle("Create Account");

        final View account_information_view = getLayoutInflater().inflate(R.layout.account_creation_information_page, null);
        builder.setView(account_information_view);
        firstNameEdit=account_information_view.findViewById(R.id.FirstName);
        lastNameEdit=account_information_view.findViewById(R.id.LastName);
        emailAddressEdit=account_information_view.findViewById(R.id.EmailAddress);
        phoneNumberEdit=account_information_view.findViewById(R.id.PhoneNumber);
        dateOfBirthEdit=account_information_view.findViewById(R.id.DateOfBirth);
        userNameEdit=account_information_view.findViewById(R.id.UserName);
        passwordEdit=account_information_view.findViewById(R.id.Password);
        confirmPasswordEdit=account_information_view.findViewById(R.id.ConfirmPassword);

        builder.setPositiveButton("Update Account", (dialog, which) -> {
            String inputPassword = passwordEdit.getText().toString();
            String inputConfirmPassword = confirmPasswordEdit.getText().toString();
            String inputUserName = userNameEdit.getText().toString();
            boolean userNameTaken = userHandler.searchUsers(inputUserName);
            boolean passwordMatch = inputPassword.equals(inputConfirmPassword);

            if(blankFields()){
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                builder1.setTitle("Error!");
                builder1.setMessage("Please Fill Out All Fields");
                builder1.setPositiveButton("OK", (dialog1, which1) -> dialog1.dismiss()) ;
                AlertDialog dialog1 = builder1.create();
                dialog1.show();
            }
            else if(inputUserName.equals(loggedName) || userNameTaken && passwordMatch){
                sendDialogDataToUserEdit(firstNameEdit.getText().toString(),
                        lastNameEdit.getText().toString(),
                        emailAddressEdit.getText().toString(),
                        phoneNumberEdit.getText().toString(),
                        dateOfBirthEdit.getText().toString(),
                        userNameEdit.getText().toString(),
                        inputPassword);
                setValues();
            }
            else if(!passwordMatch){

                AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                builder1.setTitle("Error!");
                builder1.setMessage("Passwords Do Not Match");

                builder1.setPositiveButton("OK", (dialog1, which1) ->dialog1.dismiss());

                AlertDialog dialog1=builder1.create();
                dialog1.show();
            }
            else if(!userNameTaken){
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                builder1.setTitle("Error!");
                builder1.setMessage("User Name Taken");

                builder1.setPositiveButton("OK", (dialog1, which1) ->dialog1.dismiss());

                AlertDialog dialog1=builder1.create();
                dialog1.show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Method receives the new user given information, edits the user object, then passes the updated
     * user object to the DataHandler to be updated in the database.
     * @param firstName
     * @param lastName
     * @param emailAddress
     * @param phoneNumber
     * @param dateOfBirth
     * @param userName
     * @param password
     */
    public void sendDialogDataToUserEdit(String firstName, String lastName, String emailAddress, String phoneNumber, String dateOfBirth, String userName, String password){
        loggedUser.setFirstName(firstName);
        loggedUser.setLastName(lastName);
        loggedUser.setEmailAddress(emailAddress);
        loggedUser.setPhoneNumber(phoneNumber);
        loggedUser.setDateOfBirth(dateOfBirth);
        loggedUser.setUserName(userName);
        loggedUser.setPassword(password);
        userHandler.updateUser(loggedUser);
    }

    /**
     * Method to check that there are no blank fields when a user fills out the edit form.
     * @return
     */
    public boolean blankFields(){
        boolean empty = false;

        String firstName1 = firstNameEdit.getText().toString();
        String lastName1 = lastNameEdit.getText().toString();
        String emailAddress1 = emailAddressEdit.getText().toString();
        String phoneNumber1 = phoneNumberEdit.getText().toString();
        String DOB1 = dateOfBirthEdit.getText().toString();
        String userName1 = userNameEdit.getText().toString();
        String password1 = passwordEdit.getText().toString();
        String confirmPassword1 = confirmPasswordEdit.getText().toString();

        if(TextUtils.isEmpty(firstName1) ||
                TextUtils.isEmpty(lastName1) ||
                TextUtils.isEmpty(emailAddress1) ||
                TextUtils.isEmpty(phoneNumber1) ||
                TextUtils.isEmpty(DOB1) ||
                TextUtils.isEmpty(userName1) ||
                TextUtils.isEmpty(password1) ||
                TextUtils.isEmpty(confirmPassword1)){
            empty = true;
        }
        return empty;
    }
}
