package com.nyapplication.web_service;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by fingent on 3/20/18.
 */

public abstract class RetryCallback<JsonElement> implements Callback<JsonElement> {

    private int totalRetries = 3;
    private static final String TAG = RetryCallback.class.getSimpleName();
    private final Call<JsonElement> call;
    private int retryCount = 0;

    public RetryCallback(Call<JsonElement> call, int totalRetries) {
        this.call = call;
        this.totalRetries = totalRetries;
    }

    @Override
    public void onFailure(Call<JsonElement> call, Throwable t) {
        Log.e(TAG, t.getMessage());

        if (retryCount++ < totalRetries) {
            Log.v(TAG, "Retrying API Call -  (" + retryCount + " / " + totalRetries + ")");
            retry();
        }
    }

    private void retry() {
        call.clone().enqueue(this);
    }
}