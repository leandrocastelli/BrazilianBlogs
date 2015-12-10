package com.lcsmobileapps.brazilianblogs2.controller;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import com.lcsmobileapps.brazilianblogs2.fragments.ContentFragment;


/**
 * Created by Leandro on 11/9/2015.
 */
public class Controller {
    private static Controller ourInstance = new Controller();

    public static Controller getInstance() {
        return ourInstance;
    }

    private Controller() {
    }

    private FragmentManager fragmentManager;

    public void initialize(Activity activity) {
        fragmentManager = activity.getFragmentManager();

    }

    public void blogChange(int layout, int index) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(layout, ContentFragment.newInstance(index));
        transaction.commit();
    }

    public void switchToSettings(int layout) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
    //    transaction.replace(layout, SettingsFragment.newInstance());
        transaction.addToBackStack("Settings");
        transaction.commit();
    }

}
