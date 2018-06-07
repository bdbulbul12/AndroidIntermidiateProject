package com.example.bulbul.onlinefileuploader.UserDepartmentSubject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.bulbul.onlinefileuploader.AdminDepartmentSubject.ECEDepartment;
import com.example.bulbul.onlinefileuploader.AdminUploadDept;
import com.example.bulbul.onlinefileuploader.MainActivity;
import com.example.bulbul.onlinefileuploader.R;
import com.google.firebase.auth.FirebaseAuth;

public class UserECEDepartment extends AppCompatActivity {

    private Toolbar mainToolbar;
    private ListView listView;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_ecedepartment);

        mainToolbar= (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        getSupportActionBar().setTitle("Select Subject");

        listView= (ListView) findViewById(R.id.userECEiew);

        mAuth = FirebaseAuth.getInstance();

        final String[] eceDepartment=getResources().getStringArray(R.array.ECE);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(UserECEDepartment.this,R.layout.admin_sample_view,R.id.adminSampleViewId,eceDepartment);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value=eceDepartment[position];

                if(value.equals("Telecommunication")){
                    Intent Telecommunication = new Intent(UserECEDepartment.this,MainActivity.class);
                    Telecommunication.putExtra("sub","Telecommunication");
                    startActivity(Telecommunication);

                }else if(value.equals("Engineering_Math")){
                    Intent Engineering_Math = new Intent(UserECEDepartment.this,MainActivity.class);
                    Engineering_Math.putExtra("sub","Engineering_Math");
                    startActivity(Engineering_Math);

                }else if(value.equals("Compiler_Design")){
                    Intent Compiler_Design = new Intent(UserECEDepartment.this,MainActivity.class);
                    Compiler_Design.putExtra("sub","Compiler_Design");
                    startActivity(Compiler_Design);

                }else if(value.equals("Applied_Math")){
                    Intent Applied_Math = new Intent(UserECEDepartment.this,MainActivity.class);
                    Applied_Math.putExtra("sub","Applied_Math");
                    startActivity(Applied_Math);

                }else if(value.equals("Applied_Physics")){
                    Intent Applied_Physics = new Intent(UserECEDepartment.this,MainActivity.class);
                    Applied_Physics.putExtra("sub","Applied_Physics");
                    startActivity(Applied_Physics);

                }
            }
        });
    }
}
