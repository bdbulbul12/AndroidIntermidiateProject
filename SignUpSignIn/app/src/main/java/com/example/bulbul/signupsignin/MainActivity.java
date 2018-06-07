package com.example.bulbul.signupsignin;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    DatabaseHelper databaseHelper;
    private Button signInButton,signUpButton;
    private EditText userNameEditText;
    private EditText passwordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameEditText= (EditText) findViewById(R.id.signInUserNameEditTextId);
        passwordEditText= (EditText) findViewById(R.id.signInPasswordEditTextId);

        signInButton= (Button) findViewById(R.id.signInButtonId);
        signUpButton= (Button) findViewById(R.id.signUpButtonId);

        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        signInButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String username = userNameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if(v.getId()==R.id.signInButtonId){
            Boolean result = databaseHelper.findPassword(username,password);
            if(result==true){
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }else {
                Toast.makeText(getApplicationContext(),"UserName or Password Does Not Match",Toast.LENGTH_LONG).show();
            }

        }else if(v.getId()==R.id.signUpButtonId){
            Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
            startActivity(intent);
        }
    }
}
