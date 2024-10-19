package com.example.fishingapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.annotation.Nullable;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.maps);
        if(mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            getChildFragmentManager().beginTransaction().replace(R.id.maps, mapFragment).commit();
            //mapFragment.getMapAsync(this);
        }
        mapFragment.getMapAsync(this);

//        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.maps), (v, insets) -> {
//          Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//          v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//          return insets;
//        });

        return view;
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        LatLng specificLocation = new LatLng(35.36447, -103.49461);

        googleMap.addMarker(new MarkerOptions().position(specificLocation).title("Ute Lake Marina"));

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(specificLocation, 15));
    }
}