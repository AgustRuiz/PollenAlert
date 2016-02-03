package es.agustruiz.pollenalert.ui.dailyForecast;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.Bind;
import es.agustruiz.pollenalert.R;

public class DailyForecastActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.refresh)
    FloatingActionButton mRefresh;
    DailyForecastActivityFragment dailyForecastActivityFragment = null;

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
                    dailyForecastActivityFragment.showProgressBar();
                    dailyForecastActivityFragment.clearForecast();
                    dailyForecastActivityFragment.updateForecast();
                    /*Snackbar.make(view, "Here it is, my master!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();/**/
                }catch (Exception e){
                    dailyForecastActivityFragment.hideProgressBar();
                    Snackbar.make(view, "Can't refresh forecast!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

}
