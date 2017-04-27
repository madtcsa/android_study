package com.madchen.tomatotime.utils;

import android.util.Log;

/**
 * Created by chenwei on 2017/4/24.
 */

public class DebugUtils {

    private static final boolean isDebug = true;
    private static final String LOG_TAG = "LogTag";

    public static void debugLog(String log) {
        if (isDebug) {
            Log.d(LOG_TAG, log);
        }
    }
}
