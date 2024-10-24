package com.example.fishingapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
        }
        mapFragment.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        LatLng specificLocation = new LatLng(35.36447, -103.49461); // Initial map view after logging in

        // Creating locations for large mouth bass
        LatLng largeBass1 = new LatLng(35.357524, -103.494967); // Large mouth bass
        LatLng largeBass2 = new LatLng(35.351058, -103.493747); // Large mouth bass
        LatLng largeBass3 = new LatLng(35.348326, -103.519584); // Large mouth bass
        LatLng largeBass4 = new LatLng(35.35209604, -103.46923828); // Large mouth bass

        // Creating locations for small mouth bass
        LatLng smallBass1 = new LatLng(35.351536, -103.51730347); // Small mouth bass
        LatLng smallBass2 = new LatLng(35.34901578, -103.45413208); // Small mouth bass

        // Creating locations for walleye
        LatLng walleye1 = new LatLng(35.358016, -103.504257); // Walleye
        LatLng walleye2 = new LatLng(35.35140278, -103.51763056); // Walleye

        // Creating location for crappie
        LatLng crappie = new LatLng(35.34621544, -103.53258133); // White crappie

        // Creating location for catfish
        LatLng catfish = new LatLng(35.34313496, -103.61240387); // Catfish

        // Creating location for blue gill
        LatLng blueGill = new LatLng(35.364720, -103.494729); // Blue Gill

        // Creating location for white bass
        LatLng whiteBass = new LatLng(35.342416, -103.531203); // White bass

        // Creating location for carp
        LatLng carp = new LatLng(35.343677, -103.490881); // Carp

        // Adding location markers for large mouth bass
        googleMap.addMarker(new MarkerOptions().position(largeBass1).title("Large Mouth Bass"));
        googleMap.addMarker(new MarkerOptions().position(largeBass2).title("Large Mouth Bass"));
        googleMap.addMarker(new MarkerOptions().position(largeBass3).title("Large Mouth Bass"));
        googleMap.addMarker(new MarkerOptions().position(largeBass4).title("Large Mouth Bass"));

        // Adding location markers for large mouth bass
        googleMap.addMarker(new MarkerOptions().position(smallBass1).title("Small Mouth Bass"));
        googleMap.addMarker(new MarkerOptions().position(smallBass2).title("Small Mouth Bass"));

        // Adding location markers for small mouth bass
        googleMap.addMarker(new MarkerOptions().position(walleye1).title("Walleye"));
        googleMap.addMarker(new MarkerOptions().position(walleye2).title("Walleye"));

        // Adding location markers for walleye
        googleMap.addMarker(new MarkerOptions().position(crappie).title("Crappie"));

        // Adding location markers for catfish
        googleMap.addMarker(new MarkerOptions().position(catfish).title("Catfish"));

        // Adding location markers for blue gill
        googleMap.addMarker(new MarkerOptions().position(blueGill).title("Blue Gill"));

        // Adding location markers for white bass
        googleMap.addMarker(new MarkerOptions().position(whiteBass).title("White Bass"));

        // Adding location markers for carp
        googleMap.addMarker(new MarkerOptions().position(carp).title("Carp"));

        // Initial view of the map after logging in
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(specificLocation, 15));

        // On-click listener for the markers to display the fish information
        googleMap.setOnMarkerClickListener(marker -> {
            LatLng markerLocation = marker.getPosition();
            showFishInfoDialog(marker.getTitle(), markerLocation);
            return true;
        });
    }

    private void showFishInfoDialog(String fishName, LatLng location) {
        /**
         * This method creates a card with the fish information to display to the user
         * when they click on a specific map marker
         *
         * @param   String fishName   The name of the fish
         * @param   LatLng location   The marker for the fish
         */

        // Inflating the view for the fish
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.fish_dialog, null);

        // Initializing the ImageView and TextViews
        ImageView fishImageView = dialogView.findViewById(R.id.fish_image);
        TextView fishNameTextView = dialogView.findViewById(R.id.fish_name);
        TextView fishCoordinatesTextView = dialogView.findViewById(R.id.fish_coordinates);
        fishNameTextView.setText(fishName);

        // Initially setting the imageRes value to zero
        @DrawableRes int imageRes = 0;

        // Switch statement to determine which fish image to display
        switch(fishName) {
            case "Small Mouth Bass":
                imageRes = R.drawable.smallmouthbassimage;
                break;

            case "Large Mouth Bass":
                imageRes = R.drawable.largemouthbassimage;
                break;

            case "Walleye":
                imageRes = R.drawable.walleyeimage;
                break;

            case "Crappie":
                imageRes = R.drawable.crappieimage;
                break;

            case "White Bass":
                imageRes = R.drawable.whitebassimage;
                break;

            case "Blue Gill":
                imageRes = R.drawable.bluegillimage;
                break;

            case "Carp":
                imageRes = R.drawable.carpimage;
                break;

            case "Catfish":
                imageRes = R.drawable.catfishimage;
                break;
        }

        // Setting the fish image and coordinates
        fishImageView.setImageResource(imageRes);
        fishCoordinatesTextView.setText(String.format("Coordinates: %.6f, %.6f", location.latitude, location.longitude));

        // Creating and displaying the alert dialog
        new AlertDialog.Builder(getContext()).setView(dialogView)
                .setPositiveButton("OK", null)
                .create()
                .show();
    }
}