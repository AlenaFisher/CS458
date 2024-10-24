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
import com.example.fishingapp.Domains.TomorrowDomain;
import com.example.fishingapp.R;

import java.util.ArrayList;

public class TomorrowAdapter extends RecyclerView.Adapter<TomorrowAdapter.ViewHolder> {
    ArrayList<TomorrowDomain> items;
    Context context;

    public TomorrowAdapter(ArrayList<TomorrowDomain> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public TomorrowAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_tomorrow, parent, false);
        context = parent.getContext();
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull TomorrowAdapter.ViewHolder holder, int position) {
        holder.dayTxt.setText(items.get(position).getDay());
        holder.statusTxt.setText(items.get(position).getStatus());
        holder.highTxt.setText(items.get(position).getHighTemp());
        holder.lowTxt.setText(items.get(position).getLowTemp());

        int drawableResourceId = holder.itemView.getResources().getIdentifier(items.get(position).getImageRes(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(context).load(drawableResourceId).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView dayTxt, statusTxt, lowTxt, highTxt;

        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dayTxt = itemView.findViewById(R.id.dayTxt);
            statusTxt = itemView.findViewById(R.id.statusTxt);
            lowTxt = itemView.findViewById(R.id.lowTxt);
            highTxt = itemView.findViewById(R.id.highTxt);
            image = itemView.findViewById(R.id.image);
        }
    }
}