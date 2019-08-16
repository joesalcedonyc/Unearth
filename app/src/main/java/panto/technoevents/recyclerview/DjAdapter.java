package panto.technoevents.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import panto.technoevents.R;
import panto.technoevents.apimodels.djs.DjModel;
import panto.technoevents.onDJSelectedListener;

public class DjAdapter extends RecyclerView.Adapter<DjViewHolder> {
    private onDJSelectedListener onDJSelectedListener;

    private List<DjModel> djList = new ArrayList<>();

    public DjAdapter(@NonNull final onDJSelectedListener onDJSelectedListener) {
        this.onDJSelectedListener = onDJSelectedListener;
    }

    @NonNull
    @Override
    public DjViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dj_item_view, viewGroup, false);
        return new DjViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DjViewHolder djViewHolder, int i) {
        djViewHolder.onBind(onDJSelectedListener, djList.get(i));
    }

    @Override
    public int getItemCount() {
        return djList.size();
    }

    public void setData(@NonNull final List<DjModel> djModels) {
        djList = djModels;
        notifyDataSetChanged();
    }
}
