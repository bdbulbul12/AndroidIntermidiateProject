package com.example.bulbul.onlineradio;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button playBtn;

    MediaPlayer mediaPlayer;

    boolean prepared;
    boolean started=false;

    String stream="http://vip-icecast.538.lw.triple-it.nl:80/WEB06_MP3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playBtn= (Button) findViewById(R.id.button);
        playBtn.setEnabled(false);
        playBtn.setText("Loading...");

        mediaPlayer=new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        new PlayerTask().execute(stream);

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(started){
                    started=false;
                    mediaPlayer.pause();
                    playBtn.setText("Play");
                }else{
                    started=true;
                    mediaPlayer.start();
                    playBtn.setText("Pasuse");
                }
            }
        });
    }

    class PlayerTask extends AsyncTask<String,Void,Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {

            try {
                mediaPlayer.setDataSource(params[0]);
                mediaPlayer.prepare();
                prepared=true;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return prepared;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            playBtn.setEnabled(true);
            playBtn.setText("Play");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(started){
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(started){
            mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(prepared){
            mediaPlayer.release();
        }
    }
}
