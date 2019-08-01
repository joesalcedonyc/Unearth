package panto.technoevents.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.disposables.CompositeDisposable;
import panto.technoevents.R;
import panto.technoevents.apimodels.djs.DjModel;
import panto.technoevents.recyclerview.DjAdapter;
import panto.technoevents.viewmodel.FragmentsViewModel;

import static androidx.recyclerview.widget.RecyclerView.VERTICAL;

public class DjsFragment extends Fragment{
    private DjAdapter adapter;
    private onDJSelectedListener onDjSelectedListener;
    private FragmentsViewModel fragmentsViewModel;
    private final CompositeDisposable disposable = new CompositeDisposable();

    public static DjsFragment newInstance() {
        return new DjsFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof onDJSelectedListener) {
            onDjSelectedListener = (onDJSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString() + "Must be instance of onDJSelectedListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new DjAdapter(onDjSelectedListener);
        fragmentsViewModel = ViewModelProviders.of(this).get(FragmentsViewModel.class);
        fragmentsViewModel.loadDjs();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_djs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        ProgressBar progressBar = view.findViewById(R.id.indeterminateBar);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), VERTICAL, false));
        recyclerView.setAdapter(adapter);

        fragmentsViewModel.djs.observe(this, new Observer<List<DjModel>>() {
            @Override
            public void onChanged(List<DjModel> djModels) {
                progressBar.setVisibility(View.INVISIBLE);
                adapter.setData(djModels);
            }
        });
    }

    @Override
    public void onStop() {
        disposable.clear();
        super.onStop();
    }
}
