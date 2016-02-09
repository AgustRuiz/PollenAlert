package es.agustruiz.pollenalert.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import es.agustruiz.pollenalert.R;
import es.agustruiz.pollenalert.domain.pollencheck.location.Location;

public class LocationAdapter extends ArrayAdapter<Location> {

    public static final int ICON_MY_LOCATION = android.R.drawable.ic_menu_mylocation;
    public static final int ICON_PLACE = android.R.drawable.ic_menu_myplaces;
    public static final int ICON_NOT_FOUND = android.R.drawable.ic_menu_close_clear_cancel;

    public static final String TAG_LOCATION_NOT_FOUND = "TAG_LOCATION_NOT_FOUND";
    public static final String TAG_GEOPOSITION = "TAG_GEOPOSITION";

    Context context;
    int layoutResourceId;
    Location data[] = null;

    public LocationAdapter(Context context, int layoutResourceId, Location[] data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LocationHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new LocationHolder(row);
            row.setTag(holder);
        } else {
            holder = (LocationHolder) row.getTag();
        }

        Location location = data[position];


        holder.mImageView.setImageDrawable(context.getDrawable(location.getIcon()));
        if (location.isGeoposition()) {
            holder.mLocationTextView.setText(R.string.myCurrentPosition);
            holder.mWoeidTextView.setText(this.TAG_GEOPOSITION);
        } else if(location.isLocationNotFound()){
            holder.mLocationTextView.setText(R.string.noResults);
            holder.mWoeidTextView.setText(this.TAG_LOCATION_NOT_FOUND);
        } else {
            holder.mLocationTextView.setText(location.getName()
                    + " (" + location.getRegion()
                    + ", " + location.getCountry() + ")");
            holder.mWoeidTextView.setText(location.getWoeid());
        }
        return row;
    }

    static class LocationHolder {
        @Bind(R.id.mImageView)
        ImageView mImageView;
        @Bind(R.id.mLocationTextView)
        TextView mLocationTextView;
        @Bind(R.id.mWoeidTextView)
        TextView mWoeidTextView;

        public LocationHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
