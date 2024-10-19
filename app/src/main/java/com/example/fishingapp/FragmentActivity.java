package com.example.fishingapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
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