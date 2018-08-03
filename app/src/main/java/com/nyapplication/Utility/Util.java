package com.nyapplication.Utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.lang.reflect.Method;

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
}
