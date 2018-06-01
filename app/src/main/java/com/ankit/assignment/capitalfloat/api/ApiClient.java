package com.ankit.assignment.capitalfloat.api;

import com.ankit.assignment.capitalfloat.Utills.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit mRetrofit = null;

    public static Retrofit getClient(){
        if (mRetrofit == null){
            Gson gson = new GsonBuilder().setLenient().create();
            mRetrofit = new Retrofit
                    .Builder()
                    .baseUrl(Constants.NEWS_API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient())
                    .build();
        }

        return mRetrofit;
    }

    public static OkHttpClient okHttpClient(){
        return new OkHttpClient.Builder()
                .readTimeout(180 , TimeUnit.SECONDS)
                .connectTimeout(180 , TimeUnit.SECONDS)
                .build();
    }
}
