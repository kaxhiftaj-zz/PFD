package com.techease.pfd.Fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.techease.pfd.R;



public class AboutUsFrag extends Fragment implements View.OnClickListener {

    TextView tvTitleAboutUs,tvFbGroupTitleABoutUs,tvFbLinkAboutUs;
    Typeface typeface,typeface2;
    CardView cardView;
    WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_about_us, container, false);

        tvTitleAboutUs=(TextView)view.findViewById(R.id.tvTitleABoutUs);
        cardView=(CardView)view.findViewById(R.id.card2);
        tvFbGroupTitleABoutUs=(TextView)view.findViewById(R.id.tvFbGroupABoutUs);
        tvFbLinkAboutUs=(TextView)view.findViewById(R.id.tvFbLInkABoutUs);
        webView=(WebView)view.findViewById(R.id.webView1);
        typeface=Typeface.createFromAsset(getActivity().getAssets(),"font/brandon_blk");
        typeface2=Typeface.createFromAsset(getActivity().getAssets(),"font/brandon_reg");

        tvTitleAboutUs.setTypeface(typeface);
        tvFbLinkAboutUs.setTypeface(typeface2);
        tvFbGroupTitleABoutUs.setTypeface(typeface2);

        tvFbGroupTitleABoutUs.setOnClickListener(this);
        tvFbLinkAboutUs.setOnClickListener(this);
        cardView.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.facebook.com/kpkfoodies/");

    }
}
