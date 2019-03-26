package panto.technoevents.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import panto.technoevents.R;
import panto.technoevents.apimodels.edmtrain.Event;

public class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {

    private List<Event> eventsList;

    public EventAdapter(List<Event> eventsList){
        this.eventsList = eventsList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_item_view, viewGroup, false);

        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder eventViewHolder, int i) {

        eventViewHolder.onBind(eventsList.get(i));

    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }
}
