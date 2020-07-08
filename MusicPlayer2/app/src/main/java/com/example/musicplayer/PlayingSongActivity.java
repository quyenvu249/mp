package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

public class PlayingSongActivity extends AppCompatActivity implements MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {
    ImageView img;
    TextView tvSinger, tvName, tvTimePl, tvTimeTo;
    SeekBar seekBar;
    ImageView imPr, imPl, imN;
    String urlSong;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_song);
        img = findViewById(R.id.img);
        tvSinger = findViewById(R.id.tvSinger);
        tvName = findViewById(R.id.tvName);
        tvTimePl = findViewById(R.id.tvTimePl);
        tvTimeTo = findViewById(R.id.tvTimeTo);
        seekBar = findViewById(R.id.seekBar);
        imN = findViewById(R.id.imgN);
        imPr = findViewById(R.id.imgPr);
        imPl = findViewById(R.id.imgPl);

        Intent intent = getIntent();
        Bundle b = intent.getBundleExtra("bdPlayingSong");
        Picasso.with(this).load(b.getString("linkAnh")).into(img);
        tvName.setText(b.getString("name"));
        tvSinger.setText(b.getString("singer"));
        tvTimeTo.setText(b.getDouble("duration") + "");
        urlSong = b.getString("linkMp3");

        Uri myUri = Uri.parse(urlSong); // initialize Uri here
        player = new MediaPlayer();

        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            player.setDataSource("http://www.hubharp.com/web_sound/BachGavotte.mp3");
            player.setOnErrorListener(this);
            player.setOnPreparedListener(this);
            player.prepareAsync();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        player.release();
        player = null;
    }
    @Override
    public void onPrepared(MediaPlayer play) {
        play.start();
    }
    @Override
    public boolean onError(MediaPlayer arg0, int arg1, int arg2) {
        return false;
    }
}
