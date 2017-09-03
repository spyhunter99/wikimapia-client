package com.github.spyhunter99.wikimapia_client;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by thomas on 9/3/17.
 */

public class WikiMapiaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
