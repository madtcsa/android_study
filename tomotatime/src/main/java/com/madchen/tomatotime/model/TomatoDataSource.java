package com.madchen.tomatotime.model;

import android.view.LayoutInflater;

import java.util.List;

/**
 * Created by chenwei on 15/03/2017.
 */

public interface TomatoDataSource {

    void saveTomato(Tomato tomato,SaveTomatoCallBack saveTomatoCallBack);

    void loadAllTomato(LoadAllTomatoCallBack loadAllTomatoCallBack);

    void loadMonthTomatoes(long monthTimeL, LoadMonthTomatoesCallBack loadMonthTomatoesCallBack);

    void loadDayTomatoes(long dayTimeL, LoadDayTomatoesCallBack loadDayTomatoesCallBack);

    interface LoadAllTomatoCallBack {

        void onAllTomatoLoaded(List<Tomato> tomatoList);

        void onAllDataNotAvailable();
    }

    interface LoadMonthTomatoesCallBack {

        void onMonthTomatoesLoaded(List<Tomato> tomatoList);

        void onMonthDataNotAvailable();
    }

    interface LoadDayTomatoesCallBack {

        void onDayTomatoesLoaded(List<Tomato> tomatoList);

        void onDayDataNovAvailable();
    }

    interface SaveTomatoCallBack {
        void saveTomatoSuccess();

        void saveTomatoFailed();
    }
}
