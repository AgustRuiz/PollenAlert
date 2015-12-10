package es.agustruiz.pollenalert.API;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;

public interface PollencheckAPIInterface {

    String POLLENCHECK_BASE_URL = "https://pollencheck.p.mashape.com/api/1/forecasts";

    @Headers({
            "X-Mashape-Key: PfqV3XsTTSmshpwsJAOX8iEGjQyZp1BEmqcjsngIkeKzacdUdw",
            "Accept: application/json"
    })
    @GET("/766273")
    void getTestForecast(Callback<String> response);
}
