package panto.technoevents.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import panto.technoevents.R;
import panto.technoevents.apimodels.djs.DjModel;
import panto.technoevents.ui.onDjSelectedListener;

public class DjViewHolder extends RecyclerView.ViewHolder {

    private TextView artistNameTextView;
    private ImageView artistImageView;
    private onDjSelectedListener onDjSelectedListener;


    public DjViewHolder(@NonNull View itemView, onDjSelectedListener onDjSelectedListener) {
        super(itemView);

        artistNameTextView = itemView.findViewById(R.id.artist_name_textView);
        artistImageView = itemView.findViewById(R.id.artist_image);
        this.onDjSelectedListener = onDjSelectedListener;

    }

    public void onBind(final DjModel djModel) {
        artistNameTextView.setText(djModel.getName());
        Picasso.get().load(djModel.getImage())
                .resize(220, 180)
                .into(artistImageView);

        itemView.findViewById(R.id.dj_cardView)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onDjSelectedListener.fragmentNavigation(djModel);
                    }
                });

    }
}
