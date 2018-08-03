package com.nyapplication.ui.Base;


import com.google.gson.JsonElement;
import com.nyapplication.data_models.Error;

import org.json.JSONException;

public interface IBaseAPIService {

    /**
     * @param result - object contains the result from API server.
     * @throws JSONException -throws, if there any exception occurred during parsing the result object.
     */
    void onSuccess(JsonElement result) throws JSONException;


    /**
     * @param error - contains the error details from API server.
     */
    void onError(Error error);
}
