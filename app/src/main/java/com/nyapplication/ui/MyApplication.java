package com.nyapplication.ui;

import com.nyapplication.di.AppComponent;
import com.nyapplication.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;


/**
 * Created by Sino on 1/11/18.
 */

public class MyApplication extends DaggerApplication {


    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }
}
