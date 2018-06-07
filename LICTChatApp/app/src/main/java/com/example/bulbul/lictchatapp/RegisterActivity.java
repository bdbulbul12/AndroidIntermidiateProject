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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import org.w3c.dom.Text;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private EditText mName,mEmail,mPassword;
    private Button mButton;
    private FirebaseAuth auth;

    private Toolbar mToolbar;

    //Progress Dialog
    private ProgressDialog mProgress;

    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Toolbar Set
        mToolbar = (Toolbar) findViewById(R.id.register_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Create Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mProgress = new ProgressDialog(this);

        //Firebase Auth
        auth = FirebaseAuth.getInstance();

        mName= (EditText) findViewById(R.id.displayName);
        mEmail= (EditText) findViewById(R.id.reg_email);
        mPassword= (EditText) findViewById(R.id.reg_password);
        mButton= (Button) findViewById(R.id.reg_btn);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mName.getText().toString().trim();
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if(password.length()<6){
                    Toast.makeText(RegisterActivity.this,"Your password needds at least 6 charecter",Toast.LENGTH_LONG).show();
                }

                if(!TextUtils.isEmpty(name) || !TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)){

                    mProgress.setTitle("Registering User");
                    mProgress.setMessage("Please wait while we create your account");
                    mProgress.setCanceledOnTouchOutside(false);
                    mProgress.show();
                    register_user(name,email,password);
                }else {
                    Toast.makeText(RegisterActivity.this,"You Got Error",Toast.LENGTH_LONG).show();
                }


            }
        });
    }

    private void register_user(final String name, String email, String password) {

        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {



                        if(task.isSuccessful()){
                            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                            String uid = currentUser.getUid();
                            mDatabase=FirebaseDatabase.getInstance().getReference().child("Users").child(uid);

                            String device_token = FirebaseInstanceId.getInstance().getToken();

                            HashMap<String,String> userMap = new HashMap<String, String>();
                            userMap.put("name",name);
                            userMap.put("status","Hi I'm using LICT chat app.");
                            userMap.put("image","default");
                            userMap.put("thumb_image","default");
                            userMap.put("device_token",device_token);
                            mDatabase.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        mProgress.dismiss();
                                        Intent mainIntent = new Intent(RegisterActivity.this,MainActivity.class);
                                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(mainIntent);
                                        finish();
                                    }
                                }
                            });


                        }else {
                            mProgress.hide();
                            Toast.makeText(RegisterActivity.this, "Can not sign in please check and try again" + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        }


                    }
                });
    }


}
