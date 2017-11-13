package com.example.morozione.testreminder;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;


public class Utils {

    public static String getDAte(long time) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat tateFormat = new SimpleDateFormat("dd:MM:yy");
        return tateFormat.format(time);
    }

    public static String getTiem(long time) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        return timeFormat.format(time);
    }

}
