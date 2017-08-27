package org.wikimapia.api;

/**
 * created on 1/11/2017.
 *
 * @author Alex O'Ree
 */

public class Tag {
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    String title;
}
