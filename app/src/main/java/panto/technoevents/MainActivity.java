package panto.technoevents;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import panto.technoevents.fragments.LoginFragment;
import panto.technoevents.fragments.TechnoDjsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
