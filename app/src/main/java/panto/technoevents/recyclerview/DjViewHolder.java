package panto.technoevents.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import panto.technoevents.R;
import panto.technoevents.apimodels.djs.DjModel;
import panto.technoevents.ui.onDJSelectedListener;

class DjViewHolder extends RecyclerView.ViewHolder {

    private TextView artistNameTextView;
    private ImageView artistImageView;

    DjViewHolder(@NonNull View itemView) {
        super(itemView);
        artistNameTextView = itemView.findViewById(R.id.artist_name_textView);
        artistImageView = itemView.findViewById(R.id.artist_image);
    }

    void onBind(@NonNull final onDJSelectedListener onDJSelectedListener, @NonNull final DjModel djModel) {
        artistNameTextView.setText(djModel.getName());
        Picasso.get().load(djModel.getImage())
          .resize(220, 180)
          .into(artistImageView);
        itemView.findViewById(R.id.dj_cardView)
          .setOnClickListener(v -> onDJSelectedListener.openEventsFragment(djModel));
    }
}
