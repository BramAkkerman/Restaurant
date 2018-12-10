package com.example.brama.restaurant;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Context context;
    private Callback activity;
    private String url = "https://resto.mprog.nl/categories";

    @Override
    public void onErrorResponse(VolleyError error) {
        this.activity.gotCategoriesError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        ArrayList<String> bullshit = new ArrayList<>();
        try {
            JSONArray array = response.getJSONArray("categories");
            for (int i = 0;i < array.length();i++) {
                bullshit.add(array.getString(i));
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        this.activity.gotCategories(bullshit);
    }

    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    public CategoriesRequest(Context context) {
        this.context = context;
    }

    void getCategories(Callback activity) {
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
        queue.add(jsonObjectRequest);
        this.activity = activity;
    }
}
