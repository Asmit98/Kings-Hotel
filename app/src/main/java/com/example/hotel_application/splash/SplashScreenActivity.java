package com.example.hotel_application.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.hotel_application.Home.MainActivity;
import com.example.hotel_application.R;
import com.example.hotel_application.userAccount.UserAccountActivity;
import com.example.hotel_application.utils.Constants;
import com.example.hotel_application.utils.SharedPrefUtils;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean is_logged=
                        SharedPrefUtils.getBool(SplashScreenActivity.this, Constants.IS_LOGGED_IN_KEY,false);
                if (is_logged)
                    startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                else
                    startActivity(new Intent(SplashScreenActivity.this, UserAccountActivity.class));
                finish();
            }
        }, 400);
    }


}