package com.madchen.tomatotime.countdown;

import com.madchen.tomatotime.base.BasePresenter;

/**
 * Created by chenwei on 15/03/2017.
 */

public interface CountDownPresenter extends BasePresenter {

    void createNewTomato();
    void cancelTomato();
    void interruptTomato();
    void overTomato();
}
