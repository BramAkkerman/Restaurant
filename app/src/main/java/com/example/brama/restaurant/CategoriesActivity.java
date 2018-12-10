package com.example.brama.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        CategoriesRequest APIrequest = new CategoriesRequest(this);
        APIrequest.getCategories(this);
    }

    @Override
    public void gotCategories(ArrayList<String> categories) {
        Toast.makeText(this, "Loaded categories", Toast.LENGTH_LONG).show();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categories);
        ListView listView = findViewById(R.id.categoryList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new listViewListener());
    }

    @Override
    public void gotCategoriesError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private class listViewListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
            String category = (String) parent.getItemAtPosition(position);
            intent.putExtra("category", category);
            startActivity(intent);
        }
    }
}
