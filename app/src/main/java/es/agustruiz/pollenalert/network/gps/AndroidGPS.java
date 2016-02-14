package es.agustruiz.pollenalert.network.gps;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class AndroidGPS {

    public static final String LOG_TAG = AndroidGPS.class.getName() + " [AGUST]";

    public static Location getLocation(Context context) throws ExceptionNoLocationProviderFound {
        Criteria criteria = new Criteria();
        //criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        criteria.setAltitudeRequired(false);
        LocationManager locationManager =
                (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        List<String> bestProviders = locationManager.getProviders(criteria, true);
        if (bestProviders.size() == 0) {
            throw new ExceptionNoLocationProviderFound();
        } else {
            locationManager.requestSingleUpdate(bestProviders.get(0), new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {

                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                    }

                    @Override
                    public void onProviderDisabled(String provider) {

                    }
                }, null);
            Location location =locationManager.getLastKnownLocation(bestProviders.get(0));
            if(location!=null)
                return location;
            else
                throw new ExceptionNoLocationProviderFound();
        }
    }

    public static String reverseGeocoding(Context context, double latitude, double longitude)
            throws ExceptionNoAddressFound, IOException {
        Geocoder geocoder = new Geocoder(context);
        List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
        Log.i(LOG_TAG, "Addresses (" + addresses.size() + "):");
        if (addresses.size() == 0) {
            throw new ExceptionNoAddressFound();
        } else {
            String address = addresses.get(0).getLocality()
                    + ", " + addresses.get(0).getSubAdminArea()
                    + ", " + addresses.get(0).getCountryName();
            //Toast.makeText(context, address, Toast.LENGTH_SHORT).show();
            return address;
        }
    }
}
