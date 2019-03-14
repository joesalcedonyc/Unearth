package panto.technoevents.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import panto.technoevents.R;
import panto.technoevents.controller.DjAdapter;
import panto.technoevents.network.DjRequest;
import panto.technoevents.network.RetrofitDjsSingleton;

public class TechnoDjsFragment extends Fragment {

    private View rootView;
    private RecyclerView recyclerView;
    private DjAdapter djAdapter;

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

        RetrofitDjsSingleton.getDjsRetrofitInstance()
                .create(DjRequest.class)
                .getDjs()

                .subscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(djResponse -> {
                            Log.d("TechnoDjsRequest", "onNext: " + djResponse
                                    .getDjs().get(0)
                                    .getImage());

                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                                    rootView.getContext(),
                                    LinearLayoutManager.VERTICAL,
                                    false);

                            djAdapter = new DjAdapter(djResponse.getDjs());
                            recyclerView.setLayoutManager(linearLayoutManager);
                            recyclerView.setAdapter(djAdapter);
                        },
                        throwable -> Log.d("TechnoDjsRequest", "onError: " + throwable.getMessage()));
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
