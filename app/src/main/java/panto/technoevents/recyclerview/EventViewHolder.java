package panto.technoevents.recyclerview;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import panto.technoevents.R;
import panto.technoevents.apimodels.edmtrain.Event;

public class EventViewHolder extends RecyclerView.ViewHolder {

    private TextView venueTextView;
    private TextView dateTextView;
    private TextView locationTextView;
    private ImageView shareImageView;
    private ImageView mapImageView;
    private ImageView navigationImageView;
    private String lineup;
    private String venueName;
    private String eventDate;
    private String eventLocation;
    private String eventAddress;
    private String longitude;
    private String latitude;

    private TextView lineupTextView;

    public EventViewHolder(@NonNull View itemView) {
        super(itemView);

        findTextViews(itemView);
        findImageViews(itemView);
    }

    public void onBind(final Event event) {

        venueName = event.getVenue().getName();
        assignEventDateVariables(event);
        assignEventLocationVariables(event);

        setEventInfoTextViews();

        longitude = Double.toString(event.getVenue().getLongitude());
        latitude = Double.toString(event.getVenue().getLatitude());

        String artistNamesToString = Arrays.deepToString(event.getArtistList().toArray());
        lineup = artistNamesToString.substring(1, artistNamesToString.length() - 1);

        lineupTextView.setText(lineup);

        Intent mapIntent = getMapIntent();
        Intent navIntent = getNavigationIntent();

        shareImageView.setOnClickListener(v -> emailEventDetails());
        mapImageView.setOnClickListener(v -> itemView.getContext().startActivity(mapIntent));
        navigationImageView.setOnClickListener(v -> itemView.getContext().startActivity(navIntent));
    }

    private Intent getNavigationIntent() {
        Uri parseAddress = Uri.parse("google.navigation:q=" + eventAddress);
        Intent navIntent = new Intent(Intent.ACTION_VIEW, parseAddress);
        navIntent.setPackage("com.google.android.apps.maps");
        return navIntent;
    }

    private Intent getMapIntent() {
        Uri geoCoordinates = Uri.parse("geo:" + latitude + "," + longitude + "?z=15&q=" + eventAddress);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, geoCoordinates);
        mapIntent.setPackage("com.google.android.apps.maps");
        return mapIntent;
    }

    private void findTextViews(@NonNull View itemView) {
        venueTextView = itemView.findViewById(R.id.venue_name_textView);
        dateTextView = itemView.findViewById(R.id.event_date_textView);
        locationTextView = itemView.findViewById(R.id.event_city_state_textView);
        lineupTextView = itemView.findViewById(R.id.lineup_TextView);
    }

    private void setEventInfoTextViews() {
        venueTextView.setText(venueName);
        dateTextView.setText(eventDate);
        locationTextView.setText(eventLocation);
    }

    private void findImageViews(@NonNull View itemView) {
        shareImageView = itemView.findViewById(R.id.event_share_image);
        mapImageView = itemView.findViewById(R.id.event_map_image);
        navigationImageView = itemView.findViewById(R.id.event_navigation_image);
    }

    private void assignEventDateVariables(Event event) {
        String eventYear = event.getDate().substring(0, 4);
        String eventMonthDay = event.getDate().substring(5);
        eventDate = eventMonthDay + "-" + eventYear;
    }

    private void assignEventLocationVariables(Event event) {
        eventLocation = event.getVenue().getLocation();
        eventAddress = event.getVenue().getAddress();
    }

    private void emailEventDetails() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "We out?");
        emailIntent.putExtra(Intent.EXTRA_TEXT,
                eventDate + "\n\n" +
                        lineup + " @\n\n" +
                        venueName + "\n" +
                        eventLocation + "\n" +
                        eventAddress + "\n"
        );
        itemView.getContext().startActivity(emailIntent);
    }
}
