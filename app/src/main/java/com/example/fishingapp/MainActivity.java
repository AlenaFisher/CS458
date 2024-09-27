package com.example.fishingapp;

import android.content.DialogInterface;
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

/**
 * @author Benaiah Gingrich
 * @date 09/26/2024
 */

public class MainActivity extends AppCompatActivity {

    /**
     * Creating dummy accounts and a list as a fake database for progress purposes
     */
    User DAlonzo = new User("Devon", "Alonzo", "DAlonzo", "MightyMouse", "MightyMouse@short-king.com", "5758675309", "01/01/2024");
    User AFisher = new User("Alena", "Fisher", "AFisher", "RheaRhipley", "Commander_Coder@in-charge.net", "5758675309", "01/01/2003");
    User BGingrich = new User("Benaiah", "Gingrich", "BGingrich", "Himbo1", "empty_headed@comedycentral.com", "5758675309", "01/01/1804");
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


}