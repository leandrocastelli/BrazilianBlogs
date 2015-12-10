package com.lcsmobileapps.brazilianblogs2.provider;

import android.provider.BaseColumns;

/**
 * Created by Leandro on 12/10/2015.
 */
public class PostContract implements BaseColumns{

    protected static final String DB = "posts.db";
    public static final String TABLE_NAME = "Posts";
    public static final String NAME = "Name";
    public static final String DESCRIPTION = "Description";
    public static final String IMAGE = "Image";
    public static final String LINK = "Link";
    public static final String BLOG = "Blog";


}
