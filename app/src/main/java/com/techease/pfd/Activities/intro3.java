package com.techease.pfd.Activities;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.techease.pfd.R;

/**
 * Created by Adam Noor on 14-Nov-17.
 */

public class intro3 extends Activity {
    TextView Tag,LongText;
    Typeface typeface,typeface2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        typeface=Typeface.createFromAsset(getAssets(),"font/brandon_bld");
        typeface2=Typeface.createFromAsset(getAssets(),"font/brandon_reg");

        Tag=(TextView)findViewById(R.id.HomeTag);
        LongText=(TextView)findViewById(R.id.Intro3LongText);

        Tag.setTypeface(typeface);
        LongText.setTypeface(typeface2);

    }
}
