package es.agustruiz.pollenalert.ui.forecast;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import es.agustruiz.pollenalert.R;

public class ForecastActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.refresh)
    FloatingActionButton mRefresh;

    ForecastActivityFragment forecastActivityFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        ButterKnife.bind(this);
        this.initialize();
    }

    private void initialize() {
        setSupportActionBar(this.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        this.forecastActivityFragment = (ForecastActivityFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentForecast);


        this.mRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    forecastActivityFragment.showProgressBar();
                    forecastActivityFragment.clearForecast();
                    forecastActivityFragment.callPresenterForecast();
                    /*Snackbar.make(view, "Here I am!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();/**/
                } catch (Exception e) {
                    forecastActivityFragment.hideProgressBar();
                    Snackbar.make(view, "Can't refresh forecast!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

}
