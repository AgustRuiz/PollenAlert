package es.agustruiz.pollenalert.api.yahooApi;

import android.widget.Toast;

import java.util.List;

import es.agustruiz.pollenalert.BuildConfig;
import es.agustruiz.pollenalert.domain.model.ForecastDailyFacade;
import es.agustruiz.pollenalert.presenter.Presenter;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class YahooApiClient {
    public static String LOG_TAG = YahooApiClient.class.getName();
    private static final String yahooApiKey = BuildConfig.YAHOO_API_KEY;



    public static List<ForecastDailyFacade> getWoeid(String locationString, final Presenter presenter) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(YahooWoeidApiInterface.YAHOO_BASE_URL)
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {

                        request.addHeader("Accept", "application/json")
                        request.addHeader("Authorization",
                                token.getTokenType() + " " + token.getAccessToken());



                        request.addHeader("X-Mashape-Key", yahooApiKey);
                        request.addHeader("Accept", "application/json");
                    }
                })
                .build();

        YahooWoeidApiInterface service = restAdapter.create(YahooWoeidApiInterface.class);

        service.getWoeid(locationString, new Callback<String>() {

            @Override
            public void success(String s, Response response) {
                Toast
            }

            @Override
            public void failure(RetrofitError error) {
                //presenter.errorUpdateViewForecast(error.getMessage());
                Toast.makeText()
            }
        });
        return null;
    }
}
