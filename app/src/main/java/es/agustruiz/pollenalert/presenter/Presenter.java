package es.agustruiz.pollenalert.presenter;

import es.agustruiz.pollenalert.domain.model.ForecastDailyFacade;

public interface Presenter {

    void updateViewForecast(ForecastDailyFacade forecast);

    void errorUpdateViewForecast(String messageError);
}
