package com.example.bulbul.universityfinder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

public class MainActivity extends AppCompatActivity {

    public static final String ARTIST_NAME="artistname";
    public static final String ARTIST_ID="artistid";

    private EditText editTextName;
    private Button buttonAdd,showArtistUserList;
    private Spinner spinnerGeneres;
    private Spinner addSession;

    DatabaseReference databaseArtists;

    ListView listViewArtists;
    List<Artist> artistList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseArtists = FirebaseDatabase.getInstance().getReference("students");

        editTextName= (EditText) findViewById(R.id.editTextName);
        buttonAdd= (Button) findViewById(R.id.buttonAddArtist);
        spinnerGeneres= (Spinner) findViewById(R.id.spinnerId);
        addSession= (Spinner) findViewById(R.id.sessionId);
        showArtistUserList = (Button) findViewById(R.id.showArtistUserList);

        listViewArtists= (ListView) findViewById(R.id.listViewArtist);
        artistList = new ArrayList<>();
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addArtist();
            }
        });

        showArtistUserList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TestUserListActivity.class);
                startActivity(intent);
            }
        });

        listViewArtists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Artist artist = artistList.get(position);
                Intent intent = new Intent(getApplicationContext(),TestActivity.class);
                Intent intent1 = new Intent(getApplicationContext(),AddTrackActivity.class);




                intent.putExtra(ARTIST_ID,artist.getArtistId());
                intent.putExtra(ARTIST_NAME,artist.getArtistName());
                startActivity(intent);

                //--------------------------------------Intent1 AddTracActivity Test Activity-----------------------------

                intent1.putExtra(ARTIST_ID,artist.getArtistId());
                intent1.putExtra(ARTIST_NAME,artist.getArtistName());
                startActivity(intent1);


            }
        });

        listViewArtists.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Artist artist = artistList.get(position);
                showUpdateDialog(artist.getArtistId(),artist.getArtistName());
                return false;
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
                    artistList.clear();
                for (DataSnapshot artistSnapshot : dataSnapshot.getChildren() ){
                    //Create Artist Class Object and Returning Value
                    Artist artist = artistSnapshot.getValue(Artist.class);
                    artistList.add(artist);

                }
                ArtistList adapter = new ArtistList(MainActivity.this,artistList);
                listViewArtists.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
//Upaditing Info.................
    private void showUpdateDialog(final String artistId, final String artistName){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog,null);
        dialogBuilder.setView(dialogView);


        final EditText editTextName = (EditText) dialogView.findViewById(R.id.editTextName);
        final Button buttonUpdate  = (Button) dialogView.findViewById(R.id.buttonUpdate);
        final Spinner spinnerGenres  = (Spinner) dialogView.findViewById(R.id.spinnerUpdate);
        final Spinner spinnerSession  = (Spinner) dialogView.findViewById(R.id.updateSession);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.deleteButton);

        dialogBuilder.setTitle("Updating Student : "+artistName);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString().trim();
                String genre = spinnerGenres.getSelectedItem().toString();
                String session = spinnerSession.getSelectedItem().toString();

                if(TextUtils.isEmpty(name)){
                    editTextName.setError("Name Required");
                    return;
                }
                updateArtist(artistId,name,genre,session);
                alertDialog.dismiss();
            }
        });
//Deleeting item...........
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteArtist(artistId);
            }
        });


    }



    private void deleteArtist(String artistId) {
        DatabaseReference drArtist = FirebaseDatabase.getInstance().getReference("students").child(artistId);
        DatabaseReference drTracks = FirebaseDatabase.getInstance().getReference("studentInfo").child(artistId);

        drArtist.removeValue();
        drTracks.removeValue();

        Toast.makeText(MainActivity.this,"Student is deleted",Toast.LENGTH_LONG).show();
    }

    //Update Artist-----------------
    private boolean updateArtist(String id, String name, String genre,String session){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("students").child(id);
        Artist artist = new Artist(id,name,genre,session);
        databaseReference.setValue(artist);
        Toast.makeText(MainActivity.this,"Updated Succesfully",Toast.LENGTH_LONG).show();
        return true;
    }

    private void addArtist(){
        String name = editTextName.getText().toString().trim();
        String genere = spinnerGeneres.getSelectedItem().toString();
        String session = addSession.getSelectedItem().toString();


        if(!TextUtils.isEmpty(name)){
            String id = databaseArtists.push().getKey();

            //Create An Artist Object
            Artist artist = new Artist(id,name,genere,session);
            databaseArtists.child(id).setValue(artist);
            Toast.makeText(this,"Succesfully Stored Data",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"You should enter a name",Toast.LENGTH_LONG).show();
        }
    }
}
