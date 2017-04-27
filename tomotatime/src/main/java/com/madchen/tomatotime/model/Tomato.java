package com.madchen.tomatotime.model;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by chenwei on 15/03/2017.
 */

public class Tomato {

    private int interrupt = 0;
    private long totalTimeL;
    private int[] times = new int[2];
    private int totalMinutes;
    private long startTimeL;
//    private long surplusTimeL;

    public Tomato(int totalMinutes) {
        this(totalMinutes, 0);
    }

    public Tomato(int totalMinutes, int interrupt) {

        this.totalMinutes = totalMinutes;
        this.totalTimeL = totalMinutes * 60 * 1000L;
        this.times[0] = totalMinutes - 1;
        this.times[1] = 59;
        this.interrupt = interrupt;
        this.startTimeL = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(startTimeL);
    }

    public void addInterruptCount() {
        this.interrupt += 1;
    }

    public int getMinutes() {
        return times[0];
    }

    public void setMinutes(int minutes) {
        this.times[0] = minutes;
    }

    public int getSecond() {
        return times[1];
    }

    public void setSecond(int second) {
        times[1] = second;
    }

    public int getInterrupt() {
        return interrupt;
    }

    public void setInterrupt(int interrupt) {
        this.interrupt = interrupt;
    }

    public long getTotalTimeL() {
        return totalTimeL;
    }

    public void setTotalTimeL(long totalTimeL) {
        this.totalTimeL = totalTimeL;
    }

    public int[] getTimes() {
        return times;
    }

    public void setTimes(int[] times) {
        this.times = times;
    }

    public int getTotalMinutes() {
        return totalMinutes;
    }

    public void setTotalMinutes(int totalMinutes) {
        this.totalMinutes = totalMinutes;
    }

    public long getStartTimeL() {
        return startTimeL;
    }

    public void setStartTimeL(long startTimeL) {
        this.startTimeL = startTimeL;
    }

//    public long getSurplusTimeL() {
//        return surplusTimeL;
//    }
//
//    public void setSurplusTimeL(long surplusTimeL) {
//        this.surplusTimeL = surplusTimeL;
//    }

    @Override
    public String toString() {
        return "Tomato{" +
                "interrupt=" + interrupt +
                ", totalTimeL=" + totalTimeL +
                ", times=" + Arrays.toString(times) +
                ", totalMinutes=" + totalMinutes +
                ", startTimeL=" + startTimeL +
                '}';
    }
}
