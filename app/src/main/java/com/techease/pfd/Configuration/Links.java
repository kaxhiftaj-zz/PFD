package com.techease.pfd.Configuration;

import android.content.res.AssetManager;
import android.graphics.Typeface;

/**
 * Created by Adam Noor on 17-Nov-17.
 */

public class Links {
   public static AssetManager assetManager;

public static String User_Url="http://pfd.techeasesol.com/api/v1/user/";
    public static String MyPrefs="com.pfd";
    public static Typeface typeface=Typeface.createFromAsset(assetManager,"font/brandon_blk.otf");
}
