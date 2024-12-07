/**
 * @author Alena Fisher
 * 10/27/2024
 * Last updated: 12/07/2024
 * This fragment displays weather information for the user, including a list of hourly forecasts.
 * It also provides a button to navigate to a detailed weather view for the next 7 days.
 */

package com.example.fishingapp;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.widget.TextView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.example.fishingapp.Activity.TomorrowActivity;
import com.example.fishingapp.Adapters.HourlyAdapter;
import com.example.fishingapp.Domains.Hourly;
import java.util.ArrayList;

public class WeatherFragment extends Fragment {
    // RecyclerViews for displaying hourly data
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapterHourly;

    /**
     * Called when the fragment's view is created. It sets up the RecyclerView and button listeners.
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return The root view for the fragment.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_main, container, false);

        // Initialize the RecyclerView for displaying hourly weather.
        initRecyclerView(view);

        // Set up the onClickListener to navigate to a detailed weather activity
        setNextDaysButtonListener(view);
        return view;
    }

    /**
     * Initializes the RecyclerView and sets up the hourly weather data to be displayed.
     *
     * @param view The root view of the fragment, used to find the RecyclerView.
     */
    private void initRecyclerView(View view) {
        // Creating a list of hourly weather data
        ArrayList<Hourly> items = new ArrayList<>();

        // Adding sample weather items to the list
        items.add(new Hourly("2 pm", 42, "cloudy"));
        items.add(new Hourly("3 pm", 42, "cloudy"));
        items.add(new Hourly("4 pm", 40, "cloudy"));
        items.add(new Hourly("5 am", 37, "rainy"));
        items.add(new Hourly("6 am", 34, "rainy"));

        // Find the RecyclerView by ID
        recyclerView = view.findViewById(R.id.view1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        // Set the adapter to display the hourly weather data
        adapterHourly = new HourlyAdapter(items);
        recyclerView.setAdapter(adapterHourly);
    }

    /**
     * Sets up a listener for the "Next 7 Days" button to navigate to be detailed weather activity.
     *
     * @param view The root view of the fragment, used to find the button.
     */
    private void setNextDaysButtonListener(View view) {
        // Find the "Next 7 Days" button and set a click listener to navigate to TomorrowActivity
        TextView next7daysBtn = view.findViewById(R.id.nextBtn);
        next7daysBtn.setOnClickListener(v -> {
            // Create an intent to start TomorrowActivity and navigate to the next 7 days forecast
            Intent intent = new Intent(getActivity(), TomorrowActivity.class);
            startActivity(intent); // Start the activity
        });
    }
}

