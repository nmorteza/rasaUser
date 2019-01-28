package com.rassa.rassauser.utils;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.provider.Settings;
import android.support.multidex.MultiDex;


/**
 * Created by AMiR Ehsan on 01/05/2017.
 */

public class RUserApp extends Application {

    private static  Context context;





    @Override
    public void onCreate() {
        context = getApplicationContext();

        super.onCreate();
    }

    public static Context getContext(){
        return context;
    }
    
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}