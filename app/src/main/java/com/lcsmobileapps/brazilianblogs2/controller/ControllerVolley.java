package com.lcsmobileapps.brazilianblogs2.controller;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;
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

    public static ControllerVolley getInstance() {
        return ourInstance;
    }

    public void initialize(Context context) {
        Cache cache = new DiskBasedCache(context.getCacheDir(), 1024*1024*20);
        Network network = new BasicNetwork(new HurlStack());
      //  requestQueue = new RequestQueue(cache, network);
        requestQueue = Volley.newRequestQueue(context, 1024*1024*20);
        int maxMemory = (int)(Runtime.getRuntime().maxMemory() /1024);
        imageCache = new BitmapMemoryCache(maxMemory / 8);
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
