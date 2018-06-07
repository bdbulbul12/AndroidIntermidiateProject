package com.example.bulbul.eventmanagement;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomerInfo extends AppCompatActivity {

    private EditText customerName,customerAddress,customerPhone,customerEmail;
    private EditText cusDate,cusMonth,cusYear;
    private Button submitUserInfo;

    private int getDate;
    private int getMonth;
    private int getYear;

    DatabaseReference databaseUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_info);

        databaseUserInfo= FirebaseDatabase.getInstance().getReference("customer");

        /*Bundle bundle=getIntent().getExtras();
            String customerDate=null;
            String  customerMonth = null;
        if(bundle !=null){
             customerDate=bundle.getString("Date");
             customerMonth=bundle.getString("Month");
        } */

        int date  = getIntent().getIntExtra("Date", 0);
        int month  = getIntent().getIntExtra("Month", 0);
        int year  = getIntent().getIntExtra("year", 0);

        getDate = date;
        getMonth=month+1;
        getYear=year;

        customerName= (EditText) findViewById(R.id.customerName);
        customerAddress= (EditText) findViewById(R.id.customerAddress);
        customerPhone= (EditText) findViewById(R.id.customerPhone);
        customerEmail= (EditText) findViewById(R.id.customerEmail);
        submitUserInfo= (Button) findViewById(R.id.submitUserInfoBtn);

        cusDate= (EditText) findViewById(R.id.userDate);
        cusMonth= (EditText) findViewById(R.id.userMonth);
        cusYear = (EditText) findViewById(R.id.userYear);

        String uDate = String.valueOf(getDate);
        String uMonth =String.valueOf(getMonth);
        String uYear=String.valueOf(getYear);

        cusDate.setText(uDate+"  " + "(Date)");
        cusMonth.setText(uMonth+"  "+ "(Month)");
        cusYear.setText(uYear+"  "+ "(Year)");



        submitUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addCustomerInfo();
            }
        });
    }

    public void addCustomerInfo(){
        String name = customerName.getText().toString().trim();
        String address = customerAddress.getText().toString().trim();
        String phone = customerPhone.getText().toString().trim();
        String email = customerEmail.getText().toString().trim();
        String date = cusDate.getText().toString().trim();
        String month = cusMonth.getText().toString().trim();
        String year = cusYear.getText().toString().trim();


        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(address) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(email)){
            String id = databaseUserInfo.push().getKey();

            CustomerModel customerModel = new CustomerModel(id,date,month,year,name,address,phone,email);
            databaseUserInfo.child(id).setValue(customerModel);


            Toast.makeText(CustomerInfo.this,"Data Added Sucessfully",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(CustomerInfo.this,"Please Fill all the fields",Toast.LENGTH_LONG).show();
        }
    }
}
