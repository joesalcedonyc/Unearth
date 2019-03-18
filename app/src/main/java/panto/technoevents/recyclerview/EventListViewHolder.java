package panto.technoevents.recyclerview;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import panto.technoevents.R;
import panto.technoevents.apimodels.edmtrain.Events;

public class EventListViewHolder extends RecyclerView.ViewHolder {

    private TextView venueTextView;
    private TextView dateTextView;
    private TextView locationTextView;
    private TextView addressTextView;

    public EventListViewHolder(@NonNull View itemView) {
        super(itemView);

        venueTextView = itemView.findViewById(R.id.venue_textView);
        dateTextView = itemView.findViewById(R.id.date_textView);
        locationTextView = itemView.findViewById(R.id.location_textView);
        addressTextView = itemView.findViewById(R.id.address_textView);


    }

    public void onBind(final Events events) {
        venueTextView.setText(events.getVenue().getName());
        dateTextView.setText(events.getDate());
        locationTextView.setText(events.getVenue().getLocation());
        addressTextView.setText(events.getVenue().getAddress());

        itemView.setOnClickListener(v -> {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "We out?");
            emailIntent.putExtra(Intent.EXTRA_TEXT,
                    events.getVenue().getName() + "\n"
                            + events.getDate() + "\n"
                            + events.getVenue().getLocation() + "\n"
                            + events.getVenue().getAddress());

            itemView.getContext().startActivity(emailIntent);
        });
    }
}
