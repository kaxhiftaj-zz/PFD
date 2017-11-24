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

public class intro1 extends Activity {

    TextView Resturents,Intro1LongText;
    Typeface typeface,typeface2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        typeface=Typeface.createFromAsset(getAssets(),"font/brandon_bld.otf");
        typeface2=Typeface.createFromAsset(getAssets(),"font/brandon_reg.otf");
        Resturents=(TextView)findViewById(R.id.ResturentsTag);
        Intro1LongText=(TextView)findViewById(R.id.intro1LongText);
        Resturents.setTypeface(typeface);
        Intro1LongText.setTypeface(typeface2);
        
    }
}
