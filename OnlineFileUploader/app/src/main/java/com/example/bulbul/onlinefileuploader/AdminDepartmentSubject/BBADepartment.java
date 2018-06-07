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

public class BBADepartment extends AppCompatActivity {

    private Toolbar mainToolbar;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bbadepartment);

        mainToolbar= (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        getSupportActionBar().setTitle("Select Subject");

        listView= (ListView) findViewById(R.id.bbaDepartment);

        final String[] bbaDepartment=getResources().getStringArray(R.array.BBA);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(BBADepartment.this,R.layout.admin_sample_view,R.id.adminSampleViewId,bbaDepartment);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value=bbaDepartment[position];
                if(value.equals("BCommunication")){
                    Intent BCommunication = new Intent(BBADepartment.this,AdminUploadDept.class);
                    BCommunication.putExtra("adminDept","BCommunication");
                    startActivity(BCommunication);

                }else if(value.equals("BMath")){
                    Intent BMath = new Intent(BBADepartment.this,AdminUploadDept.class);
                    BMath.putExtra("adminDept","BMath");
                    startActivity(BMath);

                }else if(value.equals("DataStructure")){
                    Intent DataStructure = new Intent(BBADepartment.this,AdminUploadDept.class);
                    DataStructure.putExtra("adminDept","DataStructure");
                    startActivity(DataStructure);

                }else if(value.equals("Accounting")){
                    Intent Accounting = new Intent(BBADepartment.this,AdminUploadDept.class);
                    Accounting.putExtra("adminDept","Accounting");
                    startActivity(Accounting);

                }else if(value.equals("Finance")){
                    Intent Finance = new Intent(BBADepartment.this,AdminUploadDept.class);
                    Finance.putExtra("adminDept","Finance");
                    startActivity(Finance);
                }
            }
        });
    }
}
