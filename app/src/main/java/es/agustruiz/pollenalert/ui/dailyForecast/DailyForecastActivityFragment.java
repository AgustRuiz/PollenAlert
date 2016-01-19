package es.agustruiz.pollenalert.ui.dailyForecast;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import es.agustruiz.pollenalert.R;
import es.agustruiz.pollenalert.domain.model.DailyPeriod;
import es.agustruiz.pollenalert.domain.model.ForecastDailyFacade;
import es.agustruiz.pollenalert.domain.model.PollenDayPeriod;
import es.agustruiz.pollenalert.presenter.DailyForecastPresenter;
import es.agustruiz.pollenalert.ui.dailyForecast.adapter.PollenDayPeriodAdapter;

/**
 * A placeholder fragment containing a simple view.
 */
public class DailyForecastActivityFragment extends Fragment {

    private static final String LOG_TAG = DailyForecastActivity.class.getName();

    @Bind(R.id.lvPollenDayPeriod)
    ListView lvPollenDayPeriod;

    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    private Context context;
    private PollenDayPeriodAdapter adapter = null;
    private DailyForecastPresenter presenter;

    /**
     * Constructor
     */
    public DailyForecastActivityFragment() {
        this.presenter = new DailyForecastPresenter(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily_forecast, container, false);
        ButterKnife.bind(this, view);
        this.context = getActivity().getApplicationContext();
        return view;
    }

    public void populateLvPollenDayPeriod(ForecastDailyFacade forecast) {
        DailyPeriod dailyPeriod = forecast.getPeriods().get(0);
        List<PollenDayPeriod> pollenDayPeriod_testData = new ArrayList<>();
        pollenDayPeriod_testData.add(dailyPeriod.getCombined());
        pollenDayPeriod_testData.add(dailyPeriod.getBirch());
        pollenDayPeriod_testData.add(dailyPeriod.getGrass());
        pollenDayPeriod_testData.add(dailyPeriod.getOlive());
        pollenDayPeriod_testData.add(dailyPeriod.getRagweed());


        PollenDayPeriod pollenDayPeriod_data[] = pollenDayPeriod_testData
                .toArray(new PollenDayPeriod[pollenDayPeriod_testData.size()]);

        if (adapter == null) {
            adapter = new PollenDayPeriodAdapter(lvPollenDayPeriod.getContext(),
                    R.layout.pollen_day_period, pollenDayPeriod_data);
        }
        lvPollenDayPeriod.setAdapter(adapter);
    }

    public void clearForecast() {
        lvPollenDayPeriod.setAdapter(null); // Empty adapter
    }

    public void updateForecast() {
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
