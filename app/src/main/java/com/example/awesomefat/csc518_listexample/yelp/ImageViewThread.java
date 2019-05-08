package com.example.awesomefat.csc518_listexample.yelp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import java.io.InputStream;

public class ImageViewThread extends Thread
{
    private ImageView iv;
    private String url;
    private ImageViewThread myself;
    private Bitmap bimage = null;

    public ImageViewThread(String url, ImageView iv)
    {
        this.iv = iv;
        this.url = url;
        this.myself = this;
    }

    public void run()
    {
        try {
            InputStream in = new java.net.URL(this.url).openStream();
            bimage = BitmapFactory.decodeStream(in);
            new Handler(Looper.getMainLooper()).post(new Runnable () {
                @Override
                public void run () {
                    myself.iv.setImageBitmap(bimage);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
