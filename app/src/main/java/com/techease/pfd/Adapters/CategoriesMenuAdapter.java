package com.techease.pfd.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.techease.pfd.Configuration.Links;
import com.techease.pfd.Controller.CategoriesMenuModel;
import com.techease.pfd.R;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Adam Noor on 29-Nov-17.
 */

public class CategoriesMenuAdapter extends RecyclerView.Adapter<CategoriesMenuAdapter.MyViewHolder> {

    Context context;
    List<CategoriesMenuModel> categoriesMenuModelList;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String menu_id,tokenId,reviewMessage,ratingValue;


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
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final CategoriesMenuModel model=categoriesMenuModelList.get(position);
        holder.ItemName.setText(model.getItemName());
        holder.ItemPrice.setText(model.getItemPrice());
        holder.ItemDes.setText(model.getItemDes());
        holder.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                sharedPreferences = context.getSharedPreferences(Links.MyPrefs, Context.MODE_PRIVATE);
                editor = sharedPreferences.edit();

               holder.ratingBar.setRating(rating);
                holder.ratingBar.setIsIndicator(true);
                 menu_id=model.getItemId();
              tokenId=sharedPreferences.getString("api_token","");
               reviewMessage="This is review";
                 ratingValue=String.valueOf(rating);
                apicall();


            }
        });

    }

    private void apicall() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://pfd.techeasesol.com/api/v1/ratings", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("zma respoonse", response);
                //  DialogUtils.sweetAlertDialog.dismiss();
                Toast.makeText(context, "Menu item successfully rated", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                DialogUtils.sweetAlertDialog.dismiss();
//                final SweetAlertDialog pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE);
//                pDialog.getProgressHelper().setBarColor(Color.parseColor("#295786"));
//                pDialog.setTitleText("Email or Password incorrect");
//                pDialog.setConfirmText("OK");
//                pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                    @Override
//                    public void onClick(SweetAlertDialog sweetAlertDialog) {
//                        pDialog.dismissWithAnimation();
//                    }
//                });
//                pDialog.show();
                Log.d("zma error", String.valueOf(error.getCause()));
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded;charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("api_token", tokenId);
                params.put("menu_id", menu_id);
                params.put("review",reviewMessage);
                params.put("rating",ratingValue);
                Log.d("zma params", String.valueOf(params));
                return checkParams(params);
            }
        };

        RequestQueue mRequestQueue = Volley.newRequestQueue(context);
        stringRequest.setRetryPolicy(new

                DefaultRetryPolicy(200000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(stringRequest);

    }
    private Map<String, String> checkParams(Map<String, String> map){
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> pairs = (Map.Entry<String, String>)it.next();
            if(pairs.getValue()==null){
                map.put(pairs.getKey(), "");
            }
        }
        return map;


    }


    @Override
    public int getItemCount() {
        return categoriesMenuModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView ItemName,ItemPrice,ItemDes;
        Typeface typeface,typeface2;
        RatingBar ratingBar;
        public MyViewHolder(View itemView) {
            super(itemView);
            ItemName=(TextView)itemView.findViewById(R.id.tvCatItemName);
            ItemDes=(TextView)itemView.findViewById(R.id.tvCatItemDes);
            ItemPrice=(TextView)itemView.findViewById(R.id.tvCatItemPrice);
            ratingBar=(RatingBar)itemView.findViewById(R.id.ratingBarItem);

            typeface=Typeface.createFromAsset(context.getAssets(),"font/brandon_reg.otf");
            typeface2=Typeface.createFromAsset(context.getAssets(),"font/brandon_bld.otf");

            ItemName.setTypeface(typeface2);
            ItemPrice.setTypeface(typeface2);
            ItemDes.setTypeface(typeface);

        }
    }
}
