package com.nyapplication.di;

import com.nyapplication.Utility.PreferenceManager;
import com.nyapplication.ui.MyApplication;
import com.nyapplication.web_service.APIInterface;
import com.nyapplication.web_service.ApiClient;

import javax.inject.Singleton;

import dagger.Binds;
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

}
