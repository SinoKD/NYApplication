package com.nyapplication.data_models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Sino K D
 * @since 8/3/18.
 * <p>
 * Model object to handle the API error messages.
 * </p>
 */
public class Error {

    private static final String MESSAGE = "message";
    private String message = "";
    private String description = "";
    private int code;


    public Error() {
    }

    public Error(Throwable e) {
        message = e.getMessage();
        description = e.getMessage();
    }

    public Error(String error) {
        JSONObject jsonObject = null;
        try {
            //jsonObject = new JSONObject(error);
            JSONObject jresult = new JSONObject(error);
            //jresult = jsonObject.getJSONObject(ERRORS);
            message = jresult.getString(Error.MESSAGE);
            //description = jsonObject.getString(Error.DESCRIPTION);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getMessage() {
        if (message == null || message.isEmpty()) {
            return "Oops something went wrong!";
        }
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
