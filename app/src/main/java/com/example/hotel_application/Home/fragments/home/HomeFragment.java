package com.example.hotel_application.Home.fragments.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotel_application.Home.Single_Room;
import com.example.hotel_application.R;
import com.example.hotel_application.api.ApiClient;
import com.example.hotel_application.utils.Response.CategoryResponse;
import com.example.hotel_application.utils.Response.CategoryResponseData;
import com.example.hotel_application.utils.Response.RoomResponse;
import com.example.hotel_application.utils.Response.RoomResponseData;
import com.example.hotel_application.utils.adapters.CategoryAdapter;
import com.example.hotel_application.utils.adapters.RoomAdapter;
import com.example.hotel_application.utils.adapters.SliderAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements  RoomAdapter.EachRoonClick, CategoryAdapter.CategoryClick {
    RecyclerView catRV, roomRV;
    SearchView searchView;

    SliderView sliderView;
    List<CategoryResponseData> categoryList;
    CategoryAdapter categoryAdapter;

    BottomNavigationView bottomNavigationView;

    List<RoomResponseData>roomList;
    RoomAdapter roomAdapter;



    SwipeRefreshLayout swipeToRefresh;

    int[] images={R.drawable.img_4,
    R.drawable.img_1,
    R.drawable.img_2,
    R.drawable.img,
            R.drawable.img_5,
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    public void setBottomNavigationView(BottomNavigationView bottomNavigationView) {
        this.bottomNavigationView = bottomNavigationView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        catRV= view.findViewById(R.id.categoryRV);
        roomRV= view.findViewById(R.id.RoomRV);
        swipeToRefresh= view.findViewById(R.id.swipToRefresh);
        searchView= view.findViewById(R.id.search);

        sliderView=view.findViewById(R.id.imageSlider);

        SliderAdapter sliderAdapter =new SliderAdapter(images);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();



        swipeToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                categoryDataCall();
                roomDataCall();

            }
        });

        categoryDataCall();
        roomDataCall();
        searchCall();
    }



    private void searchCall() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                roomAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    private void filterByclick(String selectedCat){
        roomAdapter.getFilter().filter(selectedCat);
    }

    private void roomDataCall(){
        Call<RoomResponse> roomResponseCall=ApiClient.getApiServices().getRoom();
        roomResponseCall.enqueue(new Callback<RoomResponse>() {
            @Override
            public void onResponse(Call<RoomResponse> call, Response<RoomResponse> response) {
                if(response.isSuccessful()){
                    if(!response.body().getError()){
                        if(response.body().getRooms() != null){
                            setRoomRV(response.body().getRooms());
                        }
                        else{
                            Toast.makeText(getContext(),"No data", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
                else{
                    Toast.makeText(getContext(),"Cant Call Server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RoomResponse> call, Throwable t) {

            }
        });

    }



    private void categoryDataCall() {
        Call<CategoryResponse> categoryResponseCall= ApiClient.getApiServices().getCategory();
        categoryResponseCall.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if(response.isSuccessful()){
                    if(!response.body().getError()){
                        if(response.body().getCategories() !=null){
                            setCategoryRV(response.body().getCategories());
                        }
                        else{
                            Toast.makeText(getContext(),"No data", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
                else{
                    Toast.makeText(getContext(),"Cant Call Server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {

            }
        });
    }

    private void setCategoryRV(List<CategoryResponseData> data){
        categoryList=data;
        catRV.setHasFixedSize(true);
        catRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
        categoryAdapter= new CategoryAdapter(categoryList, getContext());
        catRV.setAdapter(categoryAdapter);
        categoryAdapter.setCategoryClick(this::onCategoryClick);
        swipeToRefresh.setRefreshing(false);
    }

    private void setRoomRV(List<RoomResponseData> data){
        roomList=data;
        roomRV.setHasFixedSize(true);
        roomRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        roomAdapter= new RoomAdapter(roomList, getContext());
        roomRV.setAdapter(roomAdapter);

        roomAdapter.setEachRommClick(this);
        swipeToRefresh.setRefreshing(false);
    }


    @Override
    public void onRoomClick(int position) {
        RoomResponseData responseData = roomList.get(position);

        Intent intent = new Intent(getContext(), Single_Room.class);
        intent.putExtra(Single_Room.RoomDataKey, responseData);
        startActivity(intent);

    }

    @Override
    public void onCategoryClick(int position) {
        CategoryResponseData categoryResponseData= categoryList.get(position);
        filterByclick(categoryResponseData.getCatName());
    }
}