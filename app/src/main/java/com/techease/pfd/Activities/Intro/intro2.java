package com.techease.pfd.Activities.Intro;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.techease.pfd.R;

/**
 * Created by Adam Noor on 14-Nov-17.
 */

public class intro2 extends Activity {

    Typeface typeface,typeface2;
    TextView Bistro,longtext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        typeface=Typeface.createFromAsset(getAssets(),"font/barndon_bld.otf");
        typeface2=Typeface.createFromAsset(getAssets(),"font/brandon_reg.otf");
        Bistro=(TextView)findViewById(R.id.Bistro);
        longtext=(TextView)findViewById(R.id.intro2Longtext);
        Bistro.setTypeface(typeface);
        longtext.setTypeface(typeface2);

    }
}
