package com.example.hotel_application.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hotel_application.Booking.Booking_Detail;
import com.example.hotel_application.R;
import com.example.hotel_application.utils.Response.RoomResponseData;
import com.squareup.picasso.Picasso;

public class Single_Room extends AppCompatActivity {
    //declear
    public static  String RoomDataKey ="Data";

    ImageView RoomIV;
    TextView roomNameTV,decTV,RoomPriceTV,NumberOfRooms;
    LinearLayout BookRoom;

    RoomResponseData roomResponseData;

    boolean isAdding = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_room);

        //inisilize

        RoomIV=findViewById(R.id.RoomIV);
        roomNameTV= findViewById(R.id.roomNameTV);
        decTV=findViewById(R.id.decTV);
        RoomPriceTV=findViewById(R.id.RoomPriceTV);
        BookRoom=findViewById(R.id.BookRoom);
        NumberOfRooms=findViewById(R.id.NumberOfRoomsTV);

        roomResponseData=(RoomResponseData)getIntent().getSerializableExtra(RoomDataKey);
        roomNameTV.setText(roomResponseData.getRoomName());
        decTV.setText(roomResponseData.getRoomDesc());
        RoomPriceTV.setText("Rs." + roomResponseData.getPrice());

        NumberOfRooms.setText(roomResponseData.getAvailableNoOfRooms()+"");

        Picasso.get().load(roomResponseData.getImage()).into(RoomIV);

       getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        BookRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBooking();
            }


        });


    }
    private void openBooking() {
        Intent intent= new Intent(getBaseContext(), Booking_Detail.class);
        intent.putExtra(Booking_Detail.ROOM_NAME,roomResponseData.getRoomName());
        intent.putExtra(Booking_Detail.PRICE,roomResponseData.getPrice().toString());
        intent.putExtra(Booking_Detail.AVAILABLE,roomResponseData.getAvailableNoOfRooms().toString());
        intent.putExtra(Booking_Detail.ROOM_ID,roomResponseData.getRoomId().toString());
        startActivity(intent);
    }
}