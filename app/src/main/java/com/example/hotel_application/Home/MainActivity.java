package com.example.hotel_application.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.hotel_application.Home.fragments.BookingHistoryFragment;
import com.example.hotel_application.Home.fragments.CategoryFragment;
import com.example.hotel_application.Home.fragments.EventsFragment;
import com.example.hotel_application.Home.fragments.ProfileFragment;
import com.example.hotel_application.Home.fragments.home.HomeFragment;
import com.example.hotel_application.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment;
    ProfileFragment profileFragment;
    EventsFragment eventsFragment;
    BookingHistoryFragment bookingHistoryFragment;
    Fragment currentFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.homeBottomNav);
        homeFragment = new HomeFragment();
        bookingHistoryFragment=new BookingHistoryFragment();
        eventsFragment= new EventsFragment();
        profileFragment= new ProfileFragment();


        currentFragment = homeFragment;
        getSupportFragmentManager().beginTransaction().add(R.id.homeFrameContainer, currentFragment).commit();

        homeFragment.setBottomNavigationView(bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                if (item.getTitle().equals(getString(R.string.home))) {
                    changeFragment(homeFragment);
                    return true;

                }
                if (item.getTitle().equals(getString(R.string.bookingHistory))) {

                    changeFragment(bookingHistoryFragment);
                    return true;
                }
                if (item.getTitle().equals(getString(R.string.events))) {

                    changeFragment(eventsFragment);
                    return true;
                }

                if (item.getTitle().equals(getString(R.string.profile))) {

                    changeFragment(profileFragment);
                    return true;
                }
                return false;
            }
        });
    }

    void changeFragment(Fragment fragment) {

        if (fragment == currentFragment)
          return;

        getSupportFragmentManager().beginTransaction().hide(currentFragment).commit();
        if (fragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().show(fragment).commit();
        } else {
            getSupportFragmentManager().beginTransaction().add(R.id.homeFrameContainer, fragment).commit();
        }
        currentFragment = fragment;
    }

    }
