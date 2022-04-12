package com.example.hotel_application.Home.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hotel_application.R;
import com.example.hotel_application.userAccount.UserAccountActivity;
import com.example.hotel_application.utils.Constants;
import com.example.hotel_application.utils.SharedPrefUtils;


public class ProfileFragment extends Fragment {

    TextView logout;
    LinearLayout profEdit;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logout = view.findViewById(R.id.logOutTV);
        profEdit=view.findViewById(R.id.profedit);

        profEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),
                        EditProfile.class);
                startActivity(intent);
            }
        });






        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefUtils.setBoolean(getActivity(), Constants.IS_LOGGED_IN_KEY,false);
                SharedPrefUtils.setString(getActivity(),Constants.API_KEY_KEY,"");
                Intent intent=new Intent(getActivity(), UserAccountActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }
}