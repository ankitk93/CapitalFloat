package com.ankit.assignment.capitalfloat.fragment;

import android.content.Context;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.ankit.assignment.capitalfloat.R;
import com.ankit.assignment.capitalfloat.adapter.NewsAdapter;
import com.ankit.assignment.capitalfloat.api.ApiClient;
import com.ankit.assignment.capitalfloat.api.ApiInterface;
import com.ankit.assignment.capitalfloat.model.AllNews;
import com.ankit.assignment.capitalfloat.model.SingleArticle;
import com.ankit.assignment.capitalfloat.model.Source;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FragmentHeadlines extends Fragment {

    private View mView;
    RecyclerView mHeadlinesRecyclerView;
    ProgressBar mProgressbar;
    List<SingleArticle> mArticleList;
    Context mContext;
    RelativeLayout mHeadlinesFragment;
    NewsAdapter mNewsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_headlines , container , false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mContext = getActivity();

        mProgressbar = mView.findViewById(R.id.progressBar);
        mHeadlinesFragment = mView.findViewById(R.id.fragment_headlines);
        mHeadlinesRecyclerView = mView.findViewById(R.id.headlines_recycler_view);

        mHeadlinesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mProgressbar = new ProgressBar(getActivity());
        mProgressbar.setVisibility(View.VISIBLE);
        getHeadlines();
    }

    public void getHeadlines(){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<AllNews> newsCall = apiInterface.getTopHeadlines();

        newsCall.enqueue(new Callback<AllNews>() {
            @Override
            public void onResponse(Call<AllNews> call, Response<AllNews> response) {
                AllNews allNews = response.body();
                mArticleList = allNews.getNewsArticleList();
                mNewsAdapter = new NewsAdapter(mContext , mArticleList);
                mHeadlinesRecyclerView.setAdapter(mNewsAdapter);
                mNewsAdapter.notifyDataSetChanged();
                Log.i("news" , String.valueOf(response.body()));
            }

            @Override
            public void onFailure(Call<AllNews> call, Throwable t) {
                Log.e("error" , t.toString());
            }
        });
    }
}
