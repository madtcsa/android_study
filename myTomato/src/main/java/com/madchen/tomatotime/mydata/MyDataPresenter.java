package com.madchen.tomatotime.mydata;

import com.madchen.tomatotime.base.BasePresenter;

/**
 * Created by chenwei on 15/03/2017.
 */

public interface MyDataPresenter extends BasePresenter {

    void getAllTomatoes();

    void getTodayTomatoes(long dateTimeL);

    void getMonthTomatoes(long monthTimeL);

}
