package com.madchen.tomatotime.chart;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Created by chenwei on 2017/5/10.
 */

public class TomatoXAxisValueFormatter implements IAxisValueFormatter {

    private String[] mValues;

    public TomatoXAxisValueFormatter(String[] values) {
        mValues = values;
    }

    @Override
    public String getFormattedValue(float v, AxisBase axisBase) {
        return mValues[(int)v];
    }
}
