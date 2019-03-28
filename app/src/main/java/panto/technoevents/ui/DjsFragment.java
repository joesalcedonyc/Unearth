package panto.technoevents.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.disposables.CompositeDisposable;
import panto.technoevents.R;
import panto.technoevents.recyclerview.DjAdapter;
import panto.technoevents.viewmodel.FragmentsViewModel;

import static androidx.recyclerview.widget.RecyclerView.VERTICAL;

public class DjsFragment extends Fragment {
    private DjAdapter adapter;
    private OnDJSelectedListener onDjSelectedListener;
    private FragmentsViewModel fragmentsViewModel;
    private final CompositeDisposable disposable = new CompositeDisposable();

    public static DjsFragment newInstance() {
        return new DjsFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnDJSelectedListener) {
            onDjSelectedListener = (OnDJSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString() + "Must be instance of OnDJSelectedListener");
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

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), VERTICAL, false));
        recyclerView.setAdapter(adapter);

        fragmentsViewModel.djs.observe(this, djModels -> adapter.setData(djModels));
    }

    @Override
    public void onStop() {
        disposable.clear();
        super.onStop();
    }
}
