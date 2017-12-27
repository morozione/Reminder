package com.example.morozione.testreminder;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;


public class Utils {

    public static String getDate(long time) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat tateFormat = new SimpleDateFormat("dd:MM:yy");
        return tateFormat.format(time);
    }

    public static String getTime(long time) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        return timeFormat.format(time);
    }

    public static String getFullDate(long  time) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat fullFormat = new SimpleDateFormat("dd:MM:yy  HH:mm");
        return fullFormat.format(time);
    }
}
