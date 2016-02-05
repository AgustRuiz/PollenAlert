package es.agustruiz.pollenalert.api.pollencheck;

import java.util.List;

import es.agustruiz.pollenalert.domain.pollencheck.forecast.ForecastDailyFacade;
import es.agustruiz.pollenalert.domain.pollencheck.location.Location;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface PollencheckApiInterface {

    String POLLENCHECK_BASE_URL = "https://pollencheck.p.mashape.com/api/1/forecasts";

    @GET("/{woeid}")
    void getDailyForecast(
            @Path("woeid") String woeid,
            Callback<ForecastDailyFacade> response
    );

    // TODO Create Callback type
    @GET("/{name}")
    void getLocationByName(
            @Path("name") String name,
            Callback<List<Location>> response
    );
}
