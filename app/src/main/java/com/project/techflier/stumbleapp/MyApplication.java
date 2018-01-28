package com.project.techflier.stumbleapp;

import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by USER on 19-12-2017.
 */

public class MyApplication  extends MultiDexApplication {
    private RefWatcher mRefWatcher;

    static {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
    }

    public static RefWatcher getRefWatcher(Context context) {
        return ((MyApplication) context.getApplicationContext()).mRefWatcher;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        mRefWatcher = LeakCanary.install(this);
    }
}
