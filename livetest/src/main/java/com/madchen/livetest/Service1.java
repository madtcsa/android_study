package com.madchen.livetest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;


/**
 * This Service is Persistent Service. Do some what you want to do here.<br/>
 *
 * Created by Mars on 12/24/15.
 */
public class Service1 extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        //TODO do some thing what you want..
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int i = 0;
        while (i < 10000) {
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d("Service1", "------Service1---- onStartCommand---- ");
            ActiveUtils activeUtils = new ActiveUtils(this);
            if(!activeUtils.isServiceRunning(HeartService.class)){
                startService(new Intent(this,HeartService.class));
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
