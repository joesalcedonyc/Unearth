package panto.technoevents.recyclerview;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

import panto.technoevents.R;
import panto.technoevents.apimodels.edmtrain.Event;

public class EventViewHolder extends RecyclerView.ViewHolder {

    private TextView venueTextView;
    private TextView dateTextView;
    private TextView locationTextView;
    private String Lineup;
    private String venueName;
    private String eventDate;
    private String eventLocation;
    private String eventAddress;
    private String longitude;
    private String latitude;

    public EventViewHolder(@NonNull View itemView) {
        super(itemView);

        findTextViews(itemView);
    }

    public void onBind(final Event event) {

        venueName = event.getVenue().getName();
        assignEventDateVars(event);
        assignEventLocationVars(event);

        setEventInfoTextViews();

        longitude = Double.toString(event.getVenue().getLongitude());
        latitude = Double.toString(event.getVenue().getLatitude());

        String artistNamesToString = Arrays.deepToString(event.getArtistList().toArray());
        Lineup = artistNamesToString.substring(1, artistNamesToString.length() - 1);

        final ImageView shareImageView = itemView.findViewById(R.id.event_share_image);
        final ImageView mapImageView = itemView.findViewById(R.id.event_map_image);

        shareImageView.setOnClickListener(v -> emailEventDetails());

        mapImageView.setOnClickListener(v -> {
            Uri geoCoordinates = Uri.parse("geo:" + latitude + "," + longitude);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, geoCoordinates);

            mapIntent.setPackage("com.google.android.apps.maps");

            if (mapIntent.resolveActivity(itemView.getContext().getPackageManager()) != null) {
                itemView.getContext().startActivity(mapIntent);
            }
        });
    }

    private void findTextViews(@NonNull View itemView) {
        venueTextView = itemView.findViewById(R.id.venue_name_textView);
        dateTextView = itemView.findViewById(R.id.event_date_textView);
        locationTextView = itemView.findViewById(R.id.event_city_state_textView);
    }

    private void setEventInfoTextViews() {
        venueTextView.setText(venueName);
        dateTextView.setText(eventDate);
        locationTextView.setText(eventLocation);
    }

    private void assignEventDateVars(Event event) {
        String eventYear = event.getDate().substring(0, 4);
        String eventMonthDay = event.getDate().substring(5);
        eventDate = eventMonthDay + "-" + eventYear;
    }

    private void assignEventLocationVars(Event event) {
        eventLocation = event.getVenue().getLocation();
        eventAddress = event.getVenue().getAddress();
    }

    private void emailEventDetails() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "We out?");
        emailIntent.putExtra(Intent.EXTRA_TEXT,
                eventDate + "\n\n" +
                        Lineup + " @\n\n" +
                        venueName + "\n" +
                        eventLocation + "\n" +
                        eventAddress + "\n"
        );
        itemView.getContext().startActivity(emailIntent);
    }
}
