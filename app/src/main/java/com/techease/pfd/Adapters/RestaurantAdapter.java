package com.techease.pfd.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.techease.pfd.Controller.AppController;
import com.techease.pfd.Controller.Resturants;
import com.techease.pfd.R;

import java.util.List;

/**
 * Created by kaxhiftaj on 8/9/17.
 */

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {
    List<Resturants> myList;
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvRestaurantName;
        ImageView ivRestaurantImage;

        public ViewHolder(View itemView) {
            super(itemView);
            tvRestaurantName = (TextView)itemView.findViewById(R.id.restName);
            ivRestaurantImage = (ImageView)itemView.findViewById(R.id.restImage);
        }
    }

    public RestaurantAdapter(List<Resturants> myList){
        this.myList = myList;
    }
    @Override
    public RestaurantAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_resturant_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RestaurantAdapter.ViewHolder holder, int position) {
        holder.tvRestaurantName.setText(myList.get(position).);
        holder.ivRestaurantImage.setImageResource(myList.get(position).getrImg());

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
