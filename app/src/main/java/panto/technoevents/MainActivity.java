package panto.technoevents;

import android.annotation.SuppressLint;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
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

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EDMTrainRetrofitSingleton.getEDMTrainRetrofitInstance()
                .create(EDMTrainInterface.class)
                .getEDMResponse(237)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<EDMTrainResponse>() {
                               @Override
                               public void accept(EDMTrainResponse edmTrainResponse) throws Exception {
                                   Log.d("EDMTrainRequest", "onNext: " + edmTrainResponse
                                           .getData().get(0)
                                           .getVenue()
                                           .getLocation());
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.d("EDMTrainRequest", "onError: " + throwable.getMessage());

                            }
                        });


        TechnoDjsFragment technoDjsFragment = TechnoDjsFragment.newinstance();
        switchFragment(technoDjsFragment);

    }

    private void switchFragment(TechnoDjsFragment technoDjsFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_container, technoDjsFragment);
        fragmentTransaction.commit();
    }
}
