package com.nyapplication.ui.article_list;

import com.nyapplication.data_models.Article;
import com.nyapplication.ui.Base.IBaseView;

import java.util.ArrayList;

public interface IArticleView extends IBaseView {
    void articleLoaded(ArrayList<Article> articles);

    void startArticleDetailsActivity(Article article);
}