package com.example.brama.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

// This class is used to fill one list item with a menu item
public class MenuItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        TextView itemName = findViewById(R.id.itemName);
        ImageView itemImage = findViewById(R.id.itemImage);
        TextView itemDescription = findViewById(R.id.itemDescription);
        TextView itemPrice = findViewById(R.id.itemPrice);

        Intent intent = getIntent();

        itemName.setText(intent.getStringExtra("name"));
        itemDescription.setText(intent.getStringExtra("description"));
        itemPrice.setText("€" + intent.getStringExtra("price"));

        URLToImage image = new URLToImage(itemImage);
        image.execute(intent.getStringExtra("image"));
    }
}
