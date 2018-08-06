package com.nyapplication.ui.article_list.di;

import com.nyapplication.Utility.PreferenceManager;
import com.nyapplication.ui.article_list.ArticleListActivity;
import com.nyapplication.ui.article_list.ArticleViewPresenter;
import com.nyapplication.ui.article_list.IArticleView;
import com.nyapplication.web_service.APIInterface;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * @author Sino K D
 * @since 8/2/18.
 */

@Module
public abstract class ArticleListModule {

    @Provides
    static ArticleViewPresenter provideArticleViewPresenter(IArticleView mainView, APIInterface apiInterface,PreferenceManager preferenceManager) {
        return new ArticleViewPresenter(mainView, apiInterface,preferenceManager);
    }

    @Binds
    abstract IArticleView provideArticleView(ArticleListActivity articleListActivity);

}
