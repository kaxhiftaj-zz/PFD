package com.techease.pfd.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        holder.story.setText(data.getStory());
        holder.time.setText(data.getUpdated_time());
        holder.id.setText(data.getId());

    }



    @Override
    public int getItemCount() {
        return Gmodels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView story,time,id;
        public MyViewHolder(View itemView) {

            super(itemView);
            story=(TextView)itemView.findViewById(R.id.tvStory);
            time=(TextView)itemView.findViewById(R.id.tvUpdated);
            id=(TextView)itemView.findViewById(R.id.tvId);
        }
    }
}
