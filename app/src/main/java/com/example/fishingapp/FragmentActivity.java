/**
 * @author  Alena Fisher
 * 10/27/2024
 * Last updated: 12/07/2024
 * This activity hosts multiple fragments. It manages navigation between fragments using a BottomNavigationView.
 * Each item in the bottom navigation corresponds to a different fragment that is loaded dyanmically.
 */

package com.example.fishingapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FragmentActivity extends AppCompatActivity {
    /**
     * Called when the activity is created. This method sets the content view, initializes the bottom navigation,
     * and sets an item selected listener to handle fragment changes based on user selection.
     * @param savedInstanceState A Bundle object containing the activity's previously saved state (if any)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity);

        // Initialize the BottomNavigationView
        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigationView);

        // Set up a listener for when a user selects an item from the BottomNavigationView
        bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            // Check which menu item was selected and assign the corresponding fragment
            if(item.getItemId() == R.id.map) {
                selectedFragment = new MapFragment(); // Map fragment
            } else if(item.getItemId() == R.id.weather) {
                selectedFragment = new WeatherFragment(); // Weather fragment
            } else if(item.getItemId() == R.id.fishInformation) {
                selectedFragment = new FishInfoFragment(); // Fish Information fragment
            } else if(item.getItemId() == R.id.profileSettings) {
                selectedFragment = new ProfileFragment(); // Profile Settings fragment
            } else if(item.getItemId() == R.id.fishLog) {
                selectedFragment = new CatchLogFragment(); // Fish Log fragment
            } else {
                return false; // Return false if no valid item was selected
            }

            // Load the selected fragment
            return loadFragment(selectedFragment);
        });

        // Load the default fragment (MapFragment) when the activity starts
        loadFragment(new MapFragment());
    }

    /**
     * Loads the selected fragment into the activity by replacing the current fragment.
     * The transaction is committed to display the fragment.
     * @param fragment The fragment to be loaded into the activity
     * @return True if the fragment was successfully loaded, otherwise false.
     */
    private boolean loadFragment(Fragment fragment) {
        if(fragment != null) {
            // Begin a fragment transaction to replace the existing fragment
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.f1Fragment, fragment); // Replace the fragment in the container
            transaction.commit(); // Commit the transaction
            return true;
        }

        return false; // Return false if the fragment was not loaded
    }
}