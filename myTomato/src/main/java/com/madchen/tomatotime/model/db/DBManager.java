package com.madchen.tomatotime.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.madchen.tomatotime.model.Tomato;

import java.util.List;

/**
 * Created by chenwei on 17/03/2017.
 */

public class DBManager implements DBConstants {

    private Context mContext;
    private TomatoDBHelper mTomatoDBHelper;
    private SQLiteDatabase mSQLiteDatabase;

    public DBManager(Context context) {
        mContext = context;
        mTomatoDBHelper = new TomatoDBHelper(mContext, DBConstants.DB_NAME, null, DBConstants.DB_VERSION);
        mSQLiteDatabase = mTomatoDBHelper.getWritableDatabase();
    }

    public long saveTomato(ContentValues contentValues) {
        return mSQLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public Tomato getTomato() {
        Tomato tomato = new Tomato(0, 0);
        return tomato;
    }

    public SQLiteDatabase getSQLiteDatabase() {
        return mSQLiteDatabase;
    }
}
