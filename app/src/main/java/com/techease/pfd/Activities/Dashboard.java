package com.techease.pfd.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.techease.pfd.Configuration.Links;
import com.techease.pfd.Fragments.AboutUsFrag;
import com.techease.pfd.Fragments.AddingRecipeFragment;
import com.techease.pfd.Fragments.AllResturentFrag;
import com.techease.pfd.Fragments.BestDeal;
import com.techease.pfd.Fragments.CoupansFrag;
import com.techease.pfd.Fragments.Setting;
import com.techease.pfd.R;

public class Dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Typeface typeface,typeface2;
    TextView tvUserName,tvUserEmail;
    SharedPreferences sharedprefs;
    SharedPreferences.Editor editor;
    String fname,lname,email;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Dashboard");
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerLayout = navigationView.getHeaderView(0);

        typeface = Typeface.createFromAsset(getAssets(), "font/brandon_blk.otf");
        typeface=Typeface.createFromAsset(getAssets(),"font/brandon_reg.otf");
        tvUserName = (TextView) headerLayout.findViewById(R.id.tvUserName);
        tvUserEmail=(TextView)headerLayout.findViewById(R.id.tvAddress);
        tvUserName.setTypeface(typeface);
        tvUserEmail.setTypeface(typeface2);

        sharedprefs = this.getSharedPreferences(Links.MyPrefs, Context.MODE_PRIVATE);
        editor = sharedprefs.edit();
        fname = sharedprefs.getString("fname", "");
        lname=sharedprefs.getString("lname","");
        email=sharedprefs.getString("email","");
        if (fname != null) {
            tvUserName.setText(fname+" "+lname);
            tvUserEmail.setText(email);
        }

        fragment = new AllResturentFrag();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();



        Menu menu = navigationView.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            MenuItem menuItem = menu.getItem(i);
            applyFontToMenuItem(menuItem);
        }
        navigationView.setNavigationItemSelectedListener(this);
    }


    private void applyFontToMenuItem(MenuItem mi) {
        Typeface font = Typeface.createFromAsset(getAssets(), "font/brandon_bld.otf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("", font), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            FacebookSdk.sdkInitialize(getApplicationContext());
            LoginManager.getInstance().logOut();
            editor.putString("api_token"," ").commit();
            startActivity(new Intent(Dashboard.this, MainActivity.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.MainScreen) {
            Fragment fragmentGraph=new AllResturentFrag();
            getSupportFragmentManager().beginTransaction().replace(R.id.container,fragmentGraph).commit();
        }  else if (id == R.id.nav_manage) {
            Fragment fragmentCoupan=new CoupansFrag();
            getSupportFragmentManager().beginTransaction().replace(R.id.container,fragmentCoupan).addToBackStack("abc").commit();
        } else if (id == R.id.bestdeal) {
            Fragment fragmentBestDeal=new BestDeal();
            getSupportFragmentManager().beginTransaction().replace(R.id.container,fragmentBestDeal).addToBackStack("abc").commit();

        }else if (id==R.id.addingRecipe){
            Fragment fragmentCoupan=new AddingRecipeFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.container,fragmentCoupan).addToBackStack("abc").commit();
        }
        else if (id == R.id.aboutus) {
            Fragment fragmentCoupan=new AboutUsFrag();
            getSupportFragmentManager().beginTransaction().replace(R.id.container,fragmentCoupan).addToBackStack("abc").commit();

        }
        else if (id==R.id.nav_setting)
        {
            Fragment fragmentSetting=new Setting();
            getSupportFragmentManager().beginTransaction().replace(R.id.container,fragmentSetting).addToBackStack("abc").commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
