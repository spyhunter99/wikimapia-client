package org.wikimapia.api;

import java.util.ArrayList;
import java.util.List;

/**
 * created on 1/10/2017.
 *
 * @author Alex O'Ree
 */

public class Categories {
    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    List<Category> categories = new ArrayList<>();
}
