package es.agustruiz.pollenalert.api.yahooWhereApi;

import java.util.List;

import es.agustruiz.pollenalert.BuildConfig;
import es.agustruiz.pollenalert.presenter.Presenter;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class YahooWhereApiClient {
    public static String LOG_TAG = YahooWhereApiClient.class.getName();
    private static final String yahooApiKey = BuildConfig.YAHOO_API_KEY;



    public static List<String> getWoeid(String locationString, final Presenter presenter) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(YahooApiInterface.YAHOO_BASE_URL)
                /*.setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {

                        request.addHeader("Accept", "application/json")
                        request.addHeader("Authorization",
                                token.getTokenType() + " " + token.getAccessToken());



                        request.addHeader("X-Mashape-Key", yahooApiKey);
                        request.addHeader("Accept", "application/json");
                    }
                })/**/
                .build();

        YahooApiInterface service = restAdapter.create(YahooApiInterface.class);

        service.getWoeid(locationString, new Callback<String>() {

            @Override
            public void success(String s, Response response) {
                // TODO Success callback
            }

            @Override
            public void failure(RetrofitError error) {
                // TODO Failure callback
            }
        });
        return null;
    }
}
