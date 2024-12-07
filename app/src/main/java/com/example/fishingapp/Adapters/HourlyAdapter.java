/**
 * Alena Fisher
 * 10/27/2024
 * Last updated: 12/07/2024
 * This adapter class is responsible for binding hourly weather data to the view in a RecyclerView.
 * It takes a list of Hourly objects and inflates the view for each item, displaying the hour, temperature,
 * and weather condition image. Glide is used to load weather condition images dynamically.
 */
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
    // List of hourly weather data
    ArrayList<Hourly> items;

    // Context to access resources like images
    Context context;

    /**
     * Constructor to initialize the adapter with a list of hourly weather data.
     * @param items List of hourly weather data to be displayed in the RecyclerView.
     */
    public HourlyAdapter(ArrayList<Hourly> items) {
        this.items = items;
    }

    /**
     * Called when a new ViewHolder is created. It inflates the layout for each item in the RecyclerView.
     * This method creates and returns a new ViewHolder instance that will display the data.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder instance with the inflated layout.
     */
    @NonNull
    @Override
    public HourlyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflating the layout for each item in the RecyclerView
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_hourly, parent, false);
        context = parent.getContext(); // Storing context for image loading
        return new ViewHolder(inflate);
    }

    /**
     * Binds the data for a specific hour's weather forecast to the ViewHolder.
     * Sets the hour, temperature, and weather image.
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull HourlyAdapter.ViewHolder holder, int position) {
        // Binding the data to the view
        holder.hourTxt.setText(items.get(position).getHour());
        holder.tempTxt.setText(items.get(position).getTemp() + "°");

        // Getting the drawable resource ID for the weather condition image based on the image name
        int drawableResourceId = holder.itemView.getResources().getIdentifier(items.get(position).getImageRes(), "drawable", holder.itemView.getContext().getPackageName());

        // Using Glide to load the image into the ImageView
        Glide.with(context).load(drawableResourceId).into(holder.image);
    }

    /**
     * Returns the total number of items in the RecyclerView.
     * @return The size of the items list.
     */
    @Override
    public int getItemCount() {
        // Returning the number of items in teh data list
        return items.size();
    }

    /**
     * ViewHolder class that holds the view for each individual item in the RecyclerView.
     * It represents one hourly forecast with hour, temperature, and weather image.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        // UI components to display hour, temperature, and weather image
        TextView hourTxt, tempTxt;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initializing the views
            hourTxt = itemView.findViewById(R.id.hourTxt);
            tempTxt = itemView.findViewById(R.id.tempTxt);
            image = itemView.findViewById(R.id.image);
        }
    }
}