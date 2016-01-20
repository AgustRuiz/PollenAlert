package es.agustruiz.pollenalert.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import es.agustruiz.pollenalert.R;
import es.agustruiz.pollenalert.domain.model.Constants;
import es.agustruiz.pollenalert.domain.model.DailyPeriod;
import es.agustruiz.pollenalert.domain.model.PollenDayPeriod;

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

        // Pollen values and colors
        PollenDayPeriod pollenDayPeriod = dailyPeriod.getCombined();
        holder.avgCounter.setText(pollenDayPeriod.getAvgCounter().toString());
        holder.avgLevel.setText(pollenDayPeriod.getAvgLevel());
        holder.maxCounter.setText(pollenDayPeriod.getMaxCounter().toString());
        holder.maxLevel.setText(pollenDayPeriod.getMaxLevel());
        holder.minCounter.setText(pollenDayPeriod.getMinCounter().toString());
        holder.minLevel.setText(pollenDayPeriod.getMinLevel());

        // Icon
        holder.pollenIcon.setColorFilter(getColorFilter(pollenDayPeriod.getAvgLevel()));

        return row;
    }

    static class DailyPeriodHolder {
        @Bind(R.id.dayName)
        TextView dayName;
        @Bind(R.id.dayNumber)
        TextView dayNumber;
        @Bind(R.id.pollenIcon)
        ImageView pollenIcon;
        @Bind(R.id.avgCounter)
        TextView avgCounter;
        @Bind(R.id.avgLevel)
        TextView avgLevel;
        @Bind(R.id.maxCounter)
        TextView maxCounter;
        @Bind(R.id.maxLevel)
        TextView maxLevel;
        @Bind(R.id.minCounter)
        TextView minCounter;
        @Bind(R.id.minLevel)
        TextView minLevel;

        public DailyPeriodHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private int getColorFilter(String level) {
        switch (level) {
            case Constants.HIGH_LEVEL:
                return getContext().getResources().getColor(R.color.ColorHigh);
            case Constants.MEDIUM_LEVEL:
                return getContext().getResources().getColor(R.color.ColorMedium);
            case Constants.LOW_LEVEL:
                return getContext().getResources().getColor(R.color.ColorLow);
            case Constants.VERY_LOW_LEVEL:
                return getContext().getResources().getColor(R.color.ColorVeryLow);
        }
        return getContext().getResources().getColor(R.color.colorAccent);
    }
}
