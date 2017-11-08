package com.example.morozione.testreminder;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by morozione on 10/20/17.
 */

public class PreferenceHelper {
    public static final String SPLASH_IN_VISIBLE = "splash_in_visible";

    private static PreferenceHelper instance;
    private SharedPreferences sharedPreferences;

    private Context context;

    private PreferenceHelper() {
    }

    public static PreferenceHelper getInstance() {
        if (instance == null) {
            instance = new PreferenceHelper();
        }
        return instance;
    }

    public void init(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
    }

    public void putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, true);
    }
}
