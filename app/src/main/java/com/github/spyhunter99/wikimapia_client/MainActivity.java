package com.github.spyhunter99.wikimapia_client;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;

import org.wikimapia.api.Categories;
import org.wikimapia.api.WikimapiaAPI;

import timber.log.Timber;

public class MainActivity extends Activity implements WikimapiaAPI.OnCategoryResult {

    private static final String API_KEY = "7ACE1615-C680DC41-4697D707-0EB6D272-97E7501E-11D67C05-7A0544FB-25B194DA";

    private WikimapiaAPI mApi;

    private Handler mApiHandler;
    private HandlerThread mApiHandlerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mApi = new WikimapiaAPI(API_KEY);
        mApiHandlerThread = new HandlerThread("ApiHandler");
        mApiHandlerThread.start();
        mApiHandler = new Handler(mApiHandlerThread.getLooper());
        findViewById(R.id.load_categories).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mApiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mApi.getAllCategories(MainActivity.this);
                    }
                });
            }
        });
    }

    @Override
    public void onCategoryResult(Categories results) {
        Timber.d("num categories %d", results.size());
    }
}
