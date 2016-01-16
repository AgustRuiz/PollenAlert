package es.agustruiz.pollenalert.ui.dailyForecast;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import es.agustruiz.pollenalert.R;
import es.agustruiz.pollenalert.domain.model.PollenDayPeriod;
import es.agustruiz.pollenalert.presenter.DailyForecastPresenter;
import es.agustruiz.pollenalert.ui.dailyForecast.adapter.PollenDayPeriodAdapter;

/**
 * A placeholder fragment containing a simple view.
 */
public class DailyForecastActivityFragment extends Fragment{

    private static final String LOG_TAG = DailyForecastActivity.class.getName();

    @Bind(R.id.lvPollenDayPeriod)
    ListView lvPollenDayPeriod;

    DailyForecastPresenter presenter;

    private PollenDayPeriodAdapter adapter = null;

    /**
     * Constructor
     */
    public DailyForecastActivityFragment() {
        presenter = new DailyForecastPresenter(this);
        //PollencheckApiClient.GetPollenForecast("777597");
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
        return view;
    }

    // TODO: Remove after testing
    private void populateLvPollenDayPeriod() {
        List<PollenDayPeriod> pollenDayPeriod_testData = new ArrayList<>();
        pollenDayPeriod_testData.add(new PollenDayPeriod(2f, "dos", 1f, "uno", 3f, "tres"));
        pollenDayPeriod_testData.add(new PollenDayPeriod(5f, "cinco", 4f, "cuatro", 6f, "seis"));
        pollenDayPeriod_testData.add(new PollenDayPeriod(8f, "ocho", 7f, "siete", 9f, "nueve"));
        pollenDayPeriod_testData.add(new PollenDayPeriod(11f, "once", 10f, "diez", 12f, "doce"));
        pollenDayPeriod_testData.add(new PollenDayPeriod(14f, "catorce", 13f, "trece", 15f, "quince"));
        pollenDayPeriod_testData.add(new PollenDayPeriod(17f, "diecisiete", 16f, "dieciseis", 18f, "dieciocho"));

        this.populateLvPollenDayPeriod(pollenDayPeriod_testData);
    }

    public void populateLvPollenDayPeriod(List<PollenDayPeriod> pollenDayPeriod_testData) {
        PollenDayPeriod pollenDayPeriod_data[] = pollenDayPeriod_testData
                .toArray(new PollenDayPeriod[pollenDayPeriod_testData.size()]);

        if (adapter == null) {
            adapter = new PollenDayPeriodAdapter(lvPollenDayPeriod.getContext(),
                    R.layout.pollen_day_period, pollenDayPeriod_data);
        }

        if (lvPollenDayPeriod.getAdapter() == null) {
            Log.v(LOG_TAG, "AGUST_MSG: (1) IS NULL");
        } else {
            Log.v(LOG_TAG, "AGUST_MSG: (1) IS NOT NULL: " + lvPollenDayPeriod.getAdapter().toString());
        }

        lvPollenDayPeriod.setAdapter(adapter);
    }

    public void updateForecast() {
        this.presenter.updateForecast();
    }
}
