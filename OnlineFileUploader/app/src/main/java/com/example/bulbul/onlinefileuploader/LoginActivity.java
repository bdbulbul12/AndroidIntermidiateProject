package com.example.bulbul.onlinefileuploader;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText loginEmailText;
    private EditText loginPasswordText;
    private Button loginBtn;
    private Button loginregBtn;
    private Button adminLogin;

    private FirebaseAuth mAuth;

    private ProgressBar loginProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth =FirebaseAuth.getInstance();


        loginEmailText= (EditText) findViewById(R.id.login_email);
        loginPasswordText= (EditText) findViewById(R.id.login_password);
        loginBtn= (Button) findViewById(R.id.login_btn);
        loginregBtn= (Button) findViewById(R.id.login_reg_btn);
        loginProgress= (ProgressBar) findViewById(R.id.login_progress);
        adminLogin= (Button) findViewById(R.id.adminLoginId);


        adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent adminIntent = new Intent(LoginActivity.this,AdminLoginActivity.class);
                startActivity(adminIntent);
            }
        });



        loginregBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent regIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(regIntent);

            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String loginEmail = loginEmailText.getText().toString();
                String loginPass = loginPasswordText.getText().toString();

                if(!TextUtils.isEmpty(loginEmail) && !TextUtils.isEmpty(loginPass)){
                    loginProgress.setVisibility(View.VISIBLE);

                    mAuth.signInWithEmailAndPassword(loginEmail,loginPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                sendToMain();

                            }else {

                                String errorMessage = task.getException().getMessage();
                                Toast.makeText(LoginActivity.this,"Error"+errorMessage,Toast.LENGTH_LONG).show();
                            }
                            loginProgress.setVisibility(View.INVISIBLE);
                        }
                    });


                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser !=null){
            sendToMain();

        }
    }

    private void sendToMain() {
        Intent mainIntent = new Intent(LoginActivity.this,UserSelectedDepartment.class);
        startActivity(mainIntent);
        finish();
    }
}
