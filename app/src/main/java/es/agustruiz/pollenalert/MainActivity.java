package es.agustruiz.pollenalert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import es.agustruiz.pollenalert.API.PollencheckAPI;
import es.agustruiz.pollenalert.Models.Pollencheck.ForecastDailyFacade;

public class MainActivity extends AppCompatActivity {
    static final String LOG_TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PollencheckAPI.GetPollenForecast("766273");
    }
}
