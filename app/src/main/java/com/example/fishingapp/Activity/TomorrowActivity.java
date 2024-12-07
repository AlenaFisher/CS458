/**
 * @author  Alena Fisher
 * 10/27/2024
 * Last Updated: 12/07/2024
 * This activity is responsible for displaying the weather forecast for the upcoming week.
 * It initializes a RecyclerView that shows the weather data for each day, including the day of the week,
 * weather condition (sunny, cloudy, snowy, etc.), and high and low temperatures. It also contains a back button
 * that navigates the user back to the main weather display screen.
 */
package com.example.fishingapp.Activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fishingapp.Adapters.TomorrowAdapter;
import com.example.fishingapp.Domains.TomorrowDomain;
import com.example.fishingapp.R;
import java.util.ArrayList;

public class TomorrowActivity extends AppCompatActivity {
    // Adapter for the recycler view
    private RecyclerView.Adapter adapterTomorrow;
    // Recycler view to display weather data
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Setting the layout
        setContentView(R.layout.activity_tomorrow);

        // Initializing the RecyclerView
        initRecyclerView();
        // Setting up the back button
        setVariable();
    }

    /**
     * This method is responsible for creating a list to hold future weather data
     */
    private void initRecyclerView() {
        // Creating the list
        ArrayList<TomorrowDomain> items = new ArrayList<>();

        // Adding sample weather data to the list
        items.add(new TomorrowDomain("Wed", "sunny", "Sunny", "50", "29"));
        items.add(new TomorrowDomain("Thur", "cloudy", "Cloudy", "55", "35"));
        items.add(new TomorrowDomain("Fri", "snowy", "Snowy", "56", "31"));
        items.add(new TomorrowDomain("Sat", "snowy", "Snowy", "55", "38"));
        items.add(new TomorrowDomain("Sun", "storm", "Storm", "56", "31"));
        items.add(new TomorrowDomain("Mon", "cloudy_3", "Cloudy", "51", "30"));
        items.add(new TomorrowDomain("Tue", "cloudy_sunny", "Cloudy", "52", "29"));

        // Finding and setting the RecyclerView
        recyclerView = findViewById(R.id.view2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // Creating the adapter with weather data and setting it to the recycler view
        adapterTomorrow = new TomorrowAdapter(items);
        recyclerView.setAdapter(adapterTomorrow);
    }

    /**
     * This method is responsible for listening for the back button being clicked,
     * then taking the user back to the main weather display
     */
    private void setVariable() {
        // Finding the back button element from the layout and setting the onCLick listener
        ConstraintLayout backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(view -> finish()); // Finishing the current activity to go back
    }
}

