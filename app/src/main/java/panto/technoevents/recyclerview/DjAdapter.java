package panto.technoevents.recyclerview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import panto.technoevents.R;
import panto.technoevents.apimodels.djs.DjModel;
import panto.technoevents.ui.onDjSelectedListener;

public class DjAdapter extends RecyclerView.Adapter<DjViewHolder> {
    private onDjSelectedListener onDjSelectedListener;

    List<DjModel> djList = new ArrayList<>();

    public DjAdapter(onDjSelectedListener onDjSelectedListener) {

        this.onDjSelectedListener = onDjSelectedListener;
    }

    @NonNull
    @Override
    public DjViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dj_item_view, viewGroup, false);
        return new DjViewHolder(view, onDjSelectedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull DjViewHolder djViewHolder, int i) {
        djViewHolder.onBind(djList.get(i));
        Log.d("onBindAdapter", "AdapteronBind");
    }

    @Override
    public int getItemCount() {
        return djList.size();
    }

    public void setData(List<DjModel> djModels) {
        djList = djModels;
        notifyDataSetChanged();
    }
}
