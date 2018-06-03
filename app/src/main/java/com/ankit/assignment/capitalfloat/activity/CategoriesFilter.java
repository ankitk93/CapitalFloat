package com.ankit.assignment.capitalfloat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.ankit.assignment.capitalfloat.R;
import com.ankit.assignment.capitalfloat.api.CategoriesAdapter;
import com.ankit.assignment.capitalfloat.model.Categories;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategoriesFilter extends AppCompatActivity {

    private RecyclerView mCategoriesRecyclerView;
    CategoriesAdapter mCategoriesAdapter;
    List<String> baseCategory = new ArrayList<>();
    List<Categories> mCategoryList;
    int FILTER_CATEGORY_PAGE_KEY = 101;
    Button mDoneButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_categories);
        setTitle("Select Category");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        mCategoriesRecyclerView = findViewById(R.id.categories_recycler_view);
        mCategoriesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        baseCategory = Arrays.asList("entertainment" , "general" , "health" , "science" , "sports" , "technology" , "business");
        mCategoryList = new ArrayList<>();

        for (int i =0;i<baseCategory.size();i++){
            Categories categories = new Categories();
            categories.setCategories(baseCategory.get(i));
            categories.setSelected(false);
            mCategoryList.add(categories);
        }

        mCategoriesAdapter = new CategoriesAdapter(getApplicationContext() , mCategoryList);
        mCategoriesRecyclerView.setAdapter(mCategoriesAdapter);
        mCategoriesAdapter.notifyDataSetChanged();

        mDoneButton = findViewById(R.id.btn_done);
        mDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMainActivity();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                onBackPressed();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void startMainActivity(){
        List<Categories> categories = mCategoriesAdapter.getCategoryList();
        Intent mMainIntent = new Intent(this , MainActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable("categoryList" , (Serializable) categories);
        mBundle.putInt("filterKey" , FILTER_CATEGORY_PAGE_KEY);
        mMainIntent.putExtras(mBundle);
        startActivity(mMainIntent);
    }
}
