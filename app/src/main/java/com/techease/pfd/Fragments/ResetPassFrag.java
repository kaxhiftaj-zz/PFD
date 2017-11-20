package com.techease.pfd.Fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ResetPassFrag extends Fragment {

    Typeface typeface;
    EditText etNewPass,etReEnterPass;
    Button btnReset;
    String Pass,RePass;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_reset_pass, container, false);

        typeface=Typeface.createFromAsset(getActivity().getAssets(),"font/brandon_blk.otf");
        etNewPass=(EditText)view.findViewById(R.id.etNewPassRestPass);
        etReEnterPass=(EditText)view.findViewById(R.id.etReEnterPassRestPass);
        btnReset=(Button)view.findViewById(R.id.btnReset);

        etReEnterPass.setTypeface(typeface);
        etNewPass.setTypeface(typeface);
        btnReset.setTypeface(typeface);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onDataInput();
            }
        });

        return view;
    }

    private void onDataInput() {
        Pass=etNewPass.getText().toString();
        RePass=etReEnterPass.getText().toString();
        if(Pass.equals("")||Pass.length()<6)
        {
            etNewPass.setError("Please enter the valid 6 digit password");
        }else if (RePass.equals("")||!Pass.equals(RePass))
        {
            etReEnterPass.setError("Password does not match");
        }
        else
            DialogUtils.showProgressSweetDialog(getActivity(), "Getting registered");
        apiCall();
    }

    private void apiCall() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Links.User_Url+"change-password", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("zma respoonse", response);
                DialogUtils.sweetAlertDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.sweetAlertDialog.dismiss();
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
                params.put("pass", Pass);
                params.put("newPass", RePass);
                Log.d("zma params", String.valueOf(params));
                return checkParams(params);
            }
        };

        RequestQueue mRequestQueue = Volley.newRequestQueue(getActivity());
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

}
