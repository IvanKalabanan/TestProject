package com.ivacompany.test;

import android.app.Application;
import android.content.Context;

/**
 * Created by root on 27.12.16.
 */

public class TestApp extends Application {


    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }


    public static Context getAppContext() {
        return sContext;
    }
}