package com.madchen.tomatotime.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.madchen.tomatotime.model.db.DBConstants;
import com.madchen.tomatotime.model.db.DBManager;
import com.madchen.tomatotime.utils.DebugUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenwei on 18/03/2017.
 */

public class TomatoDataSourceImpl implements TomatoDataSource, DBConstants.TomatoTableField, DBConstants {

    private DBManager mDBManager;

    public TomatoDataSourceImpl(Context context) {

        mDBManager = new DBManager(context);
    }

    @Override
    public void saveTomato(Tomato tomato, SaveTomatoCallBack saveTomatoCallBack) {
        ContentValues cv = new ContentValues();
        cv.put(minutes, tomato.getMinutes());
        cv.put(interruptCount, tomato.getInterrupt());
        cv.put(startTimeL, tomato.getStartTimeL());
        if (mDBManager.saveTomato(cv) != -1) {
            saveTomatoCallBack.saveTomatoSuccess();
        } else {
            saveTomatoCallBack.saveTomatoFailed();
        }
    }

    @Override
    public void loadAllTomato(LoadAllTomatoCallBack loadAllTomatoCallBack) {
        String[] selectionArgs = {"?", "?", "?"};
        Cursor cursor = mDBManager.getSQLiteDatabase().rawQuery(QUERY_ALL_TOMATOES,null);
        DebugUtils.debugLog("All Tomato cursor "+ cursor.getCount());
        if (cursor != null) {
            List<Tomato> tomatoes = new ArrayList<>();
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                tomatoes.add(getTomato(cursor));
            }
            loadAllTomatoCallBack.onAllTomatoLoaded(tomatoes);
        } else {
            loadAllTomatoCallBack.onAllDataNotAvailable();
        }
    }

    @Override
    public void loadMonthTomatoes(long monthTimeL, LoadMonthTomatoesCallBack loadMonthTomatoesCallBack) {

    }

    @Override
    public void loadDayTomatoes(long dayTimeL, LoadDayTomatoesCallBack loadDayTomatoesCallBack) {

    }

    public Tomato getTomato(Cursor cursor) {
        Tomato tomato = new Tomato(0, 0);
        tomato.setMinutes(cursor.getInt(cursor.getColumnIndex(minutes)));
        tomato.setInterrupt(cursor.getInt(cursor.getColumnIndex(interruptCount)));
        tomato.setStartTimeL(cursor.getLong(cursor.getColumnIndex(startTimeL)));
        Log.d("TomatoDataSourceImpl", "----tomato---- "+ tomato.toString());
        return tomato;
    }

}
