package com.example.brama.restaurant;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

// This class is used to make a bitmap image from a link, found on StackOverflow
class URLToImage extends AsyncTask<String, Void, Bitmap> {
    private ImageView bmImage;
    public URLToImage(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String pathToFile = urls[0];
        Bitmap bitmap = null;
        try {
            InputStream in = new java.net.URL(pathToFile).openStream();
            bitmap = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return bitmap;
    }
    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}