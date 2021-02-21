package org.wikimapia.api;

import java.util.ArrayList;
import java.util.List;

/**
 * created on 1/11/2017.
 *
 * @author Alex O'Ree
 */

public class Place {
    long id;
    int language_id;
    String language_iso;
    String url;
    public String name;
    List<Tag> tags = new ArrayList<>();
    public Location location;
    double distance;
    List<LatLon> polygon;

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", language_id=" + language_id +
                ", language_iso='" + language_iso + '\'' +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", tags=" + tags +
                ", location=" + location +
                ", distance=" + distance +
                ", polygon=" + polygon +
                '}';
    }
}
