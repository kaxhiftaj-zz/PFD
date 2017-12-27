package com.techease.pfd.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
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
import com.techease.pfd.R;
import com.techease.pfd.Utils.CheckNetwork;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CoupansFrag extends Fragment {

    TextView UseCoupan,CoupanTime,CoupanName,DiscountNo,DiscountType;
    String api_token,restId;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ProgressBar progressBar;
    int progressbarstatus = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_coupans, container, false);
        if(CheckNetwork.isInternetAvailable(getActivity())) //returns true if internet available
        {

            progressBar=(ProgressBar)view.findViewById(R.id.progress_barCoupans);

            UseCoupan=(TextView)view.findViewById(R.id.useCoupan);
            CoupanName=(TextView)view.findViewById(R.id.coupanName);
            CoupanTime=(TextView)view.findViewById(R.id.coupansValidation);
            DiscountNo=(TextView)view.findViewById(R.id.DiscountNo);
            DiscountType=(TextView)view.findViewById(R.id.DiscountType);

            sharedPreferences = getActivity().getSharedPreferences(Links.MyPrefs, Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
            api_token=sharedPreferences.getString("api_token","");
            restId=sharedPreferences.getString("restId","");
            apicall();
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
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://pfd.techeasesol.com/api/v1/coupons?api_token="+api_token
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("rest respo", response);

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONObject object=jsonObject.getJSONObject("data");
                        CoupanName.setText(object.getString("coupon_code"));
                        CoupanTime.setText(object.getString("expiry"));
                        DiscountNo.setText(object.getString("discount"));
                        DiscountType.setText(object.getString("discount_type"));
                        progressBar.setVisibility(View.INVISIBLE);

                } catch (JSONException e) {
                    progressBar.setVisibility(View.INVISIBLE);
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
