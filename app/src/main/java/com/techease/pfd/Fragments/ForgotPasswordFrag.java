package com.techease.pfd.Fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.techease.pfd.R;


public class ForgotPasswordFrag extends Fragment {

    EditText etEmailForgetPass;
    Button btnSendForgetPass;
    Typeface typeface;

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

                Fragment fragment=new VerifyCodeFrag();
                getFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
            }
        });
        return view;
    }
}
