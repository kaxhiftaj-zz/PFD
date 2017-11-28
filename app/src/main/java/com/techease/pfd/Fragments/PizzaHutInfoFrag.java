package com.techease.pfd.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.techease.pfd.Configuration.Links;
import com.techease.pfd.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PizzaHutInfoFrag extends Fragment {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String restId,api_token;
    TextView tvTiming,tvLoc,tvAbout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_pizza_hut_info, container, false);
        sharedPreferences = getActivity().getSharedPreferences(Links.MyPrefs, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        restId=sharedPreferences.getString("restId","");
        api_token=sharedPreferences.getString("api_token","");
        tvTiming=(TextView)view.findViewById(R.id.tvTimingPizza_Hut_Info);
        tvLoc=(TextView)view.findViewById(R.id.tvLocationInfo);
        tvAbout=(TextView)view.findViewById(R.id.tvLongText_Pizza_Hut_Info);
        apicall();
        return view;
    }

    private void apicall() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://pfd.techeasesol.com/api/v1/resturants/"+restId+"?api_token="+api_token
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("details", response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONObject JsonGet=jsonObject.getJSONObject("data");
                    tvTiming.setText(JsonGet.getString("timings"));
                    tvLoc.setText(JsonGet.getString("location"));
                    tvAbout.setText(JsonGet.getString("about"));

                    //  DialogUtils.sweetAlertDialog.dismiss();
                    // pDialog.dismiss();



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //    DialogUtils.sweetAlertDialog.dismiss();
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
