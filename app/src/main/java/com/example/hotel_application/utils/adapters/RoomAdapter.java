package com.example.hotel_application.utils.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotel_application.R;
import com.example.hotel_application.utils.Response.CategoryResponseData;
import com.example.hotel_application.utils.Response.RoomResponseData;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> implements Serializable, Filterable {
    List<RoomResponseData> data;
    Context context;
    LayoutInflater inflater;
    List<RoomResponseData> searchData;
    EachRoonClick eachRoonClick;

    public RoomAdapter(List<RoomResponseData> data, Context context){
        this.context=context;
        this.data=data;
        searchData= new ArrayList<>(data);
        inflater= LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public RoomAdapter. ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_room,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomAdapter. ViewHolder holder, int position) {
        RoomResponseData roomResponseData= data.get(position);
        holder.roomName.setText(roomResponseData.getRoomName());
        //holder.NumberOfRooms.setText(roomResponseData.getAvailableNoOfRooms());
        holder.price.setText(roomResponseData.getPrice()+"");
        Picasso.get().load(roomResponseData.getImage()).into(holder.roomImage);


    }

    @Override
    public int getItemCount() {return data.size();}

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter= new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<RoomResponseData> roomResponseData= new ArrayList<>();
            if(constraint==null || constraint.length()==0){
                roomResponseData.addAll(searchData);
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (RoomResponseData roomResponseDataList:searchData){
                    if(roomResponseDataList.getRoomName().toLowerCase().contains(filterPattern)){
                        roomResponseData.add(roomResponseDataList);
                    }
                    else if(roomResponseDataList.getCatId().toString().toLowerCase().contains(filterPattern)){
                        roomResponseData.add(roomResponseDataList);
                    }
                }
            }
            FilterResults results=new FilterResults();
            results.values= roomResponseData;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            data.clear();
            data.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView roomImage;
        TextView roomName;
        TextView price,NumberOfRooms;
        LinearLayout mainLL;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roomImage= itemView.findViewById(R.id.RoomIV);
            roomName= itemView.findViewById(R.id.RoomNameTV);
            price= itemView.findViewById(R.id.PriceTV);
            NumberOfRooms=itemView.findViewById(R.id.NumberOfRoomsTV);
            mainLL= itemView.findViewById(R.id.mainLL);
//for clicking on room image.
        mainLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eachRoonClick.onRoomClick(getAdapterPosition());

            }
        });
        }
    }
    public interface EachRoonClick{
        void onRoomClick(int position);
    }
    public void setEachRommClick(EachRoonClick eachRommClick){
        this.eachRoonClick=eachRommClick;

    }
}
