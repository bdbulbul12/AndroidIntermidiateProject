package com.example.bulbul.onlinefileuploader.UserDepartmentSubject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.bulbul.onlinefileuploader.AdminDepartmentSubject.ITEDepartment;
import com.example.bulbul.onlinefileuploader.AdminUploadDept;
import com.example.bulbul.onlinefileuploader.MainActivity;
import com.example.bulbul.onlinefileuploader.R;
import com.google.firebase.auth.FirebaseAuth;

public class UserITEDepartment extends AppCompatActivity {

    private Toolbar mainToolbar;
    private ListView listView;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_itedepartment);

        mainToolbar= (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        getSupportActionBar().setTitle("Select Subject");

        listView= (ListView) findViewById(R.id.iteDepartment);

        mAuth = FirebaseAuth.getInstance();

        final String[] iteDepartment=getResources().getStringArray(R.array.ITE);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(UserITEDepartment.this,R.layout.admin_sample_view,R.id.adminSampleViewId,iteDepartment);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value=iteDepartment[position];

                if(value.equals("Operating_System")){
                    Intent Operating_System = new Intent(UserITEDepartment.this,MainActivity.class);
                    Operating_System.putExtra("sub","Operating_System");
                    startActivity(Operating_System);

                }else if(value.equals("Web_Engineering")){
                    Intent Web_Engineering = new Intent(UserITEDepartment.this,MainActivity.class);
                    Web_Engineering.putExtra("sub","Web_Engineering");
                    startActivity(Web_Engineering);

                }else if(value.equals("Graphics")){
                    Intent Graphics = new Intent(UserITEDepartment.this,MainActivity.class);
                    Graphics.putExtra("sub","Graphics");
                    startActivity(Graphics);

                }else if(value.equals("IT")){
                    Intent IT = new Intent(UserITEDepartment.this,MainActivity.class);
                    IT.putExtra("sub","IT");
                    startActivity(IT);

                }else if(value.equals("Geometry")){
                    Intent Geometry = new Intent(UserITEDepartment.this,MainActivity.class);
                    Geometry.putExtra("sub","Geometry");
                    startActivity(Geometry);

                }
            }
        });
    }
}
