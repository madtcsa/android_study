package com.madchen.tomatotime.mydata;

import com.madchen.tomatotime.base.BaseView;
import com.madchen.tomatotime.model.Tomato;

import java.util.List;

/**
 * Created by chenwei on 15/03/2017.
 */

public interface MyDataView extends BaseView {


    void showAllTomatoData(List<Tomato> tomatoes);
}
