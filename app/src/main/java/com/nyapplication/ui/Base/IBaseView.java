package com.nyapplication.ui.Base;

import com.nyapplication.data_models.Error;

/**
 * Created by Sino on 31/03/16.
 */
public interface IBaseView {

    /**
     * callback to reflect error to view
     *
     * @param error
     */
    void onError(Error error);


    /**
     * callback to reflect session expired
     *
     * @param msg
     */
    void onInvalidSession(String msg);

    /**
     * to show overlay progress loader
     */
    void showLoader();

    /**
     * to hide overlay progress loader
     */
    void hideLoader();
}
