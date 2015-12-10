package es.agustruiz.pollenalert.API;


import android.util.Log;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PollencheckAPI {
    public static String LOG_TAG = PollencheckAPI.class.getName();

    public static void GetPollenForecast(String woeid){
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(PollencheckAPIInterface.POLLENCHECK_BASE_URL)
                .build();

        PollencheckAPIInterface api = adapter.create(PollencheckAPIInterface.class);

        api.getTestForecast(new Callback<String>() {
            @Override
            public void success(String s, Response response) {
                Log.v(LOG_TAG, "AGUST_MSG: OK");
                Log.v(LOG_TAG, "AGUST_MSG: " + s);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.v(LOG_TAG, "AGUST_MSG: CACAFUTI");
                Log.v(LOG_TAG, "AGUST_MSG (Message): " + error.getMessage());
                //Log.v(LOG_TAG, "AGUST_MSG (SuccessType): " + error.getSuccessType().toString());

            }
        });
    }
}
