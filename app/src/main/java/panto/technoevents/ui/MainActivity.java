package panto.technoevents.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.github:
                showGithub();
                break;
            case R.id.linkedin:
                showLinkedIn();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }

    private void showLinkedIn() {
        Uri uri  = Uri.parse("https://www.linkedin.com/in/josephsalcedo/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    private void showGithub() {
        Uri uri  = Uri.parse("https://github.com/joesalcedonyc/TechnoEvents");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.search_menu, menu);
//        return true;
//    }

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
