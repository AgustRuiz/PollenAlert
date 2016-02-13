package es.agustruiz.pollenalert.presenter;


import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import es.agustruiz.pollenalert.R;
import es.agustruiz.pollenalert.network.gps.ExceptionNoAddressFound;
import es.agustruiz.pollenalert.network.pollencheck.PollencheckApiClient;
import es.agustruiz.pollenalert.network.gps.AndroidGPS;
import es.agustruiz.pollenalert.network.gps.ExceptionNoLocationProviderFound;
import es.agustruiz.pollenalert.domain.pollencheck.forecast.ForecastDailyFacade;
import es.agustruiz.pollenalert.domain.pollencheck.location.Location;
import es.agustruiz.pollenalert.ui.forecast.ForecastActivityFragment;

public class ForecastPresenter implements Presenter {

    public final static String LOG_TAG = "ForecastPresenter";

    ForecastActivityFragment fragment;

    /**
     * Constructor
     *
     * @param fragment Forecast activity fragment
     */
    public ForecastPresenter(ForecastActivityFragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void updateForecast(Context context) {
        String woeid = PreferenceManager.getDefaultSharedPreferences(context)
                .getString(context.getResources().getString(R.string.prefKey_woeid), null);
        if (woeid != null) {
            PollencheckApiClient.GetPollenForecast(woeid, this);
        } else {
            // TODO Change message
            this.errorUpdateViewForecast("Erró");
        }
    }

    @Override
    public void updateViewForecast(ForecastDailyFacade forecast) {
        this.fragment.receiveForecastData(forecast);
        this.fragment.hideProgressBar();
        this.fragment.showMainView();
    }

    @Override
    public void errorUpdateViewForecast(String messageError) {
        this.fragment.hideProgressBar();
        this.fragment.hideMainView();
        this.fragment.showErrorView("Error: " + messageError);
    }

    @Override
    public void queryLocations(String query) {
        PollencheckApiClient.GetLocationsWoeid(query, this);
    }

    @Override
    public void updateViewLocations(List<Location> locations) {
        this.fragment.receiveSearchLocation(locations);
    }

    @Override
    public void getLocationWoeid(Context context) {
        // TODO not hardcoded!
        //String location = "37.7858081+-3.7746269";
        //YahooApiClient.GetWoeidByLocation(location, this);

        Log.v("[AGUST]", "getLocationWoeid()");

        try{
            this.fragment.showProgressBar();
            android.location.Location location = AndroidGPS.getLocation(context);
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            String locationString = AndroidGPS.reverseGeocoding(context, latitude, longitude);
            this.queryLocations(locationString);
        }catch (ExceptionNoLocationProviderFound e){
            this.fragment.hideProgressBar();
            Toast.makeText(context, "No location provider found in this device", Toast.LENGTH_LONG).show();
        } catch (ExceptionNoAddressFound exceptionNoAddressFound) {
            this.fragment.hideProgressBar();
            Toast.makeText(context, "Location not found", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            this.errorUpdateViewForecast("No Internet connection");
        }

    }
}
