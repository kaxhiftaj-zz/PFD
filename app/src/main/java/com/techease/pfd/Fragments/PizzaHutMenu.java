package com.techease.pfd.Fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techease.pfd.R;


public class PizzaHutMenu extends Fragment {

    TabLayout tabLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_pizza_hut_menu, container, false);
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.pagerInfoPizzaHut);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayoutPizzaHut);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
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
                    NewDealsFrag frag=new NewDealsFrag();
                    return frag;
                case 1:
                    PizzaHutMenu frag2=new PizzaHutMenu();
                    return frag2;
                case 2:
                    FamilyDealFrag frag3=new FamilyDealFrag();
                    return frag3;
                case 3:
                    StartersFrag frag4=new StartersFrag();
                    return frag4;
                case 4:
                    DesertsFrag frag5=new DesertsFrag();
                    return frag5;
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
