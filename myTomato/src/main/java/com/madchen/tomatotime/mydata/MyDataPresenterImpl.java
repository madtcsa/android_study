package com.madchen.tomatotime.mydata;

import com.madchen.tomatotime.model.Tomato;
import com.madchen.tomatotime.model.TomatoDataSource;
import com.madchen.tomatotime.model.TomatoDataSourceImpl;

import java.util.List;

/**
 * Created by chenwei on 2017/3/26.
 */

public class MyDataPresenterImpl implements MyDataPresenter, TomatoDataSource.LoadAllTomatoCallBack,
        TomatoDataSource.LoadMonthTomatoesCallBack, TomatoDataSource.LoadDayTomatoesCallBack {

    private MyDataView mMyDataView;
    private TomatoDataSource mTomatoDataSourceImpl;

    public MyDataPresenterImpl(MyDataView myDataView) {
        mMyDataView = myDataView;
        mTomatoDataSourceImpl = new TomatoDataSourceImpl(mMyDataView.getContext());
    }

    @Override
    public void getAllTomatoes() {
        mTomatoDataSourceImpl.loadAllTomato(this);
    }

    @Override
    public void getTodayTomatoes(long dateTimeL) {
        mTomatoDataSourceImpl.loadDayTomatoes(dateTimeL, this);
    }

    @Override
    public void getMonthTomatoes(long monthTimeL) {
        mTomatoDataSourceImpl.loadMonthTomatoes(monthTimeL, this);
    }


    @Override
    public void onAllTomatoLoaded(List<Tomato> tomatoList) {
        mMyDataView.showAllTomatoData(tomatoList);
    }

    @Override
    public void onAllDataNotAvailable() {

    }

    @Override
    public void onMonthTomatoesLoaded(List<Tomato> tomatoList) {

    }

    @Override
    public void onMonthDataNotAvailable() {

    }

    @Override
    public void onDayTomatoesLoaded(List<Tomato> tomatoList) {

    }

    @Override
    public void onDayDataNovAvailable() {

    }
}
