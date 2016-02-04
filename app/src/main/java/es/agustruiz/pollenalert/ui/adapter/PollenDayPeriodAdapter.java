package es.agustruiz.pollenalert.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import es.agustruiz.pollenalert.R;
import es.agustruiz.pollenalert.domain.pollencheck.forecast.PollenDayPeriod;

public class PollenDayPeriodAdapter extends ArrayAdapter<PollenDayPeriod> {


    Context context;
    int layoutResourceId;
    PollenDayPeriod data[] = null;

    public PollenDayPeriodAdapter(Context context, int layoutResourceId, PollenDayPeriod[] data){
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        PollenDayPeriodHolder holder = null;

        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new PollenDayPeriodHolder(row);

            row.setTag(holder);
        }else{
            holder = (PollenDayPeriodHolder) row.getTag();
        }

        PollenDayPeriod pollenDayPeriod = data[position];

        holder.avgCounter.setText(String.format("%d", pollenDayPeriod.getAvgCounter().intValue()));
        holder.avgLevel.setText(pollenDayPeriod.getAvgLevel());
        holder.minCounter.setText(String.format("%d", pollenDayPeriod.getMinCounter().intValue()));
        holder.minLevel.setText(pollenDayPeriod.getMinLevel());
        holder.maxCounter.setText(String.format("%d", pollenDayPeriod.getMaxCounter().intValue()));
        holder.maxLevel.setText(pollenDayPeriod.getMaxLevel());

        return row;
    }

    static class PollenDayPeriodHolder{
        public PollenDayPeriodHolder(View view) {
            ButterKnife.bind(this, view);
        }
        @Bind(R.id.MinCounter) TextView minCounter;
        @Bind(R.id.MinLevel) TextView minLevel;
        @Bind(R.id.AvgCounter) TextView avgCounter;
        @Bind(R.id.AvgLevel) TextView avgLevel;
        @Bind(R.id.MaxCounter) TextView maxCounter;
        @Bind(R.id.MaxLevel) TextView maxLevel;
    }
}
