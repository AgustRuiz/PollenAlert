package es.agustruiz.pollenalert.ui.forecast;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import es.agustruiz.pollenalert.R;
import es.agustruiz.pollenalert.domain.model.DailyPeriod;
import es.agustruiz.pollenalert.domain.model.ForecastDailyFacade;
import es.agustruiz.pollenalert.domain.model.PollenDayPeriod;
import es.agustruiz.pollenalert.presenter.ForecastPresenter;
import es.agustruiz.pollenalert.ui.adapter.DailyPeriodAdapter;
import es.agustruiz.pollenalert.ui.adapter.PollenDayPeriodAdapter;
import es.agustruiz.pollenalert.ui.customViews.NoScrollListView;

/**
 * A placeholder fragment containing a simple view.
 */
public class ForecastActivityFragment extends Fragment {

    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    @Bind(R.id.timeValue)
    TextView woeidValue;

    @Bind(R.id.creationTimeValue)
    TextView creationTimeValue;

    @Bind(R.id.intervalValue)
    TextView intervalValue;

    @Bind(R.id.activeValue)
    TextView activeValue;

    @Bind(R.id.lvDailyPeriod)
    NoScrollListView lvDailyPeriod;
    private DailyPeriodAdapter adapter = null;

    private Context context;
    private ForecastPresenter presenter;

    public ForecastActivityFragment() {
        this.presenter = new ForecastPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forecast, container, false);
        ButterKnife.bind(this, view);
        this.context = getActivity().getApplicationContext();
        return view;
    }

    public void populateLvDailyPeriod(ForecastDailyFacade forecast) {
        DailyPeriod[] dailyPeriodArray =
                forecast.getPeriods().toArray(new DailyPeriod[forecast.getPeriods().size()]);

        if (this.adapter == null) {
            this.adapter = new DailyPeriodAdapter(this.lvDailyPeriod.getContext(),
                    R.layout.row_daily_period, dailyPeriodArray);
        }
        this.lvDailyPeriod.setAdapter(adapter);
    }

    public void callPresenterForecast(ForecastDailyFacade forecast) {
        this.woeidValue.setText(forecast.getWoeid());
        this.creationTimeValue.setText(forecast.getCreationTime());
        this.intervalValue.setText(forecast.getInterval());
        this.activeValue.setText(forecast.getActive().toString());
        this.populateLvDailyPeriod(forecast);
    }

    public void clearForecast() {
        this.woeidValue.setText(getResources().getString(R.string.blankString));
        this.creationTimeValue.setText(getResources().getString(R.string.blankString));
        this.intervalValue.setText(getResources().getString(R.string.blankString));
        this.activeValue.setText(getResources().getString(R.string.blankString));
    }

    public void callPresenterForecast() {
        this.presenter.updateForecast();
    }

    public void showProgressBar() {
        this.progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        this.progressBar.setVisibility(View.INVISIBLE);
    }

    public void showToast(String message, int length) {
        Toast.makeText(this.context, message, length).show();
    }
}
