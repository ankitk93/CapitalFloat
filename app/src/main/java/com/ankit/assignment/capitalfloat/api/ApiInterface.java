package com.ankit.assignment.capitalfloat.api;

import com.ankit.assignment.capitalfloat.Utills.Constants;
import com.ankit.assignment.capitalfloat.model.AllNews;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("top-headlines?language=en&pageSize=100&apiKey=3b255e69c52e42b4a6bf10faa92f03cb")
    Call<AllNews> getTopHeadlines(@Query("category") String sources);

    @GET("top-headlines?language=en&pageSize=100&apiKey=3b255e69c52e42b4a6bf10faa92f03cb")
    Call<AllNews> getTopHeadlines();

    @GET("everything?pageSize=100&apiKey=3b255e69c52e42b4a6bf10faa92f03cb")
    Call<AllNews> getEverything(@Query("q") String query ,@Query("sortBy") String sortBy);
}
