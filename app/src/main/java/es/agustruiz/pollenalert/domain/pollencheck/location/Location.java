package es.agustruiz.pollenalert.domain.pollencheck.location;

import java.io.Serializable;

import es.agustruiz.pollenalert.ui.adapter.LocationAdapter;

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
     * Location regio
     */
    String region;

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
     * INTERNAL
     * Tag for location icon
     */
    int icon = LocationAdapter.ICON_PLACE;

    /**
     * INTERNAL
     * Tag for geoposition location
     */
    boolean geoposition = false;

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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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

    public int getIcon(){
        return icon;
    }

    public void setIcon(int icon){
        this.icon = icon;
    }

    public boolean isGeoposition(){
        return geoposition;
    }

    public void setGeoposition(boolean geoposition){
        this.geoposition = geoposition;
    }
}
