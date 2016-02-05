package es.agustruiz.pollenalert.presenter;


import android.content.Context;
import android.preference.PreferenceManager;
import android.widget.Toast;

import es.agustruiz.pollenalert.R;
import es.agustruiz.pollenalert.api.pollencheck.PollencheckApiClient;
import es.agustruiz.pollenalert.domain.pollencheck.forecast.ForecastDailyFacade;
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

    public void updateForecast(Context context){
        String woeid = PreferenceManager.getDefaultSharedPreferences(context)
                .getString(context.getResources().getString(R.string.pref_woeid), null);
        if(woeid!=null){
            PollencheckApiClient.GetPollenForecast(woeid, this);
        }else{
            this.errorUpdateViewForecast("Erró");
        }
    }

    public void updateViewForecast(ForecastDailyFacade forecast){
        this.fragment.receiveForecastData(forecast);
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
