package com.example.fishingapp.Activity;
// CALLED BY WEATHERFRAGMENT??

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;

import com.example.fishingapp.Adapters.HourlyAdapter;
import com.example.fishingapp.Domains.Hourly;
import com.example.fishingapp.R;

import java.util.ArrayList;

public class WeatherActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterHourly;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_main);

        initRecyclerView();

        setVariable();
    }

    private void initRecyclerView() {
        ArrayList<Hourly> items = new ArrayList<>();

        items.add(new Hourly("10 pm", 59, "cloudy"));
        items.add(new Hourly("11 pm", 57, "sun"));
        items.add(new Hourly("12 pm", 55, "wind"));
        items.add(new Hourly("1 am", 52, "rainy"));
        items.add(new Hourly("2 am", 51, "storm"));

        recyclerView = findViewById(R.id.view1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        adapterHourly = new HourlyAdapter(items);
        recyclerView.setAdapter(adapterHourly);

    }

    private void setVariable() {
        TextView next7daysBtn = findViewById(R.id.nextBtn);
        next7daysBtn.setOnClickListener(view -> startActivity(new Intent(WeatherActivity.this, TomorrowActivity.class)));
    }
}
