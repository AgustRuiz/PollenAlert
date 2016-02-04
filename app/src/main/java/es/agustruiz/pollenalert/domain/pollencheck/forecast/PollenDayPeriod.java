package es.agustruiz.pollenalert.domain.pollencheck.forecast;

import java.io.Serializable;

/**
 * Pollen day period. From http://www.pollencheck.eu/docs/#!/forecasts
 */
public class PollenDayPeriod implements Serializable {
    /**
     * Average count of pollen grains in this period
     */
    Float avgCounter;

    /**
     * ['very_low' or 'low' or 'medium' or 'high']: Average level of pollen from count of grains and
     * allergenic power of pollen
     */
    String avgLevel;

    /**
     * Minimum count of pollen grains in this period
     */
    Float minCounter;

    /**
     * ['very_low' or 'low' or 'medium' or 'high']: Minimum level of pollen from count of grains
     * and allergenic power of pollen
     */
    String minLevel;

    /**
     * Maximum count of pollen grains in this period
     */
    Float maxCounter;

    /**
     * ['very_low' or 'low' or 'medium' or 'high']: Maximum level of pollen from count of grains and
     * allergenic power of pollen
     */
    String maxLevel;

    /**
     * Constructor
     *
     * @param avgCounter
     * @param avgLevel
     * @param minCounter
     * @param minLevel
     * @param maxCounter
     * @param maxLevel
     */
    public PollenDayPeriod(float avgCounter, String avgLevel, float minCounter, String minLevel, float maxCounter, String maxLevel) {
        this.avgCounter = avgCounter;
        this.avgLevel = avgLevel;
        this.minCounter = minCounter;
        this.minLevel = minLevel;
        this.maxCounter = maxCounter;
        this.maxLevel = maxLevel;
    }

    /**
     * Average counter getter
     *
     * @return Average counter
     */
    public Float getAvgCounter() {
        return avgCounter;
    }

    /**
     * Average counter setter
     *
     * @param avgCounter Average counter
     */
    public void setAvgCounter(Float avgCounter) {
        this.avgCounter = avgCounter;
    }

    /**
     * Average level getter
     *
     * @return Average level
     */
    public String getAvgLevel() {
        return avgLevel;
    }

    /**
     * Average level setter
     *
     * @param avgLevel Average level
     */
    public void setAvgLevel(String avgLevel) {
        this.avgLevel = avgLevel;
    }

    /**
     * Minimun counter getter
     *
     * @return Minimun counter
     */
    public Float getMinCounter() {
        return minCounter;
    }

    /**
     * Minimun counter setter
     *
     * @param minCounter Minimun counter
     */
    public void setMinCounter(Float minCounter) {
        this.minCounter = minCounter;
    }

    /**
     * Minimun level getter
     *
     * @return Minimun level
     */
    public String getMinLevel() {
        return minLevel;
    }

    /**
     * Minimun level setter
     *
     * @param minLevel Minimun level
     */
    public void setMinLevel(String minLevel) {
        this.minLevel = minLevel;
    }

    /**
     * Maximun counter getter
     *
     * @return Maximun counter
     */
    public Float getMaxCounter() {
        return maxCounter;
    }

    /**
     * Maximun counter setter
     *
     * @param maxCounter Maximun counter
     */
    public void setMaxCounter(Float maxCounter) {
        this.maxCounter = maxCounter;
    }

    /**
     * Maximun level getter
     *
     * @return Maximun level
     */
    public String getMaxLevel() {
        return maxLevel;
    }

    /**
     * Maximun level setter
     *
     * @param maxLevel Maximun level
     */
    public void setMaxLevel(String maxLevel) {
        this.maxLevel = maxLevel;
    }
}
