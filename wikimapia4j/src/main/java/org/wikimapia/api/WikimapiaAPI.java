
package org.wikimapia.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

/**
 * https://raw.githubusercontent.com/Sannis/wikimapia-api-clients/master/java/WikimapiaAPI.java
 * Wikimapia API Java Client Class
 *
 * @author Wikimapia API Stuff
 * @version 0.1
 * @package Wikimapia API
 * @tutorial http://wikimapia.org/api/
 */
public class WikimapiaAPI {

    Parser m_Parser = new Parser();

    public enum Format {
        XML("xml"),
        JSON("json"),
        KML("kml"),
        BINARY("binary");

        public final String key;

        Format(String format) {
            key = format;
        }
    }

    /**
     * Wikimapia API URL
     * Do not change this!
     */
    protected String m_Url = "http://api.wikimapia.org";

    /**
     * This is your Wikimapia API Key.
     * <p>
     * If you do not have the key, you can not use the API.
     * You can get it for free by registering on wikimapia.org,
     * and easily create a key on this page:
     * http://wikimapia.org/api/
     */
    protected String m_Key;

    /**
     * Wikimapia Output format
     * For now we support: xml, json, kml, binary
     * Default: xml
     */
    protected Format m_Format = Format.XML;

    /**
     * Packing output data parameter
     * For now we support: gz, none
     * Default: none
     */
    protected String m_Packing = "none";

    /**
     * Output data language
     */
    protected String m_Language = "en";

    /**
     * Object constructor
     *
     * @param string $apiKey You wikimapia API key
     * @param string $format Output format
     */
    public WikimapiaAPI(String apiKey) {
        m_Key = apiKey;
    }


    /**
     *
     * @param apiKey
     * @param format
     */
    public WikimapiaAPI(String apiKey, Format format) {
        m_Key = apiKey;
        m_Format = format;
    }

    /**
     * Set API output data format
     *
     * @param string $format
     * @return boolean
     * @tutorial http://wikimapia.org/api
     */
    public void setFormat(Format format) {
        m_Format = format;
    }

    /**
     * Get selected output data format
     *
     * @return string
     */
    public Format getFormat() {
        return m_Format;
    }

    /**
     * Set API output data packing
     *
     * @param string $pack
     * @return boolean
     * @tutorial http://wikimapia.org/api
     */
    public void setPacking(String packing) {
        m_Packing = packing;
    }

    /**
     * Get selected packing
     *
     * @return string
     */
    public String getPacking() {
        return m_Packing;
    }

    /**
     * Get output data language
     *
     * @return string
     */
    public String getLanguage() {
        return m_Language;
    }

    public void setLanguage(String language) {
        m_Language = language;
    }

    /**
     * Function to get Object data by its ID
     *
     * @param int    $objectId   Object identifier
     * @param string $language   Output language (default `en` - English)
     * @return string   Output data in selected format (see setFormat())
     */
    public String getObjectById(int objectId) {
        return doSendApiRequest("object", "&id=" + objectId);
    }

    /**
     * This is a alias of getObjectsInBoxByLatLon
     *
     * @return string
     */
    public String getObjectInBox(float lon_min, float lat_min, float lon_max, float lat_max) {
        return getObjectsInBoxByLatLon(lon_min, lat_min, lon_max, lat_max);
    }

    /**
     * Get objects in box by latitude and longitude
     *
     * @param float $lon_min Minimum longitude
     * @param float $lat_min Minimum latitude
     * @param float $lon_max Maximum longitude
     * @param float $lat_max Maximum latitude
     * @return string
     */
    public String getObjectsInBoxByLatLon(float lon_min, float lat_min, float lon_max, float lat_max) {
        return doSendApiRequest("box", "lon_min=" + lon_min + "&lat_min=" + lat_min + "&lon_max=" + lon_max + "&lat_max=" + lat_max);
    }

    /**
     * Get objects in box by latitude and longitude separated by comma
     *
     * @param string $bbox -> "$lon_min,$lat_min,$lon_max,$lat_max"
     * @return string
     */
    public String getObjectsInBoxByBBox(String bbox) {
        return doSendApiRequest("box", "bbox=" + bbox);
    }

