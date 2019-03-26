package panto.technoevents.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import io.reactivex.disposables.CompositeDisposable;
import panto.technoevents.R;
import panto.technoevents.apimodels.djs.DjModel;
import panto.technoevents.recyclerview.DjAdapter;
import panto.technoevents.viewmodel.DjsFragmentViewModel;

public class DjsFragment extends Fragment {

    private View rootView;
    private RecyclerView recyclerView;
    private DjAdapter adapter;
    private onDjSelectedListener onDjSelectedListener;
    private DjsFragmentViewModel djsFragmentViewModel;
    private final CompositeDisposable disposable = new CompositeDisposable();

    public static DjsFragment newInstance() {
        return new DjsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new DjAdapter(onDjSelectedListener);
        djsFragmentViewModel = ViewModelProviders.of(this).get(DjsFragmentViewModel.class);

        djsFragmentViewModel.loadDjs();

        djsFragmentViewModel.djs.observe(this, new Observer<List<DjModel>>() {
            @Override
            public void onChanged(List<DjModel> djModels) {
                adapter.setData(djModels);
            }
        });
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
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(),
                        LinearLayoutManager.VERTICAL,
                        false
                ));

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStop() {
        disposable.clear();
        super.onStop();
    }
}
