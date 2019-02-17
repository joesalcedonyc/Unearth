package panto.technoevents.model.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import panto.technoevents.R;
import panto.technoevents.model.DjModel;

public class DjViewHolder extends RecyclerView.ViewHolder {

    private TextView artistNameTextView;
    private ImageView artist_press_shot;


    public DjViewHolder(@NonNull View itemView) {
        super(itemView);

        artistNameTextView = itemView.findViewById(R.id.artist_textView);
        artist_press_shot = itemView.findViewById(R.id.artist_image);
    }

    public void onBind (final DjModel djModel){
        artistNameTextView.setText(djModel.getName());
        Picasso.get().load(djModel.getImage())
                .resize(600, 380)
                .into(artist_press_shot);
    }
}
