package com.example.hotel_application.utils.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hotel_application.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.ViewHolder> {
int[] images;

public SliderAdapter(int[] images){this.images=images;}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item,parent,false);
        return new ViewHolder(view) {
        };
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.imageView.setImageResource(images[position]);
    }



    @Override
    public int getCount() {
        return images.length;

    }
    public class ViewHolder extends SliderViewAdapter.ViewHolder{

        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView= itemView.findViewById(R.id.image_view);
        }
    }
}
