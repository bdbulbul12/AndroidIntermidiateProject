package com.example.bulbul.universityfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TestUserListActivity extends AppCompatActivity  {
    public static final String ARTIST_NAME="artistname";
    public static final String ARTIST_ID="artistid";


    ListView artistListView;
    DatabaseReference databaseArtists;
    List<Artist> artists;

    //SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_user_list);


        artistListView = (ListView) findViewById(R.id.artistListView);
        artists = new ArrayList<>();

        databaseArtists = FirebaseDatabase.getInstance().getReference("students");


        Intent intent = getIntent();
        String id   = intent.getStringExtra(MainActivity.ARTIST_ID);
        String name = intent.getStringExtra(MainActivity.ARTIST_NAME);


        artistListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Artist artist = artists.get(position);

                Intent intent = new Intent(getApplicationContext(),TestActivity.class);
                intent.putExtra(ARTIST_ID,artist.getArtistId());
                intent.putExtra(ARTIST_NAME,artist.getArtistName());
                startActivity(intent);

            }
        });

    }



    @Override
    protected void onStart() {
        super.onStart();
        //Retriving data From Firebase
        databaseArtists.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                artists.clear();
                for (DataSnapshot artistSnapshot : dataSnapshot.getChildren() ){
                    //Create Artist Class Object and Returning Value
                    Artist artist = artistSnapshot.getValue(Artist.class);
                    artists.add(artist);

                }

                final ArtistList adapter = new ArtistList(TestUserListActivity.this,artists);
                artistListView.setAdapter(adapter);

               // searchView= (SearchView) findViewById(R.id.searchViewId);
            /*    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        adapter.getFilter().filter(newText);
                        return false;
                    }
                }); */

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }

}
