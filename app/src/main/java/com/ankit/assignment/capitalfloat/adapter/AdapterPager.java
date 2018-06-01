package com.ankit.assignment.capitalfloat.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ankit.assignment.capitalfloat.fragment.FragmentHeadlines;
import com.ankit.assignment.capitalfloat.fragment.FragmentNews;

public class AdapterPager extends FragmentStatePagerAdapter {

    int numberOfTabs;

    public AdapterPager(FragmentManager manager , int numberOfTabs){
        super(manager);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0 : return new FragmentHeadlines();
            case 1 : return new FragmentNews();

            default: return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
