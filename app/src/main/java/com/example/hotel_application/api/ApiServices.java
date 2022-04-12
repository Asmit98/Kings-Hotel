package com.example.hotel_application.api;

import com.example.hotel_application.utils.Response.BookingHistoryResponse;
import com.example.hotel_application.utils.Response.BookingHistoryResponseData;
import com.example.hotel_application.utils.Response.BookingResponse;
import com.example.hotel_application.utils.Response.CategoryResponse;
import com.example.hotel_application.utils.Response.LoginResponse;
import com.example.hotel_application.utils.Response.RegisterResponse;
import com.example.hotel_application.utils.Response.RoomResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiServices {
    @FormUrlEncoded
    @POST("/myApi/v1/register")
    Call<RegisterResponse> register(@Field("username")String username,
                                    @Field("email")String email,
                                    @Field("phone")String phone,
                                    @Field("password")String password);


    @FormUrlEncoded
    @POST("/myApi/v1/loginUser")
    Call<LoginResponse> loginUser(@Field("email")String email,
                                  @Field("password")String password);



    @GET("/myApi/v1/get-all-category")
    Call<CategoryResponse> getCategory();

    @GET("/myApi/v1/get-all-rooms")
    Call<RoomResponse> getRoom();

    @POST("/myApi/v1/view_booking_history")
    Call<BookingHistoryResponse> getBookingHistory(@Header("api_key") String apikey);


    @FormUrlEncoded
    @POST("/myApi/v1/book-room")
    Call<BookingResponse> bookRoom(@Header("api_key") String apikey,
                                   @Field("Room_id") String Room_id,
                                   @Field("NumberOf_Rooms") Integer NumberOf_Rooms,
                                   @Field("Arrival_Date") String Arrival_date,
                                   @Field("Departure_Date") String Departure_Date,
                                   @Field("Payment_Method") String Payment_Method,
                                   @Field("Payment_Status") int Payment_Status,
                                   @Field("Price") int Price);

}
