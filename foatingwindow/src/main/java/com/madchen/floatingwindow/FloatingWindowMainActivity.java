package com.madchen.floatingwindow;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowId;
import android.view.WindowManager;

import java.security.Permission;

public class FloatingWindowMainActivity extends AppCompatActivity {

    private boolean isShowing;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mContext = getApplicationContext();
        startService(new Intent(this, FloatingWindowService.class));
    }

}
