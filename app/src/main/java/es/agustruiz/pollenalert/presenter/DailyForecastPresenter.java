package es.agustruiz.pollenalert.presenter;

import android.widget.Toast;

import es.agustruiz.pollenalert.api.pollencheck.PollencheckApiClient;
import es.agustruiz.pollenalert.domain.pollencheck.ForecastDailyFacade;
import es.agustruiz.pollenalert.ui.dailyForecast.DailyForecastActivityFragment;

public class DailyForecastPresenter implements Presenter{
    DailyForecastActivityFragment fragment;

    /**
     * Constructor
     * @param fragment Fragment
     */
    public DailyForecastPresenter(DailyForecastActivityFragment fragment) {
        this.fragment = fragment;
    }

    /*public List<PollenDayPeriod> getForecast() {
        PollencheckApiClient.GetPollenForecast("777597", this);
    }/**/

    public void updateForecast(){
        PollencheckApiClient.GetPollenForecast("777597", this);
    }

    @Override
    public void updateViewForecast(ForecastDailyFacade forecast){
        this.fragment.populateLvPollenDayPeriod(forecast);
        this.fragment.hideProgressBar();
        this.fragment.showToast("Here it is, my master!", Toast.LENGTH_SHORT);
    }

    @Override
    public void errorUpdateViewForecast(String messageError){
        this.fragment.hideProgressBar();
        this.fragment.showToast("Error: " + messageError, Toast.LENGTH_LONG);
    }
}
