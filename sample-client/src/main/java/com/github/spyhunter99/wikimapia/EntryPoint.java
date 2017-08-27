package com.github.spyhunter99.wikimapia;

import org.wikimapia.api.Categories;
import org.wikimapia.api.Category;
import org.wikimapia.api.Parser;
import org.wikimapia.api.WikimapiaAPI;

import java.util.List;

public class EntryPoint {

    public static void main(String[] args)throws Exception {

        String apiKey="YOUR KEY";

        WikimapiaAPI client = new WikimapiaAPI(apiKey);

        List<Category> categories = null;
        final int COUNT=100;
        int offset=1;
        categories = client.getCategories(offset, COUNT);
        //print them out
        print(categories);
        offset += COUNT;
        while (categories.size() > 0) {
            categories = client.getCategories(offset, COUNT);
            //print them out
            print(categories);
        }





    }

    private static void print(List<Category> categories) {
        for (Category c: categories) {
            System.out.println(c.getId() + ":" + c.getName() + ":" + c.getCount());
        }
    }
}


