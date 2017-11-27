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

import com.courseproject.fragments.FragmentMark;
import com.courseproject.fragments.FragmentSchedule;
// гланый класс для отображения инфы
public class MainPageUser extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {

    private static final int LAYOUT = R.layout.main_page_user;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private Fragment fragment = null;
    private FragmentManager fragmentManager;

    protected long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {//устанавливаем выдвигающееся меню и стандартный фрагмент
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        id = getIntent().getExtras().getLong(ConstantsForIntent.idStudent);// получаем инфу о id студента
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragmentClass = new EducationCardFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(ConstantsForIntent.idStudent, id);
        fragmentClass.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.flContent, fragmentClass).commit(); // устанавливаем фрагмент по умолчанию

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.inflateMenu(R.menu.menu);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {// переопределяем кнопку назад, для того чтобы с ее помощью можно было закрыть меню
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
    public boolean onNavigationItemSelected(MenuItem item) {// метод по обработке нажатия пунктов меню
        Fragment fragmentClass = null;
        switch(item.getItemId())
        {//устанавливаем соотвествующий фрагмент и передаюем необходимые данные
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
            case R.id.ras:
            {
                fragmentClass = new FragmentSchedule();
                Bundle bundle = new Bundle();
                bundle.putLong(ConstantsForIntent.idStudent, id);
                fragmentClass.setArguments(bundle);
                break;
            }
            case R.id.exit: {

                Intent intent = new Intent(this, BaseActivity.class);
                startActivityForResult(intent,1);
                break;
            }
        }
        if(fragmentClass != null){
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragmentClass).commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
