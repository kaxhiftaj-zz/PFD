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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class ForgotPasswordFrag extends Fragment {

    EditText etEmailForgetPass;
    Button btnSendForgetPass;
    Typeface typeface;
String email;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_forgot_password, container, false);
        typeface=Typeface.createFromAsset(getActivity().getAssets(),"font/brandon_blk.otf");
        etEmailForgetPass=(EditText)view.findViewById(R.id.etEmailForgetPass);
        btnSendForgetPass=(Button)view.findViewById(R.id.btnSendForgetPass);

        etEmailForgetPass.setTypeface(typeface);
        btnSendForgetPass.setTypeface(typeface);
        btnSendForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               onDataInput();
            }
        });
        return view;
    }

    private void onDataInput() {
        email=etEmailForgetPass.getText().toString();
        if(email.equals("")||(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()))
        {
            etEmailForgetPass.setError("Please Enter Valid Email");
        }else
            DialogUtils.showProgressSweetDialog(getActivity(), "Loading");
        apiCall();

    }

    private void apiCall() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Links.User_Url+"forgot-password", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.sweetAlertDialog.dismiss();
                Toast.makeText(getActivity(),"success", Toast.LENGTH_SHORT).show();

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.sweetAlertDialog.dismiss();
                Toast.makeText(getActivity(), String.valueOf(error.getCause()), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded;charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
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
