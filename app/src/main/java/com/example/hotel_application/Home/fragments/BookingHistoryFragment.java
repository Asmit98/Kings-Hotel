package com.example.hotel_application.Home.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hotel_application.DataHolder;
import com.example.hotel_application.R;
import com.example.hotel_application.api.ApiClient;
import com.example.hotel_application.utils.Constants;
import com.example.hotel_application.utils.Response.BookingHistoryResponse;
import com.example.hotel_application.utils.Response.BookingHistoryResponseData;
import com.example.hotel_application.utils.SharedPrefUtils;
import com.example.hotel_application.utils.adapters.BookingHistoryDetailAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BookingHistoryFragment extends Fragment {

    RecyclerView bookingHistoryRV;
    List<BookingHistoryResponseData>historyList;
    BookingHistoryDetailAdapter bookingHistoryAdapter;

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bookingHistoryRV = view.findViewById(R.id.allBookingHistoryRV);
        getOnline();
    }

    private void getOnline(){
        Call<BookingHistoryResponse> bookingHistoryResponseCall = ApiClient.getApiServices()
                .getBookingHistory(SharedPrefUtils.getString(getActivity(), Constants.API_KEY_KEY));
        bookingHistoryResponseCall.enqueue(new Callback<BookingHistoryResponse>() {
            @Override
            public void onResponse(Call<BookingHistoryResponse> call, Response<BookingHistoryResponse> response) {
                if(response.isSuccessful()){
                    if (!response.body().getError()){
                        if(response.body().getData() !=null){
                            setBookingHistoryRV(response.body().getData());
                        }
                        else{
                            Toast.makeText(getContext(),"No data", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<BookingHistoryResponse> call, Throwable t) {

            }
        });


    }

    private void setBookingHistoryRV(List<BookingHistoryResponseData> data){
        historyList=data;
        bookingHistoryRV.setHasFixedSize(true);
        bookingHistoryRV.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        bookingHistoryAdapter=new BookingHistoryDetailAdapter(historyList,getContext());
        bookingHistoryRV.setAdapter(bookingHistoryAdapter);
        bookingHistoryAdapter.setEachHisClick(this::onHistoryClick);

    }

    public void onHistoryClick(int position){
        BookingHistoryResponseData responseData = historyList.get(position);
        Intent intent = new Intent(getContext(),BookingHistoryDetail.class);
        intent.putExtra(BookingHistoryDetail.BookingHistoryDataKey,responseData);
        startActivity(intent);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_booking_history, container, false);
    }
}