package es.agustruiz.pollenalert.presenter;


import android.widget.Toast;

import es.agustruiz.pollenalert.api.PollencheckApiClient;
import es.agustruiz.pollenalert.domain.model.ForecastDailyFacade;
import es.agustruiz.pollenalert.ui.forecast.ForecastActivityFragment;

public class ForecastPresenter implements Presenter{
    ForecastActivityFragment fragment;

    /**
     * Constructor
     * @param fragment Forecast activity fragment
     */
    public ForecastPresenter(ForecastActivityFragment fragment) {
        this.fragment = fragment;
    }

    public void updateForecast(){
        PollencheckApiClient.GetPollenForecast("777597", this);
    }

    public void updateViewForecast(ForecastDailyFacade forecast){
        this.fragment.callPresenterForecast(forecast);
        this.fragment.hideProgressBar();
        this.fragment.showMainView();
        this.fragment.showToast("Here it is, my master!", Toast.LENGTH_SHORT);
    }

    public void errorUpdateViewForecast(String messageError){
        this.fragment.hideProgressBar();
        this.fragment.hideMainView();
        this.fragment.showErrorView("Error: " + messageError);
    }
}
