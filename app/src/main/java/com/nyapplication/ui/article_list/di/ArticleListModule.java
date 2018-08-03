package com.nyapplication.ui.article_list.di;

import com.nyapplication.ui.article_list.ArticleListActivity;
import com.nyapplication.ui.article_list.ArticleViewPresenter;
import com.nyapplication.ui.article_list.IArticleView;

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
    static ArticleViewPresenter provideArticleViewPresenter(IArticleView mainView) {
        return new ArticleViewPresenter(mainView);
    }

    @Binds
    abstract IArticleView provideArticleView(ArticleListActivity articleListActivity);
}
