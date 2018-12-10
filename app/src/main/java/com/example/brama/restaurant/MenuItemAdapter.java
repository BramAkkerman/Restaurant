package com.example.brama.restaurant;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MenuItemAdapter extends ArrayAdapter<MenuItem> {

    ArrayList<MenuItem> items;

    private static class ViewHolder {
        public TextView textName;
        public TextView textPrice;
        public ImageView image;
    }

    public MenuItemAdapter(Context context, int resource, ArrayList<MenuItem> objects) {
        super(context, resource, objects);
        this.items = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MenuItem item = getItem(position);
        ViewHolder viewHolder;

        final View resultView;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.menu_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.textName = convertView.findViewById(R.id.itemName);
            viewHolder.textPrice = convertView.findViewById(R.id.itemPrice);
            viewHolder.image = convertView.findViewById(R.id.itemImage);

            resultView = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            resultView = convertView;
        }

        viewHolder.textName.setText(item.getName());
        viewHolder.textPrice.setText("€" +String.valueOf(item.getPrice())+"0");

        String path = item.getImageUrl();
        URLToImage image = new URLToImage(viewHolder.image);
        image.execute(path);

        return resultView;
    }
}