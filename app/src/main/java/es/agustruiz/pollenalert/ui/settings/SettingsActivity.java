package es.agustruiz.pollenalert.ui.settings;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import es.agustruiz.pollenalert.R;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p/>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends AppCompatActivity {

    PreferenceFragment preferenceFragment = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferenceFragment = new MyPreferenceFragment();
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, this.preferenceFragment).commit();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Capture preferences change
        PreferenceManager.getDefaultSharedPreferences(getBaseContext())
                .registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
                    @Override
                    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                        // Combined pollen checked at least
                        if (!checkPollenVarietiesPrefs()) {
                            Context context = getApplicationContext();
                            PreferenceManager.getDefaultSharedPreferences(context).edit()
                                    .putBoolean("pref_combined", true).commit();
                            SwitchPreference prefCombined = (SwitchPreference)
                                    preferenceFragment.findPreference("pref_combined");
                            prefCombined.setChecked(true);
                            Toast.makeText(
                                    context,
                                    R.string.msg_combinedPollenCompulsory,
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean checkPollenVarietiesPrefs() {
        Context context = getApplicationContext();
        boolean combinedPref = PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean("pref_combined", false);
        boolean olivePref = PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean("pref_olive", false);
        boolean grassPref = PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean("pref_grass", false);
        boolean birchPref = PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean("pref_birch", false);
        boolean ragweedPref = PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean("pref_ragweed", false);

        /*Toast.makeText(
                context,
                "Combined:\t" + combinedPref + "\n"
                        + "Olive:\t" + olivePref + "\n"
                        + "Grass:\t" + grassPref + "\n"
                        + "Birch:\t" + birchPref + "\n"
                        + "Ragweed:\t" + ragweedPref,
                Toast.LENGTH_LONG
        ).show();/**/

        return !(!combinedPref && !olivePref && !grassPref && !birchPref && !ragweedPref);
    }

    public static class MyPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_generic);
        }
    }

}
