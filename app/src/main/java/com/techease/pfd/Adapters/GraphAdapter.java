package com.techease.pfd.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        final

    }



    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
