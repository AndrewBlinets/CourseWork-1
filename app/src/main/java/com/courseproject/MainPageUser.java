package com.courseproject;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainPageUser extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {

    private static final int LAYOUT = R.layout.main_page_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        try {
//            fragmentManager.beginTransaction().replace(R.id.flContent,
//                    (FragmentSearch.class).newInstance()).commit();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//
//        navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.inflateMenu(R.menu.menu_navigation_authorized_user);
//        navigationView.setNavigationItemSelectedListener(this);
//
//        String name = getIntent().getExtras().getString(FragmentConstants.USER_NAME);
//        String surname = getIntent().getExtras().getString(FragmentConstants.USER_SURNAME);
//        token = getIntent().getExtras().getString(FragmentConstants.USER_TOKEN);
//        View header = navigationView.inflateHeaderView(R.layout.navigation_header);
//        ((TextView) header.findViewById(R.id.textViewSurname)).setText(surname);
//        ((TextView) header.findViewById(R.id.textViewName)).setText(name);

    }

    @Override
    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu_toolbar; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        Class fragmentClass = null;
//        switch(item.getItemId())
//        {
//            case R.id.search_menu_toolbar:
//            {
//                fragmentClass = FragmentSearch.class;
//                break;
//            }
//        }
//
//        if(fragmentClass != null) {
//            try {
//                fragment = (Fragment) fragmentClass.newInstance();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            fragmentManager = getSupportFragmentManager();
//            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
//        }
//
//        return super.onOptionsItemSelected(item);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
//        Fragment fragmentClass = null;
//        switch(item.getItemId())
//        {
//            case R.id.i_am_driver_menu_navigation:
//            {
//                fragmentClass = new FragmntMyRoadIDriver();
//                Bundle bundle = new Bundle();
//                bundle.putString("token", token);
//                fragmentClass.setArguments(bundle);
//                break;
//            }
//            case R.id.i_am_companion_menu_navigation:
//            {
//                fragmentClass = new FragmntMyRoadIDriver();
//                Bundle bundle = new Bundle();
//                bundle.putString("token", token);
//                fragmentClass.setArguments(bundle);
//                break;
//            }
//            case R.id.create_road_menu_navigation:
//            {
//                fragmentClass = new FragmentCreateRoad();
//                break;
//            }
//            case R.id.search_menu_navigation:
//            {
//                fragmentClass = new FragmentSearch();
//                break;
//            }
//            case R.id.create_req_menu_navigation:
//            {
//                fragmentClass = new FragmentCreateRequest();
//                break;
//            }
//            case R.id.popula_menu_navigation:
//            {
//                fragmentClass = new FragmentCreateRequest();
//                break;
//            }
//            case R.id.settings_menu_navigation:
//            {
//                fragmentClass = new FragmentRegistration();
//                break;
//            }
//            case R.id.exit_menu_navigation: {
//                Intent intent = new Intent(this, MainActivityNotAutorizationUser.class);
//                startActivity(intent);
//                break;
//            }
//        }

//        if(fragmentClass != null) {
//            try {
//                fragment = (Fragment) fragmentClass.newInstance();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.flContent, fragmentClass).commit();
//        //}
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
