package com.example.hotel_application.Booking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotel_application.Home.MainActivity;
import com.example.hotel_application.R;
import com.example.hotel_application.api.ApiClient;
import com.example.hotel_application.utils.Constants;
import com.example.hotel_application.utils.Response.BookingResponse;
import com.example.hotel_application.utils.SharedPrefUtils;
import com.khalti.checkout.helper.Config;
import com.khalti.checkout.helper.OnCheckOutListener;
import com.khalti.checkout.helper.PaymentPreference;
import com.khalti.widget.KhaltiButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Payment extends AppCompatActivity {

    TextView roomName,numberOfRoom,totalPay;
    TextView arrivalDate,departureDate;
    LinearLayout payment;
    ImageView paymentBackIV;

    KhaltiButton khaltiButton;

    int price=0;
    String room_name="rn";
    int no_of_rooms=0;
    String a_date="arrival";
    String d_date="departure";
    String room_id="id";
    long priceInPaisa=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        roomName=findViewById(R.id.RoomNameTV);
        arrivalDate=findViewById(R.id.arrivalDateTV);
        departureDate=findViewById(R.id.departureDateTV);
        numberOfRoom=findViewById(R.id.NumberOfRoomsTV);
        totalPay=findViewById(R.id.totalTV);
        payment=findViewById(R.id.paymentLL);
        paymentBackIV=findViewById(R.id.paymentBackIV);
        khaltiButton=findViewById(R.id.khalti_button);

        price=Integer.valueOf(getIntent().getStringExtra(Booking_Detail.PRICE));
        room_name=getIntent().getStringExtra(Booking_Detail.ROOM_NAME);
        a_date=String.valueOf(getIntent().getStringExtra(Booking_Detail.ADATE));
        d_date=String.valueOf(getIntent().getStringExtra(Booking_Detail.DDATE));
        no_of_rooms=Integer.valueOf(getIntent().getStringExtra(Booking_Detail.QUANTITY));
        room_id=getIntent().getStringExtra(Booking_Detail.ROOM_ID);

        roomName.setText(room_name);
        arrivalDate.setText(a_date);
        departureDate.setText(d_date);
        numberOfRoom.setText(no_of_rooms+"");
        totalPay.setText(price+"");

        paymentBackIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmBook("Cash", 0);
            }
        });

        priceInPaisa=(long) (price*100);
       khaltiButton.setCheckOutConfig(getBoulderConfig());

    }

    private Config getBoulderConfig(){
        Map<String, Object> map = new HashMap<>();
        map.put("merchant_extra", "This is extra data");

        Config.Builder builder = new Config.Builder("test_public_key_b8cb408682fb4693baef9c60e629c66b", room_id, room_name, 12000L, new OnCheckOutListener() {
            @Override
            public void onError(@NonNull String action, @NonNull Map<String, String> errorMap) {
                Log.i(action, errorMap.toString());
            }

            @Override
            public void onSuccess(@NonNull Map<String, Object> data) {
                Log.i("success", data.toString());
                confirmBook("Khalti", 1);
            }
        })
                .paymentPreferences(new ArrayList<PaymentPreference>() {{
                    add(PaymentPreference.KHALTI);

                }})
                .additionalData(map)
                .productUrl("http://example.com/product")
                .mobile("9817186566");
        return builder.build();
    }


    private void confirmBook(String paymentMethod, int paymentStatus){
        Call<BookingResponse>bookingResponseCall= ApiClient.getApiServices()
                .bookRoom(SharedPrefUtils.getString(this, Constants.API_KEY_KEY),room_id, no_of_rooms,
                        a_date,d_date,paymentMethod,paymentStatus ,price);
        bookingResponseCall.enqueue(new Callback<BookingResponse>() {
            @Override
            public void onResponse(Call<BookingResponse> call, Response<BookingResponse> response) {
                if (response.isSuccessful()){
                    if(!response.body().getError()){
                        Intent intent = new Intent(Payment.this, Booking_Complete.class);
                        startActivity(intent);
                    }
                }

            }

            @Override
            public void onFailure(Call<BookingResponse> call, Throwable t) {

            }
        });

    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_cash:
                if(checked)
                    payment.setVisibility(View.VISIBLE);
                    khaltiButton.setVisibility(View.GONE);
                break;
            case R.id.radio_Khalti:d:
                if(checked)
                    payment.setVisibility(View.GONE);
                    khaltiButton.setVisibility(View.VISIBLE);
                break;
        }
    }
}