package com.lcsmobileapps.brazilianblogs2.controller;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import com.lcsmobileapps.brazilianblogs2.fragments.ContentFragment;
import com.lcsmobileapps.brazilianblogs2.util.Utils;


/**
 * Created by Leandro on 11/9/2015.
 */
public class ControllerFragment {
    private static ControllerFragment ourInstance = new ControllerFragment();

    public static ControllerFragment getInstance() {
        return ourInstance;
    }

    private ControllerFragment() {
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
