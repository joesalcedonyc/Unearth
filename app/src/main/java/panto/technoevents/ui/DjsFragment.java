package panto.technoevents.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.reactivex.disposables.CompositeDisposable;
import panto.technoevents.R;
import panto.technoevents.recyclerview.DjAdapter;
import panto.technoevents.viewmodel.FragmentsViewModel;

import static androidx.recyclerview.widget.RecyclerView.VERTICAL;

public class DjsFragment extends Fragment {
    private DjAdapter adapter;
    private onDJSelectedListener onDJSelectedListener;
    private FragmentsViewModel fragmentsViewModel;
    private final CompositeDisposable disposable = new CompositeDisposable();

    static DjsFragment newInstance() {
        return new DjsFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof onDJSelectedListener) {
            onDJSelectedListener = (onDJSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString() + "Must be instance of OnDJSelectedListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new DjAdapter(onDJSelectedListener);
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

        fragmentsViewModel.djs.observe(this, djModels -> {
            progressBar.setVisibility(View.INVISIBLE);
            adapter.setData(djModels);
        });
    }

    @Override
    public void onStop() {
        disposable.clear();
        super.onStop();
    }
}
