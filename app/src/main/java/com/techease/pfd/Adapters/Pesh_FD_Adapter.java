package com.techease.pfd.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.techease.pfd.Configuration.Links;
import com.techease.pfd.Controller.Pesh_FD_Model;
import com.techease.pfd.R;

import java.util.List;

/**
 * Created by Adam Noor on 14-Nov-17.
 */

public class Pesh_FD_Adapter extends RecyclerView.Adapter<Pesh_FD_Adapter.MyViewHolder> {
    Context context;
    List<Pesh_FD_Model> pesh_fd_models;


    public Pesh_FD_Adapter(Context context, List<Pesh_FD_Model> models) {
        this.context=context;
        this.pesh_fd_models=models;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_pesh_fd,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Pesh_FD_Model peshFdModel=pesh_fd_models.get(position);
        holder.RestName.setText(peshFdModel.getRestName());
        holder.TvAllRestId.setText(peshFdModel.getId());
        Glide.with(context).load("http://pfd.techeasesol.com/api/v1/resturants?api_token="+holder.api_token).into(holder.imageView);


    }



    @Override
    public int getItemCount() {
        return pesh_fd_models.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView RestName,TvAllRestId;
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;
        String api_token;
        public MyViewHolder(View itemView) {
            super(itemView);
            sharedPreferences = context.getSharedPreferences(Links.MyPrefs, Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
            api_token=sharedPreferences.getString("api_token","");
            imageView=(ImageView)itemView.findViewById(R.id.ivPesh_FD);
            RestName=(TextView)itemView.findViewById(R.id.tvRestName);
            TvAllRestId=(TextView)itemView.findViewById(R.id.tvIdAllRest);
        }
    }
}
