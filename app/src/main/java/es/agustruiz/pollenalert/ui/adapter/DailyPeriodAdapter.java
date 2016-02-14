package es.agustruiz.pollenalert.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import javax.net.ssl.ManagerFactoryParameters;

import butterknife.Bind;
import butterknife.ButterKnife;
import es.agustruiz.pollenalert.R;
import es.agustruiz.pollenalert.domain.pollencheck.Constants;
import es.agustruiz.pollenalert.domain.pollencheck.forecast.DailyPeriod;
import es.agustruiz.pollenalert.domain.pollencheck.forecast.PollenDayPeriod;

public class DailyPeriodAdapter extends ArrayAdapter<DailyPeriod> {

    Context context;
    int layoutResourceId;
    DailyPeriod data[] = null;

    public DailyPeriodAdapter(Context context, int layoutResourceId, DailyPeriod[] data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        DailyPeriodHolder holder;
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new DailyPeriodHolder(row);
            row.setTag(holder);
        } else {
            holder = (DailyPeriodHolder) row.getTag();
        }
        DailyPeriod dailyPeriod = data[position];

        // Date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(dailyPeriod.getTimestamp()));
        String dayOfWeek = getContext().getResources()
                .getStringArray(R.array.daysOfTheWeek)[calendar.get(Calendar.DAY_OF_WEEK)];
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        holder.dayName.setText(dayOfWeek);
        holder.dayNumber.setText(day);

        // Preferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Resources resources = context.getResources();

        if(preferences.getBoolean(resources.getString(R.string.prefKey_combined), true)){
            PollenDayPeriod pollenDayPeriod = dailyPeriod.getCombined();
            holder.combinedIcon.setColorFilter(getColorFilter(pollenDayPeriod.getAvgLevel()));
            holder.combinedAvgCounter.setText(pollenDayPeriod.getAvgCounter().toString());
            holder.combinedAvgLevel.setText(this.getPollenLevelString(pollenDayPeriod.getAvgLevel()));
            holder.combinedMaxCounter.setText(pollenDayPeriod.getMaxCounter().toString());
            holder.combinedMaxLevel.setText(this.getPollenLevelString(pollenDayPeriod.getMaxLevel()));
            holder.combinedMinCounter.setText(pollenDayPeriod.getMinCounter().toString());
            holder.combinedMinLevel.setText(this.getPollenLevelString(pollenDayPeriod.getMinLevel()));
        }else{
            holder.combinedView.setVisibility(View.GONE);
        }

        if(preferences.getBoolean(resources.getString(R.string.prefKey_olive), false)){
            PollenDayPeriod pollenDayPeriod = dailyPeriod.getOlive();
            holder.oliveIcon.setColorFilter(getColorFilter(pollenDayPeriod.getAvgLevel()));
            holder.oliveAvgCounter.setText(pollenDayPeriod.getAvgCounter().toString());
            holder.oliveAvgLevel.setText(this.getPollenLevelString(pollenDayPeriod.getAvgLevel()));
            holder.oliveMaxCounter.setText(pollenDayPeriod.getMaxCounter().toString());
            holder.oliveMaxLevel.setText(this.getPollenLevelString(pollenDayPeriod.getMaxLevel()));
            holder.oliveMinCounter.setText(pollenDayPeriod.getMinCounter().toString());
            holder.oliveMinLevel.setText(this.getPollenLevelString(pollenDayPeriod.getMinLevel()));
        }else{
            holder.oliveView.setVisibility(View.GONE);
        }

        if(preferences.getBoolean(resources.getString(R.string.prefKey_grass), false)){
            PollenDayPeriod pollenDayPeriod = dailyPeriod.getGrass();
            holder.grassIcon.setColorFilter(getColorFilter(pollenDayPeriod.getAvgLevel()));
            holder.grassAvgCounter.setText(pollenDayPeriod.getAvgCounter().toString());
            holder.grassAvgLevel.setText(this.getPollenLevelString(pollenDayPeriod.getAvgLevel()));
            holder.grassMaxCounter.setText(pollenDayPeriod.getMaxCounter().toString());
            holder.grassMaxLevel.setText(this.getPollenLevelString(pollenDayPeriod.getMaxLevel()));
            holder.grassMinCounter.setText(pollenDayPeriod.getMinCounter().toString());
            holder.grassMinLevel.setText(this.getPollenLevelString(pollenDayPeriod.getMinLevel()));
        }else{
            holder.grassView.setVisibility(View.GONE);
        }

        if(preferences.getBoolean(resources.getString(R.string.prefKey_birch), false)){
            PollenDayPeriod pollenDayPeriod = dailyPeriod.getBirch();
            holder.birchIcon.setColorFilter(getColorFilter(pollenDayPeriod.getAvgLevel()));
            holder.birchAvgCounter.setText(pollenDayPeriod.getAvgCounter().toString());
            holder.birchAvgLevel.setText(this.getPollenLevelString(pollenDayPeriod.getAvgLevel()));
            holder.birchMaxCounter.setText(pollenDayPeriod.getMaxCounter().toString());
            holder.birchMaxLevel.setText(this.getPollenLevelString(pollenDayPeriod.getMaxLevel()));
            holder.birchMinCounter.setText(pollenDayPeriod.getMinCounter().toString());
            holder.birchMinLevel.setText(this.getPollenLevelString(pollenDayPeriod.getMinLevel()));
        }else{
            holder.birchView.setVisibility(View.GONE);
        }

