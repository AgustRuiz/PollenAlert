package es.agustruiz.pollenalert.ui.forecast;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

    ForecastActivityFragment forecastFragment = null;

    private List<String> searchItems;

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

        //SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        //SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));

        //searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Toast.makeText(getApplicationContext(), "SUBMIT: " + query, Toast.LENGTH_SHORT).show();
                // TODO Dismiss searchView
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                //Toast.makeText(getApplicationContext(), "onQueryTextChange: " + query, Toast.LENGTH_SHORT).show();


                loadHistory(query);
                return false;
            }
        });
        return true;
    }

    // TODO Move to other place...
    private void loadHistory(String query) {

        List<String> locationList = new ArrayList<>();
        locationList.add("one");
        locationList.add("two");
        locationList.add("three");
        locationList.add("four");
        locationList.add("five");
        String[] locationArray = locationList.toArray(new String[locationList.size()]);

        //ArrayAdapter<String> locationAdapter = new ArrayAdapter<String>(getApplicationContext(),
        //        android.R.layout.simple_list_item_1);

        // TODO Cursor to where?
        CursorAdapter cursorAdapter = null;

        searchView.setSuggestionsAdapter(cursorAdapter);
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
    }

}
