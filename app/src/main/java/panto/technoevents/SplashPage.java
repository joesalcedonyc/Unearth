package panto.technoevents;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import panto.technoevents.UI.MainActivity;

public class SplashPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);

//        new Handler().postDelayed(new Runnable() {
//            @Override public void run() {
//                Intent i = new Intent(SplashPage.this, MainActivity.class); startActivity(i);
//                finish(); } }, 3000);
    }

}
