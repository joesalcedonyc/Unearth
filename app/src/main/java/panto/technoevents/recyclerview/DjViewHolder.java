package panto.technoevents.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import panto.technoevents.FragmentInterface;
import panto.technoevents.R;

import panto.technoevents.apimodels.djs.DjModel;

public class DjViewHolder extends RecyclerView.ViewHolder {

    private TextView artistNameTextView;
    private ImageView artistImageView;
    private FragmentInterface fragmentInterface;


    public DjViewHolder(@NonNull View itemView, FragmentInterface fragmentInterface) {
        super(itemView);

        artistNameTextView = itemView.findViewById(R.id.artist_name_textView);
        artistImageView = itemView.findViewById(R.id.artist_image);
        this.fragmentInterface = fragmentInterface;

    }

    public void onBind(final DjModel djModel) {
        artistNameTextView.setText(djModel.getName());
        Picasso.get().load(djModel.getImage())
                .resize(600, 380)
                .into(artistImageView);

        itemView.findViewById(R.id.dj_cardView)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fragmentInterface.fragmentNavigation(djModel);
                    }
                });

    }
}
