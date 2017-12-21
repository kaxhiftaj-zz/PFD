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
import com.techease.pfd.Controller.BestDealModel;
import com.techease.pfd.R;

import java.util.List;

/**
 * Created by Adam Noor on 08-Dec-17.
 */

public class BestDealAdapter extends RecyclerView.Adapter<BestDealAdapter.MyViewHolder> {

    Context context;
    List<BestDealModel> dealModels;


    public BestDealAdapter(Context context, List<BestDealModel> bestDealModels) {

        this.context=context;
        this.dealModels=bestDealModels;
    }
    
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_bestdeal,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final BestDealModel bestDealModel=dealModels.get(position);

        String checkFeatured=bestDealModel.getFeatured();
        if (checkFeatured.equals("1"))
        {
            holder.itemPrice.setText(bestDealModel.getItemPrice());
            holder.ItemFeatured.setText("Featured");
            holder.ItemDes.setText(bestDealModel.getItemDes());
            holder.itemName.setText(bestDealModel.getItemName());
            Glide.with(context).load(bestDealModel.getResturantImage()).into(holder.restImage);

        }
        else
            holder.ItemFeatured.setText("Value Meal");
            holder.ItemDes.setText(bestDealModel.getItemDes());
            holder.itemName.setText(bestDealModel.getItemName());
            holder.itemPrice.setText(bestDealModel.getItemPrice());
            Glide.with(context).load(bestDealModel.getResturantImage()).into(holder.restImage);

    }



    @Override
    public int getItemCount() {
        return dealModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView restImage,featuredImage;
        TextView itemName,ItemDes,ItemFeatured,itemPrice;
        Typeface typeface,typeface2;
        public MyViewHolder(View itemView) {
            super(itemView);

            restImage=(ImageView)itemView.findViewById(R.id.ivCustomBestDeal);
            featuredImage=(ImageView)itemView.findViewById(R.id.ivCustomBestDealFeatured);
            itemName=(TextView)itemView.findViewById(R.id.tvItemNameBestDeal);
            itemPrice=(TextView)itemView.findViewById(R.id.tvitemPriceBestDeal);
            ItemDes=(TextView)itemView.findViewById(R.id.tvDesBestDeal);
            ItemFeatured=(TextView)itemView.findViewById(R.id.tvFeaturedBestDeal);
            typeface=Typeface.createFromAsset(context.getAssets(),"font/brandon_blk.otf");
            typeface2=Typeface.createFromAsset(context.getAssets(),"font/brandon_reg.otf");

            itemName.setTypeface(typeface);
            itemPrice.setTypeface(typeface2);
            ItemDes.setTypeface(typeface2);
            ItemFeatured.setTypeface(typeface2);


        }
    }
}
