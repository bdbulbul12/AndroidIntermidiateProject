package com.example.bulbul.universityfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {

    public static final String ADMIN_USERNAME="admin";
    public static final String ADMIN_PASSWORD="123";

    private EditText admin_name;
    private EditText admin_password;
    private Button admin_login;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        admin_name= (EditText) findViewById(R.id.admin_name);
        admin_password= (EditText) findViewById(R.id.admin_password);
        admin_login= (Button) findViewById(R.id.admin_login);

        admin_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = admin_name.getText().toString().trim();
                String password = admin_password.getText().toString().trim();

                if(name.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)){
                    Intent intent=new Intent(AdminLogin.this,MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(AdminLogin.this,"Admin Succesfully Access",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(AdminLogin.this,"Username or Password Not Match or Contact with Admin : BD BULBUL",Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
