package panto.technoevents.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
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
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();
    }

    private void toFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).addToBackStack(null).commit();
    }
}
