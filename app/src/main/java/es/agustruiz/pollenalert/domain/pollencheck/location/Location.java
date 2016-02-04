package es.agustruiz.pollenalert.domain.pollencheck.location;

import java.io.Serializable;

public class Location implements Serializable {

    /**
     * Where On Earch ID (WOEID) as defined by Yahoo
     */
    String woeid;

    /**
     * Location name
     */
    String name;

    /**
     * Location country
     */
    String country;

    /**
     * Location time zone
     */
    String timeZone;

    /**
     * Location latitude
     */
    String latitude;

    /**
     * Location longitude
     */
    String longitude;

    /**
     * RESERVED
     */
    String priority;

    /**
     * RESERVED
     */
    Boolean active;

    /**
     * Constructor
     */
    public Location() {
    }

    public String getWoeid() {
        return woeid;
    }

    public void setWoeid(String woeid) {
        this.woeid = woeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
