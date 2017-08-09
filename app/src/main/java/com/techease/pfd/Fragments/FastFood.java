package com.techease.pfd.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techease.pfd.Controller.Resturants;
import com.techease.pfd.R;

import java.util.ArrayList;
import java.util.List;


public class FastFood extends Fragment {

    List<Resturants> data ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fast_food, container, false);
        data = new ArrayList<>();
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.fastFood);

        ResturantsListAdapter adapter = new ResturantsListAdapter(data, getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        fill_with_data();
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return  v ;

    }

    public void fill_with_data() {
        data.add(new Resturants("Batman ", R.drawable.pfd));
        data.add(new Resturants("X-Menn ", R.drawable.pfd));
        data.add(new Resturants("Captai ", R.drawable.pfd));

    }
}
