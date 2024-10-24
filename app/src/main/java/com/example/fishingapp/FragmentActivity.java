package com.example.fishingapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.fishingapp.Activity.WeatherActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.fragment_activity);

        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigationView);

        bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            if(item.getItemId() == R.id.map) {
                selectedFragment = new MapFragment();
            } else if(item.getItemId() == R.id.weather) {
//                Intent intent = new Intent(FragmentActivity.this, WeatherActivity.class);
//                return true;
                selectedFragment = new WeatherFragment();
            } else if(item.getItemId() == R.id.fishInformation) {
                selectedFragment = new FishInfoFragment();
            } else if(item.getItemId() == R.id.profileSettings) {
                selectedFragment = new ProfileFragment();
            } else if(item.getItemId() == R.id.fishLog) {
                selectedFragment = new LogCatchesFragment();
            } else {
                return false;
            }


            return loadFragment(selectedFragment);
        });

        loadFragment(new MapFragment());

    }


    private boolean loadFragment(Fragment fragment) {
        if(fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.f1Fragment, fragment);
            transaction.commit();
            return true;
        }

        return false;
    }
}