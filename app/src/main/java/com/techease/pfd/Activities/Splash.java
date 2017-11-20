package com.techease.pfd.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.techease.pfd.R;

public class Splash extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                    startActivity(new Intent(Splash.this, IntroActivity.class));
                    finish();
                }
            }
        };
        timer.start();


    }
}
