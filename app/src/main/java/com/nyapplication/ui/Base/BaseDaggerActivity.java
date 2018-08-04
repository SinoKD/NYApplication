package com.nyapplication.ui.Base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nyapplication.data_models.Error;
import com.nyapplication.ui.componets.LoaderDialogFragment;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by fingent on 1/17/18.
 */

public class BaseDaggerActivity extends DaggerAppCompatActivity implements IBaseView {

    private LoaderDialogFragment mLoaderDialogFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public void onError(Error error) {

    }

    @Override
    public void onInvalidSession(String msg) {

    }

    @Override
    public void showLoader() {
        try {
            mLoaderDialogFragment = new LoaderDialogFragment();
            mLoaderDialogFragment.show(getSupportFragmentManager(), "loading");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void hideLoader() {
        try {
            if (mLoaderDialogFragment != null && mLoaderDialogFragment.isVisible())
                mLoaderDialogFragment.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
