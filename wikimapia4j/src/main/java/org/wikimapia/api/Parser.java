package org.wikimapia.api;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * created on 1/11/2017.
 *
 * @author Alex O'Ree
 */

public class Parser {

    private Gson mGson;

    public Parser() {
        this.mGson = new Gson();
    }

    public Categories parseCategoryResults(String is) {
        return mGson.fromJson(is, Categories.class);
    }

    public Categories parseCategoryResults(InputStream is) {
        return mGson.fromJson(new InputStreamReader(is), Categories.class);
    }

    public SearchResults parseResults(InputStream is) {
        return mGson.fromJson(new InputStreamReader(is), SearchResults.class);
    }

    public SearchResults parseResults(String is) {
        return mGson.fromJson(is, SearchResults.class);
    }

}
