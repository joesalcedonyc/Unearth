package panto.technoevents.db;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import panto.technoevents.apimodels.djs.DjModel;

public class RemoteDataBase {
    private static RemoteDataBase instance;
    private List<DjModel> favoritesList = new ArrayList<>();
    private final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("userID/favorites");

    private RemoteDataBase() {
    }

    public static RemoteDataBase getInstance() {
        if (instance == null) {
            instance = new RemoteDataBase();
        }
        return instance;
    }

    public void addFavorite(DjModel djModel) {
        databaseReference.getRoot()
          .child("userID")
          .child("favorites")
          .child(djModel.getName())
          .setValue(djModel);
    }

    public void removeFavorite(DjModel djModel) {
        databaseReference.getRoot()
          .child("userID")
          .child("favorites")
          .child(djModel.getName())
          .removeValue();
    }

    public List<DjModel> getFavorites() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                favoritesList.clear();
                Iterable<DataSnapshot> favorites = dataSnapshot.getChildren();
                for (DataSnapshot favoriteArtists : favorites) {
                    DjModel djModel = favoriteArtists.getValue(DjModel.class);
                    favoritesList.add(djModel);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("DatabaseError", "onCancelled: " + databaseError.getMessage());
            }
        });
        return favoritesList;
    }
}
