package com.example.hotel_application.userAccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotel_application.Home.MainActivity;
import com.example.hotel_application.R;
import com.example.hotel_application.userAccount.fragments.LoginFragment;
import com.example.hotel_application.userAccount.fragments.RegisterFragment;

public class UserAccountActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);

        LoginFragment loginFragments = new LoginFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.formContainerFL, loginFragments).commit();

    }









}