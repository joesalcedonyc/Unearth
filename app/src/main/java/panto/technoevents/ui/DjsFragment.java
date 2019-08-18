package panto.technoevents.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import panto.technoevents.R;
import panto.technoevents.db.RemoteDataBase;
import panto.technoevents.onDJSelectedListener;
import panto.technoevents.recyclerview.DjAdapter;
import panto.technoevents.viewmodel.FragmentsViewModel;

import static androidx.recyclerview.widget.RecyclerView.VERTICAL;

public class DjsFragment extends Fragment {
    private DjAdapter adapter;
    private FragmentsViewModel fragmentsViewModel;
    private boolean isShowAllSelected = false;

    static DjsFragment newInstance() {
        return new DjsFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        panto.technoevents.onDJSelectedListener onDJSelectedListener;
        if (context instanceof onDJSelectedListener) {
            onDJSelectedListener = (onDJSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString() + "Must be instance of OnDJSelectedListener");
        }
        adapter = new DjAdapter(onDJSelectedListener);
        fragmentsViewModel = ViewModelProviders.of(this).get(FragmentsViewModel.class);
        fragmentsViewModel.loadDjs();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
        if (!RemoteDataBase.getInstance().getFavorites().isEmpty() && !isShowAllSelected) {
            progressBar.setVisibility(View.INVISIBLE);
            fragmentsViewModel.favorites.observe(this, djModels -> adapter.setData(djModels));
        } else
            fragmentsViewModel.djs.observe(this, djModels -> {
                progressBar.setVisibility(View.INVISIBLE);
                adapter.setData(djModels);
            });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.options_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_all:
                isShowAllSelected = true;
                setNoFavoritesTextViewVisibility(View.INVISIBLE);
                fragmentsViewModel.djs.observe(this, djModels -> adapter.setData(djModels));
                break;
            case R.id.action_favorites:
                fragmentsViewModel.loadFavorites();
                fragmentsViewModel.favorites.observe(this, djModels -> {
                    if (djModels.isEmpty())
                        setNoFavoritesTextViewVisibility(View.VISIBLE);
                    adapter.setData(djModels);
                    isShowAllSelected = false;
                });
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setNoFavoritesTextViewVisibility(int invisible) {
        Objects.requireNonNull(getView())
          .findViewById(R.id.textView_no_favorites)
          .setVisibility(invisible);
    }
}
