package panto.technoevents.UI;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import panto.technoevents.FragmentInterface;
import panto.technoevents.R;
import panto.technoevents.apimodels.djs.DjModel;

public class MainActivity extends AppCompatActivity implements FragmentInterface {
    public static final String KEY_VENUE = "venue";
    public static final String KEY_DATE = "date";
    public static final String KEY_LOCATION = "location";
    public static final String KEY_ADDRESS = "address";

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DjsFragment djsFragment = DjsFragment.newInstance();
        toFragment(djsFragment);

    }

    public void switchToEventListFrag() {

//        EventsFragment eventListFragment = EventsFragment.newInstance();
//        Bundle bundle = new Bundle();
//        addArgsToBundle();
//        eventListFragment.setArguments();

//        toFragment(eventListFragment);


    }

    private void addArgsToBundle(Bundle bundle, String venue, String date, String location, String address) {
        bundle.putString(KEY_VENUE, venue);
        bundle.putString(KEY_DATE, date);
        bundle.putString(KEY_LOCATION, location);
        bundle.putString(KEY_ADDRESS, address);
    }

    private void toFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_container, fragment).addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void fragmentNavigation(DjModel djModel) {
        EventsFragment eventsFragment = EventsFragment.newInstance(djModel);
        toFragment(eventsFragment);

    }

}
