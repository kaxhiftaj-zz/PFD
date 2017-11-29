package com.techease.pfd.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techease.pfd.Controller.CategoriesMenuModel;
import com.techease.pfd.R;

import java.util.List;

/**
 * Created by Adam Noor on 29-Nov-17.
 */

public class CategoriesMenuAdapter extends RecyclerView.Adapter<CategoriesMenuAdapter.MyViewHolder> {

    Context context;
    List<CategoriesMenuModel> categoriesMenuModelList;


    public CategoriesMenuAdapter(Context context, List<CategoriesMenuModel> cmodel) {
        this.context=context;
        this.categoriesMenuModelList=cmodel;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_categories_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final CategoriesMenuModel model=categoriesMenuModelList.get(position);
        holder.ItemName.setText(model.getItemName());
        holder.ItemPrice.setText(model.getItemPrice());
        holder.ItemDes.setText(model.getItemDes());

    }



    @Override
    public int getItemCount() {
        return categoriesMenuModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView ItemName,ItemPrice,ItemDes;
        Typeface typeface,typeface2;
        public MyViewHolder(View itemView) {
            super(itemView);
            ItemName=(TextView)itemView.findViewById(R.id.tvCatItemName);
            ItemDes=(TextView)itemView.findViewById(R.id.tvCatItemDes);
            ItemPrice=(TextView)itemView.findViewById(R.id.tvCatItemPrice);

            typeface=Typeface.createFromAsset(context.getAssets(),"font/brandon_reg.otf");
            typeface2=Typeface.createFromAsset(context.getAssets(),"font/brandon_bld.otf");

            ItemName.setTypeface(typeface2);
            ItemPrice.setTypeface(typeface2);
            ItemDes.setTypeface(typeface);

        }
    }
}
