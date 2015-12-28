package es.agustruiz.pollenalert.api;

import android.util.Log;

import es.agustruiz.pollenalert.BuildConfig;
import es.agustruiz.pollenalert.domain.model.ForecastDailyFacade;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PollencheckApi {
    public static String LOG_TAG = PollencheckApi.class.getName();
    private static final String mashapeApiKey = BuildConfig.MASHAPE_API_KEY;

    public static void GetPollenForecast(String woeid){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(PollencheckApiInterface.POLLENCHECK_BASE_URL)
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addHeader("X-Mashape-Key", mashapeApiKey);
                        request.addHeader("Accept", "application/json");
                    }
                })
                .build();

        PollencheckApiInterface pollencheckApi = restAdapter.create(PollencheckApiInterface.class);

        pollencheckApi.getTestForecast(woeid, new Callback<ForecastDailyFacade>() {
            @Override
            public void success(ForecastDailyFacade s, Response response) {
                Log.v(LOG_TAG, "AGUST_MSG: OK");
                Log.v(LOG_TAG, "AGUST_MSG (WOEID): " + s.getWoeid());
                Log.v(LOG_TAG, "AGUST_MSG (timestamp): " + s.getPeriods().get(0).getTimestamp());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(LOG_TAG, "AGUST_MSG (Message): " + error.getMessage());
            }
        });
    }
}
