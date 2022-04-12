package com.example.hotel_application.Booking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotel_application.Home.MainActivity;
import com.example.hotel_application.Home.fragments.home.HomeFragment;
import com.example.hotel_application.R;

public class Booking_Complete extends AppCompatActivity {

    TextView bookMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_complete);

        bookMore=findViewById(R.id.bookMoreTv);

        bookMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Booking_Complete.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}