    /**
     * Get objects in box by tile coordinates
     *
     * @param int $x
     * @param int $y
     * @param int $z
     * @param int $count
     * @return string
     */
    public String getObjectsInBoxByTile(int x, int y, int z, int count) {
        return doSendApiRequest("box", "x=" + x + "&y=" + y + "&z=" + z + "&count=" + count);
    }

    /**
     * Get objects in box by tile coordinates
     *
     * @param int $x
     * @param int $y
     * @param int $z
     * @return string
     */
    public String getObjectsInBoxByTile(int x, int y, int z) {
        return getObjectsInBoxByTile(x, y, x, 50);
    }

    /**
     * Get objects that intersects with point
     *
     * @param float $x
     * @param float $y
     * @return string
     */
    public String getObjectsInPoint(float x, float y) {
        return doSendApiRequest("point", "x=" + x + "&y=" + y);
    }

    /**
     * Get objects that determines search query
     *
     * @param string $query
     * @param int    $page
     * @return string
     */
    public SearchResults getObjectsBySearchQuery(String query, double lat, double lon, int page, int pageSize) {
        String result = doSendApiRequest("search", "q=" + query + "&lat=" + lat + "&lon=" + lon +
                "&page=" + page + "&count=" + pageSize);
        return m_Parser.parseResults(result);
    }

    /**
     * Get objects that determines search query
     *
     * @param string $query
     * @return string
     */
//    public SearchResults getObjectsBySearchQuery(String query) {
//        return getObjectsBySearchQuery(query, 1);
//    }

    /**
     * Send request to api
     *
     * @param string $function
     * @param string $args
     * @return string
     */
    public String doSendApiRequest(String function, String args) {
        // if you don't have a key, create it on http://wikimapia.org/api/
        if (m_Key == null) {
            return null;
        }

        String str = "";
        try {
            // Create a URL for the desired page
            URL url = new URL(m_Url + "/?function=" + function + "&key=" + m_Key + "&format=" + m_Format.key
                + "&pack=" + m_Packing + "&language=" + m_Language + "&" + args);

            // Read all the text returned by the server
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String buffer;
            while ((buffer = in.readLine()) != null) {
                str += buffer;
            }

            in.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return str;
    }

    public interface OnCategoryResult {
        void onCategoryResult(Categories results);
    }

    /**
     * since every request counts against the rate limit, save these results and use them later
     *
     * @param callback
     */
    public void getAllCategories(OnCategoryResult callback) {
        try {
            int count;
            int page = 1;
            Categories result = new Categories();
            do {
//                URL url = new URL(m_Url + "/?function=category.getall&key=" + m_Key + "&format=json&page=" + page++ + "&count=100");
                Categories categories = getCategories(page++, 100);
                count = categories.getCategories().size();
                result.addAll(categories);
            } while (count > 0);
            callback.onCategoryResult(result);
        } catch (Exception e) {
            callback.onCategoryResult(new Categories());
        }
    }

    /**
     * pages must start at 1
     *
     * @param page
     * @param pageSize
     * @return
     */
    public Categories getCategories(int page, int pageSize) {
        //http://api.wikimapia.org/?function=category.getall&key=  &format=json&page= & count=
        Categories result = new Categories();
        try {
            String reply = doSendApiRequest("category.getall",
                    String.format("page=%d&count=%d", page, pageSize));
            Categories categories = m_Parser.parseCategoryResults(reply);
            result.addAll(categories);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return result;
    }

    public SearchResults findByArea(double latNorth, double latSouth, double longEast, double longWest, List<Category> cats) {
        StringBuilder cateogires = new StringBuilder();

        for (Category c: cats) {
            cateogires.append(c.getId()).append(",");
        }
        cateogires.setLength(cateogires.length()-1);

        URL url = null;
        InputStream is = null;
        try {
         url = new URL(m_Url + "/?function=place.getbyarea&key=" + m_Key + "&format=json&page=" + 1 + "&count=" + 100 + "&lon_min=" + longWest + "&lat_min=" + latSouth +"&lon_max=" + longEast+ "&lat_max=" + latNorth + "&categories=" + cateogires.toString());
            System.out.println("fetching " + url.toString());
            is = url.openStream();
            SearchResults searchResults = m_Parser.parseResults(is);
           return searchResults;


        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            if (is != null) try {
                is.close();
            } catch (Exception ex) {
            }
        }
        return null;
    }
}
