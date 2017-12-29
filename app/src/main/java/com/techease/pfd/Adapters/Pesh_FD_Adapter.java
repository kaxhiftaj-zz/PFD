package com.techease.pfd.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.techease.pfd.Configuration.Links;
import com.techease.pfd.Controller.Pesh_FD_Model;
import com.techease.pfd.Fragments.ResutrantDetail;
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
        holder.Image_Url=peshFdModel.getImageUrl();
        holder.id=peshFdModel.getId();
        holder.editor.putString("Rest_id",peshFdModel.getId());
        holder.ratingBar.setRating(Float.parseFloat(peshFdModel.getRating()));
        Glide.with(context).load(holder.Image_Url).into(holder.imageView);
        holder.editor.putInt("No resturents",(pesh_fd_models.size()));
    }



    @Override
    public int getItemCount() {
        return pesh_fd_models.size();

    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView RestName,TvAllRestId;
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;
        String api_token,Image_Url,id;
        Typeface typeface,typeface2;
        RatingBar ratingBar;
        public MyViewHolder(View itemView) {
            super(itemView);
            sharedPreferences = context.getSharedPreferences(Links.MyPrefs, Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
            api_token=sharedPreferences.getString("api_token","");
            imageView=(ImageView)itemView.findViewById(R.id.ivPesh_FD);
            RestName=(TextView)itemView.findViewById(R.id.tvRestName);
            TvAllRestId=(TextView)itemView.findViewById(R.id.tvIdAllRest);
            ratingBar=(RatingBar)itemView.findViewById(R.id.ratingBar);
            typeface=Typeface.createFromAsset(context.getAssets(),"font/brandon_blk.otf");
            typeface2=Typeface.createFromAsset(context.getAssets(),"font/brandon_reg.otf");
            RestName.setTypeface(typeface);
            TvAllRestId.setTypeface(typeface);

            imageView.setOnClickListener(this);
            RestName.setOnClickListener(this);
            TvAllRestId.setOnClickListener(this);




        }

        @Override
        public void onClick(View v) {
            String restId=TvAllRestId.getText().toString();
            editor.putString("restId",restId);
            editor.commit();
            Fragment fragment=new ResutrantDetail();
            Bundle bundle=new Bundle();
            bundle.putString("restId",restId);
            fragment.setArguments(bundle);
            ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).addToBackStack("abc").commit();
        }
    }
}
