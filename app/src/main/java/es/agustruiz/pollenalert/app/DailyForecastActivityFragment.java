package es.agustruiz.pollenalert.app;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import es.agustruiz.pollenalert.R;
import es.agustruiz.pollenalert.api.PollencheckApiImplementation;
import es.agustruiz.pollenalert.model.PollenDayPeriod;

/**
 * A placeholder fragment containing a simple view.
 */
public class DailyForecastActivityFragment extends Fragment {

    ListView lvPollenDayPeriod = null;
    ArrayAdapter<String> pollenDayPeriodAdapter = null;
    String[] itemsTest = {"uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho",
            "nueve", "diez", "once", "doce", "trece", "catorce", "quince", "dieciseis",
            "diecisiete", "dieciocho", "diecinueve", "veinte", "veintiuno"};

    public DailyForecastActivityFragment() {
        String woeidVillacarrillo = "777597";
        PollencheckApiImplementation.GetPollenForecast(woeidVillacarrillo);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily_forecast, container, false);
        FindViews(view);

        PollenDayPeriod pollenDayPeriod_data[] = new PollenDayPeriod[]
                {
                        new PollenDayPeriod(1f, "uno", 2f, "dos", 3f, "tres"),
                        new PollenDayPeriod(4f, "cuatro", 5f, "cinco", 6f, "seis"),
                        new PollenDayPeriod(7f, "siete", 8f, "ocho", 9f, "nueve"),
                        new PollenDayPeriod(1f, "uno", 2f, "dos", 3f, "tres"),
                        new PollenDayPeriod(4f, "cuatro", 5f, "cinco", 6f, "seis"),
                        new PollenDayPeriod(7f, "siete", 8f, "ocho", 9f, "nueve"),
                        new PollenDayPeriod(1f, "uno", 2f, "dos", 3f, "tres"),
                        new PollenDayPeriod(4f, "cuatro", 5f, "cinco", 6f, "seis"),
                        new PollenDayPeriod(7f, "siete", 8f, "ocho", 9f, "nueve"),
                        new PollenDayPeriod(1f, "uno", 2f, "dos", 3f, "tres"),
                        new PollenDayPeriod(4f, "cuatro", 5f, "cinco", 6f, "seis"),
                        new PollenDayPeriod(7f, "siete", 8f, "ocho", 9f, "nueve"),
                };

        PollenDayPeriodAdapter pollenDayPeriodAdapter =
                new PollenDayPeriodAdapter(view.getContext(),
                        R.layout.pollen_day_period, pollenDayPeriod_data);


        lvPollenDayPeriod.setAdapter(pollenDayPeriodAdapter);

        return view;
    }

    private void FindViews(View view) {
        lvPollenDayPeriod = (ListView) view.findViewById(R.id.lvPollenDayPeriod);
    }
}
