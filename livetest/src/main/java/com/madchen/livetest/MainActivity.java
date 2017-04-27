package com.madchen.livetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.madchen.livetest.R.id.btn_job;
import static com.madchen.livetest.R.id.btn_service;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button jobBtn = (Button) findViewById(btn_job);
        jobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this, HeartService.class));
            }
        });

        Button serviceBtn = (Button) findViewById(btn_service);
        serviceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this, Service1.class));
            }
        });
    }
}
