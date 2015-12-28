package es.agustruiz.pollenalert.app.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import es.agustruiz.pollenalert.R;
import es.agustruiz.pollenalert.app.ui.dailyForecast.DailyForecastActivity;

public class MainActivity extends AppCompatActivity {
    static final String LOG_TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLaunchDailyForecast = (Button) findViewById(R.id.btnLaunchDailyForecast);
        btnLaunchDailyForecast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DailyForecastActivity.class);
                startActivity(intent);
            }
        });
    }

}
