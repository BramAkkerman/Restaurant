package com.example.brama.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

// This activity shows the items from the given category, retrieved from the api
public class MenuActivity extends AppCompatActivity implements MenuItemRequest.Callback {

    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        MenuItemRequest APIrequest = new MenuItemRequest(this);
        APIrequest.getMenuItems(this);

        Intent intent = getIntent();
        category = intent.getStringExtra("category");
    }

    @Override
    public void gotMenuItemError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    // Save all the items that have the same category as the one clicked on
    @Override
    public void gotMenuItem(ArrayList<MenuItem> menuItems) {
        ArrayList<MenuItem> newMenuItems = new ArrayList<>();
        for (MenuItem item : menuItems) {
            if (item.getCategory().equals(this.category)) {
                newMenuItems.add(item);
            }
        }

        ListView listView = findViewById(R.id.menuList);

        MenuItemAdapter adapter = new MenuItemAdapter(this, R.layout.menu_item,newMenuItems);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new listViewListener());
    }

    // Filling the list with items
    private class listViewListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MenuItem menuItem = (MenuItem) parent.getItemAtPosition(position);
            Intent intent = new Intent(getApplicationContext(), MenuItemActivity.class);
            intent.putExtra("name", menuItem.getName());
            intent.putExtra("description", menuItem.getDescription());
            intent.putExtra("image", menuItem.getImageUrl());
            intent.putExtra("price", String.valueOf(menuItem.getPrice())+"0");
            startActivity(intent);
        }
    }
}
