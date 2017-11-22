package com.techease.pfd.Controller;

import android.app.Application;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.widget.Toast;


public class AppController extends Application {

    private static AppController mInstance;

    public static AppController getInstance() {
        return mInstance;
    }

    public static AppController context() {
        return mInstance;
    }

    public static Resources resources() {
        return getInstance().getResources();
    }

    public static String packageName() {
        return getInstance().getPackageName();
    }

    public static String string(int resId) {
        return resources().getString(resId);
    }

    public static String string(int resId, Object... args) {
        return resources().getString(resId, args);
    }

    public static String[] stringArray(int resId) {
        return resources().getStringArray(resId);
    }

    public static Drawable drawable(int resId) {
        return ResourcesCompat.getDrawable(resources(), resId, getInstance().getTheme());

    }

    public static int color(int resId) {
        return ContextCompat.getColor(getInstance(), resId);
    }

    public static void toast(int resId) {
        toast(string(resId));
    }

    public static void toast(String message) {
        if (message != null)
            toast(message, Toast.LENGTH_SHORT);
    }

    public static void toast(String message, int length) {
        Toast t = Toast.makeText(getInstance(), message, length);
        t.show();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}
