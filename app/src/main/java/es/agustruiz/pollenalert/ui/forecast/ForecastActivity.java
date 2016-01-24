package es.agustruiz.pollenalert.ui.forecast;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import es.agustruiz.pollenalert.R;

public class ForecastActivity extends AppCompatActivity {

    final String FORECAST_FRAGMENT_TAG = "forecastFragment";

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.refresh)
    FloatingActionButton mRefresh;

    ForecastActivityFragment forecastFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        ButterKnife.bind(this);
        if (savedInstanceState != null) {
            this.forecastFragment =
                    (ForecastActivityFragment) getSupportFragmentManager()
                            .getFragment(savedInstanceState, this.FORECAST_FRAGMENT_TAG);
        } else {
            this.forecastFragment = (ForecastActivityFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.contentForecast);
        }
        this.initialize();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager()
                .putFragment(outState, this.FORECAST_FRAGMENT_TAG, this.forecastFragment);
    }

    private void initialize() {
        setSupportActionBar(this.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        this.mRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    forecastFragment.showProgressBar();
                    forecastFragment.hideMainView();
                    forecastFragment.hideErrorView();
                    forecastFragment.refreshForecast();
                    /*Snackbar.make(view, "Here I am!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();/**/
                } catch (Exception e) {
                    forecastFragment.hideProgressBar();
                    forecastFragment.hideMainView();
                    forecastFragment.showErrorView("Can't refresh forecast!");
                }
            }
        });
    }

}
