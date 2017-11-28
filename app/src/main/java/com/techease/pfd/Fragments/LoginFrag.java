package com.techease.pfd.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.techease.pfd.Activities.Dashboard;
import com.techease.pfd.Configuration.Links;
import com.techease.pfd.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class LoginFrag extends Fragment {

    EditText etUsername, etPassword;
    TextView tvSignup, tvForgetPass;
    Button btnSingIn;
    Typeface typeface,typeface2;
    String strUsername,strPassword;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        sharedPreferences = getActivity().getSharedPreferences("com.pfd", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        typeface = Typeface.createFromAsset(getActivity().getAssets(), "font/brandon_reg.otf");
        typeface2 = Typeface.createFromAsset(getActivity().getAssets(), "font/brandon_bld.otf");
        etUsername = (EditText) view.findViewById(R.id.etUsername);
        etPassword = (EditText) view.findViewById(R.id.etPassword);
        tvSignup = (TextView) view.findViewById(R.id.tvSignUp);
        tvForgetPass = (TextView) view.findViewById(R.id.tvForgetPass);
        btnSingIn = (Button) view.findViewById(R.id.btnSignIn);

        etUsername.setTypeface(typeface);
        etPassword.setTypeface(typeface);
        tvSignup.setTypeface(typeface);
        tvForgetPass.setTypeface(typeface);
        btnSingIn.setTypeface(typeface);

        btnSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onDataInput();

            }
        });
        tvForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ForgotPasswordFrag();
                getFragmentManager().beginTransaction().replace(R.id.container, fragment).addToBackStack("abc").commit();
            }
        });
        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new SignUpFrag();
                getFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
            }
        });


        return view;
    }

    private void onDataInput() {
        strUsername = etUsername.getText().toString();
       strPassword = etPassword.getText().toString();
        if (strUsername.equals("") || strUsername.length() < 3) {
            etUsername.setError("Please enter the valid username");
        } else if (strPassword.equals("") || strPassword.length() < 6) {
            etPassword.setError("Please enter correct password");
        } else {
//            DialogUtils.showProgressSweetDialog(getActivity(), "Getting Sign In");
            apiCall();
        }

    }

    private void apiCall() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Links.User_Url+"login", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("zma respoonse", response);
              //  DialogUtils.sweetAlertDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response).getJSONObject("data");
                    String api_token=jsonObject.getString("api_token");
                    String name=jsonObject.getString("name");
                    Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();
                    editor.putString("api_token",api_token);
                    editor.putString("name",name);
                    editor.commit();
                    Log.d("zma data",name);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(getActivity(), Dashboard.class));
                getActivity().finish();
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
                params.put("email", strUsername);
                params.put("password", strPassword);
                params.put("device_type","Android");
                params.put("device_token","token_here");
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