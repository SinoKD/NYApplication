package com.nyapplication.data_models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * @author Sino K D
 * @since 8/3/18.
 *
 * Model object handling the API response
 */
public class ArticleListResponse extends BaseApiResponse {

    @SerializedName(value = "results", alternate = {"articles"})
    private ArrayList<Article> articles;

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }
}
