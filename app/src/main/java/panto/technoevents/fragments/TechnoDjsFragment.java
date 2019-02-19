package panto.technoevents.fragments;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import panto.technoevents.DjWrapper;
import panto.technoevents.R;
import panto.technoevents.controller.DjAdapter;
import panto.technoevents.service.DjRequest;
import panto.technoevents.service.RetrofitSingleton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TechnoDjsFragment extends Fragment {

    private View rootView;
    private RecyclerView recyclerView;
    private DjAdapter djAdapter;

    public TechnoDjsFragment() {}
    public static TechnoDjsFragment newinstance(){
        return new TechnoDjsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_techno_djs, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView);

        Retrofit retrofit = RetrofitSingleton.getRetrofitSingleton();
        final DjRequest djRequest = retrofit.create(DjRequest.class);
        Call<DjWrapper> djs = djRequest.getDjs();
        djs.enqueue(new Callback<DjWrapper>() {
            @Override
            public void onResponse(Call<DjWrapper> call, Response<DjWrapper> response) {
                Log.d("joestag", "onResponse: " + response.body().getDjs().get(0).getImage());
                djAdapter = new DjAdapter(response.body().getDjs());
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false);
                djAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(djAdapter);
                recyclerView.setLayoutManager(linearLayoutManager);
            }

            @Override
            public void onFailure(Call<DjWrapper> call, Throwable t) {
                Log.d("joestag", "onFailure: " + t.getMessage());
            }
        });
        return rootView;
    }
}
