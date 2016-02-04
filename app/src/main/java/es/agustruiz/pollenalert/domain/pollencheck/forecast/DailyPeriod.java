package es.agustruiz.pollenalert.domain.pollencheck.forecast;

import java.io.Serializable;

/**
 * Daily period. From http://www.pollencheck.eu/docs/#!/forecasts
 */
public class DailyPeriod implements Serializable {
    /**
     * Initial time of this forecast period (day)
     */
    String time;

    /**
     * Initial time of this period as unix timestamp (milliseconds from 1/1/1970)
     */
    long timestamp;

    /**
     * RESERVED
     */
    String day;

    /**
     * RESERVED
     */
    String link;

    /**
     * Average sum of all pollens along this day and level of pollen calculated from counts
     */
    PollenDayPeriod combined;

    /**
     * Average counts of olive pollen along this day and level of pollen calculated from counts
     */
    PollenDayPeriod olive;

    /**
     * Average counts of grass pollen along this day and level of pollen calculated from counts
     */
    PollenDayPeriod grass;

    /**
     * Average counts of birch pollen along this day and level of pollen calculated from counts
     */
    PollenDayPeriod birch;

    /**
     * Average counts of ragweed pollen along this day and level of pollen calculated from counts
     */
    PollenDayPeriod ragweed;

    /**
     * Constructor
     */
    public DailyPeriod() {
    }

    /**
     * Time getter
     *
     * @return Time
     */
    public String getTime() {
        return time;
    }

    /**
     * Time setter
     *
     * @param time Time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Timestamp getter
     *
     * @return Timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Timestamp setter
     *
     * @param timestamp Timestamp
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Day getter
     *
     * @return Day
     */
    public String getDay() {
        return day;
    }

    /**
     * Day setter
     *
     * @param day Day
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * Link getter
     *
     * @return Link
     */
    public String getLink() {
        return link;
    }

    /**
     * Link setter
     *
     * @param link Link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * Combined getter
     *
     * @return Combined pollen day period
     */
    public PollenDayPeriod getCombined() {
        return combined;
    }

    /**
     * Combined setter
     *
     * @param combined Combined pollen day period
     */
    public void setCombined(PollenDayPeriod combined) {
        this.combined = combined;
    }

    /**
     * Olive getter
     *
     * @return Olive pollen day period
     */
    public PollenDayPeriod getOlive() {
        return olive;
    }

    /**
     * Olive setter
     *
     * @param olive Olive pollen day period
     */
    public void setOlive(PollenDayPeriod olive) {
        this.olive = olive;
    }

    /**
     * Grass getter
     *
     * @return Grass pollen day period
     */
    public PollenDayPeriod getGrass() {
        return grass;
    }

    /**
     * Grass setter
     *
     * @param grass Grass pollen day period
     */
    public void setGrass(PollenDayPeriod grass) {
        this.grass = grass;
    }

    /**
     * Birch getter
     *
     * @return Birch pollen day period
     */
    public PollenDayPeriod getBirch() {
        return birch;
    }

    /**
     * Birch setter
     *
     * @param ragweed Birch pollen day period
     */
    public void setBirch(PollenDayPeriod birch) {
        this.birch = birch;
    }

    /**
     * Ragweed getter
     *
     * @return Ragweed pollen day period
     */
    public PollenDayPeriod getRagweed() {
        return ragweed;
    }

    /**
     * Ragweed setter
     *
     * @param ragweed Ragweed pollen day period
     */
    public void setRagweed(PollenDayPeriod ragweed) {
        this.ragweed = ragweed;
    }
}
