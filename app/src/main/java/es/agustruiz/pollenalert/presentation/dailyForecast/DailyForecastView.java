package es.agustruiz.pollenalert.presentation.dailyForecast;

import java.util.List;

import es.agustruiz.pollenalert.domain.model.PollenDayPeriod;

public interface DailyForecastView {

    // TODO: Remove after testing
    void updateForecast();

    void updateForecast(List<PollenDayPeriod> pollenForecastList);

    void showRefreshForecastError();

    void showLoadForecastError();

    void showLoading();

    void hideLoading();

}
