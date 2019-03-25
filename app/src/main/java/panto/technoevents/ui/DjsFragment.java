package panto.technoevents.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import panto.technoevents.R;
import panto.technoevents.recyclerview.DjAdapter;
import panto.technoevents.network.DjRepository;

public class DjsFragment extends Fragment {

    private View rootView;
    private RecyclerView recyclerView;
    private onDjSelectedListener onDjSelectedListener;
    private final CompositeDisposable disposable = new CompositeDisposable();

    public static DjsFragment newInstance() {
        return new DjsFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof onDjSelectedListener) {
            onDjSelectedListener = (onDjSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString() + "Must be instance of onDjSelectedListener");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_djs, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView);

        disposable.add(
                DjRepository.getInstance()
                        .getAllDjs()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(djModels -> {
                                    Log.d("TechnoDjsRequest", "Response: "
                                            + djModels.get(0).getId());

                                    recyclerView.setLayoutManager(
                                            new LinearLayoutManager(
                                                    rootView.getContext(),
                                                    LinearLayoutManager.VERTICAL,
                                                    false
                                            ));

                                    recyclerView.setAdapter(
                                            new DjAdapter(djModels, onDjSelectedListener));

                                },

                                throwable -> Log.d("TechnoDjsRequest", "Throwable: "
                                        + throwable.getMessage())));


        return rootView;
    }

    @Override
    public void onStop() {
        disposable.clear();
        super.onStop();
    }
}
