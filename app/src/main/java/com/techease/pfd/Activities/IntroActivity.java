package com.techease.pfd.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.techease.pfd.Fragments.SliderFragmanet;

public class IntroActivity extends AppIntro {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Note here that we DO NOT use setContentView();

        // Add your slide fragments here.
        // AppIntro will automatically generate the dots indicator and buttons.

        addSlide(SliderFragmanet.newInstance(0));
        addSlide(SliderFragmanet.newInstance(1));
        addSlide(SliderFragmanet.newInstance(2));




    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        // Do something when users tap on Done button.

        sharedPreferences = this.getSharedPreferences("com.pdf", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String token=sharedPreferences.getString("api_token","");
        if (!token.equals(""))
        {
            startActivity(new Intent(IntroActivity.this,Dashboard.class));
            finish();
        }
        Intent intent = new Intent(IntroActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.

    }
}