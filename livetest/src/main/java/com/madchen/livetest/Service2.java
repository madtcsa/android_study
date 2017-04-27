package com.madchen.livetest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * DO NOT do anything in this Service!<br/>
 *
 * Created by Mars on 12/24/15.
 */
public class Service2 extends Service{

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Service2", "------Service2---- onStartCommand---- ");
        return Service.START_NOT_STICKY;
    }
}
