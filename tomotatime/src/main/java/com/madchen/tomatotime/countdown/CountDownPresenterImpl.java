package com.madchen.tomatotime.countdown;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.madchen.tomatotime.model.Tomato;
import com.madchen.tomatotime.model.TomatoDataSource;
import com.madchen.tomatotime.model.TomatoDataSourceImpl;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by chenwei on 16/03/2017.
 */

public class CountDownPresenterImpl implements CountDownPresenter,TomatoDataSource.SaveTomatoCallBack {

    private CountDownView mCountDownView;
    private TomatoDataSource mTomatoDataSourceImpl;
    private Tomato mTomato;
    private PhoneStatus mPhoneStatus;
    private TomatoDataSource.SaveTomatoCallBack mSaveTomatoCallBack;

    public CountDownPresenterImpl(CountDownView countDownView) {
        mCountDownView = countDownView;
        mTomatoDataSourceImpl = new TomatoDataSourceImpl(countDownView.getContext());
    }

    @Override
    public void createNewTomato() {
        mTomato = new Tomato(1,0);
        mCountDownView.startCountDownTime(mTomato);
        initReceiver();
    }

    @Override
    public void cancelTomato() {
        mCountDownView.cancelCountDownTime();
        cancelReceiver();
    }

    @Override
    public void interruptTomato() {
        mCountDownView.interrupt();
        mTomato.addInterruptCount();
    }

    @Override
    public void overTomato() {
        mCountDownView.overTomato();
        cancelReceiver();
        mTomatoDataSourceImpl.saveTomato(mTomato,this);
    }

    @Override
    public void saveTomatoSuccess() {

    }

    @Override
    public void saveTomatoFailed() {

    }

    private void initReceiver() {
        if (mPhoneStatus == null) {
            mPhoneStatus = new PhoneStatus();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        mCountDownView.getContext().registerReceiver(mPhoneStatus, intentFilter);
    }

    private void cancelReceiver() {
        if (mPhoneStatus != null) {
            mCountDownView.getContext().unregisterReceiver(mPhoneStatus);
        }
    }

    class PhoneStatus extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            switch (intent.getAction()) {
                case Intent.ACTION_SCREEN_ON:
                    interruptTomato();
                    break;
            }
        }
    }
}
