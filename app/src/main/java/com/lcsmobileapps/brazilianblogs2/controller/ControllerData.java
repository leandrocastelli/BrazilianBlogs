package com.lcsmobileapps.brazilianblogs2.controller;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.lcsmobileapps.brazilianblogs2.model.Post;
import com.lcsmobileapps.brazilianblogs2.provider.PostContract;
import com.lcsmobileapps.brazilianblogs2.provider.PostProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leandro.silverio on 28/12/2015.
 */
public class ControllerData {

    private static  ControllerData instance;

    public static ControllerData getInstance() {
        if (instance == null) {
            instance = new ControllerData();
        }
        return instance;
    }
    private ControllerData() {

    }

    public int insertPosts (List<Post> list, Context ctx) {

        int result = 0;
        ContentResolver contentResolver = ctx.getContentResolver();
        List<ContentValues> values = new ArrayList<ContentValues>();
        for(Post post : list) {
            values.add(post.toContentValues());
        }

        result = contentResolver.bulkInsert(PostProvider.CONTENT_URI, values.toArray(new ContentValues[values.size()]));

        return result;
    }

    public List<Post> getPosts(String blogName , Context ctx) {
        List<Post> list = new ArrayList<Post>();
        ContentResolver contentResolver = ctx.getContentResolver();
        Cursor result = contentResolver.query(PostProvider.CONTENT_URI,
                new String[]{PostContract._ID, PostContract.TITLE,PostContract.DESCRIPTION,
                        PostContract.IMAGE, PostContract.LINK, PostContract.BLOG},
                PostContract.BLOG + "=? Order By " + PostContract._ID + " desc" ,
                new String[]{blogName}, null);
        if (result == null) {
            return null;
        }
        while (result.moveToNext()) {
            list.add(new Post(result.getString(0),result.getString(1),result.getString(2),
                    result.getString(3),result.getString(4),result.getString(5)));
        }
        return list;

    }

}
