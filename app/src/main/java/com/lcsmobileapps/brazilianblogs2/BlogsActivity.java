package com.lcsmobileapps.brazilianblogs2;

import android.accounts.Account;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.util.LruCache;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lcsmobileapps.brazilianblogs2.controller.Controller;
import com.lcsmobileapps.brazilianblogs2.fragments.ContentFragment;
import com.lcsmobileapps.brazilianblogs2.provider.PostProvider;
import com.lcsmobileapps.brazilianblogs2.util.ImageHelper;
import com.lcsmobileapps.brazilianblogs2.util.Utils;

public class BlogsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ContentFragment.FragmentCallback {

    NavigationView navigationView;
    ImageView blogImage;
    Account mAcount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blogs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        drawer.openDrawer(GravityCompat.START);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initialize();
        Controller.getInstance().blogChange(R.id.fragment_holder, 0);
    }

    private void initialize() {
        //Config cache and Images
        final int maxMemory = (int)(Runtime.getRuntime().maxMemory() /1024);

        int cacheSize;
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            cacheSize = maxMemory / 8;
        }
        else {
            cacheSize = maxMemory / 4;
        }
        blogImage = (ImageView) findViewById(R.id.backdrop);
        ImageHelper.mMemoryCache = new LruCache<String, Bitmap>(cacheSize);

        //Initialize Controller
        Controller.getInstance().initialize(this);
        mAcount = Utils.createAccount(this);

        getContentResolver().addPeriodicSync(mAcount, PostProvider.AUTHORITY, Bundle.EMPTY, 60L*60L*24L);

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
            Controller.getInstance().switchToSettings(R.id.fragment_holder);
            return true;
        }

        //Uses the Menu Number to check which is the next Blog
        int id = item .getItemId() - Utils.FIRST_MENU;


        Controller.getInstance().blogChange(R.id.fragment_holder, id);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentAttached(int imageId) {
        ImageHelper.loadImage(blogImage, imageId, this);
    }
}
