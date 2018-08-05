package com.nyapplication.ui.article_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.google.gson.Gson;
import com.nyapplication.R;
import com.nyapplication.Utility.PreferenceManager;
import com.nyapplication.data_models.Article;
import com.nyapplication.ui.Base.BaseDaggerActivity;
import com.nyapplication.ui.article_details.ArticleDetails;
import com.nyapplication.ui.componets.ItemOffsetDecoration;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * @author Sino K D
 * @since 8/4/18.
 * Present the article list to user.
 */

public class ArticleListActivity extends BaseDaggerActivity implements IArticleView, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    ArticleViewPresenter articleViewPresenter;

    //Toolbar toolbar;
    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);

        /*toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.ll_swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.article_list);
        //tvEmptyHint = (TextView) view.findViewById(R.id.empty_view);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        final int spacing = getResources().getDimensionPixelOffset(R.dimen.default_spacing_small);
        recyclerView.addItemDecoration(new ItemOffsetDecoration(spacing));
        articleViewPresenter.loadArticleList();

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                //intimate the presenter the recycler view item clicked.
                articleViewPresenter.onItemClicked(position);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }

    /**
     * Function get called from presenter when articles fetched through web Api service.
     *
     * @param articles collection loaded from server.
     */
    @Override
    public void articleLoaded(ArrayList<Article> articles) {
        Log.d("Article Size", articles.size() + "");

        ArticleListAdapter articleListAdapter = new ArticleListAdapter(this, articles);
        recyclerView.setAdapter(articleListAdapter);
        runLayoutAnimation(recyclerView);
    }

    /**
     * Start the article details screen to view more details.
     *
     * @param article selected artilce.
     */
    @Override
    public void startArticleDetailsActivity(Article article) {

        Intent articleDetailsIntent = new Intent(this, ArticleDetails.class);
        articleDetailsIntent.putExtra("Article", new Gson().toJson(article));
        startActivity(articleDetailsIntent);
    }

    @Override
    protected void onDestroy() {
        articleViewPresenter.onDestroy();
        super.onDestroy();
    }

    /**
     * function to run the recycler view load animation
     *
     * @param recyclerView
     */
    private void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();

        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_fall_down_animation);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
        if (articleViewPresenter != null)
            articleViewPresenter.loadArticleList();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.period_prefs_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.period_1:
                PreferenceManager.getInstance().setApiPeriod(1);
                articleViewPresenter.loadArticleList();
                break;

            case R.id.period_7:
                PreferenceManager.getInstance().setApiPeriod(7);
                articleViewPresenter.loadArticleList();
                break;
            case R.id.period_30:
                PreferenceManager.getInstance().setApiPeriod(30);
                articleViewPresenter.loadArticleList();
                break;

        }

        return true;
    }
}
