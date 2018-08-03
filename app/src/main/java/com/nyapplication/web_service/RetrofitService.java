package com.nyapplication.web_service;

import android.util.Log;

import com.nyapplication.data_models.Error;
import com.nyapplication.ui.Base.IBaseAPIService;

import org.json.JSONException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Sino on 4/18/17.
 */

public class RetrofitService {

    private static final String RESULT = "results";
    private static final int RETRY_COUNT = 3;


    public static <JsonElement> void enqueueWithRetry(Call<com.google.gson.JsonElement> call, final IBaseAPIService listener) {

        call.enqueue(new RetryCallback<com.google.gson.JsonElement>(call, RETRY_COUNT) {

            @Override
            public void onResponse(Call<com.google.gson.JsonElement> call, Response<com.google.gson.JsonElement> response) {
                if (response.isSuccessful()) {
                    com.google.gson.JsonElement jsonElement = response.body();
                    Log.d("Retrofit", jsonElement.toString());
                    try {
                        listener.onSuccess(jsonElement);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        Error error = ErrorUtils.parseError(response);
                        listener.onError(error);
                        Log.d("Error re test", error.getMessage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<com.google.gson.JsonElement> call, Throwable t) {
                super.onFailure(call, t);

                Error error = new Error();
                if (t instanceof NoConnectivityException) {
                    error.setMessage("No Internet Connectivity");
                } else {
                    error.setMessage("OOps! something went wrong");
                }
                listener.onError(error);
            }

        });
    }


}
