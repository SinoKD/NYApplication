package com.nyapplication;

import com.nyapplication.data_models.Article;
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

@RunWith(MockitoJUnitRunner.class)
public class ArticleListTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    APIInterface apiInterface;

    @Mock
    ArticleListActivity articleListActivity;

    ArticleViewPresenter articleViewPresenter;

    private ArrayList<Article> articles = new ArrayList<>();

    @Before
    public void setUp() {
        articleViewPresenter = new ArticleViewPresenter(articleListActivity, apiInterface);
        articles.add(new Article());
        articles.add(new Article());
        articles.add(new Article());
        articleViewPresenter.setArticlesList(articles);
    }

    @Test
    public void shouldPassArticlesToView() {
        articleListActivity.articleLoaded(articles);
    }

    @Test
    public void shouldDetailsActivityLoaded() {

        articleViewPresenter.onItemClicked(0);
        Mockito.verify((IArticleView) articleListActivity).startArticleDetailsActivity(articles.get(0));
    }
}
