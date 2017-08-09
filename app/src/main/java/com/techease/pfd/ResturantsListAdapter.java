package com.techease.pfd;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by kaxhiftaj on 8/9/17.
 */

public class ResturantsListAdapter  extends  RecyclerView.Adapter<ResturantsListAdapter.ViewHolder>{

    public TextView name;
    public ImageView image;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(View itemView) {
            super(itemView);

            name = ( TextView)itemView.findViewById(R.id.restName);
            image = (ImageView)itemView.findViewById(R.id.restImage);
            
        }
    }
}
