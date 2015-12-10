package es.agustruiz.pollenalert.Models.Pollencheck;

import java.util.ArrayList;
import java.util.List;

/**
 * Forecast daily facade. From http://www.pollencheck.eu/docs/#!/forecasts
 */
public class ForecastDailyFacade {
    /**
     * Where On Earth ID (WOEID) as defined by Yahoo
     * [https://developer.yahoo.com/geo/geoplanet/guide/concepts.html#woeids]
     */
    String woeid;

    /**
     * Creation time of this forecast
     */
    String creationTime;

    /**
     * Location
     */
    LocationFacade location;

    /**
     * ['hourly' or 'daily']: Time interval for this forecast
     */
    String interval;

    /**
     * RESERVED
     */
    Boolean active;

    /**
     * You get a Period for every single day with forecast (4 days)
     */
    List<DailyPeriod> periods;

    /**
     * Constructor
     */
    public ForecastDailyFacade() {
        periods = new ArrayList<>();
    }

    /**
     * WOEID getter
     * @return WOEID
     */
    public String getWoeid() {
        return woeid;
    }

    /**
     * WOEID setter
     * @param woeid WOEID
     */
    public void setWoeid(String woeid) {
        this.woeid = woeid;
    }

    /**
     * Creation time getter
     * @return Creation time
     */
    public String getCreationTime() {
        return creationTime;
    }

    /**
     * Creation time setter
     * @param creationTime Creation time
     */
    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    /**
     * Location getter
     * @return Location
     */
    public LocationFacade getLocation() {
        return location;
    }

    /**
     * Location setter
     * @param location Location
     */
    public void setLocation(LocationFacade location) {
        this.location = location;
    }

    /**
     * Interval getter
     * @return Interval
     */
    public String getInterval() {
        return interval;
    }

    /**
     * Interval setter
     * @param interval Interval
     */
    public void setInterval(String interval) {
        this.interval = interval;
    }

    /**
     * Active getter
     * @return Active variable
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * Active setter
     * @param active Active variable
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * Periods getter
     * @return List of periods (DailyPeriod)
     */
    public List<DailyPeriod> getPeriods() {
        return periods;
    }

    /**
     * Periods setter
     * @param periods List of Periods (DailyPeriod)
     */
    public void setPeriods(List<DailyPeriod> periods) {
        this.periods = periods;
    }
}
