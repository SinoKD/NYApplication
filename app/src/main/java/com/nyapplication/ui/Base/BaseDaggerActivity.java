package com.nyapplication.ui.Base;

import com.nyapplication.data_models.Error;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by fingent on 1/17/18.
 */

public class BaseDaggerActivity extends DaggerAppCompatActivity implements IBaseView {

    @Override
    public void onError(Error error) {

    }

    @Override
    public void onInvalidSession(String msg) {

    }

    @Override
    public void showLoader() {

    }

    @Override
    public void hideLoader() {

    }
}
