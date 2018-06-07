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

public class ECEDepartment extends AppCompatActivity {
    private Toolbar mainToolbar;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecedepartment);

        mainToolbar= (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        getSupportActionBar().setTitle("Select Subject");

        listView= (ListView) findViewById(R.id.eceDepartment);

        final String[] eceDepartment=getResources().getStringArray(R.array.ECE);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ECEDepartment.this,R.layout.admin_sample_view,R.id.adminSampleViewId,eceDepartment);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value=eceDepartment[position];
                if(value.equals("Telecommunication")){
                    Intent Telecommunication = new Intent(ECEDepartment.this,AdminUploadDept.class);
                    Telecommunication.putExtra("adminDept","Telecommunication");
                    startActivity(Telecommunication);

                }else if(value.equals("Engineering_Math")){
                    Intent Engineering_Math = new Intent(ECEDepartment.this,AdminUploadDept.class);
                    Engineering_Math.putExtra("adminDept","Engineering_Math");
                    startActivity(Engineering_Math);

                }else if(value.equals("Compiler_Design")){
                    Intent Compiler_Design = new Intent(ECEDepartment.this,AdminUploadDept.class);
                    Compiler_Design.putExtra("adminDept","Compiler_Design");
                    startActivity(Compiler_Design);

                }else if(value.equals("Applied_Math")){
                    Intent Applied_Math = new Intent(ECEDepartment.this,AdminUploadDept.class);
                    Applied_Math.putExtra("adminDept","Applied_Math");
                    startActivity(Applied_Math);

                }else if(value.equals("Applied_Physics")){
                    Intent Applied_Physics = new Intent(ECEDepartment.this,AdminUploadDept.class);
                    Applied_Physics.putExtra("adminDept","Applied_Physics");
                    startActivity(Applied_Physics);

                }
            }
        });
    }
}
