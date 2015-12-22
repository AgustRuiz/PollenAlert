package es.agustruiz.pollenalert.app;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import es.agustruiz.pollenalert.R;
import es.agustruiz.pollenalert.model.PollenDayPeriod;

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

            holder = new PollenDayPeriodHolder();
            holder.minCounter = (TextView) row.findViewById(R.id.MinCounter);
            holder.minLevel = (TextView) row.findViewById(R.id.MinLevel);
            holder.avgCounter = (TextView) row.findViewById(R.id.AvgCounter);
            holder.avgLevel = (TextView) row.findViewById(R.id.AvgLevel);
            holder.maxCounter = (TextView) row.findViewById(R.id.MaxCounter);
            holder.maxLevel = (TextView) row.findViewById(R.id.MaxLevel);

            row.setTag(holder);
        }else{
            holder = (PollenDayPeriodHolder) row.getTag();
        }

        PollenDayPeriod pollenDayPeriod = data[position];

        holder.minCounter.setText(pollenDayPeriod.getMinCounter().toString());
        holder.minLevel.setText(pollenDayPeriod.getMinLevel());
        holder.avgCounter.setText(pollenDayPeriod.getAvgCounter().toString());
        holder.avgLevel.setText(pollenDayPeriod.getAvgLevel());
        holder.maxCounter.setText(pollenDayPeriod.getMaxCounter().toString());
        holder.maxLevel.setText(pollenDayPeriod.getMaxLevel());

        return row;
    }

    static class PollenDayPeriodHolder{
        TextView minCounter;
        TextView minLevel;
        TextView avgCounter;
        TextView avgLevel;
        TextView maxCounter;
        TextView maxLevel;
    }
}
