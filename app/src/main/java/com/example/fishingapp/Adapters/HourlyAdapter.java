package com.example.fishingapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fishingapp.Domains.Hourly;
import com.example.fishingapp.R;

import java.util.ArrayList;

public class HourlyAdapter extends RecyclerView.Adapter<HourlyAdapter.ViewHolder> {
    ArrayList<Hourly> items;
    Context context;

    public HourlyAdapter(ArrayList<Hourly> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public HourlyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_hourly, parent, false);
        context = parent.getContext();
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull HourlyAdapter.ViewHolder holder, int position) {
        holder.hourTxt.setText(items.get(position).getHour());
        holder.tempTxt.setText(items.get(position).getTemp() + "°");

        int drawableResourceId = holder.itemView.getResources().getIdentifier(items.get(position).getImageRes(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(context).load(drawableResourceId).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView hourTxt, tempTxt;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hourTxt = itemView.findViewById(R.id.hourTxt);
            tempTxt = itemView.findViewById(R.id.tempTxt);
            image = itemView.findViewById(R.id.image);
        }
    }
}