package com.example.hotel_application.userAccount.fragments;

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

import com.example.hotel_application.R;
import com.example.hotel_application.api.ApiClient;
import com.example.hotel_application.userAccount.UserAccountActivity;
import com.example.hotel_application.utils.Response.RegisterResponse;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends Fragment {
    EditText ET, passwordET, confirmPasswordET, nameET, phoneET;
    LinearLayout registerLL;
    ProgressBar circularProgress;
    TextView loginGo;


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ET = view.findViewById(R.id.ET);
        nameET = view.findViewById(R.id.nameET);
        phoneET = view.findViewById(R.id.phoneET);
        passwordET = view.findViewById(R.id.passwordET);
        confirmPasswordET = view.findViewById(R.id.confirmPasswordET);
        circularProgress = view.findViewById(R.id.circularProgress);
        registerLL = view.findViewById(R.id.registerLL);
        loginGo = view.findViewById(R.id.loginTV);

        loginGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.formContainerFL, new LoginFragment()).commit();
            }
        });
        registerLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    callApiToRegister(nameET.getText().toString(), ET.getText().toString(), phoneET.getText().toString(), passwordET.getText().toString());
                }
            }
        });

    }

    void toggleLoading(Boolean toogle) {
        if (toogle)
            circularProgress.setVisibility(View.VISIBLE);
        else
            circularProgress.setVisibility(View.GONE);
    }

    public void callApiToRegister(String username, String email, String phone, String password){
        Call<RegisterResponse> registerResponseCall=ApiClient.getApiServices()
                .register(username, email, phone, password);

        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()){
                    RegisterResponse registerResponse= response.body();

                    if (!registerResponse.getError()){
                        Toast.makeText(getActivity(),registerResponse.getMessage(), Toast.LENGTH_LONG).show();
                        Intent intent= new Intent(getActivity(), UserAccountActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Snackbar.make(getView(),"Login credentials failed",Snackbar.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

            }
        });

    }

    public boolean validate() {
        boolean validate = true;
        if (ET.getText().toString().isEmpty()
                || passwordET.getText().toString().isEmpty()
                || confirmPasswordET.getText().toString().isEmpty()
                || nameET.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "None of the above fields can be empty", Toast.LENGTH_SHORT).show();
            validate = false;
        } else if (!passwordET.getText().toString().equals(confirmPasswordET.getText().toString())) {
            confirmPasswordET.setError("Password doesnot match please check!!");
            validate = false;

        }

        return validate;
    }

}