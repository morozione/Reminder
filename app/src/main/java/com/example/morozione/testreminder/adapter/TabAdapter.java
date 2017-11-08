package com.example.morozione.testreminder.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.morozione.testreminder.fragment.CurrentTaskFragment;
import com.example.morozione.testreminder.fragment.DoneTaskFragment;

/**
 * Created by morozione on 10/30/17.
 */

public class TabAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;

    public TabAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            Log.d("MyLog", "getItem: " + 0);
            return new CurrentTaskFragment();
        } else if (position == 1) {
            Log.d("MyLog", "getItem: " + 0);
            return new DoneTaskFragment();
        }

        return null;
    }

}
