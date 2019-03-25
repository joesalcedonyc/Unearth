package panto.technoevents.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.squareup.picasso.Picasso;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import panto.technoevents.R;
import panto.technoevents.apimodels.djs.DjModel;
import panto.technoevents.network.DjRepository;
import panto.technoevents.recyclerview.EventAdapter;

public class EventsFragment extends Fragment {
    private View rootView;
    private static int artistId;
    private static String artistImageUrl;
    private static String artistName;
    private RecyclerView recyclerView;
    private final CompositeDisposable disposable = new CompositeDisposable();

    public EventsFragment() {
    }

    public static EventsFragment newInstance(final DjModel djModel) {

        artistId = djModel.getId();
        artistImageUrl = djModel.getImage();
        artistName = djModel.getName();

        return new EventsFragment();

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_event, container, false);
        recyclerView = rootView.findViewById(R.id.event_list_recyclerView);

        ImageView eventListArtistImageView = rootView.findViewById(R.id.event_list_artist_ImageView);
        TextView eventListArtistNameTextView = rootView.findViewById(R.id.event_list_artist_name_textView);

        Picasso.get()
                .load(artistImageUrl)
                .into(eventListArtistImageView);

        eventListArtistNameTextView.setText(artistName);

        disposable.add(DjRepository.getInstance()
                .getAllDjEvents(artistId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(events -> {
                            Log.d("EdmTrainRequest", "Response: " + events.get(0)
                                    .getVenue()
                                    .getName());

                            recyclerView.setLayoutManager(
                                    new LinearLayoutManager(
                                            rootView.getContext(),
                                            LinearLayoutManager.VERTICAL,
                                            false));
                            recyclerView.setAdapter(new EventAdapter(events));
                        },

                        throwable -> Log.d("EdmTrainRequest", "Throwable: " + throwable)));

        ToggleButton favoriteButton = rootView.findViewById(R.id.favorite_button);
        favoriteButton.setOnClickListener(v -> {
        });

        return rootView;
    }

    @Override
    public void onStop() {
        disposable.clear();
        super.onStop();
    }
}
