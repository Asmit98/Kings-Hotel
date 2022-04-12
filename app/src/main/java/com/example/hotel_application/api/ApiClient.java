package com.example.hotel_application.api;

import static com.example.hotel_application.utils.Constants.Base_URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static ApiServices getApiServices(){
        HttpLoggingInterceptor interceptorObj = new HttpLoggingInterceptor();
        interceptorObj.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient clientObj = new OkHttpClient.Builder().addInterceptor(interceptorObj).build();

        Retrofit retrofit=new Retrofit.Builder().baseUrl(Base_URL)
                .addConverterFactory(GsonConverterFactory
                .create(new GsonBuilder().create()))
                .client(clientObj).build();

        return retrofit.create(ApiServices.class);
    }
}
