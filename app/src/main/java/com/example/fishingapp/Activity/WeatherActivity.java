/**
 * @author  Alena Fisher
 * 10/27/2024
 * Last updated: 12/07/2024
 * This activity displays the weather forecast for the current day, showing hourly data
 * including temperature and weather conditions. It also provides a button to navigate
 * to a new activity that displays the weather forecast for the next 7 days.
 */

package com.example.fishingapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;
import com.example.fishingapp.Adapters.HourlyAdapter;
import com.example.fishingapp.Domains.Hourly;
import com.example.fishingapp.R;
import java.util.ArrayList;

public class WeatherActivity extends AppCompatActivity {
    // Adapters for the RecyclerView
    private RecyclerView.Adapter adapterHourly;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Setting the layout for this activity
        setContentView(R.layout.weather_main);

        // Initializing the RecyclerView with hourly weather data
        initRecyclerView();

        // Setting up the next button to navigate to the 7-day weather forecast
        setVariable();
    }

    /**
     * This method initializes the RecyclerView by creating a list of hourly weather data
     * and setting up the RecyclerView with this data
     */
    private void initRecyclerView() {
        // Creating a list to hold hourly data
        ArrayList<Hourly> items = new ArrayList<>();

        // Adding sample hourly weather data
        items.add(new Hourly("2 pm", 42, "cloudy"));
        items.add(new Hourly("3 pm", 42, "cloudy"));
        items.add(new Hourly("4 pm", 40, "cloudy"));
        items.add(new Hourly("5 am", 37, "rainy"));
        items.add(new Hourly("6 am", 34, "rainy"));

        // Initializing the RecyclerView
        recyclerView = findViewById(R.id.view1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Setting the adapter to bind the weather data to the RecyclerView
        adapterHourly = new HourlyAdapter(items);
        recyclerView.setAdapter(adapterHourly);
    }

    /**
     * This method sets up the listener for the button that navigates to the TomorrowActivity,
     * which displays the 7-day weather forecast
     */
    private void setVariable() {
        // Finding the "Next 7 Days" button from the layout
        TextView next7daysBtn = findViewById(R.id.nextBtn);

        // Setting an onClickListener to launch the TomorrowActivity when clicked
        next7daysBtn.setOnClickListener(view -> startActivity(new Intent(WeatherActivity.this, TomorrowActivity.class)));
    }
}
