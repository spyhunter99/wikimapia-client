package org.wikimapia.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thomas on 9/17/17.
 */

public class LatLon {
    @SerializedName("x")
    public double lat;
    @SerializedName("y")
    public double lon;

    @Override
    public String toString() {
        return "LatLon{" +
                "lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}
