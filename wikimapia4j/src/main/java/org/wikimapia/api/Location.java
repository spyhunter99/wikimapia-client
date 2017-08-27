package org.wikimapia.api;

import java.util.ArrayList;
import java.util.List;

/**
 * created on 1/11/2017.
 *
 * @author Alex O'Ree
 */

public class Location {
    double lat,lon,north,south,east,west;
    String country,state,place;
    long country_adm_id;
    List<Gadm> gadm = new ArrayList<>();

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getNorth() {
        return north;
    }

    public void setNorth(double north) {
        this.north = north;
    }

    public double getSouth() {
        return south;
    }

    public void setSouth(double south) {
        this.south = south;
    }

    public double getEast() {
        return east;
    }

    public void setEast(double east) {
        this.east = east;
    }

    public double getWest() {
        return west;
    }

    public void setWest(double west) {
        this.west = west;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public long getCountry_adm_id() {
        return country_adm_id;
    }

    public void setCountry_adm_id(long country_adm_id) {
        this.country_adm_id = country_adm_id;
    }

    public List<Gadm> getGadm() {
        return gadm;
    }

    public void setGadm(List<Gadm> gadm) {
        this.gadm = gadm;
    }

    public List<Polygon> getPolygon() {
        return polygon;
    }

    public void setPolygon(List<Polygon> polygon) {
        this.polygon = polygon;
    }

    public long getCity_id() {
        return city_id;
    }

    public void setCity_id(long city_id) {
        this.city_id = city_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    List<Polygon> polygon = new ArrayList<>();
    long city_id;
    String city;
    int zoom;

}
