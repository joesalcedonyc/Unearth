package panto.technoevents;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import panto.technoevents.fragments.LoginFragment;
import panto.technoevents.fragments.TechnoDjsFragment;
import panto.technoevents.model.EDMTrain.EDMTrainResponse;
import panto.technoevents.network.EDMTrainInterface;
import panto.technoevents.network.EDMTrainRetrofitSingleton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Retrofit retrofit = EDMTrainRetrofitSingleton.getEDMTrainRetrofitInstance();
//        retrofit.create(EDMTrainInterface.class)
//                .getEDMResponse(237)
//                .enqueue(new Callback<EDMTrainResponse>() {
//                    @Override
//                    public void onResponse(Call<EDMTrainResponse> call, Response<EDMTrainResponse> response) {
//                        Log.d("joestag", "onResponse: " + response.body().getData().get(0).getVenue().getLocation());
//                    }
//
//                    @Override
//                    public void onFailure(Call<EDMTrainResponse> call, Throwable t) {
//                        Log.d("joestag", "onFailure: " + t.getMessage());
//
//
//                    }
//                });

        TechnoDjsFragment technoDjsFragment = TechnoDjsFragment.newinstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_container, technoDjsFragment);
        fragmentTransaction.commit();

//        LoginFragment loginFragment = new LoginFragment();
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.main_container, loginFragment);
//        fragmentTransaction.commit();
    }
}
