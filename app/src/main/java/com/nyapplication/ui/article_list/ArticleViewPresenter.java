package com.nyapplication.ui.article_list;

import com.nyapplication.data_models.Article;
import com.nyapplication.data_models.ArticleListResponse;
import com.nyapplication.data_models.Error;
import com.nyapplication.ui.Base.IBasePresenter;
import com.nyapplication.ui.Base.IBaseView;
import com.nyapplication.web_service.ApiClient;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.nyapplication.Utility.AppConstants.APIConstants.API_KEY;


/**
 * @author Sino K D
 * @since 8/3/18.
 */
public class ArticleViewPresenter implements IBasePresenter, ArticleViewContract {

    private WeakReference<IBaseView> view;

    public ArticleViewPresenter(IBaseView view) {
        this.view = new WeakReference<IBaseView>(view);
    }

    private ArrayList<Article> articlesList;

    @Override
    public void loadArticleList() {

       /* Call serviceCall = ApiClient.getApiInterface().getAllUsers(API_KEY);
        Log.d("Called URL", serviceCall.request().url().toString());
        RetrofitService.enqueueWithRetry(serviceCall, this);*/

        view.get().showLoader();
        ApiClient.getApiInterface().getAllArticles(API_KEY)
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

    @Override
    public void onItemClicked(int pos) {
        ((IArticleView) view.get()).startArticleDetailsActivity(articlesList.get(pos));
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