package panto.technoevents.recyclerview;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import panto.technoevents.R;
import panto.technoevents.apimodels.edmtrain.ArtistList;
import panto.technoevents.apimodels.edmtrain.Events;

public class EventListViewHolder extends RecyclerView.ViewHolder {

    private TextView venueTextView;
    private TextView dateTextView;
    private TextView locationTextView;
    private TextView addressTextView;
    private String venue;
    private String eventDate;
    private String eventLocation;
    private String eventAddress;

    public EventListViewHolder(@NonNull View itemView) {
        super(itemView);

        venueTextView = itemView.findViewById(R.id.venue_textView);
        dateTextView = itemView.findViewById(R.id.date_textView);
        locationTextView = itemView.findViewById(R.id.location_textView);
        addressTextView = itemView.findViewById(R.id.address_textView);

    }

    public void onBind(final Events events) {
        venue = events.getVenue().getName();
        eventDate = events.getDate();
        eventLocation = events.getVenue().getLocation();
        eventAddress = events.getVenue().getAddress();

        venueTextView.setText(venue);
        dateTextView.setText(eventDate);
        locationTextView.setText(eventLocation);
        addressTextView.setText(eventAddress);

        String artists = Arrays.deepToString(events.getArtistList().toArray());
        String artistsTwo = artists.substring(1, artists.length() - 1);

        itemView.findViewById(R.id.share_event_button)
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
    }
}
