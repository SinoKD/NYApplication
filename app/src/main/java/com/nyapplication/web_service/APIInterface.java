package com.nyapplication.web_service;

import com.google.gson.JsonElement;
import com.nyapplication.data_models.ArticleListResponse;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.nyapplication.Utility.AppConstants.APIConstants.TOP_VIEWED;

/**
 * APIInterface.java to define all API end points.
 *
 * @author Sino K D
 * @since 4/26/17.
 */

public interface APIInterface {

    @GET(TOP_VIEWED)
    Call<JsonElement> getAllUsers(@Query("api_key") String apiKey);

    @GET(TOP_VIEWED)
    Single<ArticleListResponse> getAllArticles(@Query("api_key") String apiKey);
}
