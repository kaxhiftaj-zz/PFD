package com.techease.pfd.Activities;

import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.internal.NavigationMenuView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.techease.pfd.Fragments.Bakers;
import com.techease.pfd.Fragments.Chinese;
import com.techease.pfd.Fragments.FastFood;
import com.techease.pfd.Fragments.Italian;
import com.techease.pfd.R;
import com.techease.pfd.Fragments.Traditional;
import com.techease.pfd.Fragments.Turkish;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentTransaction fragmentTransaction;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        NavigationMenuView navMenuView = (NavigationMenuView) navigationView.getChildAt(0);
//        navigationView.setItemTextColor(ColorStateList.valueOf(getColor(R.color.colorAccent)));
        navMenuView.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));

        Fragment fastFood = null;

        fastFood = new FastFood();

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainFrame, fastFood);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.fastFood) {

           Fragment fragme = new FastFood();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.mainFrame, fragme);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            item.setChecked(true);
            setTitle(item.getTitle());

        } else if (id == R.id.chinese) {
            Fragment fragme = new Chinese();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.mainFrame, fragme);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            item.setChecked(true);
            setTitle(item.getTitle());


        } else if (id == R.id.traditional) {

            Fragment fragme = new Traditional();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.mainFrame, fragme);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            item.setChecked(true);
            setTitle(item.getTitle());

        }else if (id == R.id.turkish) {

            Fragment fragme = new Turkish();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.mainFrame, fragme);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            item.setChecked(true);
            setTitle(item.getTitle());

        }
        else if (id == R.id.bakers) {

            Fragment fragme = new Bakers();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.mainFrame, fragme);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            item.setChecked(true);
            setTitle(item.getTitle());

        }
        else if (id == R.id.italian) {

            Fragment fragme = new Italian();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.mainFrame, fragme);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            item.setChecked(true);
            setTitle(item.getTitle());

        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
