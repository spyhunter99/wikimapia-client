package org.wikimapia.api;

import java.util.ArrayList;
import java.util.List;

/**
 * created on 1/10/2017.
 *
 * @author Alex O'Ree
 */

public class Categories {

    List<Category> categories = new ArrayList<>();

    public void addAll(Categories categories) {
        this.categories.addAll(categories.getCategories());
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public int size() {
        return this.categories.size();
    }
}
