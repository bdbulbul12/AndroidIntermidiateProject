package com.example.bulbul.signupsignin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText nameEditText,emailEditText,userNameEditText,passwordEditText;
    private Button signUpButton;
    UserDetails userDetails;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nameEditText= (EditText) findViewById(R.id.signUpUserNameEditTextId);
        emailEditText= (EditText) findViewById(R.id.signUpEmailEditTextId);
        userNameEditText= (EditText) findViewById(R.id.signUpUserNameEditTextId);
        passwordEditText= (EditText) findViewById(R.id.signUpPasswordId);
        signUpButton= (Button) findViewById(R.id.signUpButtonId);

        signUpButton.setOnClickListener(this);
        userDetails = new UserDetails();
        databaseHelper = new DatabaseHelper(this);
    }

    @Override
    public void onClick(View v) {
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String username= userNameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        userDetails.setName(name);
        userDetails.setEmail(email);
        userDetails.setUsername(username);
        userDetails.setPassword(password);
        long rowId = databaseHelper.insertData(userDetails);

        if(rowId>0){
            Toast.makeText(getApplicationContext(),"Row "+rowId+" is succedfully Inserted",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getApplicationContext(),"Row "+rowId+" is not  succedfully Inserted",Toast.LENGTH_LONG).show();
        }
    }
}
