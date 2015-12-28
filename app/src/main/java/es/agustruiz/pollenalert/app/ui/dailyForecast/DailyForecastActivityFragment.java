package es.agustruiz.pollenalert.app.ui.dailyForecast;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.agustruiz.pollenalert.R;
import es.agustruiz.pollenalert.api.PollencheckApi;

/**
 * A placeholder fragment containing a simple view.
 */
public class DailyForecastActivityFragment extends Fragment {

    public DailyForecastActivityFragment() {
        PollencheckApi.GetPollenForecast("777597");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_daily_forecast, container, false);
    }
}
