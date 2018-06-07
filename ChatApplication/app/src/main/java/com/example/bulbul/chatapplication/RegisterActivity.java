package com.example.bulbul.chatapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    private EditText mDisplayName;
    private EditText mEmail;
    private EditText mPassword;
    private Button mCreteBtn;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        mDisplayName= (EditText) findViewById(R.id.reg_display_name);
        mEmail= (EditText) findViewById(R.id.reg_email);
        mPassword= (EditText) findViewById(R.id.reg_password);
        mCreteBtn= (Button) findViewById(R.id.reg_create_btn);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in

                    Toast.makeText(RegisterActivity.this,"onAuthStateChanged:signed_in:" + user.getUid(),Toast.LENGTH_LONG).show();
                } else {
                    // User is signed out

                    Toast.makeText(RegisterActivity.this,"onAuthStateChanged:signed_out",Toast.LENGTH_LONG).show();

                }
                // ...
            }
        };


        mCreteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String display_name=mDisplayName.getText().toString();
                String email=mEmail.getText().toString();
                String password=mPassword.getText().toString();
                register_user(display_name,email,password);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    private void register_user(String display_name, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                       if(task.isSuccessful()){
                           Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                           startActivity(intent);
                           finish();
                       }else {
                           Toast.makeText(RegisterActivity.this,"Error",Toast.LENGTH_LONG).show();

                       }

                        }

                        // ...
                });
    }
}
