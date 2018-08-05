package com.nyapplication.web_service;

import com.google.gson.JsonElement;
import com.nyapplication.data_models.ArticleListResponse;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.nyapplication.Utility.AppConstants.APIConstants.TOP_VIEWED_1Days;
import static com.nyapplication.Utility.AppConstants.APIConstants.TOP_VIEWED_30Days;
import static com.nyapplication.Utility.AppConstants.APIConstants.TOP_VIEWED_7Days;

/**
 * APIInterface.java to define all API end points.
 *
 * @author Sino K D
 * @since 4/26/17.
 */

public interface APIInterface {

    @GET(TOP_VIEWED_7Days)
    Call<JsonElement> getAllUsers(@Query("api_key") String apiKey);

    @GET(TOP_VIEWED_7Days)
    Single<ArticleListResponse> getAllArticles7days(@Query("api_key") String apiKey);

    @GET(TOP_VIEWED_1Days)
    Single<ArticleListResponse> getAllArticles1days(@Query("api_key") String apiKey);

    @GET(TOP_VIEWED_30Days)
    Single<ArticleListResponse> getAllArticles30days(@Query("api_key") String apiKey);
}
