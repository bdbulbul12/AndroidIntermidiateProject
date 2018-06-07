package com.example.bulbul.programmingknowledgesqlite;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editTextName,editTextSurename,editTextMarks,editTextId;
    Button btnAddData;
    Button viewAll,btnViewUpdate,btnDelete;
    TextView textView,data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        editTextName= (EditText) findViewById(R.id.editText);
        editTextSurename= (EditText) findViewById(R.id.editText2);
        editTextMarks= (EditText) findViewById(R.id.editText3);
        editTextId= (EditText) findViewById(R.id.editText4);
        btnAddData= (Button) findViewById(R.id.button_add);
        viewAll= (Button) findViewById(R.id.button_view);
        btnViewUpdate= (Button) findViewById(R.id.button_update);
        btnDelete= (Button) findViewById(R.id.button_delete);
        textView = (TextView) findViewById(R.id.viewId);


        addData();
        viewALl();
        upDateData();
        deleteData();
    }

    public void deleteData(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows = myDb.deleteData(editTextId.getText().toString());
                if(deletedRows>0){
                    Toast.makeText(MainActivity.this,"Data is Deleted",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,"Data is not deleted",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void upDateData(){
        btnViewUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = myDb.updateData(editTextId.getText().toString(),
                        editTextName.getText().toString(),
                        editTextSurename.getText().toString(),
                        editTextMarks.getText().toString());

                if(isUpdate==true){
                    Toast.makeText(MainActivity.this,"Data is Updated",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,"Data is not Inserted",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void addData(){
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean isInserted =  myDb.insertData(editTextName.getText().toString(),
                        editTextSurename.getText().toString(),
                        editTextMarks.getText().toString());
                if(isInserted==true){
                    Toast.makeText(MainActivity.this,"Data is Inserted",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,"Data is not Inserted",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void viewALl(){
        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res= myDb.getAllData();
                if(res.getCount()==0){
                    //show Message
                    showMessage("Error No","Nothing Found");
                    return;
                }


         StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append(" Id : "+res.getString(0)+"\n");
                    buffer.append(" Name : "+res.getString(1)+"\n");
                    buffer.append(" SureName : "+res.getString(2)+"\n");
                    buffer.append(" Marks : "+res.getString(3)+"\n \n");
                }
                // show all data
                showMessage ("Data",buffer.toString());
               // Log.i("result",buffer.toString());


            }
        });
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.setCancelable(true);
        builder.show();

    }
}
