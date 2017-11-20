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

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.techease.pfd.Activities.PFD;
import com.techease.pfd.Configuration.Links;
import com.techease.pfd.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SignUpFrag extends Fragment {

    EditText etUsernameSignUp, etEmailSignUp, etPasswordSignUp;
    TextView tvSignIn;
    Button btnSignUp;
    Typeface typeface;
    String strUserName, strPassword, strEmail;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String date = "2017-11-31";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        sharedPreferences = getActivity().getSharedPreferences(Links.MyPrefs, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        typeface = Typeface.createFromAsset(getActivity().getAssets(), "font/brandon_blk.otf");
        etUsernameSignUp = (EditText) view.findViewById(R.id.etUsernameSignUp);
        etEmailSignUp = (EditText) view.findViewById(R.id.etEmailSignUp);
        etPasswordSignUp = (EditText) view.findViewById(R.id.etPasswordSignUp);
        tvSignIn = (TextView) view.findViewById(R.id.tvSignInSignUp);
        btnSignUp = (Button) view.findViewById(R.id.btnSignUp);

        btnSignUp.setTypeface(typeface);
        etPasswordSignUp.setTypeface(typeface);
        etEmailSignUp.setTypeface(typeface);
        etUsernameSignUp.setTypeface(typeface);
        tvSignIn.setTypeface(typeface);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onDataInput();

            }
        });
        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new LoginFrag();
                getFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
            }
        });

        return view;
    }

    public void onDataInput() {
        strUserName = etUsernameSignUp.getText().toString();
        strEmail = etEmailSignUp.getText().toString();
        strPassword = etPasswordSignUp.getText().toString();

        if (strUserName.equals("") || strEmail.length() < 3) {
            etUsernameSignUp.setError("Enter a valid First name");
        } else if ((!android.util.Patterns.EMAIL_ADDRESS.matcher(strEmail).matches())) {
            etEmailSignUp.setError("Please enter valid email id");
        }else if(strPassword.equals("")||strPassword.length()<6)
        {
            etPasswordSignUp.setError("Enter the more then 6 digit passowrd");
        }
        else {
            DialogUtils.showProgressSweetDialog(getActivity(), "Getting registered");
            apiCall();


        }

    }

    private void apiCall() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Links.User_Url+"register", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.sweetAlertDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response).getJSONObject("data");
                    String api_token=jsonObject.getString("api_token");
                    editor.putString("api_token",api_token);
                    editor.commit();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(getActivity(), PFD.class));
                getActivity().finish();

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
                params.put("username", strUserName);
                params.put("email", strEmail);
                params.put("password", strPassword);
                params.put("dob", "1991-12-27");
                params.put("Accept", "application/json");
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
