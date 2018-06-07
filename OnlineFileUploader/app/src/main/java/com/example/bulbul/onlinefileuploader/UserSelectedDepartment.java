package com.example.bulbul.onlinefileuploader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.bulbul.onlinefileuploader.UserDepartmentSubject.UserBBADepartment;
import com.example.bulbul.onlinefileuploader.UserDepartmentSubject.UserCSEDepartment;
import com.example.bulbul.onlinefileuploader.UserDepartmentSubject.UserECEDepartment;
import com.example.bulbul.onlinefileuploader.UserDepartmentSubject.UserEconomicsDepartment;
import com.example.bulbul.onlinefileuploader.UserDepartmentSubject.UserEeeDepartment;
import com.example.bulbul.onlinefileuploader.UserDepartmentSubject.UserITEDepartment;
import com.example.bulbul.onlinefileuploader.UserDepartmentSubject.UserIctDepartment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserSelectedDepartment extends AppCompatActivity {
    private Toolbar mainToolbar;
    private ListView listView;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_selected_department);


        mainToolbar= (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        getSupportActionBar().setTitle("Select Department");

        listView= (ListView) findViewById(R.id.userSelectedDeptListView);

        mAuth = FirebaseAuth.getInstance();

        final String[] department=getResources().getStringArray(R.array.department);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(UserSelectedDepartment.this,R.layout.admin_sample_view,R.id.adminSampleViewId,department);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String value=department[position];
                if(value.equals("CSE")){
                    Intent cseIntent = new Intent(UserSelectedDepartment.this,UserCSEDepartment.class);
                    cseIntent.putExtra("dept","CSE");
                    startActivity(cseIntent);

                }else if(value.equals("ICT")){
                    Intent ictIntent = new Intent(UserSelectedDepartment.this,UserIctDepartment.class);
                    ictIntent.putExtra("dept","ICT");
                    startActivity(ictIntent);

                }else if(value.equals("EEE")){
                    Intent eeeIntent = new Intent(UserSelectedDepartment.this,UserEeeDepartment.class);
                    eeeIntent.putExtra("dept","EEE");
                    startActivity(eeeIntent);

                }else if(value.equals("ITE")){
                    Intent iteIntent = new Intent(UserSelectedDepartment.this,UserITEDepartment.class);
                    iteIntent.putExtra("dept","ITE");
                    startActivity(iteIntent);

                }else if(value.equals("BBA")){
                    Intent bbaIntent = new Intent(UserSelectedDepartment.this,UserBBADepartment.class);
                    bbaIntent.putExtra("dept","BBA");
                    startActivity(bbaIntent);

                }else if(value.equals("ECE")){
                    Intent eceIntent = new Intent(UserSelectedDepartment.this,UserECEDepartment.class);
                    eceIntent.putExtra("dept","ECE");
                    startActivity(eceIntent);

                }else if(value.equals("ECONOMICS")){
                    Intent ecoIntent = new Intent(UserSelectedDepartment.this,UserEconomicsDepartment.class);
                    ecoIntent.putExtra("dept","ECONOMICS");
                    startActivity(ecoIntent);

                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser= FirebaseAuth.getInstance().getCurrentUser();

        if(currentUser==null){
            sendToLogin();
        }
    }

    private void sendToLogin() {
        Intent loginIntent = new Intent(UserSelectedDepartment.this,LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_logout_btn:
                logOut();
                return true;

            default:
                return false;

        }

    }

    private void logOut() {
        mAuth.signOut();
        sendToLogin();
    }

}
