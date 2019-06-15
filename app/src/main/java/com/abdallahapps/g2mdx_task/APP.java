package com.abdallahapps.g2mdx_task;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.facebook.FacebookSdk;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

public class APP extends MultiDexApplication {

    public static Activity context;

    @Override
    public void onCreate() {
        super.onCreate();
        //FacebookSdk.isInitialized();
        FacebookSdk.sdkInitialize(this);
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
