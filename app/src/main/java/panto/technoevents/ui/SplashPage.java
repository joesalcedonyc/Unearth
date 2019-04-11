package panto.technoevents.ui;

import androidx.appcompat.app.AppCompatActivity;
import panto.technoevents.R;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashPage extends AppCompatActivity {
    private int splashPageDuration = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);

        new Handler().postDelayed(() -> {

            startActivity(new Intent(SplashPage.this, MainActivity.class));
            finish();
        }, splashPageDuration);
    }
}
