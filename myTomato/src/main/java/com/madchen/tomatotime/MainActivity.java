package com.madchen.tomatotime;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.idescout.sql.SqlScoutServer;
import com.madchen.tomatotime.base.BaseActivity;
import com.madchen.tomatotime.countdown.CountDownPresenter;
import com.madchen.tomatotime.countdown.CountDownPresenterImpl;
import com.madchen.tomatotime.countdown.CountDownView;
import com.madchen.tomatotime.customview.TomatoView;
import com.madchen.tomatotime.model.Tomato;
import com.madchen.tomatotime.mydata.MyDataActivity;

public class MainActivity extends BaseActivity implements CountDownView, View.OnClickListener, TomatoView.TomatoStatusListener {

    private RelativeLayout rootView;
    private CountDownPresenter mPresenter;
    private TomatoView mTomatoView;
    private TextView myDataText, settingText;
    private Context mContext;
    private RelativeLayout overViewLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SqlScoutServer.create(this, getPackageName());
        setContentView(R.layout.activity_main);
        mContext = this;
        mPresenter = new CountDownPresenterImpl(this);
        initView();
    }

    private void initView() {
        rootView = (RelativeLayout) findViewById(R.id.root);
        mTomatoView = (TomatoView) findViewById(R.id.tomato_view);
        mTomatoView.initTomato();
        myDataText = (TextView) findViewById(R.id.my_data_text);
        settingText = (TextView) findViewById(R.id.setting_text);
        myDataText.setOnClickListener(this);
        settingText.setOnClickListener(this);
        mTomatoView.setTomatoStatusListener(this);
        findViewById(R.id.btn_start_count_down).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_data_text:
                Intent intent = new Intent(MainActivity.this, MyDataActivity.class);
                startActivity(intent);
                break;
            case R.id.setting_text:

                break;
            case R.id.btn_start_count_down:
                if (!mTomatoView.isCountDowning()) {
                    mPresenter.createNewTomato(mTomatoView.getMinutes());
                } else {
                    mPresenter.cancelTomato();
                }
                break;
        }
    }

    @Override
    public void showCountDownTime() {

    }

    @Override
    public void cancelCountDownTime() {
        mTomatoView.cancelTomato();
    }

    @Override
    public void startCountDownTime(Tomato tomato) {
        mTomatoView.setTomato(tomato);
        mTomatoView.startTomato();
        Log.d("MainActivity", "----startCountDown---- ");
    }

    @Override
    public void interrupt() {

    }

    @Override
    public void overTomato() {

        mTomatoView.overTomato();
    }

    @Override
    public void finishTomato() {

        mPresenter.overTomato();
    }

    @Override
    public Context getContext() {
        return mContext;
    }
}
