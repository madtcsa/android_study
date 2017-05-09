package com.madchen.testsamsunglive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by chenwei on 2017/5/3.
 */

public class SMRamReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("com.samsung.android.sm.ACTION_RAM_BGCOMPACTION")){
//            paramContext.sendBroadcast(new Intent("com.samsung.android.sm.ACTION_RAM_BGCOMPACTION"), "com.samsung.permission.RAM_BGCOMPACTION");
        }
    }
}
