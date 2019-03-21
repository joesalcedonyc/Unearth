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

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DjsFragment djsFragment = DjsFragment.newInstance();
        toFragment(djsFragment);

    }

    @Override
    public void fragmentNavigation(DjModel djModel) {
        EventsFragment eventsFragment = EventsFragment.newInstance(djModel);
        toFragment(eventsFragment);

    }

    private void toFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_container, fragment).addToBackStack(null);
        fragmentTransaction.commit();
    }

}
