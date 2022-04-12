package com.example.hotel_application.userAccount.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotel_application.Home.MainActivity;
import com.example.hotel_application.Home.fragments.home.HomeFragment;
import com.example.hotel_application.R;
import com.example.hotel_application.api.ApiClient;
import com.example.hotel_application.utils.Constants;
import com.example.hotel_application.utils.Response.LoginResponse;
import com.example.hotel_application.utils.Response.RegisterResponse;
import com.example.hotel_application.utils.SharedPrefUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment implements View.OnClickListener {

    TextView registerGo;
    EditText emailEt, passwordET;
    LinearLayout loginBtn;
    ProgressBar circularProgress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emailEt = view.findViewById(R.id.emailET);
        passwordET = view.findViewById(R.id.passwordET);
        loginBtn = view.findViewById(R.id.loginLL);
        circularProgress = view.findViewById(R.id.circularProgress);
        loginBtn.setOnClickListener(this);

        registerGo=view.findViewById(R.id.registerTV);
        registerGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.formContainerFL, new RegisterFragment()).commit();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == loginBtn) {
            String email, password;
            email = emailEt.getText().toString();
            password = passwordET.getText().toString();
            if (email.isEmpty() || password.isEmpty())
                Toast.makeText(getContext(), "Email or Password is Empty!", Toast.LENGTH_LONG).show();
            else {
                callApiToLogin(email,password);
            }
        }

    }
    public void callApiToLogin(String email, String password){
        Call<LoginResponse> loginResponseCall= ApiClient.getApiServices().loginUser(email, password);

        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    LoginResponse loginResponse = response.body();

                    if (!loginResponse.getError()){
                        SharedPrefUtils.setString(getActivity(), Constants.API_KEY_KEY,loginResponse.getApiKey());

                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        Toast.makeText(getActivity(),"Login Success", Toast.LENGTH_LONG).show();
                        startActivity(intent);

                        SharedPrefUtils.setBoolean(getActivity(), Constants.IS_LOGGED_IN_KEY,true);
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });

    }

    public void toggleProgress(Boolean toogle) {
        if (toogle)
            circularProgress.setVisibility(View.VISIBLE);
        else
            circularProgress.setVisibility(View.GONE);
    }
}