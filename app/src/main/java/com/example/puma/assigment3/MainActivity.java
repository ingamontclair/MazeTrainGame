package com.example.puma.assigment3;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button buttonStart;
    MediaPlayer backgroundMusic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //defining button for starting a game
        buttonStart = (Button)findViewById(R.id.btn_start);
        buttonStart.setOnClickListener(new StartLstr());

    }
    class StartLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            backgroundMusic = MediaPlayer.create(MainActivity.this, R.raw.wildwest);
            backgroundMusic.setLooping(true);
            //backgroundMusic.setVolume(10.0f, 3.0f);
            backgroundMusic.start();
            if (view.getId() == R.id.btn_start) {
                Intent intent = new Intent(MainActivity.this, SelectLevel.class);
                startActivity(intent);
            }
        }
    }
}
