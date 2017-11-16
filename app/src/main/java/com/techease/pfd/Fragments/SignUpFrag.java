package com.techease.pfd.Fragments;

import android.app.Fragment;
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
import com.techease.pfd.R;

import java.util.HashMap;
import java.util.Map;

public class SignUpFrag extends Fragment {

    EditText etUsernameSignUp,etEmailSignUp,etPasswordSignUp;
    TextView tvSignIn;
    Button btnSignUp;
    Typeface typeface;
    String strUserName,strPassword,strEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sign_up, container, false);
        typeface=Typeface.createFromAsset(getActivity().getAssets(),"font/brandon_blk.otf");
        etUsernameSignUp=(EditText)view.findViewById(R.id.etUsernameSignUp);
        etEmailSignUp=(EditText)view.findViewById(R.id.etEmailSignUp);
        etPasswordSignUp=(EditText)view.findViewById(R.id.etPasswordSignUp);
        tvSignIn=(TextView)view.findViewById(R.id.tvSignInSignUp);
        btnSignUp=(Button)view.findViewById(R.id.btnSignUp);

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

                Fragment fragment=new EmailFrag();
                getFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
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
        } else {
            Log.d("zma data", strUserName+"\n"+strEmail+"\n"+"\n");
            DialogUtils.showProgressSweetDialog(getActivity(), "Getting registered");
            apiCall();


        }

    }

    private void apiCall() {
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://pfd.techeasesol.com/api/v1/user/register", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("zma  reg response", String.valueOf(response));
                DialogUtils.sweetAlertDialog.dismiss();
                if (response.contains("User successfully registered")) {

//                    try {
////                        JSONObject jsonObject = new JSONObject(String.valueOf(response)).getJSONObject("data");
////                        String strApiToken = jsonObject.getString("api_token");
//                        //Toast.makeText(getActivity(), strApiToken, Toast.LENGTH_SHORT).show();
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
                    Toast.makeText(getActivity(), "Registration Successful", Toast.LENGTH_SHORT).show();
                    //fragment = new LoginFragment();
                    //getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();


                } else {
                    DialogUtils.showWarningAlertDialog(getActivity(), "Something went wrong");
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("zma error", String.valueOf(error));
                DialogUtils.sweetAlertDialog.dismiss();
                DialogUtils.showWarningAlertDialog(getActivity(),"Error");


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
                params.put("dob", "2017-11-31");
                params.put("Accept", "application/json");
                Log.d("zma params", String.valueOf(params));
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
