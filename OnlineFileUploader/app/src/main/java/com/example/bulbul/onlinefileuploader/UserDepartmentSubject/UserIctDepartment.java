package com.example.bulbul.onlinefileuploader.UserDepartmentSubject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.bulbul.onlinefileuploader.AdminDepartmentSubject.ICTDepartment;
import com.example.bulbul.onlinefileuploader.AdminUploadDept;
import com.example.bulbul.onlinefileuploader.MainActivity;
import com.example.bulbul.onlinefileuploader.R;
import com.google.firebase.auth.FirebaseAuth;

public class UserIctDepartment extends AppCompatActivity {

    private Toolbar mainToolbar;
    private ListView listView;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_ict_department);

        mainToolbar= (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        getSupportActionBar().setTitle("Select Subject");

        listView= (ListView) findViewById(R.id.userICTView);

        mAuth = FirebaseAuth.getInstance();

        final String[] ictDepartment=getResources().getStringArray(R.array.ICT);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(UserIctDepartment.this,R.layout.admin_sample_view,R.id.adminSampleViewId,ictDepartment);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value=ictDepartment[position];

                if(value.equals("C_Plus")){
                    Intent C_Plus = new Intent(UserIctDepartment.this,MainActivity.class);
                    C_Plus.putExtra("sub","C_Plus");
                    startActivity(C_Plus);

                }else if(value.equals("Oracle")){
                    Intent Oracle = new Intent(UserIctDepartment.this,MainActivity.class);
                    Oracle.putExtra("sub","Oracle");
                    startActivity(Oracle);

                }else if(value.equals("Networking")){
                    Intent Networking = new Intent(UserIctDepartment.this,MainActivity.class);
                    Networking.putExtra("sub","Networking");
                    startActivity(Networking);

                }else if(value.equals("Android")){
                    Intent Android = new Intent(UserIctDepartment.this,MainActivity.class);
                    Android.putExtra("sub","Android");
                    startActivity(Android);
                }else if(value.equals("Distributed")){
                    Intent Distributed = new Intent(UserIctDepartment.this,MainActivity.class);
                    Distributed.putExtra("sub","Distributed");
                    startActivity(Distributed);
                }
            }
        });

    }
}
