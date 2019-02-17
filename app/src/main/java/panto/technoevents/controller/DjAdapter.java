package panto.technoevents.controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import panto.technoevents.R;
import panto.technoevents.model.DjModel;
import panto.technoevents.view.DjViewHolder;

public class DjAdapter extends RecyclerView.Adapter<DjViewHolder> {

    List<DjModel> djList;
    public DjAdapter(List<DjModel> djList){
        this.djList = djList;
    }

    @NonNull
    @Override
    public DjViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dj_item_view, viewGroup, false);
        return new DjViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DjViewHolder djViewHolder, int i) {
        djViewHolder.onBind(djList.get(i));

    }

    @Override
    public int getItemCount() {
        return djList.size();
    }
}
