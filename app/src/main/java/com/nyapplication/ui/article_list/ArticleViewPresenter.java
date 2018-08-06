package com.nyapplication.ui.article_list;

import com.nyapplication.Utility.PreferenceManager;
import com.nyapplication.data_models.Article;
import com.nyapplication.data_models.ArticleListResponse;
import com.nyapplication.data_models.Error;
import com.nyapplication.ui.Base.IBasePresenter;
import com.nyapplication.ui.Base.IBaseView;
import com.nyapplication.web_service.APIInterface;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.nyapplication.Utility.AppConstants.APIConstants.API_KEY;


/**
 * Article list presenter provide all data needed for ArticleListActivity.Java
 *
 * @author Sino K D
 * @since 8/3/18.
 */
public class ArticleViewPresenter implements IBasePresenter, ArticleViewContract {

    private WeakReference<IBaseView> view;
    private APIInterface apiInterface;
    private PreferenceManager preferenceManager;

    @Inject
    public ArticleViewPresenter(IBaseView view, APIInterface apiInterface, PreferenceManager preferenceManager) {
        this.view = new WeakReference<IBaseView>(view);
        this.apiInterface = apiInterface;
        this.preferenceManager = preferenceManager;
    }

    private ArrayList<Article> articlesList;

    public void setArticlesList(ArrayList<Article> articlesList) {
        this.articlesList = articlesList;
    }

    /**
     * Fetch articles from server.
     */
    @Override
    public void loadArticleList() {

        view.get().showLoader();

        getApiResponseHandler()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<ArticleListResponse, ArrayList<Article>>() {
                    @Override
                    public ArrayList<Article> apply(ArticleListResponse articleListResponse) throws Exception {
                        return articleListResponse.getArticles();
                    }
                })
                .subscribe(new DisposableSingleObserver<ArrayList<Article>>() {
                    @Override
                    public void onSuccess(ArrayList<Article> articles) {
                        articlesList = articles;
                        ((IArticleView) view.get()).articleLoaded(articles);
                        view.get().hideLoader();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.get().onError(new Error(e));
                        view.get().hideLoader();
                    }
                });
    }

    /**
     * Returns Observable based the user preference.
     *
     * @return Observable based the user preference
     */
    private Single<ArticleListResponse> getApiResponseHandler() {

        int period = preferenceManager.getApiPeriod();
        switch (period) {
            case 1:
                return apiInterface.getAllArticles1days(API_KEY);
            case 7:
                return apiInterface.getAllArticles7days(API_KEY);
            case 30:
                return apiInterface.getAllArticles30days(API_KEY);

            default:
                return apiInterface.getAllArticles7days(API_KEY);
        }
    }

    /**
     * Intimate the view with requested article.
     *
     * @param pos selected position.
     */
    @Override
    public void onItemClicked(int pos) {
        ((IArticleView) view.get()).startArticleDetailsActivity(getItemAtPos(pos));
    }

    /**
     * Returns the article from collection
     *
     * @param pos position of the article
     * @return article at requested position.
     */
    public Article getItemAtPos(int pos) {
        return articlesList.get(pos);
    }

    @Override
    public void onDestroy() {
        view = null;
    }
}

interface ArticleViewContract {
    void loadArticleList();

    void onItemClicked(int pos);
}