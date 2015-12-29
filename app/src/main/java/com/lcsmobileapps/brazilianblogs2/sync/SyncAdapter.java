package com.lcsmobileapps.brazilianblogs2.sync;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import android.util.Log;

import com.lcsmobileapps.brazilianblogs2.sync.net.NetDownloader;

/**
 * Created by Leandro on 12/15/2015.
 */
public class SyncAdapter extends AbstractThreadedSyncAdapter {
    Context context;
    ContentResolver mContentResolver;
    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        Log.i("Leandro", "SyncAdapter created");
        mContentResolver = context.getContentResolver();
        this.context = context;
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        Log.i("Leandro", "Im syncing at " + System.currentTimeMillis());
        Thread syncThread = new Thread(new NetDownloader(mContentResolver,context));
        syncThread.start();

    }


}
