package es.agustruiz.pollenalert.api.pollencheck;

import es.agustruiz.pollenalert.domain.pollencheck.forecast.ForecastDailyFacade;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface PollencheckApiInterface {

    String POLLENCHECK_BASE_URL = "https://pollencheck.p.mashape.com/api/1/forecasts";

    @GET("/{woeid}")
        // woeid=766273
    void getDailyForecastByWoeid(
            @Path("woeid") String woeid,
            Callback<ForecastDailyFacade> response
    );

    // TODO Create Callback type
    @GET("/{name}")
        // woeid=766273
    void getLocationByName(
            @Path("name") String name,
            Callback<void> response
    );
}
