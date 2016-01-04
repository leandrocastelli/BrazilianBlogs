package com.lcsmobileapps.brazilianblogs2;

import android.accounts.Account;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.util.LruCache;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.lcsmobileapps.brazilianblogs2.controller.ControllerFragment;
import com.lcsmobileapps.brazilianblogs2.controller.ControllerVolley;
import com.lcsmobileapps.brazilianblogs2.fragments.ContentFragment;
import com.lcsmobileapps.brazilianblogs2.provider.PostProvider;
import com.lcsmobileapps.brazilianblogs2.util.ImageHelper;
import com.lcsmobileapps.brazilianblogs2.util.Utils;

public class BlogsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ContentFragment.FragmentCallback {

    NavigationView navigationView;
    ImageView blogImage;
    Account mAcount;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blogs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        drawer.openDrawer(GravityCompat.START);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initialize();
        ControllerFragment.getInstance().blogChange(R.id.fragment_holder, 0);
    }

    private void initialize() {

        blogImage = (ImageView) findViewById(R.id.backdrop);


        //Initialize Controllers
        ControllerFragment.getInstance().initialize(this);
        ControllerVolley.getInstance().initialize(getApplicationContext());

        ImageHelper.mMemoryCache = (LruCache<String, Bitmap>) ControllerVolley.getInstance().getImageCache();
        mAcount = Utils.createAccount(this);

        getContentResolver().setIsSyncable(mAcount, PostProvider.AUTHORITY, 1);
        getContentResolver().setSyncAutomatically(mAcount, PostProvider.AUTHORITY, true);
        getContentResolver().addPeriodicSync(mAcount, PostProvider.AUTHORITY, Bundle.EMPTY, 15L);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                getContentResolver().requestSync(mAcount, PostProvider.AUTHORITY, Bundle.EMPTY);
            }
        });

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.blogs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        //Change to Settings
        if (item.getItemId() == R.id.nav_settings) {
            ControllerFragment.getInstance().switchToSettings(R.id.fragment_holder);
            return true;
        }

        //Uses the Menu Number to check which is the next Blog
        int id = item .getItemId() - Utils.FIRST_MENU;


        ControllerFragment.getInstance().blogChange(R.id.fragment_holder, id);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        ControllerVolley.getInstance().cancelRequests(Utils.IMAGE_DOWNLOAD_TAG);
    }

    @Override
    public void onFragmentAttached(int imageId) {
        ImageHelper.loadImage(blogImage, imageId, this);
    }
}
