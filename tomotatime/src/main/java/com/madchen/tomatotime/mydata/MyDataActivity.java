package com.madchen.tomatotime.mydata;

import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Utils;
import com.madchen.tomatotime.R;
import com.madchen.tomatotime.base.BaseActivity;
import com.madchen.tomatotime.model.Tomato;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenwei on 2017/3/26.
 */

public class MyDataActivity extends BaseActivity implements MyDataView, View.OnClickListener
        , OnChartGestureListener, OnChartValueSelectedListener {

    private MyDataPresenter myDataPresenterImpl;
    private TextView totalTimeText, totalTimeRank, todayTime, todayRank;
    private LineChart mLineChart;
    private Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mydata_layout);
        this.mContext = this;
        initView();
        myDataPresenterImpl = new MyDataPresenterImpl(this);
        myDataPresenterImpl.getAllTomatoes();

    }

    private void initView() {
        totalTimeText = (TextView) findViewById(R.id.text_total_tomato_time);
        totalTimeRank = (TextView) findViewById(R.id.text_total_time_rank);
        todayTime = (TextView) findViewById(R.id.text_today_tomato_time);
        todayRank = (TextView) findViewById(R.id.text_today_rank);
        mLineChart = (LineChart) findViewById(R.id.char_tomato);
        initCharView();
    }

    private void initCharView() {

        mLineChart.setOnChartGestureListener(this);
        mLineChart.setOnChartValueSelectedListener(this);
        mLineChart.setDrawGridBackground(false);
        mLineChart.getDescription().setEnabled(false);
        mLineChart.setTouchEnabled(true);
        mLineChart.setDragEnabled(true);
        mLineChart.setScaleEnabled(false);
        mLineChart.setPinchZoom(true);

        XAxis xAxis = mLineChart.getXAxis();
//        xAxis.enableGridDashedLine(10f, 10f, 0f);
        xAxis.setDrawLabels(true);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMaximum(12f);
        xAxis.setAxisMinimum(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.WHITE);

        YAxis leftAxis = mLineChart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
        leftAxis.setAxisMaximum(200f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setTextColor(Color.WHITE);
//        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(false);
        // limit lines are drawn behind data (and not on top)
        leftAxis.setDrawLimitLinesBehindData(true);
        mLineChart.getAxisRight().setEnabled(false);
        // add data
//        setData(12, 100);
        mLineChart.animateX(2500);
        // get the legend (only possible after setting data)
        Legend l = mLineChart.getLegend();
        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);
        // // dont forget to refresh the drawing
        // mChart.invalidate();
    }

    public void showAllTomatoData(List<Tomato> tomatoList) {
        List<Entry> values = new ArrayList<>();
        LineDataSet lineDataSet;
        for (int i = 0; i < tomatoList.size(); i++) {
            values.add(new Entry(i, tomatoList.get(i).getMinutes(), getResources().getDrawable(R.drawable.star)));
        }
        if (mLineChart.getData() != null &&
                mLineChart.getData().getDataSetCount() > 0) {
            lineDataSet = (LineDataSet) mLineChart.getData().getDataSetByIndex(0);
            lineDataSet.setValues(values);
            mLineChart.getData().notifyDataChanged();
            mLineChart.notifyDataSetChanged();
        } else {
            lineDataSet = new LineDataSet(values, "All Tomato");
            lineDataSet.setDrawIcons(false);
            lineDataSet.enableDashedLine(10f, 5f, 0f);
            lineDataSet.enableDashedHighlightLine(10f, 5f, 0f);
            lineDataSet.setColor(Color.WHITE);
            lineDataSet.setCircleColor(Color.WHITE);
            lineDataSet.setValueTextColor(Color.WHITE);
            lineDataSet.setLineWidth(1f);
            lineDataSet.setCircleRadius(1.5f);
            lineDataSet.setDrawCircleHole(false);
            lineDataSet.setValueTextSize(9f);
            lineDataSet.setDrawFilled(true);
            lineDataSet.setFormLineWidth(1f);
            lineDataSet.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            lineDataSet.setFormSize(15.f);

            if (Utils.getSDKInt() >= 18) {
                // fill drawable only supported on api level 18 and above
                Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_red);
                lineDataSet.setFillDrawable(drawable);
            } else {
                lineDataSet.setFillColor(Color.WHITE);
            }

            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(lineDataSet); // add the datasets

            // create a data object with the datasets
            LineData data = new LineData(dataSets);

            // set data
            mLineChart.setData(data);
        }
    }

    private void setData(int count, float range) {

        ArrayList<Entry> values = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {

            float val = (float) (Math.random() * range) + 3;
            values.add(new Entry(i, val, getResources().getDrawable(R.drawable.star)));
        }

        LineDataSet set1;

        if (mLineChart.getData() != null &&
                mLineChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mLineChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            mLineChart.getData().notifyDataChanged();
            mLineChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "DataSet 1");

            set1.setDrawIcons(false);

            // set the line to be drawn like this "- - - - - -"
            set1.enableDashedLine(10f, 5f, 0f);
            set1.enableDashedHighlightLine(10f, 5f, 0f);
            set1.setColor(Color.WHITE);
            set1.setCircleColor(Color.WHITE);
            set1.setValueTextColor(Color.WHITE);
            set1.setLineWidth(1f);
            set1.setCircleRadius(1.5f);
            set1.setDrawCircleHole(false);
            set1.setValueTextSize(9f);
            set1.setDrawFilled(true);
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            if (Utils.getSDKInt() >= 18) {
                // fill drawable only supported on api level 18 and above
                Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_red);
                set1.setFillDrawable(drawable);
            } else {
                set1.setFillColor(Color.WHITE);
            }

            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(set1); // add the datasets

            // create a data object with the datasets
            LineData data = new LineData(dataSets);

            // set data
            mLineChart.setData(data);
        }
    }


    @Override
    public Context getContext() {
        return this.mContext;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onChartGestureStart(MotionEvent motionEvent, ChartTouchListener.ChartGesture chartGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent motionEvent, ChartTouchListener.ChartGesture chartGesture) {

    }

    @Override
    public void onChartLongPressed(MotionEvent motionEvent) {

    }

    @Override
    public void onChartDoubleTapped(MotionEvent motionEvent) {

    }

    @Override
    public void onChartSingleTapped(MotionEvent motionEvent) {

    }

    @Override
    public void onChartFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {

    }

    @Override
    public void onChartScale(MotionEvent motionEvent, float v, float v1) {

    }

    @Override
    public void onChartTranslate(MotionEvent motionEvent, float v, float v1) {

    }

    @Override
    public void onValueSelected(Entry entry, Highlight highlight) {

    }

    @Override
    public void onNothingSelected() {

    }
}
