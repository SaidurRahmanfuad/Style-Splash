package com.saidur.stylesplash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.saidur.stylesplash.utils.Session;

public class Splash extends AppCompatActivity {
    private ProgressBar mProgress;
    ImageView splashlogo;
    Session sessionManagement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sessionManagement = new Session(Splash.this);

        mProgress = (ProgressBar) findViewById(R.id.splash_progress);

        splashlogo = findViewById(R.id.splash_logo);
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        splashlogo.startAnimation(myFadeInAnimation);
        new Thread(new Runnable() {
            public void run() {
                doWork();
                startApp();
                finish();
            }
        }).start();
    }

    private void doWork() {
        for (int progress = 0; progress < 100; progress += 20) {
            try {
                Thread.sleep(400);
                mProgress.setProgress(progress);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void startApp() {
        sessionManagement.checkLogin();
    }
}