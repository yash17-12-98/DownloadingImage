package com.yashuthar.downloadingimage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    //https://p1.hiclipart.com/preview/677/263/683/picsart-background-png-clipart-thumbnail.jpg

    ImageView imageView;
    public void downloadImage(View view) {
        ImageDownloader task = new ImageDownloader();
        Bitmap myimage;
        try {

            myimage = task.execute("https://images.bewakoof.com/t540/lazy-shinchan-half-sleeve-t-shirt-men-s-printed-t-shirts-211395-1553601991.jpg").get();
            imageView.setImageBitmap(myimage);

        } catch (Exception e) {

            e.printStackTrace();

        }
        Log.i("Interaction", "Button tapped");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap>
    {

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {

                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                Bitmap mybitmap = BitmapFactory.decodeStream(inputStream);
                return mybitmap;

            }
            catch (MalformedURLException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();
            }
            return null;
        }
    }
}