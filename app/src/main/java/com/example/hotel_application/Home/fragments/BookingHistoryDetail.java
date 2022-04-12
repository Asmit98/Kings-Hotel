package com.example.hotel_application.Home.fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.hotel_application.R;

public class BookingHistoryDetail extends AppCompatActivity {

    public static  String BookingHistoryDataKey="Data";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_history_detail);
    }
}