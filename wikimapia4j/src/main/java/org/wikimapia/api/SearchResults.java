package org.wikimapia.api;

import java.util.ArrayList;
import java.util.List;

/**
 * created on 1/11/2017.
 *
 * @author Alex O'Ree
 */

public class SearchResults {
    String language;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public long getFound() {
        return found;
    }

    public void setFound(long found) {
        this.found = found;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    long found;
    List<Place> places = new ArrayList<>();
    int page,count;
}
