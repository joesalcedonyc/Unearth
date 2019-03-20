package panto.technoevents.recyclerview;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;

import panto.technoevents.R;
import panto.technoevents.apimodels.edmtrain.Event;

public class EventViewHolder extends RecyclerView.ViewHolder {

    private TextView venueTextView;
    private TextView dateTextView;
    private TextView locationTextView;
    private String venue;
    private String eventDate;
    private String eventLocation;
    private String eventAddress;
    private String longitude;
    private String latitude;

    public EventViewHolder(@NonNull View itemView) {
        super(itemView);

        venueTextView = itemView.findViewById(R.id.venue_name_textView);
        dateTextView = itemView.findViewById(R.id.event_date_textView);
        locationTextView = itemView.findViewById(R.id.event_city_state_textView);

    }

    public void onBind(final Event event) {
        venue = event.getVenue().getName();

        eventDate = event.getDate();
        String year = event.getDate().substring(0, 4);
        String monthDay = event.getDate().substring(5);
        String date = monthDay + "-" + year;

        eventLocation = event.getVenue().getLocation();
        eventAddress = event.getVenue().getAddress();
        longitude = Double.toString(event.getVenue().getLongitude());
        latitude = Double.toString(event.getVenue().getLatitude());


        venueTextView.setText(venue);
        dateTextView.setText(date);
        locationTextView.setText(eventLocation);

        final String artists = Arrays.deepToString(event.getArtistList().toArray());
        final String artistsTwo = artists.substring(1, artists.length() - 1);

        itemView.findViewById(R.id.event_share_button)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emailEventDetails();
                    }


                    private void emailEventDetails() {
                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                        emailIntent.setData(Uri.parse("mailto:"));
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "We out?");
                        emailIntent.putExtra(Intent.EXTRA_TEXT,
                                eventDate + "\n\n" +
                                        artistsTwo + " @\n\n" +
                                        venue + "\n" +
                                        eventLocation + "\n" +
                                        eventAddress + "\n"
                        );
                        itemView.getContext().startActivity(emailIntent);
                    }
                });

        itemView.findViewById(R.id.event_map_button)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri geoCoordinates = Uri.parse("geo:"+ latitude + ","+ longitude);
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, geoCoordinates);
                        mapIntent.setPackage("com.google.android.apps.maps");
                        if (mapIntent.resolveActivity(itemView.getContext().getPackageManager()) != null) {
                            itemView.getContext().startActivity(mapIntent);
                        }

                    }
                });

    }
}
