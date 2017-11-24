package com.techease.pfd.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.techease.pfd.Controller.GraphModel;
import com.techease.pfd.R;

import java.util.List;

/**
 * Created by Adam Noor on 23-Nov-17.
 */

public class GraphAdapter extends RecyclerView.Adapter<GraphAdapter.MyViewHolder> {

    private List<GraphModel> Gmodels;
    Context context;


    public GraphAdapter(Context context, List<GraphModel> models) {
        this.context=context;
        this.Gmodels=models;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootview= LayoutInflater.from(parent.getContext()).inflate(R.layout.customgraph,parent,false);
        return new MyViewHolder(rootview);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final GraphModel data=Gmodels.get(position);
        holder.PostStory.setText(data.getStory());
        holder.PostMessage.setText(data.getPostMessage());
        holder.PostName.setText(data.getPostName());
        Glide.with(context).load(data.getImageUrl()).into(holder.imageView);
    }



    @Override
    public int getItemCount() {
        return Gmodels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView PostStory,PostName,PostMessage;
        ImageView imageView;
        Typeface typeface,typeface2;
        public MyViewHolder(View itemView) {

            super(itemView);
            PostStory=(TextView)itemView.findViewById(R.id.tvStory);
            PostName=(TextView)itemView.findViewById(R.id.PostName);
            PostMessage=(TextView)itemView.findViewById(R.id.tvPostMessage);
            imageView=(ImageView)itemView.findViewById(R.id.ivPostPic);
            typeface=Typeface.createFromAsset(context.getAssets(),"font/brandon_bld.otf");
            typeface2=Typeface.createFromAsset(context.getAssets(),"font/brandon_reg.otf");
            PostName.setTypeface(typeface);
            PostMessage.setTypeface(typeface2);
            PostStory.setTypeface(typeface);

        }

    }
}
