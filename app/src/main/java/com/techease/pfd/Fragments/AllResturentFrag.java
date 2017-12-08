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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.techease.pfd.Adapters.Pesh_FD_Adapter;
import com.techease.pfd.Utils.CheckNetwork;
import com.techease.pfd.Configuration.Links;
import com.techease.pfd.Controller.Pesh_FD_Model;
import com.techease.pfd.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AllResturentFrag extends Fragment {

    android.widget.SearchView searchView;
    RecyclerView recyclerView;
    List<Pesh_FD_Model> PFDmodels;
    Pesh_FD_Adapter pesh_fd_adapter;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String api_token;
    ProgressBar progressBar;
    int progressbarstatus = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.allresturantfrag, container, false);

        if(CheckNetwork.isInternetAvailable(getActivity()))
        {
            progressBar=(ProgressBar)view.findViewById(R.id.progress_bar);
            searchView=(android.widget.SearchView) view.findViewById(R.id.sv);
            searchView.setQueryHint("Search Here");
            sharedPreferences = getActivity().getSharedPreferences(Links.MyPrefs, Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
            api_token=sharedPreferences.getString("api_token","");
            recyclerView=(RecyclerView)view.findViewById(R.id.rvPesh_FD);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            PFDmodels=new ArrayList<>();
            apicall();
            pesh_fd_adapter=new Pesh_FD_Adapter(getActivity(),PFDmodels);
            recyclerView.setAdapter(pesh_fd_adapter);

        }
        else
        {
            Toast.makeText(getActivity(),"No Internet Connection",Toast.LENGTH_SHORT).show();
        }



        return view;
    }

    private void apicall() {
        progressBar.setVisibility(View.VISIBLE);
       setProgressValue(progressbarstatus);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://pfd.techeasesol.com/api/v1/resturants?api_token="+api_token
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("rest respo", response);
                    try {
                        JSONObject jsonObject=new JSONObject(response);
                        JSONArray jsonArr=jsonObject.getJSONArray("data");
                        for (int i=0; i<jsonArr.length(); i++)
                        {
                            JSONObject temp = jsonArr.getJSONObject(i);
                            Pesh_FD_Model model=new Pesh_FD_Model();
                            model.setRestName(temp.getString("name"));
                            model.setId(temp.getString("id"));
                            model.setImageUrl(temp.getString("image_url"));
                            model.setLocation(temp.getString("location"));
                            JSONArray ratingArray = temp.getJSONArray("rating");
                            for (int j = 0; j<ratingArray.length();j++){
                                JSONObject tempRating = ratingArray.getJSONObject(j);
                                model.setRating(tempRating.getString("average"));
                            }

                            PFDmodels.add(model);
                            progressBar.setVisibility(View.INVISIBLE);

                        }
                        pesh_fd_adapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
              progressBar.setVisibility(View.INVISIBLE);
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
    private void setProgressValue(final int progress) {

        // set the progress
        progressBar.setProgress(progress);
        // thread is used to change the progress value
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setProgressValue(progress + 10);
            }
        });
        thread.start();
    }
}
