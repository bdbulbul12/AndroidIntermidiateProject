package com.example.bulbul.onlinefileuploader.UserDepartmentSubject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.bulbul.onlinefileuploader.AdminDepartmentSubject.CSEDepartment;
import com.example.bulbul.onlinefileuploader.AdminUploadDept;
import com.example.bulbul.onlinefileuploader.MainActivity;
import com.example.bulbul.onlinefileuploader.R;
import com.example.bulbul.onlinefileuploader.UserSelectedDepartment;
import com.google.firebase.auth.FirebaseAuth;

public class UserCSEDepartment extends AppCompatActivity {

    private Toolbar mainToolbar;
    private ListView listView;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_csedepartment);

        mainToolbar= (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        getSupportActionBar().setTitle("Select Subject");

        listView= (ListView) findViewById(R.id.userCSEView);

        mAuth = FirebaseAuth.getInstance();

        final String[] cseDepartment=getResources().getStringArray(R.array.CSE);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(UserCSEDepartment.this,R.layout.admin_sample_view,R.id.adminSampleViewId,cseDepartment);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value=cseDepartment[position];

                if(value.equals("C_Programming")){
                    Intent C_Programming = new Intent(UserCSEDepartment.this,MainActivity.class);
                    C_Programming.putExtra("sub","C_Programming");
                    startActivity(C_Programming);

                }else if(value.equals("Computer_Fundamental")){
                    Intent Computer_Fundamental = new Intent(UserCSEDepartment.this,MainActivity.class);
                    Computer_Fundamental.putExtra("sub","Computer_Fundamental");
                    startActivity(Computer_Fundamental);
                }else if(value.equals("Database")){
                    Intent Database = new Intent(UserCSEDepartment.this,MainActivity.class);
                    Database.putExtra("sub","Database");
                    startActivity(Database);
                }else if(value.equals("Java")){
                    Intent Java = new Intent(UserCSEDepartment.this,MainActivity.class);
                    Java.putExtra("sub","Java");
                    startActivity(Java);

                }else if(value.equals("Algorithm")){
                    Intent Algorithm = new Intent(UserCSEDepartment.this,MainActivity.class);
                    Algorithm.putExtra("sub","Algorithm");
                    startActivity(Algorithm);
                }
            }
        });


    }
}
