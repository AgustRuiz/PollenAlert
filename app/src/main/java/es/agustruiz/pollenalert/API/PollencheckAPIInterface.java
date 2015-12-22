package es.agustruiz.pollenalert.api;

import es.agustruiz.pollenalert.model.ForecastDailyFacade;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface PollencheckApiInterface {

    String POLLENCHECK_BASE_URL = "https://pollencheck.p.mashape.com/api/1/forecasts";

    @GET("/{woeid}") // woeid=766273
    void getTestForecast(@Path("woeid") String woeid, Callback<ForecastDailyFacade> response);
}
