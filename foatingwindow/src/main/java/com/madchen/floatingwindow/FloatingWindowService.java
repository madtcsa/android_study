package com.madchen.floatingwindow;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.support.v4.app.NotificationCompat;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by chenwei on 2017/4/15.
 */

public class FloatingWindowService extends Service {
    private View mView;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLayoutParams;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Floating Window")
                .setContentText("Text");
        Intent notificationIntent = new Intent(this, FloatingWindowMainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        builder.setContentIntent(pendingIntent);
        startForeground(10001, builder.build());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mLayoutParams = new WindowManager.LayoutParams();
        mWindowManager = (WindowManager) getApplication().getSystemService(WINDOW_SERVICE);
        mLayoutParams.type = WindowManager.LayoutParams.TYPE_TOAST;
        mLayoutParams.format = PixelFormat.TRANSLUCENT;
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        mLayoutParams.gravity = Gravity.CENTER;
        mLayoutParams.x = 0;
        mLayoutParams.y = 0;
        mLayoutParams.width = 200;
        mLayoutParams.height=180;
        mView = LayoutInflater.from(this).inflate(R.layout.floatting_window_layout,null);
        mWindowManager.addView(mView,mLayoutParams);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mView!=null)
        {
            mWindowManager.removeView(mView);
        }
    }
}
