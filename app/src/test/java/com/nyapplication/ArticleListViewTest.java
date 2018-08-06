package com.nyapplication;

import com.nyapplication.Utility.PreferenceManager;
import com.nyapplication.data_models.Article;
import com.nyapplication.data_models.ArticleListResponse;
import com.nyapplication.ui.article_list.ArticleListActivity;
import com.nyapplication.ui.article_list.ArticleViewPresenter;
import com.nyapplication.ui.article_list.IArticleView;
import com.nyapplication.web_service.APIInterface;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;

import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

@RunWith(MockitoJUnitRunner.class)

public class ArticleListViewTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    APIInterface apiInterface;

    @Mock
    PreferenceManager preferenceManager;

    @Mock
    ArticleListActivity articleListActivity;

    ArticleViewPresenter articleViewPresenter;

    Single<ArticleListResponse> articleListResponseSingle;

    private ArticleListResponse articleListResponse;
    private ArrayList<Article> articles = new ArrayList<>();

    @Before
    public void setUp() {

        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());

        articleViewPresenter = new ArticleViewPresenter(articleListActivity, apiInterface, preferenceManager);
        articles.add(new Article());
        articles.add(new Article());
        articles.add(new Article());

        articleListResponse = new ArticleListResponse();
        articleListResponse.setArticles(articles);
        articleViewPresenter.setArticlesList(articleListResponse.getArticles());

        articleListResponseSingle = Single.just(articleListResponse);
    }

    @Test
    public void shouldPassArticlesToView() {

        Mockito.when(articleViewPresenter.getApiResponseHandler()).thenReturn(articleListResponseSingle);
        articleViewPresenter.loadArticleList();

        Mockito.verify((IArticleView) articleListActivity).articleLoaded(articles);
    }

    @Test
    public void shouldDetailsActivityLoaded() {

        articleViewPresenter.onItemClicked(0);
        Mockito.verify((IArticleView) articleListActivity).startArticleDetailsActivity(articles.get(0));
    }
}
