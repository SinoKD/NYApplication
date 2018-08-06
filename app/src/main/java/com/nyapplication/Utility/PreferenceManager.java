package com.nyapplication.Utility;

import android.content.Context;
import android.content.SharedPreferences;

import com.nyapplication.ui.MyApplication;

import javax.inject.Inject;

/**
 * @author Sino K D.
 * @since 4/11/17.
 */

public class PreferenceManager {

    public static final String PREFERENCE_FILE_NAME = "Preference_File";
    public static final String API_PERIOD = "api_period";


    Context context;
    private static PreferenceManager preferenceManager;
    SharedPreferences sharedPreferences; //preference object
    SharedPreferences.Editor editor; //preference editor object

    private PreferenceManager() {
        this.context = MyApplication.getInstance();
        sharedPreferences = context.getSharedPreferences(PREFERENCE_FILE_NAME, 0);
        editor = sharedPreferences.edit();
    }

    public static PreferenceManager getInstance() {
        if (preferenceManager == null) {
            preferenceManager = new PreferenceManager();
        }
        return preferenceManager;
    }

    public void setApiPeriod(int apiPeriod) {
        editor.putInt(API_PERIOD, apiPeriod);
        editor.commit();
    }

    public int getApiPeriod() {
        return sharedPreferences.getInt(API_PERIOD, 7);
    }

}
