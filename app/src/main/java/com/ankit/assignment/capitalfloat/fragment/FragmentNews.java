package com.ankit.assignment.capitalfloat.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ankit.assignment.capitalfloat.R;
import com.ankit.assignment.capitalfloat.adapter.NewsAdapter;
import com.ankit.assignment.capitalfloat.api.ApiClient;
import com.ankit.assignment.capitalfloat.api.ApiInterface;
import com.ankit.assignment.capitalfloat.model.AllNews;
import com.ankit.assignment.capitalfloat.model.SingleArticle;

import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentNews extends Fragment {

    private View mView;
    RecyclerView mNewsRecyclerView;
    NewsAdapter mNewsAdapter;
    List<SingleArticle> mSingleArticleList;
    SpotsDialog mProgressDialog;
    Context mContext;

    //change the query string here as the api do not provide news without query string in
    String queryString = "india";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_news , container , false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mContext = getActivity();

        mNewsRecyclerView = mView.findViewById(R.id.news_recycler_view);
        mNewsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mProgressDialog = new SpotsDialog(getContext());
        mProgressDialog.show();

        new getSourcesFromDb().execute();
    }

    public void getNews(){
        ApiInterface newsInterface = ApiClient.getClient().create(ApiInterface.class);
        retrofit2.Call<AllNews> newsCall = newsInterface.getEverything(queryString ,"popularity");
        newsCall.enqueue(new Callback<AllNews>() {
            @Override
            public void onResponse(retrofit2.Call<AllNews> call, Response<AllNews> response) {
                AllNews news =response.body();
                mSingleArticleList = news.getNewsArticleList();
                mNewsAdapter = new NewsAdapter(getContext() , mSingleArticleList);
                mNewsRecyclerView.setAdapter(mNewsAdapter);
                //mNewsAdapter.notifyDataSetChanged();
                mProgressDialog.hide();
            }

            @Override
            public void onFailure(retrofit2.Call<AllNews> call, Throwable t) {
                Log.e("news error" ,t.toString());
            }
        });
    }

    public class getSourcesFromDb extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            getNews();
        }
    }
}
