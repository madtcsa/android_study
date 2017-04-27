package com.madchen.livetest;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

/**
 * Created by chenwei on 2017/4/27.
 */

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class DaemonService extends JobService {

    @Override
    public void onCreate() {
        super.onCreate();
        startJobScheduler();
    }

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.v("DaemonService", "on start job");
        try {
            ActiveUtils activeUtils = new ActiveUtils(getApplicationContext());
            if (!activeUtils.isServiceRunning(HeartService.class)) {
                startService(new Intent(getApplicationContext(), HeartService.class));
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void startJobScheduler() {
        Log.v("DaemonService", "start job");
        try {
            int id = 2122;
            JobInfo.Builder builder = new JobInfo.Builder(id, new ComponentName(getPackageName(), DaemonService.class.getName()));
            builder.setPeriodic(1000 * 2);
            builder.setPersisted(true);
            JobScheduler jobScheduler = (JobScheduler) this.getSystemService(Context.JOB_SCHEDULER_SERVICE);
            jobScheduler.schedule(builder.build());
            // Android24版本才有scheduleAsPackage方法， 期待中
            //Class clz = Class.forName("android.app.job.JobScheduler");
            //Method[] methods = clz.getMethods();
            //Method method = clz.getMethod("scheduleAsPackage", JobInfo.class , String.class, Integer.class, String.class);
            //Object obj = method.invoke(jobScheduler, builder.build(), "com.brycegao.autostart", "brycegao", "test");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
