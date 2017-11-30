package com.techease.pfd.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.techease.pfd.Adapters.CategoriesMenuAdapter;
import com.techease.pfd.Configuration.Links;
import com.techease.pfd.Controller.CategoriesMenuModel;
import com.techease.pfd.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ResutantCategoriesFrag extends Fragment {

    RecyclerView recyclerView;
    List<CategoriesMenuModel> Cmodel;
    CategoriesMenuAdapter categoriesMenuAdapter;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
String restId,api_token,catId;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_categories, container, false);
        sharedPreferences = getActivity().getSharedPreferences(Links.MyPrefs, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        api_token=sharedPreferences.getString("api_token","");
        restId=sharedPreferences.getString("restId","");
        catId= String.valueOf(getArguments().getInt("id"));
        Log.d("zma token",api_token);
        Log.d("zma restId",restId);
        Log.d("zma catId",catId);
        recyclerView=(RecyclerView)view.findViewById(R.id.rvCategories);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Cmodel=new ArrayList<>();
        apicall();
        categoriesMenuAdapter=new CategoriesMenuAdapter(getActivity(),Cmodel);
        recyclerView.setAdapter(categoriesMenuAdapter);
        return view;
    }

    private void apicall() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://pfd.techeasesol.com/api/v1/resturants/"+restId+"/categories/"+catId+"?api_token="+api_token
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("zma Cmenu", response);


                    try {
                        JSONObject jsonObject=new JSONObject(response);
                        JSONObject jsonObj=jsonObject.getJSONObject("data");
                        Log.d("zma data",jsonObj.toString());
                        JSONArray JArray = jsonObj.getJSONArray("menu");
                            for (int j=0; j<JArray.length(); j++)
                            {
                                JSONObject Obj=JArray.getJSONObject(j);
                                CategoriesMenuModel model=new CategoriesMenuModel();
                                model.setItemName(Obj.getString("item_name"));
                                model.setItemDes(Obj.getString("description"));
                                model.setItemPrice(Obj.getString("price"));
                                JSONArray ratingArray=Obj.getJSONArray("rating");
                                for (int z=0; z<ratingArray.length(); z++)
                                {
                                    JSONObject temp=ratingArray.getJSONObject(z);
                                    model.setItemRating(temp.getString("average"));
                                }
                                Cmodel.add(model);
                        }
                        categoriesMenuAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //DialogUtils.sweetAlertDialog.dismiss();
                // DialogUtils.showErrorTypeAlertDialog(getActivity(), "Server error");
                Log.d("error" , String.valueOf(error.getCause()));

            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded;charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }

        };
        RequestQueue mRequestQueue = Volley.newRequestQueue(getActivity());
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(200000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(stringRequest);
    }

}
