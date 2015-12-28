package es.agustruiz.pollenalert.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import es.agustruiz.pollenalert.api.PollencheckApi;
import es.agustruiz.pollenalert.R;

public class MainActivity extends AppCompatActivity {
    static final String LOG_TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PollencheckApi.GetPollenForecast("766273");
    }
}
