package com.ankit.assignment.capitalfloat.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.ankit.assignment.capitalfloat.R;
import com.bumptech.glide.Glide;

public class DetailsActivity extends AppCompatActivity {

    Bundle mBundle;
    String mImageUrl , mNewsDesc , mNewsAuthor , mNewsTitle;
    TextView mNewsDescTextView , mNewsAuthorTextView , mNewsTitleTextView;
    ImageView mNewsImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        setTitle("Details");
        getDetails();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        mNewsDescTextView = findViewById(R.id.tv_news_desc);
        mNewsTitleTextView = findViewById(R.id.tv_news_title);
        mNewsAuthorTextView = findViewById(R.id.tv_news_author);
        mNewsImageView = findViewById(R.id.iv_news_image);

        Glide.with(this)
                .load(mImageUrl)
                .into(mNewsImageView);

        mNewsTitleTextView.setText(mNewsTitle);
        mNewsDescTextView.setText(mNewsDesc);
        mNewsAuthorTextView.setText("- "+mNewsAuthor);
    }
    public void getDetails(){
        mBundle = getIntent().getExtras();
        if (mBundle != null){
            mImageUrl = mBundle.getString("imageUrl");
            mNewsDesc = mBundle.getString("newsDesc");
            mNewsAuthor = mBundle.getString("newsAuthor");
            mNewsTitle = mBundle.getString("newsTitle");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
