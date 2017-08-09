package com.techease.pfd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

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
            startActivity(new Intent(LoginActivity.this , MainActivity.class));
            finish();
        }
    });

    }
}
