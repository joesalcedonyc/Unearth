package panto.technoevents.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import panto.technoevents.R;
import panto.technoevents.apimodels.djs.DjModel;
import panto.technoevents.recyclerview.EventAdapter;
import panto.technoevents.viewmodel.FragmentsViewModel;

import static androidx.recyclerview.widget.RecyclerView.VERTICAL;

public class EventsFragment extends Fragment {
    private static String artistImageUrl;
    private static String artistName;
    private EventAdapter eventAdapter;
    private FragmentsViewModel fragmentsViewModel;
    private DjModel djModel;

    public EventsFragment() {
    }

    public static EventsFragment newInstance(DjModel djModel) {
        Bundle bundle = new Bundle();
        EventsFragment fragment = new EventsFragment();
        bundle.putParcelable("DJ", djModel);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            djModel = getArguments().getParcelable("DJ");
        }

        eventAdapter = new EventAdapter();
        fragmentsViewModel = ViewModelProviders.of(this).get(FragmentsViewModel.class);
        artistImageUrl = djModel.getImage();
        artistName = djModel.getName();

        fragmentsViewModel.loadEvents(djModel.getId());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_event, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView eventListArtistImageView = view.findViewById(R.id.event_list_artist_ImageView);
        TextView eventListArtistNameTextView = view.findViewById(R.id.event_list_artist_name_textView);
        RecyclerView recyclerView = view.findViewById(R.id.event_list_recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), VERTICAL, false));
        recyclerView.setAdapter(eventAdapter);

        Picasso.get()
                .load(artistImageUrl)
                .into(eventListArtistImageView);

        eventListArtistNameTextView.setText(artistName);

        fragmentsViewModel.djEvents.observe(this, events -> eventAdapter.setData(events));

        ToggleButton favoriteButton = view.findViewById(R.id.favorite_button);
        favoriteButton.setOnClickListener(v -> {});
    }
}
