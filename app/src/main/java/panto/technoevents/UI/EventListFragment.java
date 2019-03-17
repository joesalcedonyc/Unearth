package panto.technoevents.UI;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import panto.technoevents.R;
import panto.technoevents.apimodels.djs.DjModel;
import panto.technoevents.apimodels.edmtrain.Events;

public class EventListFragment extends Fragment {
    private View rootView;

    public EventListFragment() {
    }

    public static EventListFragment newInstance(DjModel djModel){
        return new EventListFragment();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_event_list, container, false);

        return rootView;
    }
}
