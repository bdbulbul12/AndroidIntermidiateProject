package com.example.bulbul.onlinefileuploader.UserDepartmentSubject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.bulbul.onlinefileuploader.AdminDepartmentSubject.ECONOMICSDepartment;
import com.example.bulbul.onlinefileuploader.AdminUploadDept;
import com.example.bulbul.onlinefileuploader.MainActivity;
import com.example.bulbul.onlinefileuploader.R;
import com.google.firebase.auth.FirebaseAuth;

public class UserEconomicsDepartment extends AppCompatActivity {

    private Toolbar mainToolbar;
    private ListView listView;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_economics_department);

        mainToolbar= (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        getSupportActionBar().setTitle("Select Subject");

        listView= (ListView) findViewById(R.id.userEconomicsView);

        mAuth = FirebaseAuth.getInstance();

        final String[] economicsDepartment=getResources().getStringArray(R.array.ECONOMICS);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(UserEconomicsDepartment.this,R.layout.admin_sample_view,R.id.adminSampleViewId,economicsDepartment);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value=economicsDepartment[position];

                if(value.equals("Economics")){
                    Intent Economics = new Intent(UserEconomicsDepartment.this,MainActivity.class);
                    Economics.putExtra("sub","Economics");
                    startActivity(Economics);

                }else if(value.equals("History")){
                    Intent History = new Intent(UserEconomicsDepartment.this,MainActivity.class);
                    History.putExtra("sub","History");
                    startActivity(History);

                }else if(value.equals("Political_Science")){
                    Intent Political_Science = new Intent(UserEconomicsDepartment.this,MainActivity.class);
                    Political_Science.putExtra("sub","Political_Science");
                    startActivity(Political_Science);

                }else if(value.equals("Basic_Economics")){
                    Intent Basic_Economics = new Intent(UserEconomicsDepartment.this,MainActivity.class);
                    Basic_Economics.putExtra("sub","Basic_Economics");
                    startActivity(Basic_Economics);

                }else if(value.equals("Principal_Of_Economics")){
                    Intent Principal_Of_Economics = new Intent(UserEconomicsDepartment.this,MainActivity.class);
                    Principal_Of_Economics.putExtra("sub","Principal_Of_Economics");
                    startActivity(Principal_Of_Economics);

                }
            }
        });

    }
}
