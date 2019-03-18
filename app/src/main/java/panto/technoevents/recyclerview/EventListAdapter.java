package panto.technoevents.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import panto.technoevents.R;
import panto.technoevents.apimodels.edmtrain.Events;

public class EventListAdapter extends RecyclerView.Adapter<EventListViewHolder> {

    private List<Events> eventsList;

    public EventListAdapter (List<Events> eventsList){
        this.eventsList = eventsList;
    }

    @NonNull
    @Override
    public EventListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_list_item_view, viewGroup, false);

        return new EventListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventListViewHolder eventListViewHolder, int i) {

        eventListViewHolder.onBind(eventsList.get(i));

    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }
}
