package com.example.bulbul.onlinefileuploader.UserDepartmentSubject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.bulbul.onlinefileuploader.AdminDepartmentSubject.EEEDepartment;
import com.example.bulbul.onlinefileuploader.AdminUploadDept;
import com.example.bulbul.onlinefileuploader.MainActivity;
import com.example.bulbul.onlinefileuploader.R;
import com.google.firebase.auth.FirebaseAuth;

public class UserEeeDepartment extends AppCompatActivity {

    private Toolbar mainToolbar;
    private ListView listView;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_eee_department);

        mainToolbar= (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        getSupportActionBar().setTitle("Select Subject");

        listView= (ListView) findViewById(R.id.eeeDepartment);

        mAuth = FirebaseAuth.getInstance();

        final String[] eeeDepartment=getResources().getStringArray(R.array.EEE);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(UserEeeDepartment.this,R.layout.admin_sample_view,R.id.adminSampleViewId,eeeDepartment);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value=eeeDepartment[position];

                if(value.equals("Physics")){
                    Intent Physics = new Intent(UserEeeDepartment.this,MainActivity.class);
                    Physics.putExtra("sub","Physics");
                    startActivity(Physics);

                }else if(value.equals("Electrical")){
                    Intent Electrical = new Intent(UserEeeDepartment.this,MainActivity.class);
                    Electrical.putExtra("sub","Electrical");
                    startActivity(Electrical);
                }else if(value.equals("Electronics")){
                    Intent Electronics = new Intent(UserEeeDepartment.this,MainActivity.class);
                    Electronics.putExtra("sub","Electronics");
                    startActivity(Electronics);

                }else if(value.equals("ECommerce")){
                    Intent ECommerce = new Intent(UserEeeDepartment.this,MainActivity.class);
                    ECommerce.putExtra("sub","ECommerce");
                    startActivity(ECommerce);

                }else if(value.equals("DigitalLogic")){
                    Intent DigitalLogic = new Intent(UserEeeDepartment.this,MainActivity.class);
                    DigitalLogic.putExtra("sub","DigitalLogic");
                    startActivity(DigitalLogic);
                }
            }
        });

    }
}
