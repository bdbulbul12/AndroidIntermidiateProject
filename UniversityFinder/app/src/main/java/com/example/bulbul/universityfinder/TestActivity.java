package com.example.bulbul.universityfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    ListView trackListView;
    TextView nameOfArtist;
    DatabaseReference databaseTracks;
    List<Track> tracks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

     //   testShow= (TextView) findViewById(R.id.testShow);
        trackListView = (ListView) findViewById(R.id.trackListview);
        nameOfArtist= (TextView) findViewById(R.id.nameOfArtist);
        tracks = new ArrayList<>();

        Intent intent = getIntent();
        String id   = intent.getStringExtra(MainActivity.ARTIST_ID);
        String name = intent.getStringExtra(MainActivity.ARTIST_NAME);
        nameOfArtist.setText("Selected Student :"+name);

        databaseTracks = FirebaseDatabase.getInstance().getReference("studentInfo").child(id);
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

                TrackList adapterTracks = new TrackList(TestActivity.this,tracks);
                trackListView.setAdapter(adapterTracks);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
