package com.techease.pfd.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.techease.pfd.Adapters.BestDealAdapter;
import com.techease.pfd.Configuration.Links;
import com.techease.pfd.Controller.BestDealModel;
import com.techease.pfd.R;
import com.techease.pfd.Utils.CheckNetwork;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BestDeal extends Fragment {

    RecyclerView recyclerView;
    List<BestDealModel> bestDealModels;
    BestDealAdapter bestDealAdapter;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String api_token;
    MaterialSearchBar searchView;
    ProgressBar progressBar;
    int progressbarstatus = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_best_deal, container, false);

        //Declaration
        recyclerView=(RecyclerView)view.findViewById(R.id.rvBestDeal);
        searchView=(MaterialSearchBar) view.findViewById(R.id.svBestdeal);
        progressBar=(ProgressBar)view.findViewById(R.id.progress_barBestDeal);
        searchEducationList();
        if(CheckNetwork.isInternetAvailable(getActivity()))
        {

            sharedPreferences = getActivity().getSharedPreferences(Links.MyPrefs, Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
            api_token=sharedPreferences.getString("api_token","");
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            bestDealModels=new ArrayList<>();
            apicall();
            bestDealAdapter=new BestDealAdapter(getActivity(),bestDealModels);
            recyclerView.setAdapter(bestDealAdapter);
        }
        else
        {
            Toast.makeText(getActivity(),"No Internet Connection",Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    private void searchEducationList() {
        searchView.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence query, int i, int i1, int i2) {
                Log.d("LOG_TAG", getClass().getSimpleName() + " text changed " + searchView.getText());

                query = query.toString().toLowerCase();
                // Toast.makeText(TrafficSigns.this, "Query is: "+query, Toast.LENGTH_SHORT).show();
                List<BestDealModel> newData = new ArrayList<>();
                for (int j = 0; j < bestDealModels.size(); j++) {
                    final String test2 = bestDealModels.get(j).getItemName().toLowerCase();

                    if (test2.startsWith(String.valueOf(query))) {
                        newData.add(bestDealModels.get(j));
                    }
                }
                // specify an adapter (see also next example)
                bestDealAdapter = new BestDealAdapter(getActivity(), newData);
                recyclerView.setAdapter(bestDealAdapter);
                bestDealAdapter.notifyDataSetChanged();
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void apicall() {
        progressBar.setVisibility(View.VISIBLE);
        setProgressValue(progressbarstatus);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://pfd.techeasesol.com/api/v1/featured?api_token="+api_token
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
                        BestDealModel model=new BestDealModel();
                        model.setFeatured(temp.getString("featured"));
                        JSONObject Obj=temp.getJSONObject("menu");
                        model.setItemDes(Obj.getString("description"));
                        model.setItemName(Obj.getString("item_name"));
                        model.setItemPrice(Obj.getString("price"));
                        JSONObject InnerObj=Obj.getJSONObject("menu_category");
                        JSONObject InnerMostObj=InnerObj.getJSONObject("resturant");
                        model.setResturantImage(InnerMostObj.getString("image_url"));
                        model.setId(InnerMostObj.getString("id"));

                        bestDealModels.add(model);
                        progressBar.setVisibility(View.INVISIBLE);

                    }
                    bestDealAdapter.notifyDataSetChanged();

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
