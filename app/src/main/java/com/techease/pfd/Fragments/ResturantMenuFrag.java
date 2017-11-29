package com.techease.pfd.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.techease.pfd.Configuration.Links;
import com.techease.pfd.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class ResturantMenuFrag extends Fragment {

    TabLayout tabLayout;
    String restId,api_token;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String[] Categories;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_resturant_menu, container, false);
        sharedPreferences = getActivity().getSharedPreferences(Links.MyPrefs, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        restId=sharedPreferences.getString("restId","");
        api_token=sharedPreferences.getString("api_token","");
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.pagerMenuPizzaHut);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayoutMenuPizzaHut);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

      //  apicall();
        tabLayout.addTab(tabLayout.newTab().setText("NEW DEAL"));
        tabLayout.addTab(tabLayout.newTab().setText("FAMILY DEAL"));
        tabLayout.addTab(tabLayout.newTab().setText("STARTERS"));
        tabLayout.addTab(tabLayout.newTab().setText("DESERTS"));

        viewPager.setAdapter(new PagerAdapter(((FragmentActivity)getActivity()).getSupportFragmentManager(), tabLayout.getTabCount()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

    private void apicall() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://pfd.techeasesol.com/api/v1/resturants/"+restId+"/categories?api_token="+api_token
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("rest respo", response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArr=jsonObject.getJSONArray("data");
                    for (int i=0; i<jsonArr.length(); i++)
                    {
                        JSONObject temp = jsonArr.getJSONObject(i);
                        Categories=new String[jsonArr.length()];
                        Categories[i]=temp.getString("category_name");
                        tabLayout.addTab(tabLayout.newTab().setText(Categories[i]));

                        //  DialogUtils.sweetAlertDialog.dismiss();
                        // pDialog.dismiss();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //    DialogUtils.sweetAlertDialog.dismiss();
                Log.d("error" , String.valueOf(error.getCause()));

            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded;charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }

        };
        RequestQueue mRequestQueue = Volley.newRequestQueue(getActivity());
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(200000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(stringRequest);
    }

    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public PagerAdapter(android.support.v4.app.FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }



        @Override
        public android.support.v4.app.Fragment getItem(int position) {

            switch (position) {
                case 0:
                    ResutantCategoriesFrag frag=new ResutantCategoriesFrag();
                    Bundle bundle=new Bundle();
                    bundle.putInt("id",1);
                    frag.setArguments(bundle);
                    return frag;
                case 1:
                    ResutantCategoriesFrag frag2=new ResutantCategoriesFrag();
                    Bundle bundle2=new Bundle();
                    bundle2.putInt("id",2);
                    frag2.setArguments(bundle2);
                    return frag2;
                case 2:
                    ResutantCategoriesFrag frag3=new ResutantCategoriesFrag();
                    Bundle bundle3=new Bundle();
                    bundle3.putInt("id",3);
                    frag3.setArguments(bundle3);
                    return frag3;
                case 3:
                    ResutantCategoriesFrag frag4=new ResutantCategoriesFrag();
                    Bundle bundle4=new Bundle();
                    bundle4.putInt("id",4);
                    frag4.setArguments(bundle4);
                    return frag4;
                case 4:
                    ResutantCategoriesFrag frag5=new ResutantCategoriesFrag();
                    Bundle bundle5=new Bundle();
                    bundle5.putInt("id",5);
                    frag5.setArguments(bundle5);
                    return frag5;
//                case 5:
//                    ResutantCategoriesFrag frag6=new ResutantCategoriesFrag();
//                    Bundle bundle6=new Bundle();
//                    bundle6.putInt("id",6);
//                    frag6.setArguments(bundle6);
//                    return frag6;
//                case 6:
//                    ResutantCategoriesFrag frag7=new ResutantCategoriesFrag();
//                    Bundle bundle7=new Bundle();
//                    bundle7.putInt("id",7);
//                    frag7.setArguments(bundle7);
//                    return frag7;
//                case 7:
//                    ResutantCategoriesFrag frag8=new ResutantCategoriesFrag();
//                    Bundle bundle8=new Bundle();
//                    bundle8.putInt("id",8);
//                    frag8.setArguments(bundle8);
//                    return frag8;
//                case 8:
//                    ResutantCategoriesFrag frag9=new ResutantCategoriesFrag();
//                    Bundle bundle9=new Bundle();
//                    bundle9.putInt("id",9);
//                    frag9.setArguments(bundle9);
//                    return frag9;
//                case 9:
//                    ResutantCategoriesFrag frag10=new ResutantCategoriesFrag();
//                    Bundle bundle10=new Bundle();
//                    bundle10.putInt("id",10);
//                    frag10.setArguments(bundle10);
//                    return frag10;
//                case 10:
//                    ResutantCategoriesFrag frag11=new ResutantCategoriesFrag();
//                    return frag11;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }
}
