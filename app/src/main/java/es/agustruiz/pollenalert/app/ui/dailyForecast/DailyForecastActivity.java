package es.agustruiz.pollenalert.app.ui.dailyForecast;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import es.agustruiz.pollenalert.R;

public class DailyForecastActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.refresh) FloatingActionButton mRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);
        ButterKnife.bind(this);
        this.initialize();
    }

    private void initialize() {
        /*if (this.toolbar == null) {
            this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        }/**/
        setSupportActionBar(this.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        /*if (this.mRefresh == null) {
            this.mRefresh = (FloatingActionButton) findViewById(R.id.refresh);
        }/**/
        this.mRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
