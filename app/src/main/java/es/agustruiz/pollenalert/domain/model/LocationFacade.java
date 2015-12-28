package es.agustruiz.pollenalert.domain.model;


/**
 * Location facade. From http://www.pollencheck.eu/docs/#!/forecasts
 */
public class LocationFacade {
    /**
     * Name of the forecast location (city or town)
     */
    String name;

    /**
     * Region/State/Province of this forecast location
     */
    String region;

    /**
     * Country of this forecast location
     */
    String country;

    /**
     * Time zone of this forecast location as show in
     * [http://en.wikipedia.org/wiki/List_of_tz_database_time_zones]
     */
    String timeZone;

    /**
     * Constructor
     */
    public LocationFacade() {
    }

    /**
     * Name getter
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * Name setter
     * @param name Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Region getter
     * @return Region
     */
    public String getRegion() {
        return region;
    }

    /**
     * Region setter
     * @param region Region
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Country getter
     * @return Country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Country setter
     * @param country Country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Time zone getter
     * @return Time zone
     */
    public String getTimeZone() {
        return timeZone;
    }

    /**
     * Time zone setter
     * @param timeZone Time zone
     */
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
}
