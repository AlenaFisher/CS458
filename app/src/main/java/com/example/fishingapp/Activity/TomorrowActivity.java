package com.example.fishingapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fishingapp.Adapters.TomorrowAdapter;
import com.example.fishingapp.Domains.TomorrowDomain;
import com.example.fishingapp.FragmentActivity;
import com.example.fishingapp.MainActivity;
import com.example.fishingapp.R;

import java.util.ArrayList;

public class TomorrowActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterTomorrow;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tomorrow);

        initRecyclerView();
        setVariable();
    }

    private void initRecyclerView() {
        ArrayList<TomorrowDomain> items = new ArrayList<>();

        items.add(new TomorrowDomain("Thur", "sunny", "Sunny", "84", "45"));
        items.add(new TomorrowDomain("Fri", "sunny", "Sunny", "65", "41"));
        items.add(new TomorrowDomain("Sat", "sunny", "Sunny", "74", "50"));
        items.add(new TomorrowDomain("Sun", "sunny", "Sunny", "77", "55"));
        items.add(new TomorrowDomain("Mon", "sunny", "Sunny", "82", "61"));
        items.add(new TomorrowDomain("Tue", "sunny", "Sunny", "78", "44"));
        items.add(new TomorrowDomain("Wed", "sunny", "Sunny", "60", "36"));

        recyclerView = findViewById(R.id.view2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapterTomorrow = new TomorrowAdapter(items);
        recyclerView.setAdapter(adapterTomorrow);
    }

    private void setVariable() {
        ConstraintLayout backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(view -> finish());

        //startActivity(new Intent(TomorrowActivity.this, FragmentActivity.class))
    }
}

