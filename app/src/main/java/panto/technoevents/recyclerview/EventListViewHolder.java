package panto.technoevents.recyclerview;

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

    public void onBind(Events events) {
        venueTextView.setText(events.getVenue().getName());
        dateTextView.setText(events.getDate());
        locationTextView.setText(events.getVenue().getLocation());
        addressTextView.setText(events.getVenue().getAddress());
    }
}
