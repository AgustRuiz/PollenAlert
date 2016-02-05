package es.agustruiz.pollenalert.ui.forecast;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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

    SearchView searchView;
    MenuItem searchMenuItem;
    Activity activity;

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
        getMenuInflater().inflate(R.menu.menu_search, menu);
        searchMenuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                colapseSearchView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // TODO Search every second here...
                forecastFragment.searchLocations(query);
                return false;
            }
        });
        return true;
    }

    private void colapseSearchView() {
        searchMenuItem.collapseActionView();
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(searchView.getWindowToken(), 0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
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
        this.activity = this;
    }

}
