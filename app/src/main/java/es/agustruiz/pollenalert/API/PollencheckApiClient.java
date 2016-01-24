package es.agustruiz.pollenalert.api;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import es.agustruiz.pollenalert.BuildConfig;
import es.agustruiz.pollenalert.domain.model.DailyPeriod;
import es.agustruiz.pollenalert.domain.model.ForecastDailyFacade;
import es.agustruiz.pollenalert.domain.model.PollenDayPeriod;
import es.agustruiz.pollenalert.presenter.DailyForecastPresenter;
import es.agustruiz.pollenalert.presenter.Presenter;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedInput;

public class PollencheckApiClient {
    public static String LOG_TAG = PollencheckApiClient.class.getName();
    private static final String mashapeApiKey = BuildConfig.MASHAPE_API_KEY;

    public static List<ForecastDailyFacade> GetPollenForecast(String woeid, final Presenter presenter) {
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

        PollencheckApiInterface service = restAdapter.create(PollencheckApiInterface.class);

        service.getTestForecast(woeid, new Callback<ForecastDailyFacade>() {
            @Override
            public void success(ForecastDailyFacade s, Response response) {
                TypedInput body = response.getBody();
                presenter.updateViewForecast(s);
            }

            @Override
            public void failure(RetrofitError error) {
                presenter.errorUpdateViewForecast(error.getMessage());
            }
        });

        return null;
    }
}
