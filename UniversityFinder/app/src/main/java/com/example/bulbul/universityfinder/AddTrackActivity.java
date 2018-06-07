package com.example.bulbul.universityfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddTrackActivity extends AppCompatActivity {

    TextView textViewArtistName;
    EditText editTextTrackName;
    EditText phoneNumber;

    ListView listViewTracks;

    Button buttonAddTrack;
    Button showTrack;

    Spinner addSemester;

    DatabaseReference databaseTracks;
    List<Track> tracks;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_track);

        textViewArtistName = (TextView) findViewById(R.id.textViewArtistName);
        addSemester= (Spinner) findViewById(R.id.addSemester);
        phoneNumber      = (EditText) findViewById(R.id.phoneNumber);
        buttonAddTrack     = (Button) findViewById(R.id.buttonAddTrack);
        showTrack = (Button) findViewById(R.id.showTrack);




        listViewTracks     = (ListView) findViewById(R.id.listViewTracks);

       Intent intent = getIntent();

        tracks = new ArrayList<>();

        String id   = intent.getStringExtra(MainActivity.ARTIST_ID);
        String name = intent.getStringExtra(MainActivity.ARTIST_NAME);

         textViewArtistName.setText(name);

        databaseTracks = FirebaseDatabase.getInstance().getReference("studentInfo").child(id);

        showTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(AddTrackActivity.this,TestActivity.class);
                startActivity(intent3);
            }
        });

        buttonAddTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTrack();
            }
        });
    }




 @Override
    protected void onStart() {
        super.onStart();
        databaseTracks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    tracks.clear();
               for (DataSnapshot trackSnapshot : dataSnapshot.getChildren()){
                    //Create Artist Class Object and Returning Value
                      Track track = trackSnapshot.getValue(Track.class);
                      tracks.add(track);


                }

                    TrackList adapterTracks = new TrackList(AddTrackActivity.this,tracks);
                     listViewTracks.setAdapter(adapterTracks);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void saveTrack(){
        String semester = addSemester.getSelectedItem().toString();
        double phone = Double.parseDouble(phoneNumber.getText().toString().trim());

        if(!TextUtils.isEmpty(semester)){
            String id = databaseTracks.push().getKey();

            Track track = new Track(id,semester,phone);
            databaseTracks.child(id).setValue(track);
            Toast.makeText(this,"Info Saved Succesfully",Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(this,"Semester should not be Empty",Toast.LENGTH_LONG).show();
        }
    }
}
