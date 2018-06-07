package com.example.bulbul.onlinefileuploader;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;


public class AdminUploadDept extends AppCompatActivity {
    //the listview
    ListView listView;
    List<Upload> uploadList;
    private Toolbar mainToolbar;
    private Spinner spinner;
    String[] fileType;
    private String getSubject;
    private Button btn;

    //this is the pic pdf code used in file chooser
    final static int PICK_PDF_CODE = 2342;

    //these are the views
    TextView textViewStatus;
    EditText editTextFilename;
    ProgressBar progressBar;

    //the firebase objects for storage and database
    StorageReference mStorageReference;
    DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_upload_dept);


        //Title Show
        Bundle bundle = getIntent().getExtras();
        String adminDepartment = null;
        if (bundle != null) {
            adminDepartment = bundle.getString("adminDept");
            //showAdminFile(adminDepartment);
        }

        getSubject = adminDepartment;
        fileType=getResources().getStringArray(R.array.File_TYPE);
        spinner=(Spinner)findViewById(R.id.fType);
        ArrayAdapter<String> adapterFile = new ArrayAdapter<String>(this,R.layout.spinnersampleview,R.id.textViewExampleId,fileType);
        spinner.setAdapter(adapterFile);

        mainToolbar = (Toolbar) findViewById(R.id.main_toolbar2);
        setSupportActionBar(mainToolbar);

        getSupportActionBar().setTitle("Admin Uploaded File");


        uploadList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.adminView);

        //getting firebase objects
        mStorageReference = FirebaseStorage.getInstance().getReference();
        //   mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS);

        //getting the views
        textViewStatus = (TextView) findViewById(R.id.textViewStatus);
        editTextFilename = (EditText) findViewById(R.id.editTextFileName);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);


        //attaching listeners to views
        //findViewById(R.id.cseButtonUploadFile).setOnClickListener(this);
        // findViewById(R.id.textViewUploads).setOnClickListener(this);

        btn= (Button) findViewById(R.id.cseButtonUploadFile);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFile();
                upFile();
            }
        });



        //-----------------ListView------------------------------

        //adding a clicklistener on listview
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Upload upload = uploadList.get(position);
                showUpdateDialog(upload.getId(), upload.getName());
                return false;
            }
        });



    }

    private void upFile() {
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

  /*  private void showAdminFile(String adminDepartment) {

        //-------------------CSE SUBJECT START----------------------------------------------


        String spinnerItem = spinner.getSelectedItem().toString();

        if(adminDepartment.equals("C_Programming") && spinnerItem.equals("ppt")){


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



        }else if(spinnerItem.equals("videos") && adminDepartment.equals("C_Programming")){


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



        }else if(adminDepartment.equals("C_Programming") && spinnerItem.equals("tutorials")){


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



        }

        else if(adminDepartment.equals("Computer_Fundamental") && spinnerItem.equals("ppt")){

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



        }else if(adminDepartment.equals("Database")){

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



        }else if(adminDepartment.equals("Java")){

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



        }else if(adminDepartment.equals("Algorithm")){

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
        else if(adminDepartment.equals("C_Plus")){

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



        }else if(adminDepartment.equals("Oracle")){

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



        }else if(adminDepartment.equals("Networking")){

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



        }else if(adminDepartment.equals("Android")){

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


        }else if(adminDepartment.equals("Algorithm")){

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

        else if(adminDepartment.equals("Physics")){

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


        } else if(adminDepartment.equals("Electrical")){

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


        } else if(adminDepartment.equals("Electronics")){

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


        }else if(adminDepartment.equals("ECommerce")){

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


        }else if(adminDepartment.equals("DigitalLogic")){

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

        else if(adminDepartment.equals("Operating_System")){

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


        }else if(adminDepartment.equals("Web_Engineering")){

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


        }else if(adminDepartment.equals("Graphics")){

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


        }else if(adminDepartment.equals("IT")){

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


        }else if(adminDepartment.equals("Geometry")){

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

        else if(adminDepartment.equals("BCommunication")){

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


        }else if(adminDepartment.equals("BMath")){

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


        }else if(adminDepartment.equals("DataStructure")){

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


        }else if(adminDepartment.equals("Accounting")){

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


        }else if(adminDepartment.equals("Finance")){

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

        else if(adminDepartment.equals("Telecommunication")){

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


        }else if(adminDepartment.equals("Engineering_Math")){

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


        }else if(adminDepartment.equals("Compiler_Design")){

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


        }else if(adminDepartment.equals("Applied_Math")){

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


        }else if(adminDepartment.equals("Applied_Physics")){

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

        else if(adminDepartment.equals("Economics")){

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


        } else if(adminDepartment.equals("History")){

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


        }else if(adminDepartment.equals("Political_Science")){

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


        }else if(adminDepartment.equals("Basic_Economics")){

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


        }else if(adminDepartment.equals("Principal_Of_Economics")){

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
    //----------------------updating info--------------------

    private void showUpdateDialog(final String fileId,final String name) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog,null);
        dialogBuilder.setView(dialogView);




        final Button buttonDelete = (Button) dialogView.findViewById(R.id.deleteButton);

        dialogBuilder.setTitle("Updating File Title"+name);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();



        //--------- Deleting item...........

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteArtist(fileId);

            }
        });



    }

    private void deleteArtist(String fileId) {

        //------------------------------------------Filed of CSE----------------------------

        DatabaseReference fileUpload = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("C_Programming").child("ppt").child(fileId);
        DatabaseReference fileUpload2 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("C_Programming").child("video").child(fileId);
        DatabaseReference fileUpload3= FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("C_Programming").child("tutorials").child(fileId);
        DatabaseReference fileUpload4= FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("C_Programming").child("others").child(fileId);

        DatabaseReference fileUpload5= FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("Computer_Fundamental").child("ppt").child(fileId);
        DatabaseReference fileUpload6= FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("Computer_Fundamental").child("video").child(fileId);
        DatabaseReference fileUpload7= FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("Computer_Fundamental").child("tutorials").child(fileId);
        DatabaseReference fileUpload8= FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("Computer_Fundamental").child("others").child(fileId);

        DatabaseReference fileUpload9= FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("Database").child("ppt").child(fileId);
        DatabaseReference fileUpload10= FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("Database").child("video").child(fileId);
        DatabaseReference fileUpload11= FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("Database").child("tutorials").child(fileId);
        DatabaseReference fileUpload12= FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("Database").child("others").child(fileId);

        DatabaseReference fileUpload13= FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("Java").child("ppt").child(fileId);
        DatabaseReference fileUpload14= FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("Java").child("video").child(fileId);
        DatabaseReference fileUpload15= FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("Java").child("tutorials").child(fileId);
        DatabaseReference fileUpload16= FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("Java").child("others").child(fileId);

        DatabaseReference fileUpload17= FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("Algorithm").child("ppt").child(fileId);
        DatabaseReference fileUpload18= FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("Algorithm").child("video").child(fileId);
        DatabaseReference fileUpload19= FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("Algorithm").child("tutorials").child(fileId);
        DatabaseReference fileUpload20= FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("CSE").child("Algorithm").child("others").child(fileId);


        //------------------------------------------Filed of ICT----------------------------

        DatabaseReference fileUpload21 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("C_Plus").child("ppt").child(fileId);
        DatabaseReference fileUpload22= FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("C_Plus").child("videos").child(fileId);
        DatabaseReference fileUpload23= FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("C_Plus").child("tutorials").child(fileId);
        DatabaseReference fileUpload24= FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("C_Plus").child("others").child(fileId);

        DatabaseReference fileUpload25 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("Oracle").child("ppt").child(fileId);
        DatabaseReference fileUpload26 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("Oracle").child("videos").child(fileId);
        DatabaseReference fileUpload27 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("Oracle").child("tutorials").child(fileId);
        DatabaseReference fileUpload28 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("Oracle").child("others").child(fileId);

        DatabaseReference fileUpload29 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("Networking").child("ppt").child(fileId);
        DatabaseReference fileUpload30 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("Networking").child("videos").child(fileId);
        DatabaseReference fileUpload31 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("Networking").child("tutorials").child(fileId);
        DatabaseReference fileUpload32 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("Networking").child("others").child(fileId);

        DatabaseReference fileUpload33 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("Android").child("ppt").child(fileId);
        DatabaseReference fileUpload34 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("Android").child("videos").child(fileId);
        DatabaseReference fileUpload35 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("Android").child("tutorials").child(fileId);
        DatabaseReference fileUpload36 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("Android").child("others").child(fileId);

        DatabaseReference fileUpload37 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("Distributed").child("ppt").child(fileId);
        DatabaseReference fileUpload38 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("Distributed").child("videos").child(fileId);
        DatabaseReference fileUpload39 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("Distributed").child("tutorials").child(fileId);
        DatabaseReference fileUpload40 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ICT").child("Distributed").child("others").child(fileId);

        //------------------------------------------Filed of EEE----------------------------

        DatabaseReference fileUpload41 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("Physics").child("ppt").child(fileId);
        DatabaseReference fileUpload42 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("Physics").child("videos").child(fileId);
        DatabaseReference fileUpload43 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("Physics").child("tutorials").child(fileId);
        DatabaseReference fileUpload44 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("Physics").child("others").child(fileId);

        DatabaseReference fileUpload45 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("Electrical").child("ppt").child(fileId);
        DatabaseReference fileUpload46 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("Electrical").child("videos").child(fileId);
        DatabaseReference fileUpload47 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("Electrical").child("tutorials").child(fileId);
        DatabaseReference fileUpload48 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("Electrical").child("others").child(fileId);

        DatabaseReference fileUpload49 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("Electronics").child("ppt").child(fileId);
        DatabaseReference fileUpload50 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("Electronics").child("videos").child(fileId);
        DatabaseReference fileUpload51 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("Electronics").child("tutorials").child(fileId);
        DatabaseReference fileUpload52 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("Electronics").child("others").child(fileId);

        DatabaseReference fileUpload53= FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("Electronics").child("ppt").child(fileId);
        DatabaseReference fileUpload54 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("Electronics").child("videos").child(fileId);
        DatabaseReference fileUpload55 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("Electronics").child("tutorials").child(fileId);
        DatabaseReference fileUpload56 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("Electronics").child("others").child(fileId);

        DatabaseReference fileUpload57 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("Electronics").child("ppt").child(fileId);
        DatabaseReference fileUpload58 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("Electronics").child("videos").child(fileId);
        DatabaseReference fileUpload59 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("Electronics").child("tutorials").child(fileId);
        DatabaseReference fileUpload60 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("EEE").child("Electronics").child("others").child(fileId);

        //------------------------------------------Filed of ITE----------------------------

        DatabaseReference fileUpload61 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Operating_System").child("ppt").child(fileId);
        DatabaseReference fileUpload62 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Operating_System").child("videos").child(fileId);
        DatabaseReference fileUpload63 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Operating_System").child("tutorials").child(fileId);
        DatabaseReference fileUpload64 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Operating_System").child("others").child(fileId);

        DatabaseReference fileUpload65 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Web_Engineering").child("ppt").child(fileId);
        DatabaseReference fileUpload66 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Web_Engineering").child("videos").child(fileId);
        DatabaseReference fileUpload67 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Web_Engineering").child("tutorials").child(fileId);
        DatabaseReference fileUpload68 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Web_Engineering").child("others").child(fileId);

        DatabaseReference fileUpload69 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Graphics").child("ppt").child(fileId);
        DatabaseReference fileUpload70 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Graphics").child("videos").child(fileId);
        DatabaseReference fileUpload71 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Graphics").child("tutorials").child(fileId);
        DatabaseReference fileUpload72 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Graphics").child("others").child(fileId);

        DatabaseReference fileUpload73 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Graphics").child("ppt").child(fileId);
        DatabaseReference fileUpload74 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Graphics").child("videos").child(fileId);
        DatabaseReference fileUpload75 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Graphics").child("tutorials").child(fileId);
        DatabaseReference fileUpload76 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Graphics").child("others").child(fileId);

        DatabaseReference fileUpload77 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Graphics").child("ppt").child(fileId);
        DatabaseReference fileUpload78 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Graphics").child("videos").child(fileId);
        DatabaseReference fileUpload79 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Graphics").child("tutorials").child(fileId);
        DatabaseReference fileUpload80 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ITE").child("Graphics").child("others").child(fileId);

        //------------------------------------------Filed of BBA----------------------------

        DatabaseReference fileUpload81 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("BCommunication").child("ppt").child(fileId);
        DatabaseReference fileUpload82 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("BCommunication").child("videos").child(fileId);
        DatabaseReference fileUpload83 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("BCommunication").child("tutorials").child(fileId);
        DatabaseReference fileUpload84 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("BCommunication").child("others").child(fileId);

        DatabaseReference fileUpload85= FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("BMath").child("ppt").child(fileId);
        DatabaseReference fileUpload86 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("BMath").child("videos").child(fileId);
        DatabaseReference fileUpload87 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("BMath").child("tutorials").child(fileId);
        DatabaseReference fileUpload88 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("BMath").child("others").child(fileId);

        DatabaseReference fileUpload89 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("DataStructure").child("ppt").child(fileId);
        DatabaseReference fileUpload90 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("DataStructure").child("videos").child(fileId);
        DatabaseReference fileUpload91 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("DataStructure").child("tutorials").child(fileId);
        DatabaseReference fileUpload92 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("DataStructure").child("others").child(fileId);

        DatabaseReference fileUpload93 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("Accounting").child("ppt").child(fileId);
        DatabaseReference fileUpload94 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("Accounting").child("videos").child(fileId);
        DatabaseReference fileUpload95 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("Accounting").child("tutorials").child(fileId);
        DatabaseReference fileUpload96 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("Accounting").child("others").child(fileId);

        DatabaseReference fileUpload97 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("Accounting").child("ppt").child(fileId);
        DatabaseReference fileUpload98 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("Accounting").child("videos").child(fileId);
        DatabaseReference fileUpload99 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("Accounting").child("tutorials").child(fileId);
        DatabaseReference fileUpload100 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("BBA").child("Accounting").child("others").child(fileId);

        //------------------------------------------Filed of ECE----------------------------


        DatabaseReference fileUpload101 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Telecommunication").child("ppt").child(fileId);
        DatabaseReference fileUpload102= FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Telecommunication").child("videos").child(fileId);
        DatabaseReference fileUpload103= FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Telecommunication").child("tutorials").child(fileId);
        DatabaseReference fileUpload104 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Telecommunication").child("others").child(fileId);

        DatabaseReference fileUpload105 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Engineering_Math").child("ppt").child(fileId);
        DatabaseReference fileUpload106= FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Engineering_Math").child("videos").child(fileId);
        DatabaseReference fileUpload107= FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Engineering_Math").child("tutorials").child(fileId);
        DatabaseReference fileUpload108 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Engineering_Math").child("others").child(fileId);

        DatabaseReference fileUpload109 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Compiler_Design").child("ppt").child(fileId);
        DatabaseReference fileUpload110= FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Compiler_Design").child("videos").child(fileId);
        DatabaseReference fileUpload111= FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Compiler_Design").child("tutorials").child(fileId);
        DatabaseReference fileUpload112= FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Compiler_Design").child("others").child(fileId);

        DatabaseReference fileUpload113= FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Compiler_Design").child("ppt").child(fileId);
        DatabaseReference fileUpload114 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Compiler_Design").child("videos").child(fileId);
        DatabaseReference fileUpload115 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Compiler_Design").child("tutorials").child(fileId);
        DatabaseReference fileUpload116 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Compiler_Design").child("others").child(fileId);

        DatabaseReference fileUpload117 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Compiler_Design").child("ppt").child(fileId);
        DatabaseReference fileUpload118= FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Compiler_Design").child("videos").child(fileId);
        DatabaseReference fileUpload119= FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Compiler_Design").child("tutorials").child(fileId);
        DatabaseReference fileUpload120= FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECE").child("Compiler_Design").child("others").child(fileId);

        //------------------------------------------Filed of Economics----------------------------

        DatabaseReference fileUpload121 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("Economics").child("ppt").child(fileId);
        DatabaseReference fileUpload122 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("Economics").child("videos").child(fileId);
        DatabaseReference fileUpload123= FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("Economics").child("tutorials").child(fileId);
        DatabaseReference fileUpload124= FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("Economics").child("others").child(fileId);

        DatabaseReference fileUpload125 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("History").child("ppt").child(fileId);
        DatabaseReference fileUpload126 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("History").child("videos").child(fileId);
        DatabaseReference fileUpload127 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("History").child("tutorials").child(fileId);
        DatabaseReference fileUpload128= FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("History").child("others").child(fileId);

        DatabaseReference fileUpload129 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("Political_Science").child("ppt").child(fileId);
        DatabaseReference fileUpload130 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("Political_Science").child("videos").child(fileId);
        DatabaseReference fileUpload131 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("Political_Science").child("tutorials").child(fileId);
        DatabaseReference fileUpload132 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("Political_Science").child("others").child(fileId);

        DatabaseReference fileUpload133 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("Political_Science").child("ppt").child(fileId);
        DatabaseReference fileUpload134 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("Political_Science").child("videos").child(fileId);
        DatabaseReference fileUpload135 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("Political_Science").child("tutorials").child(fileId);
        DatabaseReference fileUpload136 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("Political_Science").child("others").child(fileId);

        DatabaseReference fileUpload137 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("Political_Science").child("ppt").child(fileId);
        DatabaseReference fileUpload138 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("Political_Science").child("videos").child(fileId);
        DatabaseReference fileUpload139 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("Political_Science").child("tutorials").child(fileId);
        DatabaseReference fileUpload140 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS).child("ECONOMICS").child("Political_Science").child("others").child(fileId);

        fileUpload.removeValue();
        fileUpload2.removeValue();
        fileUpload3.removeValue();
        fileUpload4.removeValue();
        fileUpload5.removeValue();
        fileUpload6.removeValue();
        fileUpload7.removeValue();
        fileUpload8.removeValue();
        fileUpload9.removeValue();
        fileUpload11.removeValue();
        fileUpload12.removeValue();
        fileUpload13.removeValue();
        fileUpload14.removeValue();
        fileUpload15.removeValue();
        fileUpload16.removeValue();
        fileUpload17.removeValue();
        fileUpload18.removeValue();
        fileUpload19.removeValue();
        fileUpload20.removeValue();
        fileUpload21.removeValue();
        fileUpload22.removeValue();
        fileUpload23.removeValue();
        fileUpload24.removeValue();
        fileUpload25.removeValue();
        fileUpload26.removeValue();
        fileUpload27.removeValue();
        fileUpload28.removeValue();
        fileUpload29.removeValue();
        fileUpload30.removeValue();
        fileUpload31.removeValue();
        fileUpload32.removeValue();
        fileUpload33.removeValue();
        fileUpload34.removeValue();
        fileUpload35.removeValue();
        fileUpload36.removeValue();
        fileUpload37.removeValue();
        fileUpload38.removeValue();
        fileUpload39.removeValue();
        fileUpload40.removeValue();
        fileUpload41.removeValue();
        fileUpload42.removeValue();
        fileUpload43.removeValue();
        fileUpload44.removeValue();
        fileUpload45.removeValue();
        fileUpload46.removeValue();
        fileUpload47.removeValue();
        fileUpload48.removeValue();
        fileUpload49.removeValue();
        fileUpload50.removeValue();
        fileUpload51.removeValue();
        fileUpload52.removeValue();
        fileUpload53.removeValue();
        fileUpload54.removeValue();
        fileUpload55.removeValue();
        fileUpload56.removeValue();
        fileUpload57.removeValue();
        fileUpload58.removeValue();
        fileUpload59.removeValue();
        fileUpload60.removeValue();
        fileUpload61.removeValue();
        fileUpload62.removeValue();
        fileUpload63.removeValue();
        fileUpload64.removeValue();
        fileUpload65.removeValue();
        fileUpload66.removeValue();
        fileUpload67.removeValue();
        fileUpload68.removeValue();
        fileUpload69.removeValue();
        fileUpload70.removeValue();
        fileUpload71.removeValue();
        fileUpload72.removeValue();
        fileUpload73.removeValue();
        fileUpload74.removeValue();
        fileUpload75.removeValue();
        fileUpload76.removeValue();
        fileUpload77.removeValue();
        fileUpload78.removeValue();
        fileUpload79.removeValue();
        fileUpload80.removeValue();
        fileUpload81.removeValue();
        fileUpload82.removeValue();
        fileUpload83.removeValue();
        fileUpload84.removeValue();
        fileUpload85.removeValue();
        fileUpload86.removeValue();
        fileUpload87.removeValue();
        fileUpload88.removeValue();
        fileUpload89.removeValue();
        fileUpload90.removeValue();
        fileUpload91.removeValue();
        fileUpload92.removeValue();
        fileUpload93.removeValue();
        fileUpload94.removeValue();
        fileUpload95.removeValue();
        fileUpload96.removeValue();
        fileUpload97.removeValue();
        fileUpload98.removeValue();
        fileUpload99.removeValue();
        fileUpload100.removeValue();
        fileUpload101.removeValue();
        fileUpload102.removeValue();
        fileUpload103.removeValue();
        fileUpload104.removeValue();
        fileUpload105.removeValue();
        fileUpload106.removeValue();
        fileUpload107.removeValue();
        fileUpload108.removeValue();
        fileUpload109.removeValue();
        fileUpload110.removeValue();
        fileUpload111.removeValue();
        fileUpload112.removeValue();
        fileUpload113.removeValue();
        fileUpload114.removeValue();
        fileUpload115.removeValue();
        fileUpload116.removeValue();
        fileUpload117.removeValue();
        fileUpload118.removeValue();
        fileUpload119.removeValue();
        fileUpload120.removeValue();
        fileUpload121.removeValue();
        fileUpload122.removeValue();
        fileUpload123.removeValue();
        fileUpload124.removeValue();
        fileUpload125.removeValue();
        fileUpload126.removeValue();
        fileUpload127.removeValue();
        fileUpload127.removeValue();
        fileUpload128.removeValue();
        fileUpload129.removeValue();
        fileUpload130.removeValue();
        fileUpload131.removeValue();
        fileUpload132.removeValue();
        fileUpload133.removeValue();
        fileUpload134.removeValue();
        fileUpload135.removeValue();
        fileUpload136.removeValue();
        fileUpload137.removeValue();
        fileUpload138.removeValue();
        fileUpload139.removeValue();
        fileUpload140.removeValue();


        Toast.makeText(AdminUploadDept.this,"File is deleted",Toast.LENGTH_LONG).show();
    }






    //this function will get the pdf from the storage
    private void getFile() {
        //for greater than lolipop versions we need the permissions asked on runtime
        //so if the permission is not available user will go to the screen to allow storage permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.parse("package:" + getPackageName()));
            startActivity(intent);
            return;
        }

        //creating an intent for file chooser
        Intent intent = new Intent();
        // intent.setType("docx/*");
        intent.setType("*/*");
        //intent.setType("image/*|application/*|audio/*|all/*");

        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_PDF_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //when the user choses the file
        if (requestCode == PICK_PDF_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            //if a file is selected
            if (data.getData() != null) {
                //uploading the file
                uploadFile(data.getData());
            }else{
                Toast.makeText(this, "No file chosen", Toast.LENGTH_SHORT).show();
            }
        }
    }


    //this method is uploading the file
    //the code is same as the previous tutorial
    //so we are not explaining it
    private void uploadFile(Uri data) {
        progressBar.setVisibility(View.VISIBLE);
        StorageReference sRef = mStorageReference.child(Constants.STORAGE_PATH_UPLOADS + System.currentTimeMillis());
        sRef.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @SuppressWarnings("VisibleForTests")
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressBar.setVisibility(View.GONE);
                        textViewStatus.setText("File Uploaded Successfully");

                        String id=mDatabaseReference.push().getKey();

                        Upload upload = new Upload(editTextFilename.getText().toString(), taskSnapshot.getDownloadUrl().toString(),id);
                        // mDatabaseReference.child(mDatabaseReference.push().getKey()).setValue(upload);
                        mDatabaseReference.child(id).setValue(upload);

                        //String id=mDatabaseReference.push().getKey();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @SuppressWarnings("VisibleForTests")
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                        textViewStatus.setText((int) progress + "% Uploading...");
                    }
                });

    }



}
