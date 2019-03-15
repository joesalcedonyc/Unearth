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

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import panto.technoevents.R;
import panto.technoevents.apimodels.djs.DjModel;
import panto.technoevents.recyclerview.DjAdapter;
import panto.technoevents.network.DjRepository;

public class TechnoDjsFragment extends Fragment {

    private View rootView;
    private RecyclerView recyclerView;


    public TechnoDjsFragment() {
    }

    public static TechnoDjsFragment newinstance() {
        return new TechnoDjsFragment();
    }

    @SuppressLint("CheckResult")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_techno_djs, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView);

        DjRepository.getInstance()
                .getAllDjs()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<DjModel>>() {
                               @Override
                               public void accept(List<DjModel> djModels) throws Exception {
                                   Log.d("TechnoDjsRequest", "TechnoDjsResponse: "
                                           + djModels.get(0).getId());

                                   recyclerView.setLayoutManager(new LinearLayoutManager(
                                           rootView.getContext(),
                                           LinearLayoutManager.VERTICAL,
                                           false
                                   ));
                                   recyclerView.setAdapter(new DjAdapter(djModels));
                               }
                           },
                        throwable -> Log.d(
                                "TechnoDjsRequest", "TechnoDjsThrowable: "
                                        + throwable.getMessage()));

        return rootView;
    }
}
