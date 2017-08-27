package com.github.spyhunter99.wikimapia;

import org.wikimapia.api.Categories;
import org.wikimapia.api.Category;
import org.wikimapia.api.Parser;
import org.wikimapia.api.Place;
import org.wikimapia.api.SearchResults;
import org.wikimapia.api.WikimapiaAPI;

import java.util.ArrayList;
import java.util.List;

public class EntryPoint {

    public static void main(String[] args)throws Exception {

        String apiKey="YOUR KEY";

        WikimapiaAPI client = new WikimapiaAPI(apiKey);

        List<Category> food = new ArrayList<>();
        boolean foundFoodCategory = false;
        //get all the categories
        List<Category> categories = null;
        final int COUNT=100;
        int offset=1;
        categories = client.getCategories(offset, COUNT);
        //print them out
        for (Category c: categories) {
            print(c);
            if (c.getId()==74) {
                if (!foundFoodCategory) {
                    food.add(c);
                    foundFoodCategory=true;
                }
            }
        }
        offset += COUNT;
        while (categories.size() > 0) {
            categories = client.getCategories(offset, COUNT);
            //print them out

            for (Category c: categories) {
                print(c);
                if (c.getId()==74) {
                    if (!foundFoodCategory) {
                        food.add(c);
                        foundFoodCategory=true;
                    }
                }
            }

        }


        SearchResults searchResults  = client.findByArea(41, 40, -73, -75, food);
         for (Place place : searchResults.getPlaces()){
             System.out.println(place.getTitle() + " " + place.getLocation().getLat() + "," + place.getLocation().getLon());
         }


    }

    private static void print(Category c) {

            System.out.println(c.getId() + ":" + c.getName() + ":" + c.getCount());

    }
}


