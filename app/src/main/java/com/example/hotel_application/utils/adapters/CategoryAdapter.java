package com.example.hotel_application.utils.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotel_application.R;
import com.example.hotel_application.utils.Response.CategoryResponseData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViweHolder> {
    List<CategoryResponseData> data;
    Context context;
    LayoutInflater inflater;

    CategoryClick categoryClick;

    public CategoryAdapter(List<CategoryResponseData> data, Context context){
        this.context=context;
        this.data=data;
        inflater= LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CategoryAdapter.ViweHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_category,parent,false);
        return new ViweHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViweHolder holder, int position) {
        CategoryResponseData categoryResponseData= data.get(position);
        holder.catName.setText(categoryResponseData.getCatName());
        Picasso.get().load(categoryResponseData.getCatImage()).into(holder.catImage);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViweHolder extends RecyclerView.ViewHolder {
        ImageView catImage;
        TextView catName;
        public ViweHolder(@NonNull View itemView) {
            super(itemView);
            catImage= itemView.findViewById(R.id.CatIV);
            catName= itemView.findViewById(R.id.catNameTV);
            catImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    categoryClick.onCategoryClick(getAdapterPosition());
                }
            });
        }
    }

    public  interface CategoryClick{
        void onCategoryClick(int position);
    }

    public  void setCategoryClick(CategoryClick categoryClick){
        this.categoryClick=categoryClick;
    }
}
