package com.example.bulbul.onlinefileuploader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.bulbul.onlinefileuploader.AdminDepartmentSubject.BBADepartment;
import com.example.bulbul.onlinefileuploader.AdminDepartmentSubject.CSEDepartment;
import com.example.bulbul.onlinefileuploader.AdminDepartmentSubject.ECEDepartment;
import com.example.bulbul.onlinefileuploader.AdminDepartmentSubject.ECONOMICSDepartment;
import com.example.bulbul.onlinefileuploader.AdminDepartmentSubject.EEEDepartment;
import com.example.bulbul.onlinefileuploader.AdminDepartmentSubject.ICTDepartment;
import com.example.bulbul.onlinefileuploader.AdminDepartmentSubject.ITEDepartment;

public class AdminSelectDepartment extends AppCompatActivity {

    private Toolbar mainToolbar;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_select_department);

        mainToolbar= (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        getSupportActionBar().setTitle("Select Department");

        listView= (ListView) findViewById(R.id.adminSelectedDeptListView);

        final String[] department=getResources().getStringArray(R.array.department);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AdminSelectDepartment.this,R.layout.admin_sample_view,R.id.adminSampleViewId,department);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String value=department[position];
                if(value.equals("CSE")){
                    Intent cseIntent = new Intent(AdminSelectDepartment.this,CSEDepartment.class);
                    cseIntent.putExtra("adminDept","CSE");
                    startActivity(cseIntent);

                }else if(value.equals("ICT")){
                    Intent cseIntent = new Intent(AdminSelectDepartment.this,ICTDepartment.class);
                    cseIntent.putExtra("adminDept","ICT");
                    startActivity(cseIntent);

                }else if(value.equals("EEE")){
                    Intent eeeIntent = new Intent(AdminSelectDepartment.this,EEEDepartment.class);
                    eeeIntent.putExtra("adminDept","EEE");
                    startActivity(eeeIntent);

                }else if(value.equals("ITE")){
                    Intent iteIntent = new Intent(AdminSelectDepartment.this,ITEDepartment.class);
                    iteIntent.putExtra("adminDept","ITE");
                    startActivity(iteIntent);

                }else if(value.equals("BBA")){
                    Intent bbaIntent = new Intent(AdminSelectDepartment.this,BBADepartment.class);
                    bbaIntent.putExtra("adminDept","BBA");
                    startActivity(bbaIntent);

                }else if(value.equals("ECE")){
                    Intent eceIntent = new Intent(AdminSelectDepartment.this,ECEDepartment.class);
                    eceIntent.putExtra("adminDept","ECE");
                    startActivity(eceIntent);

                }else if(value.equals("ECONOMICS")){
                    Intent ecoIntent = new Intent(AdminSelectDepartment.this,ECONOMICSDepartment.class);
                    ecoIntent.putExtra("adminDept","ECONOMICS");
                    startActivity(ecoIntent);

                }
            }
        });
    }
}
