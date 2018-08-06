package com.nyapplication.di;

import android.app.Application;

import com.nyapplication.Utility.PreferenceManager;
import com.nyapplication.web_service.APIInterface;
import com.nyapplication.web_service.ApiClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Sino K D
 * @since 8/3/18.
 */
@Module
public abstract class AppModule {


    @Singleton
    @Provides
    static APIInterface provideAPIInterface() {
        return ApiClient.getApiInterface();
    }

    @Singleton
    @Provides
    static PreferenceManager providePreferenceManager(Application application) {
        return new PreferenceManager(application);
    }

}
