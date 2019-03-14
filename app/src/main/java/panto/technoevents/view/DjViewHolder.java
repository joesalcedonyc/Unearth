package panto.technoevents.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import panto.technoevents.R;
import panto.technoevents.model.djgist.DjModel;

public class DjViewHolder extends RecyclerView.ViewHolder {

    private TextView artistNameTextView;
    private ImageView artist_press_shot;


    public DjViewHolder(@NonNull View itemView) {
        super(itemView);

        artistNameTextView = itemView.findViewById(R.id.artist_textView);
        artist_press_shot = itemView.findViewById(R.id.artist_image);
        Log.d("joesviewholdertag", "viewHolder:");
    }

    public void onBind (final DjModel djModel){
        artistNameTextView.setText(djModel.getName());
        Picasso.get().load(djModel.getImage())
                .resize(600, 380)
                .into(artist_press_shot);
        Log.d("joesviewholdertag", "onBind:");
    }
}
