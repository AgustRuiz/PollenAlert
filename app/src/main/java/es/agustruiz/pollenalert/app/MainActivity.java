package es.agustruiz.pollenalert.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import es.agustruiz.pollenalert.R;
import es.agustruiz.pollenalert.api.PollencheckApiImplementation;

public class MainActivity extends AppCompatActivity {
    static final String LOG_TAG = MainActivity.class.getName();

    Button btnDailyForecastActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initialize();

        /*/PollencheckApiImplementation.GetPollenForecast("766273");

        String woeidVillacarrillo = "777597";
        PollencheckApiImplementation.GetPollenForecast(woeidVillacarrillo);/**/

    }

    private void initialize() {
        // btnDailyForecastActivity
        btnDailyForecastActivity = (Button) findViewById(R.id.btnDailyForecastActivity);
        btnDailyForecastActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DailyForecastActivity.class));
            }
        });


    }
}
