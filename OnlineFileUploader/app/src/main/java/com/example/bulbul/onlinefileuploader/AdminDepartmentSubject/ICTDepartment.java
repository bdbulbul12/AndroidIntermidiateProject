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

public class ICTDepartment extends AppCompatActivity {

    private Toolbar mainToolbar;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ictdepartment);

        mainToolbar= (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        getSupportActionBar().setTitle("Select Subject");

        listView= (ListView) findViewById(R.id.ictDepartment);

        final String[] ictDepartment=getResources().getStringArray(R.array.ICT);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ICTDepartment.this,R.layout.admin_sample_view,R.id.adminSampleViewId,ictDepartment);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value=ictDepartment[position];
                if(value.equals("C_Plus")){
                    Intent C_Plus = new Intent(ICTDepartment.this,AdminUploadDept.class);
                    C_Plus.putExtra("adminDept","C_Plus");
                    startActivity(C_Plus);

                }else if(value.equals("Oracle")){
                    Intent Oracle = new Intent(ICTDepartment.this,AdminUploadDept.class);
                    Oracle.putExtra("adminDept","Oracle");
                    startActivity(Oracle);

                }else if(value.equals("Networking")){
                    Intent Networking = new Intent(ICTDepartment.this,AdminUploadDept.class);
                    Networking.putExtra("adminDept","Networking");
                    startActivity(Networking);

                }else if(value.equals("Android")){
                    Intent Android = new Intent(ICTDepartment.this,AdminUploadDept.class);
                    Android.putExtra("adminDept","Android");
                    startActivity(Android);
                }else if(value.equals("Distributed")){
                    Intent Distributed = new Intent(ICTDepartment.this,AdminUploadDept.class);
                    Distributed.putExtra("adminDept","Distributed");
                    startActivity(Distributed);
                }
            }
        });
    }
}
