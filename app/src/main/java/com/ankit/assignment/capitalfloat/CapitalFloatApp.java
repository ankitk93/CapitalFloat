package com.ankit.assignment.capitalfloat;

import android.app.Application;
import android.content.Context;

public class CapitalFloatApp extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;
    }

    public Context getContext(){
        return mContext;
    }

    public static CapitalFloatApp getInstance(){
        return (CapitalFloatApp) mContext;
    }


}
