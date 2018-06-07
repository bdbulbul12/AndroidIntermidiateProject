package com.example.bulbul.onlinefileuploader;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    List<Upload> uploadList;
    DatabaseReference mDatabaseReference;

    private Toolbar mainToolbar;

    private FirebaseAuth mAuth;

    private TextView textView;

    private Spinner spinner;
    String[] fileType;
    private String getSubject;
    private Button showBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Title Show
        Bundle bundle = getIntent().getExtras();
        String department = null;
        if (bundle != null) {
            department = bundle.getString("sub");
            //showAdminFile(adminDepartment);
        }

        getSubject = department;
        fileType=getResources().getStringArray(R.array.File_TYPE);
        spinner=(Spinner)findViewById(R.id.userFileType);
        ArrayAdapter<String> adapterFile = new ArrayAdapter<String>(this,R.layout.spinnersampleview,R.id.textViewExampleId,fileType);
        spinner.setAdapter(adapterFile);

        showBtn= (Button) findViewById(R.id.showUploadFile);
        
        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFile();
            }
        });


        uploadList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView);

        mAuth = FirebaseAuth.getInstance();

        mainToolbar= (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        getSupportActionBar().setTitle("Download Uploaded File");
        


        //adding a clicklistener on listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {

                AlertDialog.Builder altDial = new AlertDialog.Builder(MainActivity.this);
                altDial.setMessage("Do you want to Download ?").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                //getting the upload
                                Upload upload = uploadList.get(i);

                                //Opening the upload file in browser using the upload url
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse(upload.getUrl()));
                                startActivity(intent);

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                    AlertDialog alert = altDial.create();
                    alert.setTitle("Download File Dialog");
                    alert.show();




            }
        });




    }

    private void showFile() {

        String spinnerItem = spinner.getSelectedItem().toString();

        //------------CSE Start----------------
        //-----------Cprogramming start-----------
        if(getSubject.equals("C_Programming") && spinnerItem.equals("ppt") ){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("C_Programming").child("ppt");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }else if(getSubject.equals("C_Programming")&& spinnerItem.equals("videos")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("C_Programming").child("videos");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("C_Programming")&& spinnerItem.equals("tutorials")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("C_Programming").child("tutorials");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("C_Programming")&& spinnerItem.equals("others")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("C_Programming").child("others");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        //-------------computer fundamental start---------------------------
        else if(getSubject.equals("Computer_Fundamental")&& spinnerItem.equals("ppt")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("Computer_Fundamental").child("ppt");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Computer_Fundamental")&& spinnerItem.equals("videos")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("Computer_Fundamental").child("videos");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Computer_Fundamental")&& spinnerItem.equals("tutorials")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("Computer_Fundamental").child("tutorials");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Computer_Fundamental")&& spinnerItem.equals("others")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("Computer_Fundamental").child("others");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        //---------------------------Database---Start-------------------------------------
        else if(getSubject.equals("Database")&& spinnerItem.equals("ppt")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("Database").child("ppt");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Database")&& spinnerItem.equals("videos")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("Database").child("videos");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Database")&& spinnerItem.equals("tutorials")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("Database").child("tutorials");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Database")&& spinnerItem.equals("others")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("Database").child("others");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        //----------------------Java---Start---------------------------------
        else if(getSubject.equals("Java")&& spinnerItem.equals("ppt")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("Java").child("ppt");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Java")&& spinnerItem.equals("videos")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("Java").child("videos");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Java")&& spinnerItem.equals("others")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("Java").child("others");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Java")&& spinnerItem.equals("tutorials")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("Java").child("tutorials");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        //---------------------Algorithm-----------Start----------------------------

        else if(getSubject.equals("Algorithm")&& spinnerItem.equals("ppt")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("Algorithm").child("ppt");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Algorithm")&& spinnerItem.equals("videos")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("Algorithm").child("videos");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Algorithm")&& spinnerItem.equals("tutorials")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("Algorithm").child("tutorials");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Algorithm")&& spinnerItem.equals("others")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("Algorithm").child("others");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

        //--------------------ICT---Dept Start-----------------------------------
        //-----------------C_Plus------strat--------------------------------

        else if(getSubject.equals("C_Plus")&& spinnerItem.equals("ppt")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("C_Plus").child("ppt");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("C_Plus")&& spinnerItem.equals("videos")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("C_Plus").child("videos");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("C_Plus")&& spinnerItem.equals("tutorials")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("C_Plus").child("tutorials");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("C_Plus")&& spinnerItem.equals("others")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("C_Plus").child("others");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        //------------Oracle----Start---------------------------------

        else if(getSubject.equals("Oracle")&& spinnerItem.equals("ppt")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("Oracle").child("ppt");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Oracle")&& spinnerItem.equals("videos")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("Oracle").child("videos");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Oracle")&& spinnerItem.equals("tutorials")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("Oracle").child("tutorials");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Oracle")&& spinnerItem.equals("others")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("Oracle").child("others");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        //--------------------Networking----start---------------

        else if(getSubject.equals("Networking")&& spinnerItem.equals("ppt")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("Networking").child("ppt");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Networking")&& spinnerItem.equals("videos")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("Networking").child("videos");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Networking")&& spinnerItem.equals("tutorials")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("Networking").child("tutorials");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Networking")&& spinnerItem.equals("others")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("Networking").child("others");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        //-------------Android------strat-------------------------

        else if(getSubject.equals("Android")&& spinnerItem.equals("ppt")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("Android").child("ppt");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Android")&& spinnerItem.equals("videos")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("Android").child("videos");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Android")&& spinnerItem.equals("tutorials")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("Android").child("tutorials");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Android")&& spinnerItem.equals("others")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("Android").child("others");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

        //---------------------Distributed--------------------------

        else if(getSubject.equals("Distributed")&& spinnerItem.equals("ppt")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("Distributed").child("ppt");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Distributed")&& spinnerItem.equals("videos")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("Distributed").child("videos");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Distributed")&& spinnerItem.equals("tutorials")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("Distributed").child("tutorials");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Distributed")&& spinnerItem.equals("others")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("Distributed").child("others");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

        //---------------EEE---------------start--------------------------
        //------------------Physics--------------------------------

        else if(getSubject.equals("Physics")&& spinnerItem.equals("ppt")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("Physics").child("ppt");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Physics")&& spinnerItem.equals("videos")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("Physics").child("videos");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Physics")&& spinnerItem.equals("tutorials")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("Physics").child("tutorials");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Physics")&& spinnerItem.equals("others")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("Physics").child("others");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

        //------------------------Electrical--------------------------
        else if(getSubject.equals("Electrical")&& spinnerItem.equals("ppt")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("Electrical").child("ppt");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Electrical")&& spinnerItem.equals("videos")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("Electrical").child("videos");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Electrical")&& spinnerItem.equals("tutorials")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("Electrical").child("tutorials");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Electrical")&& spinnerItem.equals("others")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("Electrical").child("others");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        //-------------------------------Electronics----------------------------

        else if(getSubject.equals("Electronics")&& spinnerItem.equals("ppt")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("Electronics").child("ppt");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Electronics")&& spinnerItem.equals("videos")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("Electronics").child("videos");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Electronics")&& spinnerItem.equals("tutorials")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("Electronics").child("tutorials");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Electronics")&& spinnerItem.equals("others")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("Electronics").child("others");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

        //-----------------ECommerce----------------------------------------


        else if(getSubject.equals("ECommerce")&& spinnerItem.equals("ppt")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("ECommerce").child("ppt");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("ECommerce")&& spinnerItem.equals("videos")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("ECommerce").child("videos");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("ECommerce")&& spinnerItem.equals("tutorials")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("ECommerce").child("tutorials");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("ECommerce")&& spinnerItem.equals("others")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("ECommerce").child("others");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        //--------------------DigitalLogic---------------------------

        else if(getSubject.equals("DigitalLogic")&& spinnerItem.equals("ppt")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("DigitalLogic").child("ppt");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("DigitalLogic")&& spinnerItem.equals("videos")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("DigitalLogic").child("videos");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("DigitalLogic")&& spinnerItem.equals("tutorials")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("DigitalLogic").child("tutorials");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("DigitalLogic")&& spinnerItem.equals("others")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("DigitalLogic").child("others");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

        //----------------ITE ----start------------------------

        //---------------------Operating_System----------------------

        else if(getSubject.equals("Operating_System")&& spinnerItem.equals("ppt")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Operating_System").child("ppt");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        } else if(getSubject.equals("Operating_System")&& spinnerItem.equals("videos")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Operating_System").child("videos");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Operating_System")&& spinnerItem.equals("tutorials")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Operating_System").child("tutorials");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Operating_System")&& spinnerItem.equals("others")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Operating_System").child("others");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

        //----------------Web_Engineering----------------------------

        else if(getSubject.equals("Web_Engineering")&& spinnerItem.equals("ppt")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Web_Engineering").child("ppt");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Web_Engineering")&& spinnerItem.equals("videos")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Web_Engineering").child("videos");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Web_Engineering")&& spinnerItem.equals("tutorials")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Web_Engineering").child("tutorials");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Web_Engineering")&& spinnerItem.equals("others")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Web_Engineering").child("others");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

        //-------------------Graphics----------------------

        else if(getSubject.equals("Graphics")&& spinnerItem.equals("ppt")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Graphics").child("ppt");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Graphics")&& spinnerItem.equals("videos")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Graphics").child("videos");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Graphics")&& spinnerItem.equals("tutorials")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Graphics").child("tutorials");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Graphics")&& spinnerItem.equals("others")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Graphics").child("others");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

        //-------------------------IT---------------------

        else if(getSubject.equals("IT")&& spinnerItem.equals("ppt")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("IT").child("others");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("IT")&& spinnerItem.equals("videos")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("IT").child("videos");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("IT")&& spinnerItem.equals("tutorials")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("IT").child("tutorials");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("IT")&& spinnerItem.equals("others")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("IT").child("others");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

        //-----------------------------Geometry----------------------------

        else if(getSubject.equals("Geometry")&& spinnerItem.equals("ppt")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Geometry").child("ppt");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Geometry")&& spinnerItem.equals("videos")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Geometry").child("videos");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Geometry")&& spinnerItem.equals("tutorials")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Geometry").child("tutorials");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Geometry")&& spinnerItem.equals("others")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Geometry").child("others");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

        //--------------------------BBA---start-----------------------------------

        //------------------------------BCommunication---------------------------

        else if(getSubject.equals("BCommunication")&& spinnerItem.equals("ppt")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("BCommunication").child("ppt");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("BCommunication")&& spinnerItem.equals("videos")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("BCommunication").child("videos");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("BCommunication")&& spinnerItem.equals("tutorials")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("BCommunication").child("tutorials");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("BCommunication")&& spinnerItem.equals("others")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("BCommunication").child("others");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

        //-------------------------------------BMath------------------------------

        else if(getSubject.equals("BMath")&& spinnerItem.equals("ppt")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("BMath").child("ppt");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("BMath")&& spinnerItem.equals("videos")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("BMath").child("videos");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("BMath")&& spinnerItem.equals("tutorials")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("BMath").child("tutorials");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("BMath")&& spinnerItem.equals("others")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("BMath").child("others");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

        //-----------------------DataStructure-------------------------------

        else if(getSubject.equals("DataStructure")&& spinnerItem.equals("ppt")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("DataStructure").child("ppt");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("DataStructure")&& spinnerItem.equals("videos")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("DataStructure").child("videos");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("DataStructure")&& spinnerItem.equals("tutorials")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("DataStructure").child("tutorials");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("DataStructure")&& spinnerItem.equals("others")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("DataStructure").child("others");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        //-------------------------Accounting------------------------------

        else if(getSubject.equals("Accounting")&& spinnerItem.equals("ppt")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("Accounting").child("ppt");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Accounting")&& spinnerItem.equals("videos")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("Accounting").child("videos");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Accounting")&& spinnerItem.equals("tutorials")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("Accounting").child("tutorials");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Accounting")&& spinnerItem.equals("others")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("Accounting").child("others");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

        //-------------------------Finance---------------------------------------------

        else if(getSubject.equals("Finance")&& spinnerItem.equals("ppt")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("Finance").child("ppt");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Finance")&& spinnerItem.equals("videos")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("Finance").child("videos");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Finance")&& spinnerItem.equals("tutorials")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("Finance").child("tutorials");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Finance")&& spinnerItem.equals("others")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("Finance").child("others");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

        //------------------ECE---satrt---------------------
        //--------------------Telecommunication-----------------------

        else if(getSubject.equals("Telecommunication")&& spinnerItem.equals("ppt")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Telecommunication").child("ppt");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Telecommunication")&& spinnerItem.equals("videos")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Telecommunication").child("videos");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Telecommunication")&& spinnerItem.equals("tutorials")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Telecommunication").child("tutorials");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Telecommunication")&& spinnerItem.equals("others")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Telecommunication").child("others");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

        //-----------------Engineering_Math----------------------

        else if(getSubject.equals("Engineering_Math")&& spinnerItem.equals("ppt")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Engineering_Math").child("ppt");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Engineering_Math")&& spinnerItem.equals("videos")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Engineering_Math").child("videos");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Engineering_Math")&& spinnerItem.equals("tutorials")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Engineering_Math").child("tutorials");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Engineering_Math")&& spinnerItem.equals("others")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Engineering_Math").child("others");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

        //----------------------------Compiler_Design---------------------------


        else if(getSubject.equals("Compiler_Design")&& spinnerItem.equals("ppt")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Compiler_Design").child("ppt");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Compiler_Design")&& spinnerItem.equals("videos")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Compiler_Design").child("videos");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Compiler_Design")&& spinnerItem.equals("tutorials")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Compiler_Design").child("tutorials");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Compiler_Design")&& spinnerItem.equals("others")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Compiler_Design").child("others");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

        //--------------------Applied_Math---------------------------

        else if(getSubject.equals("Applied_Math")&& spinnerItem.equals("ppt")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Applied_Math").child("ppt");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Applied_Math")&& spinnerItem.equals("videos")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Applied_Math").child("videos");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Applied_Math")&& spinnerItem.equals("tutorials")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Applied_Math").child("tutorials");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Applied_Math")&& spinnerItem.equals("others")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Applied_Math").child("others");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        //---------------------Applied_Physics-------------------------

        else if(getSubject.equals("Applied_Physics")&& spinnerItem.equals("ppt")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Applied_Physics").child("ppt");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        } else if(getSubject.equals("Applied_Physics")&& spinnerItem.equals("videos")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Applied_Physics").child("videos");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        } else if(getSubject.equals("Applied_Physics")&& spinnerItem.equals("tutorials")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Applied_Physics").child("tutorials");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Applied_Physics")&& spinnerItem.equals("others")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Applied_Physics").child("others");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

        //-----------------------------ECONOMICS-------------------------

        //---------------------------------Economics----------------------

        else if(getSubject.equals("Economics")&& spinnerItem.equals("ppt")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("Economics").child("ppt");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Economics")&& spinnerItem.equals("videos")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("Economics").child("videos");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Economics")&& spinnerItem.equals("tutorials")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("Economics").child("tutorials");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Economics")&& spinnerItem.equals("others")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("Economics").child("others");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        //------------------------History--------------------------------

        else if(getSubject.equals("History")&& spinnerItem.equals("ppt")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("History").child("ppt");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("History")&& spinnerItem.equals("videos")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("History").child("videos");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("History")&& spinnerItem.equals("tutorials")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("History").child("tutorials");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("History")&& spinnerItem.equals("others")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("History").child("others");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        //----------------------------Political_Science--------------------------------

        else if(getSubject.equals("Political_Science")&& spinnerItem.equals("ppt")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("Political_Science").child("ppt");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Political_Science")&& spinnerItem.equals("videos")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("Political_Science").child("videos");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Political_Science")&& spinnerItem.equals("tutorials")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("Political_Science").child("tutorials");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Political_Science")&& spinnerItem.equals("others")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("Political_Science").child("others");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

        //----------------------------Basic_Economics---------------------------------------

        else if(getSubject.equals("Basic_Economics")&& spinnerItem.equals("ppt")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("Basic_Economics").child("ppt");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Basic_Economics")&& spinnerItem.equals("videos")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("Basic_Economics").child("videos");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Basic_Economics")&& spinnerItem.equals("tutorials")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("Basic_Economics").child("tutorials");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Basic_Economics")&& spinnerItem.equals("others")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("Basic_Economics").child("others");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

        //-----------------------Principal_Of_Economics-----------------------------

        else if(getSubject.equals("Principal_Of_Economics")&& spinnerItem.equals("ppt")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("Principal_Of_Economics").child("ppt");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Principal_Of_Economics")&& spinnerItem.equals("videos")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("Principal_Of_Economics").child("videos");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Principal_Of_Economics")&& spinnerItem.equals("tutorials")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("Principal_Of_Economics").child("tutorials");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(getSubject.equals("Principal_Of_Economics")&& spinnerItem.equals("others")){
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("Principal_Of_Economics").child("others");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
    }

   /* private void showFile(String department) {
        //-------------------CSE SUBJECT START----------------------------------------------

        if(department.equals("C_Programming")){

            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("C_Programming");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



        }else if(department.equals("Computer_Fundamental")){

            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("Computer_Fundamental");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



        }else if(department.equals("Database")){

            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("Database");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



        }else if(department.equals("Java")){

            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("Java");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



        }else if(department.equals("Algorithm")){

            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("Algorithm");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



        }
        //-------------------ICT SUBJECT START----------------------------------------------
        else if(department.equals("C_Plus")){

            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("C_Plus");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



        }else if(department.equals("Oracle")){

            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("Oracle");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



        }else if(department.equals("Networking")){

            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("Networking");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



        }else if(department.equals("Android")){

            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("Android");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }else if(department.equals("Algorithm")){

            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("Algorithm");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }
        //----------------Start EEE Department-----------------------------------------

        else if(department.equals("Physics")){

            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("Physics");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        } else if(department.equals("Electrical")){

            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("Electrical");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        } else if(department.equals("Electronics")){

            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("Electronics");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }else if(department.equals("ECommerce")){

            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("ECommerce");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }else if(department.equals("DigitalLogic")){

            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("DigitalLogic");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }

        //-------------------------------Start ITE Department -----------------------------------

        else if(department.equals("Operating_System")){

            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Operating_System");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }else if(department.equals("Web_Engineering")){

            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Web_Engineering");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }else if(department.equals("Graphics")){

            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Graphics");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }else if(department.equals("IT")){

            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("IT");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }else if(department.equals("Geometry")){

            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Geometry");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }

        //--------------------------BBA Department Subject List-----------------------------------------------

        else if(department.equals("BCommunication")){

            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("BCommunication");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }else if(department.equals("BMath")){

            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("BMath");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }else if(department.equals("DataStructure")){

            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("DataStructure");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }else if(department.equals("Accounting")){

            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("Accounting");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }else if(department.equals("Finance")){

            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("Finance");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }

        //----------------------Start ECE Department Subject ------------------------------

        else if(department.equals("Telecommunication")){

            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Telecommunication");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }else if(department.equals("Engineering_Math")){

            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Engineering_Math");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }else if(department.equals("Compiler_Design")){

            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Compiler_Design");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }else if(department.equals("Applied_Math")){

            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Applied_Math");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }else if(department.equals("Applied_Physics")){

            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Applied_Physics");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }

        //----------------------Start Economics Department-------------------------------------------------

        else if(department.equals("Economics")){

            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("Economics");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        } else if(department.equals("History")){

            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("History");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }else if(department.equals("Political_Science")){

            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("Political_Science");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }else if(department.equals("Basic_Economics")){

            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("Basic_Economics");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }else if(department.equals("Principal_Of_Economics")){

            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("Principal_Of_Economics");


            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }


                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                    listView.invalidateViews();
                    listView.setBackgroundColor(Color.GRAY);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }


    }  */


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser= FirebaseAuth.getInstance().getCurrentUser();

        if(currentUser==null){
            sendToLogin();
        }
    }

    private void sendToLogin() {
        Intent loginIntent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_logout_btn:
                logOut();
                return true;

            default:
                return false;

        }

    }

    private void logOut() {
        mAuth.signOut();
        sendToLogin();
    }
}