package com.example.hotel_application.Booking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotel_application.Home.Single_Room;
import com.example.hotel_application.R;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.util.Calendar;

public class Booking_Detail extends AppCompatActivity {

    TextView roomNumber, arrivalDate,departureDate;
    LinearLayout submit;
    TextView price;
    ImageView bookingBackIV;
    Button plus, minus;
    DatePickerDialog.OnDateSetListener setListener;

    public static String ROOM_NAME="room";
    public static String PRICE="price";
    public static String ROOM_ID="id";

    public static String AVAILABLE="t";
    public static String QUANTITY="q";

    public static String ADATE="a";
    public static String DDATE="d";

    int q=0;
    int p=0;
    int t=0;
    int available=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        arrivalDate=findViewById(R.id.arrivalDate);
        departureDate=findViewById(R.id.departureDate);
        roomNumber=findViewById(R.id.roomNumber);
        submit=findViewById(R.id.submitLL);
        price=findViewById(R.id.pricePay);
        plus=findViewById(R.id.plus);
        minus=findViewById(R.id.minus);
        bookingBackIV=findViewById(R.id.bookingBackIV);
        bookingBackIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        Calendar calender=Calendar.getInstance();
        final int year = calender.get(Calendar.YEAR);
        final int month = calender.get(Calendar.MONTH);
        final int day = calender.get(Calendar.DAY_OF_MONTH);

        arrivalDate.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View v) {
                DatePickerDialog datePickerDialog= new DatePickerDialog(
                        Booking_Detail.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {

                        month=month+1;
                        String date=day+"/"+month+"/"+year;
                        arrivalDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        departureDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog= new DatePickerDialog(
                        Booking_Detail.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month=month+1;
                        String date=day+"/"+month+"/"+year;
                        departureDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

//        MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker();
//        materialDateBuilder.setTitleText("SELECT A Arrival DATE");
//
//        MaterialDatePicker.Builder depatureDateBuilder = MaterialDatePicker.Builder.datePicker();
//        depatureDateBuilder.setTitleText("Select A Departure Date");
//
//        final  MaterialDatePicker materialDatePicker1 = depatureDateBuilder.build();
//
//        final MaterialDatePicker materialDatePicker = materialDateBuilder.build();
//
//        departureDate.setText("");
//        arrivalDate.setText("");
//
//        departureDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                materialDatePicker1.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
//            }
//        });
//
//         arrivalDate.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//                 materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
//             }
//         });
//
//        materialDatePicker1.addOnPositiveButtonClickListener(
//                new MaterialPickerOnPositiveButtonClickListener() {
//                    @Override
//                    public void onPositiveButtonClick(Object selection) {
//
//                        departureDate.setText(materialDatePicker1.getHeaderText()+"");
//
//                    }
//                });
//
//        materialDatePicker.addOnPositiveButtonClickListener(
//                 new MaterialPickerOnPositiveButtonClickListener() {
//                     @Override
//                     public void onPositiveButtonClick(Object selection) {
//
//                         arrivalDate.setText( materialDatePicker.getHeaderText()+"");
//
//                     }
//                 });

        q= Integer.valueOf(roomNumber.getText().toString());

        p= Integer.valueOf(getIntent().getExtras().getString(PRICE));
        available= Integer.valueOf(getIntent().getExtras().getString(AVAILABLE));

        t=p*q;

        price.setText("Submit ( "+t+" )");

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q= Integer.valueOf(roomNumber.getText().toString());

                if(q<available){
                    q++;

                    p= Integer.valueOf(getIntent().getExtras().getString(PRICE));

                    t=p*q;
                    price.setText("Submit ( "+t+" )");
                    roomNumber.setText(q+"");
                }

            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q= Integer.valueOf(roomNumber.getText().toString());
                if(q>1){
                    q--;

                    p= Integer.valueOf(getIntent().getExtras().getString(PRICE));

                    t=p*q;

                    price.setText("Submit ( "+t+" )");
                    roomNumber.setText(q+"");
                }

            }
        });





        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(arrivalDate.getText().toString().equals("") || departureDate.getText().toString().equals("")) {
                    Toast.makeText(Booking_Detail.this, "Connot be empty", Toast.LENGTH_LONG).show();
                }
                else{
                   // Toast.makeText(Booking_Detail.this, arrivalDate.getText(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getBaseContext(), Payment.class);
                    intent.putExtra(ROOM_NAME, getIntent().getExtras().getString(ROOM_NAME));
                    intent.putExtra(PRICE, String.valueOf(t));
                    intent.putExtra(ROOM_ID, getIntent().getExtras().getString(ROOM_ID));
                    intent.putExtra(QUANTITY, roomNumber.getText().toString());
                    intent.putExtra(ADATE, arrivalDate.getText().toString());
                    intent.putExtra(DDATE, departureDate.getText().toString());
                    startActivity(intent);
                }
            }
        });

    }



}