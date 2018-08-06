package com.nyapplication.ui.article_details;

import android.os.Build;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nyapplication.R;
import com.nyapplication.Utility.Util;
import com.nyapplication.data_models.Article;
import com.nyapplication.ui.article_details.viewpager.SlidingImage_Adapter;
import com.nyapplication.ui.article_details.viewpager.ZoomOutPageTransformer;
import com.viewpagerindicator.LinePageIndicator;

/**
 * @author Sino K D
 * @since 8/4/18.
 * Present the article details to user.
 */

public class ArticleDetails extends AppCompatActivity {

    //private Toolbar toolbar;
    private TextView tvArticleTitle;
    private TextView tvCaption;
    private TextView tvCopyRight;
    private TextView tvAuthor;
    private TextView tvPublishDate;
    private TextView tvAbstract;
    private TextView tvReadMore;
    private ViewPager viewPager;
    private LinePageIndicator pageIndicator;
    private SlidingImage_Adapter slidingImage_adapter;
    private Bundle bundle;
    private Article article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);

       /* toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        bundle = getIntent().getExtras();
        String articleStr = bundle.getString("Article");

        if (articleStr != null && !articleStr.isEmpty()) {
            article = new Gson().fromJson(articleStr, Article.class);
        }

        viewPager = findViewById(R.id.viewPager);
        pageIndicator = findViewById(R.id.circular_view_page_indicator);
        tvAbstract = findViewById(R.id.tv_details_abstract);
        tvArticleTitle = findViewById(R.id.tv_details_title);
        tvAuthor = findViewById(R.id.tv_details_by);
        tvCaption = findViewById(R.id.tv_details_caption);
        tvCopyRight = findViewById(R.id.tv_details_copy_right);
        tvPublishDate = findViewById(R.id.tv_details_publish_date);
        tvReadMore = findViewById(R.id.tv_details_read_more);

        setUpView(article);
    }

    private void setUpView(Article article) {

        if (article != null) {
            slidingImage_adapter = new SlidingImage_Adapter(this, article.getMedia().get(0).getMedia_metadata());
            viewPager.setAdapter(slidingImage_adapter);
            viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
            pageIndicator.setViewPager(viewPager);

            tvArticleTitle.setText(article.getTitle());
            if (article.getMedia().get(0).getCaption().isEmpty()) {
                tvCaption.setVisibility(View.GONE);
            } else {
                tvCaption.setText(article.getMedia().get(0).getCaption());
            }
            if (article.getMedia().get(0).getCopyright().isEmpty()) {
                tvCopyRight.setVisibility(View.GONE);
            } else
                tvCopyRight.setText(article.getMedia().get(0).getCopyright());
            tvAuthor.setText(article.getByline());
            tvPublishDate.setText(Util.convertUTI(article.getPublished_date()));
            tvAbstract.setText(article.getArticleAbstract());

            String readMoreLink = " <a href=" + article.getUrl() + " >Read More</a>";

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tvReadMore.setMovementMethod(LinkMovementMethod.getInstance());
                tvReadMore.setText(Html.fromHtml(readMoreLink, Html.FROM_HTML_MODE_LEGACY));
            } else {
                tvReadMore.setMovementMethod(LinkMovementMethod.getInstance());
                tvReadMore.setText(Html.fromHtml(readMoreLink));
            }

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
