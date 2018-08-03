package com.nyapplication.ui.article_list;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.nyapplication.R;
import com.nyapplication.data_models.Article;
import com.nyapplication.ui.Base.BaseDaggerActivity;

import java.util.ArrayList;

import javax.inject.Inject;

public class ArticleListActivity extends BaseDaggerActivity implements IArticleView {

    @Inject
    ArticleViewPresenter articleViewPresenter;

    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);

        recyclerView = (RecyclerView) findViewById(R.id.article_list);
        //tvEmptyHint = (TextView) view.findViewById(R.id.empty_view);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        articleViewPresenter.loadArticleList();
    }

    @Override
    public void articleLoaded(ArrayList<Article> articles) {
        Log.d("Article Size", articles.size() + "");

        ArticleListAdapter articleListAdapter = new ArticleListAdapter(this, articles);
        recyclerView.setAdapter(articleListAdapter);
    }

    @Override
    protected void onDestroy() {
        articleViewPresenter.onDestroy();
        super.onDestroy();
    }
}
