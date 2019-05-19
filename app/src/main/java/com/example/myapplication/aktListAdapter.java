package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class aktListAdapter extends ArrayAdapter <aktParcel> {


    private static class ViewHolder {
        private TextView itemViewname;
        private TextView itemViewort;
        private TextView itemViewzeit;

    }

    public aktListAdapter (Context context, int textViewResourceId, ArrayList<aktParcel> items ) {
        super(context, textViewResourceId, items);
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.lv_akt, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.itemViewname = (TextView) convertView.findViewById(R.id.txt_name);
            viewHolder.itemViewort = (TextView) convertView.findViewById(R.id.txt_ort);
            viewHolder.itemViewzeit = (TextView) convertView.findViewById(R.id.txt_zeit);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        aktParcel item = getItem(position);

        if(item != null) {
            viewHolder.itemViewname.setText(item.getName());
            viewHolder.itemViewort.setText(item.getOrt());
            viewHolder.itemViewzeit.setText(item.getZeit());
        }

        return convertView;
    }


}
