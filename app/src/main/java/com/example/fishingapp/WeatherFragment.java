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
import android.view.View;
import android.view.ViewGroup;

import com.example.fishingapp.Activity.TomorrowActivity;
import com.example.fishingapp.Adapters.HourlyAdapter;
import com.example.fishingapp.Domains.Hourly;

import java.util.ArrayList;

public class WeatherFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapterHourly;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_main, container, false);

        initRecyclerView(view);
        setNextDaysButtonListener(view);
        return view;
    }

    private void initRecyclerView(View view) {
        ArrayList<Hourly> items = new ArrayList<>();

        items.add(new Hourly("2 pm", 83, "sunny"));
        items.add(new Hourly("3 pm", 84, "sunny"));
        items.add(new Hourly("4 pm", 84, "sunny"));
        items.add(new Hourly("5 pm", 82, "sunny"));
        items.add(new Hourly("6 pm", 75, "sunny"));

        recyclerView = view.findViewById(R.id.view1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        adapterHourly = new HourlyAdapter(items);
        recyclerView.setAdapter(adapterHourly);
    }

    private void setNextDaysButtonListener(View view) {
        TextView next7daysBtn = view.findViewById(R.id.nextBtn);
        next7daysBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), TomorrowActivity.class);
            startActivity(intent);
        });
    }
}

