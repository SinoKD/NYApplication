package com.nyapplication.web_service;


import com.nyapplication.BuildConfig;
import com.nyapplication.ui.MyApplication;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ApiClient.java provide retrofit instance for network calls.
 *
 * @author Sino K D
 * @since 4/26/17
 */

public class ApiClient {

    public static String BASE_URL;
    public static final int TIME_OUT_IN_MS = 30 * 1000;
    private static Retrofit retrofit = null;

    private static APIInterface apiInterface = null;

    /**
     * Method to provide retrofit instance for network calls
     *
     * @return retrofit instance.
     */
    public static Retrofit getClient() {

        if (retrofit == null) {

            BASE_URL = BuildConfig.BASE_URL;
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(TIME_OUT_IN_MS, TimeUnit.MILLISECONDS)
                    .readTimeout(TIME_OUT_IN_MS, TimeUnit.MILLISECONDS)
                    .addInterceptor(interceptor)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }

    /**
     * Method to get API endpoints defined.
     *
     * @return apiInterface - implementation of the API endpoints defined by the interface.
     */
    public static APIInterface getApiInterface() {
        if (apiInterface == null) {
            apiInterface = getClient().create(APIInterface.class);
        }
        return apiInterface;
    }
}