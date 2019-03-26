package panto.technoevents.ui;

import android.annotation.SuppressLint;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import panto.technoevents.R;
import panto.technoevents.apimodels.djs.DjModel;

public class MainActivity extends AppCompatActivity implements onDjSelectedListener {

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DjsFragment djsFragment = DjsFragment.newInstance();
        toDjsFragment(djsFragment);

    }

    @Override
    public void fragmentNavigation(DjModel djModel) {
        EventsFragment eventsFragment = EventsFragment.newInstance(djModel);
        toFragment(eventsFragment);

    }

    private void toDjsFragment(Fragment fragment) {
        FragmentManager fragmentManager = get;
        Transa fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.commit();
    }

    private void toFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_container, fragment).addToBackStack(null);
        fragmentTransaction.commit();
    }
}
