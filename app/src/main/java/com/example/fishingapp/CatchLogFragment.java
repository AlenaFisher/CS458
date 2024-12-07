package com.example.fishingapp;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.fishingapp.Activity.TomorrowActivity;

import java.util.LinkedList;

public class CatchLogFragment extends Fragment {

    ListView simpleList;
    CustomCatchLogAdapter adapter;
    LinkedList<Catch> list = new LinkedList<>();
    EditText title;
    EditText length;
    EditText weight;
    EditText baitUsed;
    EditText location;
    Button createLogBtn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catch_log, container, false);


        Catch catch1 = new Catch("Small mouth bass", "the Lake", "24", "spinner","8 inches");
        list.add(catch1);

        createLogBtn = view.findViewById(R.id.createLogBtn);
        simpleList = view.findViewById(R.id.listView);
        adapter = new CustomCatchLogAdapter(list, getActivity().getApplicationContext());
        simpleList.setAdapter(adapter);
        createNewLog(this);

        return view;
    }

    private void createNewLog(CatchLogFragment fragment){
        createLogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getContext());
                builder.setTitle("Log Catch");
                final View customLayout = getLayoutInflater().inflate(R.layout.create_log_dialog, null);
                builder.setView(customLayout);

                //connects "create task" button to the following instructions
                builder.setPositiveButton("Create task", (dialog, which) -> {

                    //set instance variables to the components
                    title = customLayout.findViewById(R.id.title);
                    length = customLayout.findViewById(R.id.length);
                    weight = customLayout.findViewById(R.id.weight);
                    baitUsed = customLayout.findViewById(R.id.baitUsed);
                    location = customLayout.findViewById(R.id.location);
                    Catch fishCatch = new Catch(title.getText().toString(), location.getText().toString(), weight.getText().toString(), baitUsed.getText().toString(), length.getText().toString());

                    //add to list
                    list.add(fishCatch);
                    adapter.notifyDataSetChanged();


                });
                builder.setNegativeButton("Cancel", (dialog, which) -> {
                    dialog.dismiss();
                });

                //show alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });


    }
}