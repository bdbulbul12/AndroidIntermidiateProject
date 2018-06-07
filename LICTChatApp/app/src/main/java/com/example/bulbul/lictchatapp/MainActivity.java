package com.example.bulbul.lictchatapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.media.RatingCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    private DatabaseReference mUserRef;
    private TabLayout mTablayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth= FirebaseAuth.getInstance();
        mToolbar= (Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("LICT Chat");

        if(auth.getCurrentUser() !=null){
            mUserRef= FirebaseDatabase.getInstance().getReference().child("Users").child(auth.getCurrentUser().getUid());
        }



        //tabs
        mViewPager= (ViewPager) findViewById(R.id.main_tab_pager);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(mSectionsPagerAdapter);

        mTablayout= (TabLayout) findViewById(R.id.main_tabs);
        mTablayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser==null){
            sendTostart();
        }else {
            mUserRef.child("online").setValue("true");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        FirebaseUser currentUser = auth.getCurrentUser();

        if(currentUser !=null){

            mUserRef.child("online").setValue(ServerValue.TIMESTAMP);

        }


    }

    private void sendTostart() {
        Intent intent = new Intent(MainActivity.this, StartActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId()==R.id.main_logout_btn){
            FirebaseAuth.getInstance().signOut();
            sendTostart();
        }

        if(item.getItemId()==R.id.main_setting_btn){
            Intent settingsIntent = new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(settingsIntent);
        }

        if(item.getItemId()==R.id.main_all_btn){
            Intent settingsIntent = new Intent(MainActivity.this,UsersActivity.class);
            startActivity(settingsIntent);
        }

        return true;
    }
}
