package es.agustruiz.pollenalert.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import es.agustruiz.pollenalert.R;
import es.agustruiz.pollenalert.ui.dailyForecast.DailyForecastActivity;

public class MainActivity extends AppCompatActivity {
    static final String LOG_TAG = MainActivity.class.getName();

    @Bind(R.id.btnLaunchDailyForecast)
    Button btnLaunchDailyForecast;

    @Bind(R.id.btnLaunchForecastActivity)
    Button btnLaunchForecastActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        this.btnLaunchDailyForecast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DailyForecastActivity.class);
                startActivity(intent);
            }
        });

        this.btnLaunchForecastActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ForecastActivity.class);
                startActivity(intent);
            }
        });
    }




}
