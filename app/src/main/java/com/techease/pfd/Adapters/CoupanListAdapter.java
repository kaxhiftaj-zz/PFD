package com.techease.pfd.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techease.pfd.Controller.CoupanListModel;
import com.techease.pfd.Fragments.CoupansFrag;
import com.techease.pfd.R;

import java.util.List;

/**
 * Created by Adam Noor on 08-Dec-17.
 */

public class CoupanListAdapter extends RecyclerView.Adapter<CoupanListAdapter.MyViewHolder> {

    Context context;
    List<CoupanListModel> coupanListModels;
    public CoupanListAdapter(Context context, List<CoupanListModel> coupanListModels) {

        this.context=context;
        this.coupanListModels=coupanListModels;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_of_coupans,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final CoupanListModel model=coupanListModels.get(position);

        Log.d("addd",model.getCoupanCode());
        holder.coupanCode.setText(model.getCoupanCode());
        holder.coupanDiscountType.setText(model.getDiscountType());
        holder.coupanDiscount.setText(model.getDiscountNo());
        holder.coupanDate.setText(model.getCoupanDate());
    }


    @Override
    public int getItemCount() {
        return coupanListModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView coupanCode,coupanDate,coupanDiscount,coupanDiscountType;
        Typeface typeface,typeface2;

        public MyViewHolder(View itemView) {
            super(itemView);

            coupanCode=(TextView)itemView.findViewById(R.id.coupanListItemCode);
            coupanDate=(TextView)itemView.findViewById(R.id.CoupanExpiryDate);
            coupanDiscount=(TextView)itemView.findViewById(R.id.CoupanDiscount);
            coupanDiscountType=(TextView)itemView.findViewById(R.id.coupanDiscountType);

            typeface=Typeface.createFromAsset(context.getAssets(),"font/brandon_blk.otf");
            typeface2=Typeface.createFromAsset(context.getAssets(),"font/brandon_reg.otf");

            coupanCode.setTypeface(typeface);
            coupanDiscountType.setTypeface(typeface2);
            coupanDiscount.setTypeface(typeface);
            coupanDate.setTypeface(typeface2);

            coupanCode.setOnClickListener(this);
            coupanDate.setOnClickListener(this);
            coupanDiscount.setOnClickListener(this);
            coupanDiscountType.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {

            CoupansFrag fragment=new CoupansFrag();
            ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();


        }
    }
}
