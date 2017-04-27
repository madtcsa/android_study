package com.madchen.livetest;

import android.app.ActivityManager;
import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.util.List;

/**
 * Created by chenwei on 2017/4/27.
 */

public class ActiveUtils {
    private static final String TAG = ActiveUtils.class.getSimpleName();
    private Context mContext;

    public ActiveUtils(Context context) {
        mContext = context;
    }

    public boolean isServiceRunning(Class<?> serviceClass) {
        if (serviceClass == null || mContext == null) {
            Log.d(TAG, "isServiceRunning: serviceClass or mContext is null!");
            return false;
        }
        ActivityManager manager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> runningService = manager.getRunningServices(Integer.MAX_VALUE);
        if (null != runningService && runningService.size() != 0) {
            for (ActivityManager.RunningServiceInfo serviceInfo : runningService) {
                if (serviceClass.getName().equals(serviceInfo.service.getClassName())) {
                    return true;
                }
            }
        }
        Log.d(TAG, serviceClass + " isServiceRunning: " + false);
        return false;
    }

    public boolean isNetWorkEnable() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetWorkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetWorkInfo != null && activeNetWorkInfo.isConnected();
    }

    public static final int LOCATION_SERVICE_UNENABLE = 1;
    public static final int GPS_PROVIDER_UNENABLE = 2;
    public static final int NETWORK_PROVIDER_UNENABLE = 3;
    public static final int LOCATION_SERVICE_ENABEL = 4;

    public  int isLocationServiceEnable() {

        LocationManager locationManager = (LocationManager) this.mContext.getSystemService(Context.LOCATION_SERVICE);
        boolean isGpsProviderEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetWorkProviderEnable = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (!isGpsProviderEnable && !isNetWorkProviderEnable) {
            return LOCATION_SERVICE_UNENABLE;
        }
        if (!isGpsProviderEnable) {
            return GPS_PROVIDER_UNENABLE;
        }
        if (!isNetWorkProviderEnable) {
            return NETWORK_PROVIDER_UNENABLE;
        }
        return LOCATION_SERVICE_ENABEL;
    }
}
