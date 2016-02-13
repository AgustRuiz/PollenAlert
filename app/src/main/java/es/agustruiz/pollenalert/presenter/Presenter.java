package es.agustruiz.pollenalert.presenter;

import android.content.Context;

import java.util.List;

import es.agustruiz.pollenalert.domain.pollencheck.forecast.ForecastDailyFacade;
import es.agustruiz.pollenalert.domain.pollencheck.location.Location;

public interface Presenter {

    void updateForecast(Context context);

    void updateViewForecast(ForecastDailyFacade forecast);

    void errorUpdateViewForecast(String messageError);

    void queryLocations(String query);

    void updateViewLocations(List<Location> locations);

    void getLocationWoeid(Context context);
}
