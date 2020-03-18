package com.sujit.zomatoapidemo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;

public class NetworkUtils {

    public static boolean isNetworkOnline(Context context) {
        boolean status = false;
        if (context != null) {
            ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connManager != null) {
                NetworkInfo mWifi = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    mWifi = connManager.getNetworkInfo(NetworkCapabilities.TRANSPORT_WIFI);
                } else {
                    mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                }
                if (mWifi != null && mWifi.isConnected()) {
                    return true;
                }
                try {
                    NetworkInfo netInfo = connManager.getNetworkInfo(0);
                    if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                        status = true;
                    } else {
                        netInfo = connManager.getNetworkInfo(1);
                        if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED)
                            status = true;
                    }
                } catch (Exception e) {
                    return false;
                }
            }
        }
        return status;
    }
}
