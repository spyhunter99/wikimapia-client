package com.github.spyhunter99.wikimapia_client;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.wikimapia.api.Categories;
import org.wikimapia.api.Place;
import org.wikimapia.api.SearchResults;
import org.wikimapia.api.WikimapiaAPI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import timber.log.Timber;

public class MainActivity extends Activity implements WikimapiaAPI.OnCategoryResult {

    private static final int PERMISSION_REQUEST_READ_EXTERNAL_STORAGE = 42;

    // API key is loaded from the first line of this file
    private static final String API_KEY_PATH = Environment.getExternalStorageDirectory().getPath() + "/wikimapia_api_key";

    private WikimapiaAPI mApi;
    private TextView mResultsView;
    private EditText mSearchQueryEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mResultsView = findViewById(R.id.results);
        mSearchQueryEditText = findViewById(R.id.search_query);
        mSearchQueryEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (!requestSdCardPermssion()) {
                        Toast.makeText(MainActivity.this,
                                "Please give permission to read api key from sdcard", Toast.LENGTH_LONG).show();
                    } else {
                        String text = mSearchQueryEditText.getText().toString();
                        doRequest(text);
                    }
                    hideSoftKeyboard();
                    return true;
                }
                return false;            }
        });
        requestSdCardPermssion();
    }

    @Override
    public void onCategoryResult(Categories results) {
        Timber.d("num categories %d", results.size());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0
                        && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Please give permission to read api key from sdcard", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void doRequest(final String query) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String apiKey = loadApiKeyFromSdCard();
                    mApi = new WikimapiaAPI(apiKey, WikimapiaAPI.Format.JSON);
                    SearchResults results = mApi.getObjectsBySearchQuery(query, 48.864716, 2.349014, 1, 10);
                    Timber.d(results.toString());
                    final StringBuilder builder = new StringBuilder();
                    for (Place p : results.getPlaces()) {
                        builder.append(p.name).append("\n");
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mResultsView.setText(builder.toString());
                        }
                    });
                } catch (IOException e) {
                    Timber.e(e);
                }
            }
        }).start();
    }

    private boolean requestSdCardPermssion() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        ActivityCompat.requestPermissions(this,
                new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE },
                PERMISSION_REQUEST_READ_EXTERNAL_STORAGE);
        return false;
    }

    private String loadApiKeyFromSdCard() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(API_KEY_PATH));
        return reader.readLine();
    }

    private void hideSoftKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.toggleSoftInput(0, 0);
    }
}
