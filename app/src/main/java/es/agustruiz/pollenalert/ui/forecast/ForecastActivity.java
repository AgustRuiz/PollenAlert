package es.agustruiz.pollenalert.ui.forecast;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import es.agustruiz.pollenalert.R;
import es.agustruiz.pollenalert.ui.settings.SettingsActivity;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        //SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.action_settings:
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                return true;
        }
        return false;
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
