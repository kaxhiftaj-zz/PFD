package com.techease.pfd.Fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
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

import cn.pedant.SweetAlert.SweetAlertDialog;

public class VerifyCodeFrag extends Fragment {

    EditText etVerifyCode;
    Button btnVerify;
    Typeface typeface;
    String code,email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_verify_code, container, false);
        typeface=Typeface.createFromAsset(getActivity().getAssets(),"font/brandon_reg.otf");
        etVerifyCode=(EditText)view.findViewById(R.id.etVerifyCode);
        btnVerify=(Button)view.findViewById(R.id.btnVerify);

        etVerifyCode.setTypeface(typeface);
        btnVerify.setTypeface(typeface);
        Bundle bundle=getArguments();
        email=bundle.getString("email");


        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
onDataInput();
            }
        });
        return view;
    }

    private void onDataInput() {
        code=etVerifyCode.getText().toString();
        if(code.equals(""))
        {
            etVerifyCode.setError("Please enter the code");
        }
        else
            DialogUtils.showProgressSweetDialog(getActivity(), "Verifying");
        apiCall();

    }

    private void apiCall() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Links.User_Url+"verify", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("zma respoonse", response);
                DialogUtils.sweetAlertDialog.dismiss();

                Fragment fragment= new ResetPassFrag();
                getFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.sweetAlertDialog.dismiss();

                    final SweetAlertDialog pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE);
                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#295786"));
                    pDialog.setTitleText("Invalid verification code");
                    pDialog.setConfirmText("OK");
                    pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            pDialog.dismissWithAnimation();
                        }
                    });
                    pDialog.show();

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
                params.put("verification_code", code);
                params.put("email",email);
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
