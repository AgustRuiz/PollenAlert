package es.agustruiz.pollenalert.presenter;

import android.view.View;
import android.widget.Toast;

import java.util.List;

import es.agustruiz.pollenalert.api.PollencheckApiClient;
import es.agustruiz.pollenalert.domain.model.PollenDayPeriod;
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

    public void updateViewForecast(List<PollenDayPeriod> forecast){
        this.fragment.populateLvPollenDayPeriod(forecast);
        this.fragment.hideProgressBar();
        this.fragment.showToast("Here it is, my master!", Toast.LENGTH_SHORT);
    }

    public void errorUpdateViewForecast(String messageError){
        this.fragment.hideProgressBar();
        this.fragment.showToast("Error: " + messageError, Toast.LENGTH_LONG);
    }


}
