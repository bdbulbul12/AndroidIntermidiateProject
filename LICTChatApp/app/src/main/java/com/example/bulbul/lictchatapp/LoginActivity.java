package com.example.bulbul.lictchatapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private EditText mLoginEmail,mLoginPassword;
    private Button mlogin_btn;
    private ProgressDialog mLoginProgress;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth=FirebaseAuth.getInstance();

        mToolbar= (Toolbar) findViewById(R.id.login_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Login");

        mLoginProgress = new ProgressDialog(this);

        mLoginEmail= (EditText) findViewById(R.id.login_email);
        mLoginPassword= (EditText) findViewById(R.id.login_pass);
        mlogin_btn= (Button) findViewById(R.id.login_btn);

        mlogin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mLoginEmail.getText().toString().trim();
                String password = mLoginPassword.getText().toString().trim();

                if(!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)){
                    mLoginProgress.setTitle("Logging In");
                    mLoginProgress.setMessage("Please wait while check your credentials.");
                    mLoginProgress.setCanceledOnTouchOutside(false);
                    mLoginProgress.show();
                    login_user(email,password);
                }
            }
        });
    }

    private void login_user(String email, String password) {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    mLoginProgress.dismiss();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }else {
                    mLoginProgress.hide();
                    Toast.makeText(LoginActivity.this, "Can not sign in please check and try again" + task.getException(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
