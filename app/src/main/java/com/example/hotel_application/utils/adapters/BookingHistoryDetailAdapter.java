package com.example.hotel_application.utils.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotel_application.R;
import com.example.hotel_application.utils.Response.BookingHistoryResponseData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BookingHistoryDetailAdapter extends RecyclerView.Adapter<BookingHistoryDetailAdapter.ViewHolder> {

    List<BookingHistoryResponseData> data;
    Context context;
    LayoutInflater inflater;
    List<BookingHistoryResponseData>searchData;
    EachHis eachHis;

    public BookingHistoryDetailAdapter(List<BookingHistoryResponseData> data, Context context){
        this.context=context;
        this.data=data;
        searchData= new ArrayList<>(data);
        inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public BookingHistoryDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_booking_history,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingHistoryDetailAdapter.ViewHolder holder, int position) {

        BookingHistoryResponseData bookingHistoryResponseData = data.get(position);
        holder.roomNameTV.setText(bookingHistoryResponseData.getRoomName());
        holder.bookingDateTV.setText(bookingHistoryResponseData.getBookingDate());
        holder.paymentStatusTV.setText(bookingHistoryResponseData.getPaymentStatus()+"");
        holder.paymentMethodTV.setText(bookingHistoryResponseData.getPaymentMethod());
        Picasso.get().load(bookingHistoryResponseData.getRoomImage()).into(holder.roomIV);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout mainLL;
        ImageView deleteIV, roomIV;
        TextView roomNameTV,bookingDateTV, paymentMethodTV, paymentStatusTV;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            mainLL = itemView.findViewById(R.id.mainLL);
            deleteIV = itemView.findViewById(R.id.removebookIV);
            roomIV = itemView.findViewById(R.id.roomIV);
            roomNameTV = itemView.findViewById(R.id.roomNameTV);
            bookingDateTV = itemView.findViewById(R.id.bookingDateTV);
            paymentMethodTV = itemView.findViewById(R.id.paymentMethod);
            paymentStatusTV = itemView.findViewById(R.id.paymentStatus);

            mainLL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    eachHis.onHistoryClick(getAdapterPosition());
                }
            });

        }
    }

    public interface EachHis{

        void onHistoryClick(int Position);
    }
    public void setEachHisClick(EachHis eachHis1 ){
        this.eachHis=eachHis1;
    }
}
