package com.example.bulbul.navigationdrawer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        navigationView= (NavigationView) findViewById(R.id.nav_view);

        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawer);
        mToggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setUpDrawerContent(navigationView);

        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,new Dasboard()).commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void selectItemDrawer(MenuItem menuItem){
        switch(menuItem.getItemId()){
            case R.id.db:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,new Dasboard()).commit();
                menuItem.setChecked(true);
                setTitle(menuItem.getTitle());
                mDrawerLayout.closeDrawers();
                break;
            case R.id.event:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,new Events()).commit();
                menuItem.setChecked(true);
                setTitle(menuItem.getTitle());
                mDrawerLayout.closeDrawers();
                break;
            case R.id.settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,new Settings()).commit();
                menuItem.setChecked(true);
                setTitle(menuItem.getTitle());
                mDrawerLayout.closeDrawers();
                break;
            case R.id.search:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,new Search()).commit();
                menuItem.setChecked(true);
                setTitle(menuItem.getTitle());
                mDrawerLayout.closeDrawers();
                break;
            default:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,new Settings()).commit();
                break;
        }
    }

    public void setUpDrawerContent(NavigationView navigationView){

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                selectItemDrawer(item);
                return true;
            }
        });
    }

}
