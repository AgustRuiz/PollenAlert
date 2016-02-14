package es.agustruiz.pollenalert.ui.forecast;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import es.agustruiz.pollenalert.R;
import es.agustruiz.pollenalert.ui.about.AboutActivity;
import es.agustruiz.pollenalert.ui.settings.SettingsActivity;

public class ForecastActivity extends AppCompatActivity {

    final String FORECAST_FRAGMENT_TAG = "forecastFragment";

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    //@Bind(R.id.action_search)
    SearchView searchView;
    MenuItem searchMenuItem;
    Activity activity;

    ForecastActivityFragment forecastFragment = null;

    Timer timerSearch = null;
    final static long SHEARCH_TIMEOUT = 300;
    final static long SHEARCH_MIN_CHARS = 2;

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
        getMenuInflater().inflate(R.menu.menu_forecast, menu);
        searchMenuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setQueryHint(getResources().getString(R.string.msg_search_hint));

        MenuItemCompat.setOnActionExpandListener(searchMenuItem,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        forecastFragment.showSearchView();
                        startTimerSearch();
                        return true;
                    }

                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        forecastFragment.hideSearchView();
                        stopTimerSearch();
                        return true;
                    }
                }
        );

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                colapseSearchView();
                forecastFragment.hideProgressBar();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                final String trimQuery = query.trim();
                if (trimQuery.length() >= SHEARCH_MIN_CHARS) {
                    forecastFragment.showProgressBar();
                    restartTimerSearch();
                    timerSearch.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            forecastFragment.searchLocation(trimQuery);
                        }
                    }, SHEARCH_TIMEOUT);
                } else {
                    forecastFragment.hideProgressBar();
                }
                return true;
            }
        });

        return true;
    }

    public void colapseSearchView() {
        searchMenuItem.collapseActionView();
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(searchView.getWindowToken(), 0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.action_refresh_forecast:
                forecastFragment.refreshForecast();
                return true;
            case R.id.action_settings:
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                return true;
            case R.id.action_about:
                startActivity(new Intent(getApplicationContext(), AboutActivity.class));
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

    private void startTimerSearch() {
        this.timerSearch = new Timer();
    }

    private void stopTimerSearch() {
        if (this.timerSearch != null) {
            this.timerSearch.cancel();
            this.timerSearch.purge();
        }
    }

    private void restartTimerSearch() {
        if (this.timerSearch != null) {
            this.timerSearch.cancel();
            this.timerSearch.purge();
        }
        this.timerSearch = new Timer();
    }

    private void initialize() {
        setSupportActionBar(this.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        this.activity = this;
    }
}
