package panto.technoevents.UI;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import panto.technoevents.R;
import panto.technoevents.apimodels.djs.DjModel;
import panto.technoevents.apimodels.edmtrain.Events;
import panto.technoevents.network.DjRepository;
import panto.technoevents.recyclerview.EventListAdapter;

public class EventListFragment extends Fragment {
    private View rootView;
    private static int artistId;
    private static String artistImageUrl;
    private static String artistName;

    private RecyclerView recyclerView;
    private ImageView eventListArtistImageView;
    private TextView eventListArtistNameTextView;


    public EventListFragment() {
    }

    public static EventListFragment newInstance(final DjModel djModel) {

        artistId = djModel.getId();
        artistImageUrl = djModel.getImage();
        artistName = djModel.getName();


        Log.d("DjId:", " " + djModel.getId());
        return new EventListFragment();

    }

    @SuppressLint("CheckResult")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(
                R.layout.fragment_event_list, container, false);

        recyclerView = rootView.findViewById(R.id.event_list_recyclerView);

        eventListArtistImageView = rootView.findViewById(R.id.event_list_artist_ImageView);
        eventListArtistNameTextView = rootView.findViewById(R.id.event_list_artist_name_textView);

        Log.d("artistname", artistName);
        Log.d("artistimage", artistImageUrl);

        Picasso.get()
                .load(artistImageUrl)
                .into(eventListArtistImageView);

        eventListArtistNameTextView.setText(artistName);

        DjRepository.getInstance()
                .getAllDjEvents(artistId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Events>>() {
                               @Override
                               public void accept(List<Events> events) throws Exception {
                                   Log.d("EdmTrainRequest", "Response: " +
                                           events.get(0)
                                                   .getVenue()
                                                   .getName());


                                   recyclerView.setLayoutManager(new LinearLayoutManager(
                                           rootView.getContext(),
                                           LinearLayoutManager.VERTICAL,
                                           false));
                                   recyclerView.setAdapter(new EventListAdapter(events));

                               }
                           },

                        throwable -> Log.d("EdmTrainRequest", "Throwable: "
                                + throwable));

        return rootView;
    }
}
