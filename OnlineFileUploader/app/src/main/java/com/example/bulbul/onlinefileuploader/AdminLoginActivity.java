package com.example.bulbul.onlinefileuploader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLoginActivity extends AppCompatActivity {
    private Toolbar mainToolbar;
    public static final String adminUserName="admin";
    public static final String adminPassword="admin";

    private EditText adminUser,adminPass;
    private Button adminLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        adminUser= (EditText) findViewById(R.id.adminUserName);
        adminPass= (EditText) findViewById(R.id.adminUserPassword);
        adminLogin= (Button) findViewById(R.id.adminLogin);

        mainToolbar= (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        getSupportActionBar().setTitle("Admin Login");


        adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName=adminUser.getText().toString().trim();
                String password=adminPass.getText().toString().trim();

                if(userName.equals(adminUserName) && password.equals(adminPassword)){
                    Intent adminIntent=new Intent(AdminLoginActivity.this,AdminSelectDepartment.class);
                    startActivity(adminIntent);
                }else{
                    Toast.makeText(AdminLoginActivity.this,"UserName or Password does not match",Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
