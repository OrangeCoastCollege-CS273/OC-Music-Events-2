package edu.orangecoastcollege.cs273.ocmusicevents2;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;

/**
 * An activity that displays a list of items by binding to a View, and exposes event handlers when the user selects an item.
 */
public class EventListActivity extends ListActivity {

    private List<MusicEvent> mAllEventsList;


    /**
     * Called when the activity is starting.
     * Fills the list with data from JSON
     * sets the list adapter to a new {@link EventListAdapter} with the fetched list
     * @param savedInstanceState Saved instance if the activity was previously ran
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            mAllEventsList = JSONLoader.loadJSONFromAsset(this);

        } catch (IOException e) {
            Log.e("OC Music Events", "Error loading from JSON");
        }
//        setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, MusicEvent.titles));
        setListAdapter(new EventListAdapter(this, R.layout.music_event_list_item, mAllEventsList));

    }

    /**
     * On a click of a list item creates an intent and loads it with data about the event
     *
     * @param l ListView holding the item
     * @param v Clicked view
     * @param position Position of clicked view
     * @param id The row id of the item that was clicked
     */
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        Intent detailsIntent = new Intent(this, EventDetailsActivity.class);
        MusicEvent selectedEvent = mAllEventsList.get(position);
        detailsIntent.putExtra("Title", selectedEvent.getTitle());
        detailsIntent.putExtra("Date", selectedEvent.getDate());
        detailsIntent.putExtra("Day", selectedEvent.getDay());
        detailsIntent.putExtra("Location", selectedEvent.getLocation());
        detailsIntent.putExtra("Address1", selectedEvent.getAddress1());
        detailsIntent.putExtra("Address2", selectedEvent.getAddress2());
        detailsIntent.putExtra("ImageName", selectedEvent.getImageName());
        detailsIntent.putExtra("Time", selectedEvent.getTime());

        startActivity(detailsIntent);
    }
}
