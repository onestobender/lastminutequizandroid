package com.giumig.apps.lastminutequiz.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import com.giumig.apps.lastminutequiz.R;

/**
 * Created by gius on 13/12/16.
 */

public class SplashActivity extends AppCompatActivity {

    private Handler splashHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashHandler = new Handler();
        splashHandler.postDelayed(launcherRunnable, 1500);
    }

    private Runnable launcherRunnable = new Runnable() {
        @Override
        public void run() {
            launchMainActivity();
         }
    };



    private void launchMainActivity()   {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        SplashActivity.this.startActivity(intent);
        SplashActivity.this.finish();
    }


}
