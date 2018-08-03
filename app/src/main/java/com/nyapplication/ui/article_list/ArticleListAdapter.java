package com.nyapplication.ui.article_list;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nyapplication.R;
import com.nyapplication.data_models.Article;

import java.util.ArrayList;

/**
 * @author Sino K D
 * @since 8/3/18.
 */
public class ArticleListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    ArrayList<Article> articles;

    public ArticleListAdapter(Context context, ArrayList<Article> articles) {

        this.context = context;
        this.articles = articles;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_item_article, parent, false);
        ArticleItemHolder deviceItemHolder = new ArticleItemHolder(view);
        return deviceItemHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Article article = articles.get(position);
        ArticleItemHolder deviceItemHolder = (ArticleItemHolder) holder;

        deviceItemHolder.tvTitle.setText(article.getTitle());
        deviceItemHolder.tvAuthor.setText(article.getByline());
        deviceItemHolder.tvPublishedDate.setText(article.getPublished_date());
    }


    @Override
    public int getItemCount() {
        return articles.size();
    }

    private static class ArticleItemHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvAuthor;
        TextView tvPublishedDate;
        CardView cardViewDevice;

        public ArticleItemHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tv_article_title);
            tvAuthor = (TextView) itemView.findViewById(R.id.tv_article_author);
            tvPublishedDate = (TextView) itemView.findViewById(R.id.tv_article_date);
            cardViewDevice = (CardView) itemView.findViewById(R.id.cv_article_tile);
        }
    }

}