package es.agustruiz.pollenalert.ui.dailyForecast;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import es.agustruiz.pollenalert.R;
import es.agustruiz.pollenalert.api.PollencheckApiClient;

public class DailyForecastActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.refresh)
    FloatingActionButton mRefresh;
    DailyForecastActivityFragment dailyForecastActivityFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);
        ButterKnife.bind(this);
        this.initialize();
    }

    private void initialize() {
        setSupportActionBar(this.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        this.dailyForecastActivityFragment =
                (DailyForecastActivityFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.contentDailyForecast);

        this.mRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    //PollencheckApiClient.GetPollenForecast("777597");
                    dailyForecastActivityFragment.updateForecast();

                    Snackbar.make(view, "Here it is, my master!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }catch (Exception e){
                    Snackbar.make(view, "Can't refresh forecast!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

}
