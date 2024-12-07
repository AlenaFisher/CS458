package com.example.fishingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * This is the custom adapter class, an adapter to connect the list view with a linked list. it extends Base Adapter
 * @author Devon Alonzo
 * @date 9/16/2024
 */
public class CustomCatchLogAdapter extends BaseAdapter  {
    Context context;
    LinkedList<Catch> list = new LinkedList<>();
    LayoutInflater inflater;

    /**
     * A constructor that intializes the instance vairables
     * @param list a linked list with object task
     * @param context the context of the page
     */
    public CustomCatchLogAdapter(LinkedList<Catch> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = (LayoutInflater.from(context));
    }


    @Override
    /**
     * Get the number of elements in the list
     */
    public int getCount() {
        return list.size();
    }

    @Override
    /**
     * gets an item of type integer
     */
    public Object getItem(int i) {
        return null;
    }

    @Override
    /**
     * gets item id
     */
    public long getItemId(int i) {
        return 0;
    }

    @Override
    /**
     * gets the view and then inflates the list view
     */
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.listview, null);
        TextView listTxt = (TextView)           view.findViewById(R.id.textView);
        listTxt.setText("Fish: " + list.get(i).getTitle() + "\n" + "length (inches): " + list.get(i).getLength() +
                "\n" + "bait used: " + list.get(i).getWeight() + "\n" + "Location: " + list.get(i).getLocation());
        return view;
    }

}
