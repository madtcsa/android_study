package com.madchen.tomatotime.countdown;

import com.madchen.tomatotime.base.BaseView;
import com.madchen.tomatotime.model.Tomato;

/**
 * Created by chenwei on 15/03/2017.
 */

public interface CountDownView extends BaseView {

    void showCountDownTime();

    void cancelCountDownTime();

    void startCountDownTime(Tomato tomato);

    void interrupt();

    void overTomato();

}
