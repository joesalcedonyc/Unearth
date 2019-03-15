package panto.technoevents.UI;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import panto.technoevents.R;
import panto.technoevents.apimodels.edmtrain.EDMTrainResponse;
import panto.technoevents.network.EventsApi;
import panto.technoevents.network.EventRetrofitProvider;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventRetrofitProvider.getInstance()
                .create(EventsApi.class)
                .getArtistEvents(237)
                .subscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::logResponse,
                        this::logThrowable);

        TechnoDjsFragment technoDjsFragment = TechnoDjsFragment.newInstance();
        toFragment(technoDjsFragment);

    }

    private void logResponse(EDMTrainResponse edmTrainResponse) {
        Log.d("EDMTrainRequest", "Response: " + edmTrainResponse
                .getEvents().get(0)
                .getVenue()
                .getAddress());
    }

    private void logThrowable(Throwable throwable) {
        Log.d("EDMTrainRequest", "Throwable: " + throwable.getMessage());
    }

    private void toFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.commit();
    }
}
