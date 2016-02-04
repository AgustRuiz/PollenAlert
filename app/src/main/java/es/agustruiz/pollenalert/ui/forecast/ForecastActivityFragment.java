package es.agustruiz.pollenalert.ui.forecast;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import es.agustruiz.pollenalert.R;
import es.agustruiz.pollenalert.domain.pollencheck.forecast.DailyPeriod;
import es.agustruiz.pollenalert.domain.pollencheck.forecast.ForecastDailyFacade;
import es.agustruiz.pollenalert.domain.pollencheck.forecast.LocationFacade;
import es.agustruiz.pollenalert.presenter.ForecastPresenter;
import es.agustruiz.pollenalert.ui.adapter.DailyPeriodAdapter;
import es.agustruiz.pollenalert.ui.customViews.NoScrollListView;

/**
 * A placeholder fragment containing a simple view.
 */
public class ForecastActivityFragment extends Fragment {

    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    @Bind(R.id.mainView)
    View mainView;

    @Bind(R.id.locationNameValue)
    TextView locationNameValue;

    @Bind(R.id.locationRegionValue)
    TextView locationRegionValue;

    @Bind(R.id.locationCountryValue)
    TextView locationCountryValue;

    @Bind(R.id.creationTimeValue)
    TextView creationTimeValue;

    @Bind(R.id.lvDailyPeriod)
    NoScrollListView lvDailyPeriod;
    DailyPeriodAdapter dailyPeriodAdapter = null;
    Parcelable lvDailyPeriodState;

    @Bind(R.id.errorView)
    View errorView;

    @Bind(R.id.errorText)
    TextView errorText;

    private Context context;
    private ForecastPresenter presenter;

    private Boolean isOk;
    private Boolean isError;

    private ForecastDailyFacade forecastDailyFacade = null;

    public ForecastActivityFragment() {
        this.presenter = new ForecastPresenter(this);
        this.isOk = false;
        this.isError = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forecast, container, false);
        ButterKnife.bind(this, view);
        this.context = getActivity().getApplicationContext();
        if (this.isOk) this.showMainView();
        else this.hideMainView();
        if (this.isError) this.showErrorView();
        else this.hideErrorView();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            this.locationNameValue.setText(savedInstanceState.getString("locationNameValue"));
            this.locationRegionValue.setText(savedInstanceState.getString("locationRegionValue"));
            this.locationCountryValue.setText(savedInstanceState.getString("locationCountryValue"));
            this.creationTimeValue.setText(savedInstanceState.getString("creationTimeValue"));
            this.errorText.setText(savedInstanceState.getString("errorText"));

            try {
                this.forecastDailyFacade =
                        (ForecastDailyFacade) savedInstanceState.getSerializable("forecastDailyFacade");
                this.populateLvDailyPeriod(this.forecastDailyFacade);
            } catch (Exception ignored) {
            }

            this.isOk = savedInstanceState.getBoolean("isOk");
            this.isError = savedInstanceState.getBoolean("isError");
            if (this.isOk) this.showMainView();
            else if (this.isError) this.showErrorView();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("locationNameValue", this.locationNameValue.getText().toString());
        outState.putString("locationRegionValue", this.locationRegionValue.getText().toString());
        outState.putString("locationCountryValue", this.locationCountryValue.getText().toString());
        outState.putString("creationTimeValue", this.creationTimeValue.getText().toString());
        outState.putString("errorText", this.errorText.getText().toString());

        outState.putSerializable("forecastDailyFacade", this.forecastDailyFacade);

        outState.putBoolean("isOk", this.isOk);
        outState.putBoolean("isError", this.isError);
    }

    private void populateLvDailyPeriod(ForecastDailyFacade forecast) {
        DailyPeriod[] dailyPeriodArray =
                forecast.getPeriods().toArray(new DailyPeriod[forecast.getPeriods().size()]);

        if (this.dailyPeriodAdapter == null) {
            this.dailyPeriodAdapter = new DailyPeriodAdapter(this.lvDailyPeriod.getContext(),
                    R.layout.row_daily_period, dailyPeriodArray);
        }
        this.lvDailyPeriodState = this.lvDailyPeriod.onSaveInstanceState();
        this.lvDailyPeriod.setAdapter(dailyPeriodAdapter);
        this.lvDailyPeriod.onRestoreInstanceState(this.lvDailyPeriodState);
    }

    public void receiveForecastData(ForecastDailyFacade forecast) {
        LocationFacade location = forecast.getLocation();
        this.locationNameValue.setText(location.getName());
        this.locationRegionValue.setText(location.getRegion());
        this.locationCountryValue.setText(location.getCountry());

        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            cal.setTime(sdf.parse(forecast.getCreationTime()));
            sdf.setCalendar(cal);
            Date creationDate = cal.getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm, MM/dd/yyy");
            this.creationTimeValue.setText(formatter.format(creationDate));
        } catch (ParseException e) {
            this.creationTimeValue.setText("???");
        }

        this.populateLvDailyPeriod(forecast);
        this.forecastDailyFacade = forecast;

        this.isOk = true;
        this.isError = false;
    }

    public void refreshForecast() {
        this.presenter.updateForecast();
    }

    public void showProgressBar() {
        this.progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        this.progressBar.setVisibility(View.INVISIBLE);
    }

    public void showMainView() {
        this.mainView.setVisibility(View.VISIBLE);
    }

    public void hideMainView() {
        this.mainView.setVisibility(View.INVISIBLE);
    }

    public void showToast(String message, int length) {
        Toast.makeText(this.context, message, length).show();
    }

    public void showErrorView() {
        this.errorView.setVisibility(View.VISIBLE);
        this.isError = true;
        this.isOk = false;
    }

    public void showErrorView(String errorText) {
        this.errorText.setText(errorText);
        this.showErrorView();
    }

    public void hideErrorView() {
        this.errorView.setVisibility(View.INVISIBLE);
    }
}
