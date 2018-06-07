package com.example.bulbul.onlinefileuploader.UserDepartmentSubject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.bulbul.onlinefileuploader.AdminDepartmentSubject.BBADepartment;
import com.example.bulbul.onlinefileuploader.AdminUploadDept;
import com.example.bulbul.onlinefileuploader.MainActivity;
import com.example.bulbul.onlinefileuploader.R;
import com.google.firebase.auth.FirebaseAuth;

public class UserBBADepartment extends AppCompatActivity {

    private Toolbar mainToolbar;
    private ListView listView;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_bbadepartment);

        mainToolbar= (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        getSupportActionBar().setTitle("Select Subject");

        listView= (ListView) findViewById(R.id.userBBAView);

        mAuth = FirebaseAuth.getInstance();

        final String[] bbaDepartment=getResources().getStringArray(R.array.BBA);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(UserBBADepartment.this,R.layout.admin_sample_view,R.id.adminSampleViewId,bbaDepartment);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value=bbaDepartment[position];

                if(value.equals("BCommunication")){
                    Intent BCommunication = new Intent(UserBBADepartment.this,MainActivity.class);
                    BCommunication.putExtra("sub","BCommunication");
                    startActivity(BCommunication);

                }else if(value.equals("BMath")){
                    Intent BMath = new Intent(UserBBADepartment.this,MainActivity.class);
                    BMath.putExtra("sub","BMath");
                    startActivity(BMath);

                }else if(value.equals("DataStructure")){
                    Intent DataStructure = new Intent(UserBBADepartment.this,MainActivity.class);
                    DataStructure.putExtra("sub","DataStructure");
                    startActivity(DataStructure);

                }else if(value.equals("Accounting")){
                    Intent Accounting = new Intent(UserBBADepartment.this,MainActivity.class);
                    Accounting.putExtra("sub","Accounting");
                    startActivity(Accounting);

                }else if(value.equals("Finance")){
                    Intent Finance = new Intent(UserBBADepartment.this,MainActivity.class);
                    Finance.putExtra("sub","Finance");
                    startActivity(Finance);
                }
            }
        });

    }
}
