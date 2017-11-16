package com.techease.pfd.Fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.techease.pfd.R;


public class EmailFrag extends Fragment {

    EditText etUsername,etPassword;
    TextView tvSignup,tvForgetPass;
    Button btnSingIn;
    Typeface typeface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_email, container, false);

        typeface=Typeface.createFromAsset(getActivity().getAssets(),"font/brandon_blk.otf");
        etUsername=(EditText)view.findViewById(R.id.etUsername);
        etPassword=(EditText)view.findViewById(R.id.etPassword);
        tvSignup=(TextView)view.findViewById(R.id.tvSignUp);
        tvForgetPass=(TextView)view.findViewById(R.id.tvForgetPass);
        btnSingIn=(Button)view.findViewById(R.id.btnSignIn);

        etUsername.setTypeface(typeface);
        etPassword.setTypeface(typeface);
        tvSignup.setTypeface(typeface);
        tvForgetPass.setTypeface(typeface);
        btnSingIn.setTypeface(typeface);

        btnSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tvForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new ForgotPasswordFrag();
                getFragmentManager().beginTransaction().replace(R.id.container,fragment).addToBackStack("abc").commit();
            }
        });
        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new SignUpFrag();
                getFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
            }
        });


        return view;
    }

}
