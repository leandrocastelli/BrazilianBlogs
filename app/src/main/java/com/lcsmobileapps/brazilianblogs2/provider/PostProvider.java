package com.lcsmobileapps.brazilianblogs2.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.lcsmobileapps.brazilianblogs2.R;

/**
 * Created by Leandro on 12/10/2015.
 */
public class PostProvider extends ContentProvider {

    private DBHelper dbHelper;
    private static UriMatcher uriMatcher;


    public static final String AUTHORITY = "com.lcsmobileapps.brazilianblogs2.provider";
    private static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.com.lcsmobileapps.providers." + PostContract.TABLE_NAME;
    private static final String CONTENT_TYPE_ITEM = "vnd.android.cursor.item/vnd.com.lcsmobileapps.providers." + PostContract.TABLE_NAME;
    protected static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PostContract.TABLE_NAME);


    private static final int ALL_POSTS = 0;
    private static final int ITEM_POST = 1;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, PostContract.TABLE_NAME, ALL_POSTS);
        uriMatcher.addURI(AUTHORITY, PostContract.TABLE_NAME + "/#", ITEM_POST);


    }
    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor  cursor = database.query(PostContract.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        int res = uriMatcher.match(uri);
        switch (res) {
            case ALL_POSTS:
                return CONTENT_TYPE;
            case ITEM_POST:
                return CONTENT_TYPE_ITEM;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {



        SQLiteDatabase database = dbHelper.getWritableDatabase();
        long value = database.insertWithOnConflict(PostContract.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);

        if (value != -1) {
            return Uri.withAppendedPath(uri, String.valueOf(value));
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        SQLiteDatabase dataBase = dbHelper.getWritableDatabase();

        return dataBase.delete(PostContract.TABLE_NAME, selection, selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        return database.update(PostContract.TABLE_NAME, values, selection, selectionArgs);
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        int total = 0;
        for (ContentValues value : values) {
            Uri result = insert(uri, value);
            if (result != null) {
                total++;
            }
        }
        return total;
    }

    protected final class DBHelper extends SQLiteOpenHelper {
        String CREATE_TABLE =
                "CREATE TABLE IF NOT EXISTS " + PostContract.TABLE_NAME + " ( "
                        + PostContract._ID + " INTEGER PRIMARY KEY , "
                        + PostContract.NAME+ " TEXT NOT NULL, "
                        + PostContract.DESCRIPTION + " TEXT NOT NULL, "
                        + PostContract.LINK + " TEXT NOT NULL UNIQUE, "
                        + PostContract.IMAGE + " TEXT NOT NULL UNIQUE, "
                        + PostContract.BLOG + " TEXT NOT NULL )";


        public DBHelper(Context context) {
            super(context, PostContract.DB, null, 1);

        }



        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL(CREATE_TABLE);
        }


        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
