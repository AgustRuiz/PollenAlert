package es.agustruiz.pollenalert.network.pollencheck;

import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;

import java.util.List;

import es.agustruiz.pollenalert.BuildConfig;
import es.agustruiz.pollenalert.R;
import es.agustruiz.pollenalert.domain.pollencheck.forecast.ForecastDailyFacade;
import es.agustruiz.pollenalert.domain.pollencheck.location.Location;
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

    public static void GetPollenForecast(final Context context, final String woeid, final Presenter presenter) {
        PrepareService();
        // TODO get if woeid is default value
        service.getDailyForecast(woeid, new Callback<ForecastDailyFacade>() {
            @Override
            public void success(ForecastDailyFacade s, Response response) {
                presenter.updateViewForecast(s);
            }

            @Override
            public void failure(RetrofitError error) {
                switch (error.getKind()){
                    case NETWORK:
                        presenter.errorUpdateViewForecast(context.getString(R.string.msg_noInternet));
                        break;
                    case HTTP:
                        switch (error.getResponse().getStatus()){
                            case 404:
                                // Make
                                presenter.errorUpdateViewForecast(context.getString(R.string.msg_locationNotFound));
                                break;
                            case 401:
                                presenter.errorUpdateViewForecast(context.getString(R.string.msg_unauthorizedError));
                                break;
                            case 500:
                                presenter.errorUpdateViewForecast(context.getString(R.string.msg_internalServerError));
                                break;
                            default:
                                RegisterLocation(context, woeid, presenter);
                        }
                    default:
                        RegisterLocation(context, woeid, presenter);
                }
            }
        });
    }

    public static void GetLocationsWoeid(String query, final Presenter presenter) {
        PrepareService();
        service.getLocationByName(query, new Callback<List<Location>>() {
            @Override
            public void success(List<Location> locations, Response response) {
                presenter.updateViewLocations(locations);
            }

            @Override
            public void failure(RetrofitError error) {
                // TODO modify error message
                presenter.errorUpdateViewForecast("getLocationByName error");
            }
        });
    }

    public static void RegisterLocation(final Context context, String woeid, final Presenter presenter) {
        PrepareService();
        service.putLocation(woeid, new Callback<Location>() {
            @Override
            public void success(Location location, Response response) {
                presenter.locationRegisteredSuccess(context, location);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                presenter.locationRegisteredError(context);
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
