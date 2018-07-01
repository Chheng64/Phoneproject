package com.mobile.thephoneshop.thephoneshop;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity  {

   // private DrawerLayout mDrawerLayout;
    private ActionBar toolbar;
    private ViewPager viewPager;

    private DrawerLayout navDrawer;
    private ActionBarDrawerToggle navAction;
    private NavigationView navView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_phone:
                    toolbar.setTitle("All phone");
                    fragment = new PhoneFragment();
                    break;
                case R.id.navigation_brand:
                    toolbar.setTitle("Brand");
                    fragment = new BrandFragment();
                    break;
                case R.id.navigation_myorder:
                    toolbar.setTitle("My order");
                    fragment = new MyorderFragment();
                    break;
            }
            return loadFragment(fragment);
        }
    };
    private boolean loadFragment(Fragment fragment){
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navDrawer = (DrawerLayout) findViewById(R.id.nav_drawer);
        navAction = new ActionBarDrawerToggle(this, navDrawer, R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        navDrawer.addDrawerListener(navAction);
        navAction.syncState();
        System.out.print(navDrawer);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){

                    case R.id.account:
                        Toast.makeText(MainActivity.this, "My Account",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.settings:
                        Toast.makeText(MainActivity.this, "My Account",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.mycart:
                        Toast.makeText(MainActivity.this, "My Account",Toast.LENGTH_SHORT).show();

                }
                return true;
            }
        });

        //get slide show
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

        viewPager.setAdapter(viewPagerAdapter);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SlideshowTime(), 1000,2000);

        toolbar = getSupportActionBar();
        loadFragment(new PhoneFragment());

        //navigation drawer
        //navigation bottom
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        toolbar.setTitle("All phone");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(navAction.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class SlideshowTime extends TimerTask{

        @Override
        public void run() {

            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem()==0){
                        viewPager.setCurrentItem(1);
                    }
                    else if (viewPager.getCurrentItem()==1){
                        viewPager.setCurrentItem(2);
                    }
                    else if (viewPager.getCurrentItem()==2){
                        viewPager.setCurrentItem(3);
                    }
                    else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }

}
