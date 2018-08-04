package com.nyapplication.Utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Sino K D
 * @since 8/2/18.
 */
public class Util {

    public static boolean haveNetworkConnectivity(Context context) {
        boolean haveConnectedWifi = false;

        final String WIFI_CONNECTION_CHECK = "WIFI";

        final String MOBILE_CONNECTION_CHECK = "MOBILE";
        boolean haveConnectedMobile = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        int lte_count = 0;
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase(WIFI_CONNECTION_CHECK) && ni.isConnected()) {
                haveConnectedWifi = true;
            }
            if (ni.getTypeName().equalsIgnoreCase(MOBILE_CONNECTION_CHECK) && ni.isConnected()) {
                haveConnectedMobile = true;
            }

            if (ni.getTypeName().equalsIgnoreCase(MOBILE_CONNECTION_CHECK) && ni.isConnected() && (ni.getSubtypeName().equals("LTE"))) {
                lte_count = +1;
            }
            if (lte_count > 0) {
                boolean mobileDataEnabled = false; // Assume disabled
                ConnectivityManager cm1 = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                try {
                    Class cmClass = Class.forName(cm1.getClass().getName());
                    Method method = cmClass.getDeclaredMethod("getMobileDataEnabled");
                    method.setAccessible(true);
                    mobileDataEnabled = (Boolean) method.invoke(cm1);
                    return mobileDataEnabled;
                } catch (Exception e) {

                }
            }

        }

        return haveConnectedWifi || haveConnectedMobile;
    }


    public static String convertUTI(String time) {


        if (time != null && !time.isEmpty()) {
            String inputPattern = "yyyy-MM-dd";
            String outputPattern = "MMM. dd, yyyy";
            SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.ENGLISH);
            inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern, Locale.ENGLISH);
            outputFormat.setTimeZone(TimeZone.getDefault());

            Date date = null;
            String str = null;

            try {
                date = inputFormat.parse(time);
                str = outputFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

           /* if (str != null && !str.isEmpty()) {
                str = str.replaceFirst(" ", getDayOfMonthSuffix(date.getDate()) + " ");
            }*/

            return str;
        } else {
            return "";
        }
    }
}
