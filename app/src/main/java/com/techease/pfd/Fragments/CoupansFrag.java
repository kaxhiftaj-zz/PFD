package com.techease.pfd.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techease.pfd.R;
public class CoupansFrag extends Fragment {

    TextView UseCoupan,CoupanTime,CoupanName;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_coupans, container, false);
        UseCoupan=(TextView)view.findViewById(R.id.useCoupan);
        return view;
    }

}
