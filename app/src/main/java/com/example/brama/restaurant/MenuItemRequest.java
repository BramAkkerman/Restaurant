package com.example.brama.restaurant;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuItemRequest  implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Context context;
    private Callback activity;
    private String url = "https://resto.mprog.nl/menu";

    public MenuItemRequest(Context context) {
        this.context = context;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        this.activity.gotMenuItemError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        ArrayList<MenuItem> all = new ArrayList<>();
        try {
            JSONArray items = response.getJSONArray("items");
            for (int i = 0;i < items.length();i++) {
                JSONObject item = items.getJSONObject(i);
                MenuItem menu = new MenuItem(item.getString("name"),
                        item.getString("description"),item.getString("image_url"),
                        item.getDouble("price"),item.getString("category"));
                all.add(menu);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        this.activity.gotMenuItem(all);
    }

    public interface Callback {
        void gotMenuItem(ArrayList<MenuItem> menuItems);
        void gotMenuItemError(String message);
    }

    void getMenuItems(Callback activity) {
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
        queue.add(jsonObjectRequest);
        this.activity = activity;
    }
}
