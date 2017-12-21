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

    TextView tvTitleAboutUs,tvFbGroupTitleABoutUs,tvFbLinkAboutUs,tv1,tv2,tv3,tvCompany;
    Typeface typeface,typeface2,typeface3;
    CardView cardView,cardView2,cardView3;
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
        tv1=(TextView)view.findViewById(R.id.tv1);
        tv2=(TextView)view.findViewById(R.id.tv2);
        tv3=(TextView)view.findViewById(R.id.tvLongText);
        tvCompany=(TextView)view.findViewById(R.id.tvCompany);
        cardView2=(CardView)view.findViewById(R.id.card3);
        cardView3=(CardView)view.findViewById(R.id.cardViewCustomPesh_FD);
        typeface=Typeface.createFromAsset(getActivity().getAssets(),"font/brandon_blk.otf");
        typeface2=Typeface.createFromAsset(getActivity().getAssets(),"font/brandon_reg.otf");
        typeface2=Typeface.createFromAsset(getActivity().getAssets(),"font/brandon_bld.otf");

        tvTitleAboutUs.setTypeface(typeface);
        tvFbLinkAboutUs.setTypeface(typeface2);
        tvFbGroupTitleABoutUs.setTypeface(typeface3);
        tv2.setTypeface(typeface3);
        tv1.setTypeface(typeface3);
        tv3.setTypeface(typeface2);
        tvCompany.setTypeface(typeface2);

        tvFbGroupTitleABoutUs.setOnClickListener(this);
        tvFbLinkAboutUs.setOnClickListener(this);
        cardView.setOnClickListener(this);

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.setVisibility(View.VISIBLE);
                cardView.setVisibility(View.INVISIBLE);
                cardView2.setVisibility(View.INVISIBLE);
                tv1.setVisibility(View.INVISIBLE);
                cardView3.setVisibility(View.INVISIBLE);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("http://www.techeasesol.com/");
            }
        });
        tvCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.setVisibility(View.VISIBLE);
                cardView.setVisibility(View.INVISIBLE);
                cardView2.setVisibility(View.INVISIBLE);
                tv1.setVisibility(View.INVISIBLE);
                cardView3.setVisibility(View.INVISIBLE);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("http://www.techeasesol.com/");
            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.setVisibility(View.VISIBLE);
                cardView.setVisibility(View.INVISIBLE);
                cardView2.setVisibility(View.INVISIBLE);
                tv1.setVisibility(View.INVISIBLE);
                cardView3.setVisibility(View.INVISIBLE);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://www.techeasesol.com/");
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.facebook.com/kpkfoodies/");

    }
}
