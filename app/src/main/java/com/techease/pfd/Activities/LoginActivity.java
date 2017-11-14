package com.techease.pfd.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.techease.pfd.R;

public class LoginActivity extends AppCompatActivity {

    ImageButton fbLogin ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fbLogin = (ImageButton)findViewById(R.id.fblogin);
    fbLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            
        }
    });

    }
}
