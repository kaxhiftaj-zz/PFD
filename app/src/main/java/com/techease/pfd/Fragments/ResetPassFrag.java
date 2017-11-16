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

public class ResetPassFrag extends Fragment {

    Typeface typeface;
    EditText etNewPass,etReEnterPass;
    Button btnReset;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_reset_pass, container, false);

        typeface=Typeface.createFromAsset(getActivity().getAssets(),"font/brandon_blk.otf");
        etNewPass=(EditText)view.findViewById(R.id.etNewPassRestPass);
        etReEnterPass=(EditText)view.findViewById(R.id.etReEnterPassRestPass);
        btnReset=(Button)view.findViewById(R.id.btnReset);

        etReEnterPass.setTypeface(typeface);
        etNewPass.setTypeface(typeface);
        btnReset.setTypeface(typeface);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment= new EmailFrag();
                getFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
            }
        });

        return view;
    }

}
