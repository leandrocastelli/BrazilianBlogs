package com.lcsmobileapps.brazilianblogs2.sync.net;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lcsmobileapps.brazilianblogs2.model.Post;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leandro on 12/27/2015.
 */
public class NetDownloader implements Runnable{
    ContentResolver mProvider;
    @Override
    public void run() {
        String postJson = "";
        postJson = getJson("http://ckilee.esy.es/post");
        Gson gson = new GsonBuilder().create();
        Type listTypePost = new TypeToken<ArrayList<Post>>() {
        }.getType();
        List<Post> postList = gson.fromJson(postJson, listTypePost);

       // mProvider.applyBatch(p)

    }
    public NetDownloader(ContentResolver provider) {
        this.mProvider = provider;
    }
    private String getJson(String urlString){


        StringBuffer chaine = new StringBuffer("");
        try{
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("User-Agent", "");
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();

            InputStream inputStream = connection.getInputStream();

            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while ((line = rd.readLine()) != null) {
                chaine.append(line);
            }

        } catch (IOException e) {
            // writing exception to log
            e.printStackTrace();
        }

        return chaine.toString();
    }

}
