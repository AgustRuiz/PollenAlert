package es.agustruiz.pollenalert.domain.yahooWhere;

public class LocationYahoo {

    /**
     * Woeid of location
     */
    String woeid;

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
     * Constructor
     */
    public LocationYahoo() {
    }

    /**
     * Woeid getter
     *
     * @return Woeid value
     */
    public String getWoeid() {
        return woeid;
    }

    /**
     * Woeid setter
     *
     * @param woeid Woeid value
     */
    public void setWoeid(String woeid) {
        this.woeid = woeid;
    }

    /**
     * Name getter
     *
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * Name setter
     *
     * @param name Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Region getter
     *
     * @return Region
     */
    public String getRegion() {
        return region;
    }

    /**
     * Region setter
     *
     * @param region Region
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Country getter
     *
     * @return Country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Country setter
     *
     * @param country Country
     */
    public void setCountry(String country) {
        this.country = country;
    }
}
