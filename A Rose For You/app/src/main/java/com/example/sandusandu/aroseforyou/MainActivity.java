package com.example.sandusandu.aroseforyou;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.io.IOException;

public class MainActivity extends AppCompatActivity  {
    MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        mp = new MediaPlayer();
        AssetManager assetManager = this.getAssets();
        AssetFileDescriptor descriptor;

        if (mp.isPlaying()) {
           mp.pause();
            mp.stop();
            mp.release();


        } else {
            try {

                descriptor = assetManager.openFd("Titanic_Instrumental_Piano_.ogg");
                mp.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
                descriptor.close();
                mp.prepare();
                mp.setVolume(1f, 1f);
                mp.setLooping(true);
                mp.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mp.stop();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mp.pause();
        mp.stop();
        mp.release();

    }
}
