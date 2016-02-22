package com.delta.pragyan16;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by rahul on 22/1/16.
 */
public class GridAdapter extends ArrayAdapter<String> {

    public GridAdapter(Context context,String[] objects) {
        super(context, R.layout.grid_layout, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());

        View v = inflater.inflate(R.layout.grid_layout, parent, false);
        TextView textView = (TextView) v. findViewById(R.id.gridText);
        String string = getItem(position);
        textView.setText(string);
        return v;
    }
}
