package com.techease.pfd.Fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techease.pfd.R;



public class AboutUsFrag extends Fragment {

    TextView tvTitleAboutUs;
    Typeface typeface;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_about_us, container, false);

        tvTitleAboutUs=(TextView)view.findViewById(R.id.tvTitleABoutUs);

        return view;
    }
}
