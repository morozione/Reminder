package com.example.morozione.testreminder.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.morozione.testreminder.fragment.CurrentTaskFragment;
import com.example.morozione.testreminder.fragment.DoneTaskFragment;

public class TabAdapter extends FragmentStatePagerAdapter {
    public static final int CURRENT_TASK_FRAGMENT = 0;
    public static final int DONE_TASK_FRAGMENT = 1;

    private int numberOfTabs;

    private CurrentTaskFragment currentTaskFragment;
    private DoneTaskFragment doneTaskFragment;

    public TabAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;

        currentTaskFragment = new CurrentTaskFragment();
        doneTaskFragment = new DoneTaskFragment();
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == CURRENT_TASK_FRAGMENT) {
            return currentTaskFragment;
        } else if (position == DONE_TASK_FRAGMENT) {
            return doneTaskFragment;
        }

        return null;
    }

}
