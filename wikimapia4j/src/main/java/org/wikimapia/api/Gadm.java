package org.wikimapia.api;

/**
 * created on 1/11/2017.
 *
 * @author Alex O'Ree
 */

public class Gadm {
    long id,county,level,is_last_level;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCounty() {
        return county;
    }

    public void setCounty(long county) {
        this.county = county;
    }

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }

    public long getIs_last_level() {
        return is_last_level;
    }

    public void setIs_last_level(long is_last_level) {
        this.is_last_level = is_last_level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    String name, iso,type,translation;
}
