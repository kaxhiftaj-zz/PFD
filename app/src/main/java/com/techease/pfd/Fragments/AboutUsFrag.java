package com.techease.pfd.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.techease.pfd.R;



public class AboutUsFrag extends Fragment implements View.OnClickListener {

    TextView tvFbGroupTitleABoutUs, tvFbLinkAboutUs, tv1, tv2, tv3, tvCompany;
    Typeface typeface, typeface2, typeface3;
    CardView cardView, cardView2, cardView3, cardView4;
    WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);


        cardView = (CardView) view.findViewById(R.id.card2);
        cardView4 = (CardView) view.findViewById(R.id.card4);
        tvFbGroupTitleABoutUs = (TextView) view.findViewById(R.id.tvFbGroupABoutUs);
        tvFbLinkAboutUs = (TextView) view.findViewById(R.id.tvFbLInkABoutUs);
        webView = (WebView) view.findViewById(R.id.webView1);
        tv1 = (TextView) view.findViewById(R.id.tv1);
        tv2 = (TextView) view.findViewById(R.id.tv2);
        tv3 = (TextView) view.findViewById(R.id.tvLongText);
        tvCompany = (TextView) view.findViewById(R.id.tvCompany);
        cardView2 = (CardView) view.findViewById(R.id.card3);
        cardView3 = (CardView) view.findViewById(R.id.cardViewCustomPesh_FD);
        typeface = Typeface.createFromAsset(getActivity().getAssets(), "font/brandon_blk.otf");
        typeface2 = Typeface.createFromAsset(getActivity().getAssets(), "font/brandon_reg.otf");
        typeface3 = Typeface.createFromAsset(getActivity().getAssets(), "font/brandon_bld.otf");


        tvFbLinkAboutUs.setTypeface(typeface2);
        tvFbGroupTitleABoutUs.setTypeface(typeface3);
        tv2.setTypeface(typeface3);
        tv1.setTypeface(typeface3);
        tv3.setTypeface(typeface2);
        tvCompany.setTypeface(typeface2);

        tvFbGroupTitleABoutUs.setOnClickListener(this);
        tvFbLinkAboutUs.setOnClickListener(this);
        cardView.setOnClickListener(this);

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setTitle("ABOUT PFD");
                alertDialogBuilder.setMessage(" We aim to create a community that shares our love for food and is honest, responsible in their feedback.   There is a vacuum for our province and its rich food . To ensure that the PFD serves its purpose there are certain rules that we expect our members to follow:\n" +
                        "• The app is about your food experiences (be it at home, restaurant, or somewhere else)\n" +
                        "• Be honest in your review of a restaurant.\n" +
                        "• If you are the owner of a restaurant or a catering business, you can post / promote it ONCE a week. When you post please state that you belong to the management or own the restaurant. Any excessive posts within a week marketing your restaurant will result in deletion.\n" +
                        "• HEALTHY CRITICISM is welcome but criticizing a restaurant just for the sake of it is not acceptable and unfair to them.\n" +
                        "• And most importantly, please be COURTEOUS and RESPECTFUL towards estaurants. No personal attacks will be tolerated.\n" +
                        "We hope that this app will become a healthy platform that will help foodies in connecting with each Resturants and benefiting from our experiences.");
                alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                        // getActivity().finish();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.setVisibility(View.VISIBLE);
                cardView.setVisibility(View.INVISIBLE);
                cardView2.setVisibility(View.INVISIBLE);
                tv1.setVisibility(View.INVISIBLE);
                cardView3.setVisibility(View.INVISIBLE);
                cardView4.setVisibility(View.INVISIBLE);
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
                cardView4.setVisibility(View.INVISIBLE);
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
                cardView4.setVisibility(View.INVISIBLE);
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
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);

                return true;
            }
        });

    }
}