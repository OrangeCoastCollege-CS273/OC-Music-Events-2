package edu.orangecoastcollege.cs273.ocmusicevents2;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

/**
 * An activity to host the details of a particular Event
 */
public class EventDetailsActivity extends AppCompatActivity {

    /**
     * Called when the activity is starting
     *
     * Loads the data from the Intent which opened up the activity
     * Then loads the data into the Views
     * @param savedInstanceState Saved instance if the activity was previously ran
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);


        Intent intent = getIntent();
        String title = intent.getStringExtra("Title");
        String time = intent.getStringExtra("Time");
        String date = intent.getStringExtra("Date");
        String day = intent.getStringExtra("Day");
        String location = intent.getStringExtra("Location");
        String address1 = intent.getStringExtra("Address1");
        String address2 = intent.getStringExtra("Address2");

        String imageFileName = title.replace(" ", "") + ".jpeg";

        ImageView eventImageView = (ImageView) findViewById(R.id.eventImageView);
        TextView eventTitleTextView = (TextView) findViewById(R.id.eventTitleTextView);
        TextView eventDateDayTextView = (TextView) findViewById(R.id.eventDateDayTextView);
        TextView eventTimeTextView = (TextView) findViewById(R.id.eventTimeTextView);
        TextView eventLocationTextView = (TextView) findViewById(R.id.eventLocationTextView);
        TextView eventAddress1TextView = (TextView) findViewById(R.id.eventAddress1TextView);
        TextView eventAddress2TextView = (TextView) findViewById(R.id.eventAddress2TextView);

        AssetManager am = this.getAssets();
        try {
            InputStream stream = am.open(imageFileName);
            Drawable image = Drawable.createFromStream(stream, title);
            eventImageView.setImageDrawable(image);
        }
        catch (IOException e)
        {
            Log.e("OC Music Events", "Error loading image: " + imageFileName, e);
        }
        eventTitleTextView.setText(title);
        eventDateDayTextView.setText(date + " - " + day);
        eventTimeTextView.setText(time);
        eventLocationTextView.setText(location);
        eventAddress1TextView.setText(address1);
        eventAddress2TextView.setText(address2);
    }

    /**
     * On click closes the Activity
     * @param v The view which was clicked
     */
    protected void goBackToList(View v)
    {
        finish();
    }
}
