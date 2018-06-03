package com.ankit.assignment.capitalfloat.activity;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.icu.util.DateInterval;
import android.support.design.widget.FloatingActionButton;
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
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.ankit.assignment.capitalfloat.R;
import com.ankit.assignment.capitalfloat.adapter.AdapterPager;
import com.ankit.assignment.capitalfloat.model.Categories;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    NavigationView mNavigationView;
    Toolbar mToolbar;
    ActionBar mActionbar;
    TabLayout mTabLayout;
    ViewPager mMainViewPager;
    AdapterPager mPagerAdapter;
    DrawerLayout mDrawerLayout;
    List<Categories> mSelectedCategory;
    int FILTER_CATEGORY_PAGE_KEY;
    Bundle mBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBundle = getIntent().getExtras();
        if (mBundle!= null){
            FILTER_CATEGORY_PAGE_KEY = mBundle.getInt("filterKey");
        }

        if (FILTER_CATEGORY_PAGE_KEY == 101){
            mSelectedCategory = (List<Categories>) mBundle.getSerializable("categoryList");
        }

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
        mPagerAdapter = new AdapterPager(getSupportFragmentManager() , mBundle);
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

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.option_category :
                Toast.makeText(this, "Choose Category", Toast.LENGTH_SHORT).show();
                Intent categoryIntent = new Intent(this , CategoriesFilter.class);
                startActivity(categoryIntent);
                break;

            case R.id.option_sort :
                Toast.makeText(this, "SortBy: Popularity", Toast.LENGTH_SHORT).show();
                mMainViewPager.setCurrentItem(2 , true);
        }
        return true;
    }
}
