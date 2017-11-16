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

public class VerifyCodeFrag extends Fragment {

    EditText etVerifyCode;
    Button btnVerify;
    Typeface typeface;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_verify_code, container, false);
        typeface=Typeface.createFromAsset(getActivity().getAssets(),"font/brandon_blk.otf");
        etVerifyCode=(EditText)view.findViewById(R.id.etVerifyCode);
        btnVerify=(Button)view.findViewById(R.id.btnVerify);

        etVerifyCode.setTypeface(typeface);
        btnVerify.setTypeface(typeface);

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment=new ResetPassFrag();
                getFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
            }
        });
        return view;
    }

}
