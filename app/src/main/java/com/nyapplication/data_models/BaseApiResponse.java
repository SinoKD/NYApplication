package com.nyapplication.data_models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * @author Sino K D
 * @since 8/3/18.
 */
public abstract class BaseApiResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("errors")
    private ArrayList<String> errors;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<String> getErrors() {
        return errors;
    }

    public void setErrors(ArrayList<String> errors) {
        this.errors = errors;
    }
}
