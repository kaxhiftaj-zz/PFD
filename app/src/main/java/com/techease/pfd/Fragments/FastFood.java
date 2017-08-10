package com.techease.pfd.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techease.pfd.Controller.ItemHelper;
import com.techease.pfd.Controller.MyAdapter;
import com.techease.pfd.R;

import java.util.ArrayList;
import java.util.List;


public class FastFood extends Fragment {

    List<ItemHelper> data ;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fast_food, container, false);
        mRecyclerView = (RecyclerView)v.findViewById(R.id.rfastFood);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        fill_with_data();
        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(data);
        mRecyclerView.setAdapter(mAdapter);

        return  v ;

    }

    public void fill_with_data() {
        data = new ArrayList<>();
        data.add(new ItemHelper("Batman ", R.drawable.pfd));
        data.add(new ItemHelper("X-Menn ", R.drawable.pfd));
        data.add(new ItemHelper("Captai ", R.drawable.pfd));

    }
}
