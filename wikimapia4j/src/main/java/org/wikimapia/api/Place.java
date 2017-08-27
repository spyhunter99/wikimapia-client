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
    String urlhtml;
    String title;
    List<Tag> tags = new ArrayList<>();

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(int language_id) {
        this.language_id = language_id;
    }

    public String getLanguage_iso() {
        return language_iso;
    }

    public void setLanguage_iso(String language_iso) {
        this.language_iso = language_iso;
    }

    public String getUrlhtml() {
        return urlhtml;
    }

    public void setUrlhtml(String urlhtml) {
        this.urlhtml = urlhtml;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    Location location;
}
