package com.nyapplication.di;

import com.nyapplication.ui.article_list.ArticleListActivity;
import com.nyapplication.ui.article_list.di.ArticleListModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author Sino K D
 * @since 8/3/18.
 */

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = ArticleListModule.class)
    abstract ArticleListActivity bindMainActivity();
}
