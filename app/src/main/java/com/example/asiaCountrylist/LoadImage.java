package com.example.asiaCountrylist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class LoadImage extends AsyncTask<String,Void,Bitmap> {
    ImageView imageView;
    public LoadImage(ImageView ivResult){
        this.imageView=ivResult;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        String urlLink=strings[0];
        Log.i("URL",urlLink);
        Bitmap bitmap=null;
        try {
            InputStream inputStream=new java.net.URL(urlLink).openStream();
            bitmap= BitmapFactory.decodeStream(inputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imageView.setImageBitmap(bitmap);
    }
}
