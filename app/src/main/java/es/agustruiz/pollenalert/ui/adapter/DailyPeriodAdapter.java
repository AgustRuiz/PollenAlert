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
import es.agustruiz.pollenalert.domain.model.DailyPeriod;
import es.agustruiz.pollenalert.domain.model.PollenDayPeriod;

public class DailyPeriodAdapter extends ArrayAdapter<DailyPeriod> {


    Context context;
    int layoutResourceId;
    DailyPeriod data[] = null;

    public DailyPeriodAdapter(Context context, int layoutResourceId, DailyPeriod[] data){
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        DailyPeriodHolder holder = null;

        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new DailyPeriodHolder(row);

            row.setTag(holder);
        }else{
            holder = (DailyPeriodHolder) row.getTag();
        }

        DailyPeriod dailyPeriod = data[position];

        holder.time.setText(dailyPeriod.getTime());
        holder.timestamp.setText(String.format("%d", dailyPeriod.getTimestamp()));


        return row;
    }

    static class DailyPeriodHolder {
        public DailyPeriodHolder(View view) {
            ButterKnife.bind(this, view);
        }
        @Bind(R.id.timeValue) TextView time;
        @Bind(R.id.timestampValue) TextView timestamp;
    }
}
