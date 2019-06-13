package com.example.healthcarematrix;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    VideoView v1;
    Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //To make app fullscreen
// *******************************************************
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //To make Landscape layout
//******************************************************
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setContentView(R.layout.activity_main);


//******************************************************
        //Beginning Code
//******************************************************
        start = findViewById(R.id.btnRestart); //Start button object
        v1 = findViewById(R.id.loopingVideoView); //VideoView object

        //Code for Video****************************************************************
        String videopath = "android.resource://" + getPackageName() + "/" + R.raw.videoplayback; //Introduction Video Path
        Uri uri = Uri.parse(videopath); //Introduction video URI
            //Video Loop-----------------------------------------
            v1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    v1.start(); //need to make transition seamless.
                }
            });
            //---------------------------------------------------
        v1.setVideoURI(uri);
        v1.requestFocus();
        v1.start();
        //*****************************************************************************

        //Start Button Click Event------------------------------
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v1.stopPlayback();
                startActivity(new Intent(MainActivity.this,sessionActivity.class));

            }
        });
        //------------------------------------------------------
    }
}
