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

public class EEEDepartment extends AppCompatActivity {
    private Toolbar mainToolbar;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eeedepartment);

        mainToolbar= (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        getSupportActionBar().setTitle("Select Subject");

        listView= (ListView) findViewById(R.id.eeeDepartment);

        final String[] eeeDepartment=getResources().getStringArray(R.array.EEE);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(EEEDepartment.this,R.layout.admin_sample_view,R.id.adminSampleViewId,eeeDepartment);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value=eeeDepartment[position];
                if(value.equals("Physics")){
                    Intent Physics = new Intent(EEEDepartment.this,AdminUploadDept.class);
                    Physics.putExtra("adminDept","Physics");
                    startActivity(Physics);

                }else if(value.equals("Electrical")){
                    Intent Electrical = new Intent(EEEDepartment.this,AdminUploadDept.class);
                    Electrical.putExtra("adminDept","Electrical");
                    startActivity(Electrical);
                }else if(value.equals("Electronics")){
                    Intent Electronics = new Intent(EEEDepartment.this,AdminUploadDept.class);
                    Electronics.putExtra("adminDept","Electronics");
                    startActivity(Electronics);

                }else if(value.equals("ECommerce")){
                    Intent ECommerce = new Intent(EEEDepartment.this,AdminUploadDept.class);
                    ECommerce.putExtra("adminDept","ECommerce");
                    startActivity(ECommerce);

                }else if(value.equals("DigitalLogic")){
                    Intent DigitalLogic = new Intent(EEEDepartment.this,AdminUploadDept.class);
                    DigitalLogic.putExtra("adminDept","Electronics");
                    startActivity(DigitalLogic);
                }
            }
        });
    }
}
