package com.giumig.apps.lastminutequiz;

import android.app.Application;
import android.util.Log;

/**
 * Created by gius on 13/12/16.
 */

public class LastMinuteApplication extends Application {

    private static LastMinuteApplication instance;
    private String TAG;

    @Override
    public void onCreate() {
        super.onCreate();

        initApplication();

    }

    public static LastMinuteApplication getInstance()   {
        return instance;
    }


    private void initApplication()  {
        instance = this;

        TAG = getClass().getSimpleName();
        Log.d(TAG, "application initialization");


    }





}
