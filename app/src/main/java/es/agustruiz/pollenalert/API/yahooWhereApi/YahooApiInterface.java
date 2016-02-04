package es.agustruiz.pollenalert.api.yahooWhereApi;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface YahooApiInterface {

    String YAHOO_BASE_URL = "http://where.yahooapis.com/v1/";

    @GET("/{locationString}")
    void getWoeid(@Path("locationString") String locationString, Callback<String> response);
}