        if(preferences.getBoolean(resources.getString(R.string.prefKey_ragweed), false)){
            PollenDayPeriod pollenDayPeriod = dailyPeriod.getRagweed();
            holder.ragweedIcon.setColorFilter(getColorFilter(pollenDayPeriod.getAvgLevel()));
            holder.ragweedAvgCounter.setText(pollenDayPeriod.getAvgCounter().toString());
            holder.ragweedAvgLevel.setText(this.getPollenLevelString(pollenDayPeriod.getAvgLevel()));
            holder.ragweedMaxCounter.setText(pollenDayPeriod.getMaxCounter().toString());
            holder.ragweedMaxLevel.setText(this.getPollenLevelString(pollenDayPeriod.getMaxLevel()));
            holder.ragweedMinCounter.setText(pollenDayPeriod.getMinCounter().toString());
            holder.ragweedMinLevel.setText(this.getPollenLevelString(pollenDayPeriod.getMinLevel()));
        }else{
            holder.ragweedView.setVisibility(View.GONE);
        }

        return row;
    }

    static class DailyPeriodHolder {
        @Bind(R.id.dayName) TextView dayName;
        @Bind(R.id.dayNumber) TextView dayNumber;

        @Bind(R.id.combinedView) View combinedView;
        @Bind(R.id.combinedIcon) ImageView combinedIcon;
        @Bind(R.id.combinedAvgCounter) TextView combinedAvgCounter;
        @Bind(R.id.combinedAvgLevel) TextView combinedAvgLevel;
        @Bind(R.id.combinedMaxCounter) TextView combinedMaxCounter;
        @Bind(R.id.combinedMaxLevel) TextView combinedMaxLevel;
        @Bind(R.id.combinedMinCounter) TextView combinedMinCounter;
        @Bind(R.id.combinedMinLevel) TextView combinedMinLevel;

        @Bind(R.id.oliveView) View oliveView;
        @Bind(R.id.oliveIcon) ImageView oliveIcon;
        @Bind(R.id.oliveAvgCounter) TextView oliveAvgCounter;
        @Bind(R.id.oliveAvgLevel) TextView oliveAvgLevel;
        @Bind(R.id.oliveMaxCounter) TextView oliveMaxCounter;
        @Bind(R.id.oliveMaxLevel) TextView oliveMaxLevel;
        @Bind(R.id.oliveMinCounter) TextView oliveMinCounter;
        @Bind(R.id.oliveMinLevel) TextView oliveMinLevel;

        @Bind(R.id.grassView) View grassView;
        @Bind(R.id.grassIcon) ImageView grassIcon;
        @Bind(R.id.grassAvgCounter) TextView grassAvgCounter;
        @Bind(R.id.grassAvgLevel) TextView grassAvgLevel;
        @Bind(R.id.grassMaxCounter) TextView grassMaxCounter;
        @Bind(R.id.grassMaxLevel) TextView grassMaxLevel;
        @Bind(R.id.grassMinCounter) TextView grassMinCounter;
        @Bind(R.id.grassMinLevel) TextView grassMinLevel;

        @Bind(R.id.birchView) View birchView;
        @Bind(R.id.birchIcon) ImageView birchIcon;
        @Bind(R.id.birchAvgCounter) TextView birchAvgCounter;
        @Bind(R.id.birchAvgLevel) TextView birchAvgLevel;
        @Bind(R.id.birchMaxCounter) TextView birchMaxCounter;
        @Bind(R.id.birchMaxLevel) TextView birchMaxLevel;
        @Bind(R.id.birchMinCounter) TextView birchMinCounter;
        @Bind(R.id.birchMinLevel) TextView birchMinLevel;

        @Bind(R.id.ragweedView) View ragweedView;
        @Bind(R.id.ragweedIcon) ImageView ragweedIcon;
        @Bind(R.id.ragweedAvgCounter) TextView ragweedAvgCounter;
        @Bind(R.id.ragweedAvgLevel) TextView ragweedAvgLevel;
        @Bind(R.id.ragweedMaxCounter) TextView ragweedMaxCounter;
        @Bind(R.id.ragweedMaxLevel) TextView ragweedMaxLevel;
        @Bind(R.id.ragweedMinCounter) TextView ragweedMinCounter;
        @Bind(R.id.ragweedMinLevel) TextView ragweedMinLevel;

        public DailyPeriodHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private int getColorFilter(String level) {
        switch (level) {
            case Constants.LEVEL_HIGH:
                return getContext().getResources().getColor(R.color.ColorHigh);
            case Constants.LEVEL_MEDIUM:
                return getContext().getResources().getColor(R.color.ColorMedium);
            case Constants.LEVEL_LOW:
                return getContext().getResources().getColor(R.color.ColorLow);
            case Constants.LEVEL_VERY_LOW:
                return getContext().getResources().getColor(R.color.ColorVeryLow);
            default:
                return getContext().getResources().getColor(R.color.colorAccent);
        }

    }

    private String getPollenLevelString(String level){
        switch (level) {
            case Constants.LEVEL_HIGH:
                return getContext().getResources().getString(R.string.high);
            case Constants.LEVEL_MEDIUM:
                return getContext().getResources().getString(R.string.medium);
            case Constants.LEVEL_LOW:
                return getContext().getResources().getString(R.string.low);
            case Constants.LEVEL_VERY_LOW:
                return getContext().getResources().getString(R.string.very_low);
            default:
                return getContext().getResources().getString(R.string.unknown);
        }
    }
}
