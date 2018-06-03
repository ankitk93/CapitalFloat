package com.ankit.assignment.capitalfloat.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ankit.assignment.capitalfloat.fragment.FragmentHeadlines;
import com.ankit.assignment.capitalfloat.fragment.FragmentNews;

public class AdapterPager extends FragmentStatePagerAdapter {

    int numberOfTabs =2;
    private Bundle mBundle;

    public AdapterPager(FragmentManager manager , Bundle bundle){
        super(manager);
        this.mBundle = bundle;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0 :
                FragmentHeadlines fragmentHeadlines = new FragmentHeadlines();
                fragmentHeadlines.setArguments(this.mBundle);
                return fragmentHeadlines;

            case 1 : return new FragmentNews();

            default: return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
