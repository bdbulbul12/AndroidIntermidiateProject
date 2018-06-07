package com.example.bulbul.mysqlitedatabasedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText nameEditText,ageEditText,genderEditText,idEditText;
    private Button addButton,showData,updateButton,deleteButton;

    MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDatabaseHelper = new MyDatabaseHelper(this);
        SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();

        nameEditText= (EditText) findViewById(R.id.nameEditTextId);
        ageEditText= (EditText) findViewById(R.id.ageEditTextId);
        genderEditText= (EditText) findViewById(R.id.genderEditTextId);
        idEditText= (EditText) findViewById(R.id.idEditTextId);
        addButton= (Button) findViewById(R.id.addButtonId);
        showData= (Button) findViewById(R.id.showAllDataId);
        updateButton= (Button) findViewById(R.id.updateDataButton);
        deleteButton= (Button) findViewById(R.id.deleteDataButton);

        addButton.setOnClickListener(this);
        showData.setOnClickListener(this);
        updateButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String name = nameEditText.getText().toString();
        String age = ageEditText.getText().toString();
        String gender = genderEditText.getText().toString();
        String id = idEditText.getText().toString();
        if(v.getId()==R.id.addButtonId){
           long rowId =  myDatabaseHelper.insertData(name,age,gender);
            if(rowId==-1){
                Toast.makeText(getApplicationContext(),"Unsuccesfull",Toast.LENGTH_LONG).show();

            }else {
                Toast.makeText(getApplicationContext(),"Row "+rowId+"is successfully inserted",Toast.LENGTH_LONG).show();
            }

        }

        if(v.getId()==R.id.showAllDataId){
            Cursor cursor  = myDatabaseHelper.displayAllData();
            if(cursor.getCount()==0){
                displayData("Error","No data Found");
                return;
            }
            StringBuffer stringBuffer = new StringBuffer();
            while (cursor.moveToNext()){
                stringBuffer.append("ID :"+cursor.getString(0)+"\n");
                stringBuffer.append("Name :"+cursor.getString(1)+"\n");
                stringBuffer.append("Age : "+cursor.getString(2)+"\n");
                stringBuffer.append("Gender : "+cursor.getString(3)+"\n \n ");
            }
            displayData("ResultSet",stringBuffer.toString());
        }

        if(v.getId()==R.id.updateDataButton){
           boolean isUpdated = myDatabaseHelper.updateData(id,name,age,gender);
            if(isUpdated==true){
                Toast.makeText(getApplicationContext(),"Data is updated",Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(getApplicationContext(),"Data is not updated",Toast.LENGTH_LONG).show();
            }
        }

        if(v.getId()==R.id.deleteDataButton){
            int value=myDatabaseHelper.deleteData(id);
            if(value>0){
                Toast.makeText(getApplicationContext(),"Data is Deleted",Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(getApplicationContext(),"Data is not deleted",Toast.LENGTH_LONG).show();
            }
        }
    }

    public void displayData(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.show();

    }
}
