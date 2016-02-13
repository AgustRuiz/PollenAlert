package es.agustruiz.pollenalert.network.pollencheck;

import java.util.List;

import es.agustruiz.pollenalert.domain.pollencheck.forecast.ForecastDailyFacade;
import es.agustruiz.pollenalert.domain.pollencheck.location.Location;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface PollencheckApiInterface {

    String POLLENCHECK_BASE_URL = "https://pollencheck.p.mashape.com/api/1";

    @GET("/forecasts/{woeid}")
    void getDailyForecast(
            @Path("woeid") String woeid,
            Callback<ForecastDailyFacade> response
    );

    @GET("/locations")
    void getLocationByName(
            @Query("q") String query,
            Callback<List<Location>> response
    );
}
