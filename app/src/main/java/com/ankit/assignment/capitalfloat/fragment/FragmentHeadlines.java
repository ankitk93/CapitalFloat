package com.ankit.assignment.capitalfloat.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ankit.assignment.capitalfloat.R;
import com.ankit.assignment.capitalfloat.activity.MainActivity;
import com.ankit.assignment.capitalfloat.adapter.NewsAdapter;
import com.ankit.assignment.capitalfloat.api.ApiClient;
import com.ankit.assignment.capitalfloat.api.ApiInterface;
import com.ankit.assignment.capitalfloat.model.AllNews;
import com.ankit.assignment.capitalfloat.model.Categories;
import com.ankit.assignment.capitalfloat.model.SingleArticle;
import com.ankit.assignment.capitalfloat.model.Source;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FragmentHeadlines extends Fragment{

    private View mView;
    RecyclerView mHeadlinesRecyclerView;
    List<SingleArticle> mArticleList;
    Context mContext;
    RelativeLayout mHeadlinesFragment;
    NewsAdapter mNewsAdapter;
    List<Categories> CategoryList;
    Bundle mBundle;
    StringBuilder categoryString = new StringBuilder();
    SpotsDialog mDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_headlines , container , false);

        mBundle = getArguments();
        if (mBundle != null){
            CategoryList = (List<Categories>) mBundle.getSerializable("categoryList");
        }
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mContext = getActivity();

        mHeadlinesFragment = mView.findViewById(R.id.fragment_headlines);
        mHeadlinesRecyclerView = mView.findViewById(R.id.headlines_recycler_view);

        mHeadlinesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mDialog = new SpotsDialog(getContext());
        mDialog.show();
        new getSourcesFromDb().execute();
    }


    public void getHeadlines(){

        if (mBundle != null){
            for (int i = 0 ; i<CategoryList.size() ; i++){
                String categoryName = CategoryList.get(i).getCategories();
                Log.e("name" , categoryName);
                Log.e("list" , String.valueOf(CategoryList));
                categoryString.append(CategoryList.get(i).getCategories());
                Log.e("mmm" , categoryString.toString());
            }

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<AllNews> newsCall = apiInterface.getTopHeadlines(categoryString.toString());

            newsCall.enqueue(new Callback<AllNews>() {
                @Override
                public void onResponse(Call<AllNews> call, Response<AllNews> response) {
                    AllNews allNews = response.body();
                    mArticleList = allNews.getNewsArticleList();
                    mNewsAdapter = new NewsAdapter(getContext() , mArticleList);
                    mHeadlinesRecyclerView.setAdapter(mNewsAdapter);
                    //mNewsAdapter.notifyDataSetChanged();
                    mDialog.hide();
                }

                @Override
                public void onFailure(Call<AllNews> call, Throwable t) {
                    Log.e("error" , t.toString());
                }
            });
        }else {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<AllNews> newsCall = apiInterface.getTopHeadlines();

            newsCall.enqueue(new Callback<AllNews>() {
                @Override
                public void onResponse(Call<AllNews> call, Response<AllNews> response) {
                    AllNews allNews = response.body();
                    mArticleList = allNews.getNewsArticleList();
                    mNewsAdapter = new NewsAdapter(getContext() , mArticleList);
                    mHeadlinesRecyclerView.setAdapter(mNewsAdapter);
                    //mNewsAdapter.notifyDataSetChanged();
                    mDialog.hide();
                }

                @Override
                public void onFailure(Call<AllNews> call, Throwable t) {
                    Log.e("error" , t.toString());
                }
            });
        }

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
            getHeadlines();
        }
    }
}
