/**
 * @author Alena Fisher
 * 10/27/2024
 * Lst updated: 12/07/2024
 * This adapter class is responsible for binding the weather forecast data for the next 7 days
 * to the view in a RecyclerView. IT takes a list of TomorrowDomain obkects, which contain
 * information about the day of the week, weather condition, high and low temperatures,
 * and an image representing the weather condition. Glide is used to dynamically load the weather images.
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
import com.example.fishingapp.Domains.TomorrowDomain;
import com.example.fishingapp.R;
import java.util.ArrayList;

public class TomorrowAdapter extends RecyclerView.Adapter<TomorrowAdapter.ViewHolder> {
    // List of TomorrowDomain objects representing the weather forecast for the next 7 days
    ArrayList<TomorrowDomain> items;
    Context context;

    /**
     * Constructor for TomorrowAdapter.
     * Initializes the adapter with a list of TomorrowDomain objects
     * @param items A list of TomorrowDomain objects representing weather forecasts.
     */
    public TomorrowAdapter(ArrayList<TomorrowDomain> items) {
        this.items = items;
    }

    /**
     * Called when a new ViewHolder is created. It inflates the layout for each item in the RecyclerView.
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     * @return a new ViewHolder instance.
     */
    @NonNull
    @Override
    public TomorrowAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate the layout for individual weather forecast item
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_tomorrow, parent, false);

        // Get context to use for loading resources
        context = parent.getContext();

        // Return a new ViewHolder with the inflated view
        return new ViewHolder(inflate);
    }

    /**
     * Bind the weather data for a specific day to the ViewHolder.
     * Sets the text for the day, status, high and low temperatures, and loads the weather image.
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull TomorrowAdapter.ViewHolder holder, int position) {
        // Set the day, weather status, high and low temperatures for the current position
        holder.dayTxt.setText(items.get(position).getDay());
        holder.statusTxt.setText(items.get(position).getStatus());
        holder.highTxt.setText(items.get(position).getHighTemp());
        holder.lowTxt.setText(items.get(position).getLowTemp());

        // Dynamically load the weather image using Glide
        int drawableResourceId = holder.itemView.getResources().getIdentifier(items.get(position).getImageRes(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(context).load(drawableResourceId).into(holder.image);
    }

    /**
     * Returns the total number of items in the RecyclerView.
     * @return The number of items in the list
     */
    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * ViewHolder class that holds the view for each individual item in the RecyclerView.
     * Each ViewHolder represents one day's weather forecast.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        // TextViews for displaying day, status, high and low temperatures
        TextView dayTxt, statusTxt, lowTxt, highTxt;

        // ImageView for displaying the weather image
        ImageView image;

        /**
         * Constructor for ViewHolder.
         * Initializes the views that will display the weather data for a single day.
         *
         * @param itemView The view representing a single item in the RecyclerView.
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize the views
            dayTxt = itemView.findViewById(R.id.dayTxt);
            statusTxt = itemView.findViewById(R.id.statusTxt);
            lowTxt = itemView.findViewById(R.id.lowTxt);
            highTxt = itemView.findViewById(R.id.highTxt);
            image = itemView.findViewById(R.id.image);
        }
    }
}