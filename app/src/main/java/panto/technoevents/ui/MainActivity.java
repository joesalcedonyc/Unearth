package panto.technoevents.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import androidx.appcompat.app.AppCompatActivity;
import panto.technoevents.R;
import panto.technoevents.apimodels.djs.DjModel;

public class MainActivity extends AppCompatActivity implements OnDJSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toDjsFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public void openEventsFragment(DjModel djModel) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, EventsFragment.newInstance(djModel))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void toDjsFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, DjsFragment.newInstance())
                .commit();
    }
}
