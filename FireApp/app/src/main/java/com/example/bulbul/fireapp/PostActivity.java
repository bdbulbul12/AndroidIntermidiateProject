package com.example.bulbul.fireapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.security.PrivateKey;

public class PostActivity extends AppCompatActivity {
    private ImageButton selectedImage;
    private static final int GALLERY_REQUEST=1;
    private EditText mTitleText;
    private EditText mDescText;
    private Button mSubmitButton;
    private Uri mImageUri=null;
    private StorageReference mStoarge;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        mStoarge= FirebaseStorage.getInstance().getReference();

        selectedImage= (ImageButton) findViewById(R.id.imageSelect);
        mTitleText= (EditText) findViewById(R.id.titleField);
        mDescText= (EditText) findViewById(R.id.descField);
        mSubmitButton= (Button) findViewById(R.id.submitButtonId);
        mProgress=new ProgressDialog(this);



        selectedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,GALLERY_REQUEST);
            }
        });

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stratPosting();
            }
        });
    }

    private void stratPosting() {
        mProgress.setMessage("Posting to blog...");
        mProgress.show();
        String title_val= mTitleText.getText().toString().trim();
        String desc_val = mDescText.getText().toString().trim();

        if(!TextUtils.isEmpty(title_val) && !TextUtils.isEmpty(desc_val) && mImageUri !=null){
            StorageReference fillPath = mStoarge.child("blogImages").child(mImageUri.getLastPathSegment());
            fillPath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUri = taskSnapshot.getDownloadUrl();
                    mProgress.dismiss();

                }
            });
        }
    }

    @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){
            mImageUri= data.getData();
            selectedImage.setImageURI(mImageUri);
        }
    }
}
