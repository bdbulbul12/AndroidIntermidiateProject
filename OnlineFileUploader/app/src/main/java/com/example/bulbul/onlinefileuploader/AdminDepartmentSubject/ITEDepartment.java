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

public class ITEDepartment extends AppCompatActivity {
    private Toolbar mainToolbar;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itedepartment);

        mainToolbar= (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        getSupportActionBar().setTitle("Select Subject");

        listView= (ListView) findViewById(R.id.iteDepartment);

        final String[] iteDepartment=getResources().getStringArray(R.array.ITE);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ITEDepartment.this,R.layout.admin_sample_view,R.id.adminSampleViewId,iteDepartment);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value=iteDepartment[position];
                if(value.equals("Operating_System")){
                    Intent Operating_System = new Intent(ITEDepartment.this,AdminUploadDept.class);
                    Operating_System.putExtra("adminDept","Operating_System");
                    startActivity(Operating_System);

                }else if(value.equals("Web_Engineering")){
                    Intent Web_Engineering = new Intent(ITEDepartment.this,AdminUploadDept.class);
                    Web_Engineering.putExtra("adminDept","Web_Engineering");
                    startActivity(Web_Engineering);

                }else if(value.equals("Graphics")){
                    Intent Graphics = new Intent(ITEDepartment.this,AdminUploadDept.class);
                    Graphics.putExtra("adminDept","Graphics");
                    startActivity(Graphics);

                }else if(value.equals("IT")){
                    Intent IT = new Intent(ITEDepartment.this,AdminUploadDept.class);
                    IT.putExtra("adminDept","IT");
                    startActivity(IT);
                }else if(value.equals("Geometry")){
                    Intent Geometry = new Intent(ITEDepartment.this,AdminUploadDept.class);
                    Geometry.putExtra("adminDept","Geometry");
                    startActivity(Geometry);
                }
            }
        });
    }
}
