package com.example.bulbul.onlinefileuploader.AdminDepartmentSubject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.bulbul.onlinefileuploader.AdminUploadDept;
import com.example.bulbul.onlinefileuploader.R;

public class CSEDepartment extends AppCompatActivity {
    private Toolbar mainToolbar;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csedepartment);

        mainToolbar= (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        getSupportActionBar().setTitle("Select Subject");

        listView= (ListView) findViewById(R.id.cseDepartment);

        final String[] cseDepartment=getResources().getStringArray(R.array.CSE);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CSEDepartment.this,R.layout.admin_sample_view,R.id.adminSampleViewId,cseDepartment);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value=cseDepartment[position];
                if(value.equals("C_Programming")){
                    Intent C_Programming = new Intent(CSEDepartment.this,AdminUploadDept.class);
                    C_Programming.putExtra("adminDept","C_Programming");
                    startActivity(C_Programming);

                }else if(value.equals("Computer_Fundamental")){
                    Intent Computer_Fundamental = new Intent(CSEDepartment.this,AdminUploadDept.class);
                    Computer_Fundamental.putExtra("adminDept","Computer_Fundamental");
                    startActivity(Computer_Fundamental);
                }else if(value.equals("Database")){
                    Intent Database = new Intent(CSEDepartment.this,AdminUploadDept.class);
                    Database.putExtra("adminDept","Database");
                    startActivity(Database);
                }else if(value.equals("Java")){
                    Intent Java = new Intent(CSEDepartment.this,AdminUploadDept.class);
                    Java.putExtra("adminDept","Java");
                    startActivity(Java);

                }else if(value.equals("Algorithm")){
                    Intent Algorithm = new Intent(CSEDepartment.this,AdminUploadDept.class);
                    Algorithm.putExtra("adminDept","Algorithm");
                    startActivity(Algorithm);
                }
            }
        });
    }
}
