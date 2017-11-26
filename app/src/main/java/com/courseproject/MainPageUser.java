package com.courseproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.courseproject.constants.ConstantsForIntent;
import com.courseproject.fragments.EducationCardFragment;

import com.courseproject.fragments.FragmentRating;
import com.courseproject.fragments.FragmentMark;

public class MainPageUser extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {

    private static final int LAYOUT = R.layout.main_page_user;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private Fragment fragment = null;
    private FragmentManager fragmentManager;

    protected long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        id = getIntent().getExtras().getLong(ConstantsForIntent.idStudent);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragmentClass = new EducationCardFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(ConstantsForIntent.idStudent, id);
        fragmentClass.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.flContent, fragmentClass).commit();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.inflateMenu(R.menu.menu);
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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragmentClass = null;
        switch(item.getItemId())
        {
            case R.id.education_card:
            {
                fragmentClass = new EducationCardFragment();
                Bundle bundle = new Bundle();
                bundle.putLong(ConstantsForIntent.idStudent, id);
                fragmentClass.setArguments(bundle);
                break;
            }
            case R.id.mark:
            {
                fragmentClass = new FragmentMark();
                Bundle bundle = new Bundle();
                bundle.putLong(ConstantsForIntent.idStudent, id);
                fragmentClass.setArguments(bundle);
                break;
            }
            case R.id.rating:
            {
                fragmentClass = new FragmentRating();
                break;
            }
            case R.id.exit: {

                Intent intent = new Intent(this, BaseActivity.class);
                setResult(RESULT_OK, intent);
                break;
            }
        }

//        if(fragmentClass != null) {
//            try {
//                fragment = (Fragment) fragmentClass.newInstance();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragmentClass).commit();
        //}
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
