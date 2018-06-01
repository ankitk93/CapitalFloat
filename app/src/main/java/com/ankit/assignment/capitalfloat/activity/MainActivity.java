package com.ankit.assignment.capitalfloat.activity;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.Window;
import android.view.WindowManager;

import com.ankit.assignment.capitalfloat.R;
import com.ankit.assignment.capitalfloat.adapter.AdapterPager;

public class MainActivity extends AppCompatActivity {

    NavigationView mNavigationView;
    Toolbar mToolbar;
    ActionBar mActionbar;
    TabLayout mTabLayout;
    ViewPager mMainViewPager;
    AdapterPager mPagerAdapter;
    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mActionbar = getSupportActionBar();
        mActionbar.setDisplayHomeAsUpEnabled(true);
        mActionbar.setTitle("CapitalFloat News");

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        window.setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

        //Adding Tabs
        mTabLayout = findViewById(R.id.tabLayout);
        mTabLayout.addTab(mTabLayout.newTab().setText("HeadLines"));
        mTabLayout.addTab(mTabLayout.newTab().setText("News"));

        //view pager
        mMainViewPager = findViewById(R.id.viewPager);
        mPagerAdapter = new AdapterPager(getSupportFragmentManager() , mTabLayout.getTabCount());
        mMainViewPager.setAdapter(mPagerAdapter);

        mMainViewPager.setCurrentItem(0, true);
        mMainViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mMainViewPager.setCurrentItem(tab.getPosition(), true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //drawer setup
        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.option_search).getActionView();

        /*ComponentName componentName = new ComponentName(this, SearchResults.class);
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(componentName));*/

        return true;
    }
}
