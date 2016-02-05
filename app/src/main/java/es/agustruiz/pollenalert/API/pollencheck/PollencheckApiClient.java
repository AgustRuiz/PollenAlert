package es.agustruiz.pollenalert.api.pollencheck;

import es.agustruiz.pollenalert.BuildConfig;
import es.agustruiz.pollenalert.domain.pollencheck.forecast.ForecastDailyFacade;
import es.agustruiz.pollenalert.presenter.Presenter;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PollencheckApiClient {
    public static String LOG_TAG = PollencheckApiClient.class.getName();
    private static PollencheckApiInterface service = null;
    private static final String mashapeApiKey = BuildConfig.MASHAPE_API_KEY;

    public static void GetPollenForecast(String woeid, final Presenter presenter) {
        PrepareService();
        service.getDailyForecast(woeid, new Callback<ForecastDailyFacade>() {
            @Override
            public void success(ForecastDailyFacade s, Response response) {
                presenter.updateViewForecast(s);
            }

            @Override
            public void failure(RetrofitError error) {
                // TODO manage different error types
                presenter.errorUpdateViewForecast(error.getMessage());
            }
        });
    }

    private static void PrepareService() {
        if(service == null){
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
            service = restAdapter.create(PollencheckApiInterface.class);
        }
    }
}
