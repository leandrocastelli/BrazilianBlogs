package com.lcsmobileapps.brazilianblogs2.controller;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.widget.ImageView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.lcsmobileapps.brazilianblogs2.sync.net.BitmapMemoryCache;

/**
 * Created by Leandro on 1/1/2016.
 */
public class ControllerVolley {
    private static ControllerVolley ourInstance = new ControllerVolley();
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private ImageLoader.ImageCache imageCache;

    public Cache getDiskCache() {
        return diskCache;
    }

    private Cache diskCache;

    public static ControllerVolley getInstance() {
        return ourInstance;
    }

    public void initialize(Context context) {
        requestQueue = Volley.newRequestQueue(context, 1024*1024*200);
        imageCache = new BitmapMemoryCache(context);
        diskCache = requestQueue.getCache();


        imageLoader = new ImageLoader(requestQueue, imageCache);

    }
    private ControllerVolley() {
    }

    public void cancelRequests(String TAG) {
        if (requestQueue != null) {
            requestQueue.cancelAll(TAG);
        }
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public ImageLoader.ImageCache getImageCache() {
        return imageCache;
    }

}
