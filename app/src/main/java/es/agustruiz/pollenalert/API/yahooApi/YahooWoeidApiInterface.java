package es.agustruiz.pollenalert.api.yahooApi;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface YahooWoeidApiInterface {

    String YAHOO_BASE_URL = "???";

    @GET("/{locationString}")
    void getWoeid(@Path("locationString") String locationString, Callback<String> response);
}
