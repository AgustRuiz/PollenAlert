package es.agustruiz.pollenalert.presenter;

import es.agustruiz.pollenalert.domain.pollencheck.forecast.ForecastDailyFacade;

public interface Presenter {

    void updateViewForecast(ForecastDailyFacade forecast);

    void errorUpdateViewForecast(String messageError);
}
