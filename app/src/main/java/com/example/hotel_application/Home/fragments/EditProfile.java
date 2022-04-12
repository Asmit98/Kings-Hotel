package com.example.hotel_application.Home.fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.hotel_application.R;

public class EditProfile extends AppCompatActivity {

    ImageView editProfBackIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

       editProfBackIV= findViewById(R.id.editProfileBackIV);

       editProfBackIV.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });
    }
}