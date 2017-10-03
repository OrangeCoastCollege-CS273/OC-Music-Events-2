package edu.orangecoastcollege.cs273.ocmusicevents2;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Creates a custom inflated UI for the list of events
 */
public class EventListAdapter extends ArrayAdapter<MusicEvent> {

    private Context mContext;
    private int mResource;
    private List<MusicEvent> mAllEventsList;

    /**
     * Creates an {@link EventListAdapter}
     * @param context The current context
     * @param resource The resource ID for a layout file containing a TextView to use when instantiating Views
     * @param objects The objects to represent in the ListView
     */
    public EventListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<MusicEvent>objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        mAllEventsList = objects;
    }

    /**
     * Get a View that displays the data at the specified position in the data set.
     *
     * @param position The position of the item within the adapter's data set of the item whose view we want.
     * @param convertView The old view to reuse, if possible.
     * @param parent The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View listItemView = inflater.inflate(mResource, null);

        ImageView listItemImageView = (ImageView) listItemView.findViewById(R.id.listItemImageView);
        TextView listItemTitleTextView = (TextView) listItemView.findViewById(R.id.listItemTitleTextView);
        TextView listItemDateTextView = (TextView) listItemView.findViewById(R.id.listItemDateTextView);

        MusicEvent selectedEvent = mAllEventsList.get(position);
        listItemTitleTextView.setText(selectedEvent.getTitle());
        listItemDateTextView.setText(selectedEvent.getDate());

        AssetManager assetManager = mContext.getAssets();
        try {
            InputStream stream = assetManager.open(selectedEvent.getImageName());
            Drawable drawable = Drawable.createFromStream(stream, selectedEvent.getTitle());
            listItemImageView.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listItemView;
    }
}
