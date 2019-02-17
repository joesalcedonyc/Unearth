package panto.technoevents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import panto.technoevents.controller.DjAdapter;
import panto.technoevents.service.DjRequest;
import panto.technoevents.service.RetrofitSingleton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DjAdapter djAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        Retrofit retrofit = RetrofitSingleton.getRetrofitSingleton();
        final DjRequest djRequest = retrofit.create(DjRequest.class);
        Call<DjWrapper> djs = djRequest.getDjs();
        djs.enqueue(new Callback<DjWrapper>() {
            @Override
            public void onResponse(Call<DjWrapper> call, Response<DjWrapper> response) {
                Log.d("joestag", "onResponse: " + response.body().getDjs().get(0).getImage());
                djAdapter = new DjAdapter(response.body().getDjs());
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                recyclerView.setAdapter(djAdapter);
                recyclerView.setLayoutManager(linearLayoutManager);
            }

            @Override
            public void onFailure(Call<DjWrapper> call, Throwable t) {
                Log.d("joestag", "onFailure: " + t.getMessage());
            }
        });
    }
}
