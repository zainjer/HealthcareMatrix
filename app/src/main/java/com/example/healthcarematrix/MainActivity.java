package com.example.healthcarematrix;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
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
       // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setContentView(R.layout.activity_main);


//******************************************************
        //Beginning Code
//******************************************************
        checkpermision();
        Checker.populater(); // To Populate Questions in the Checker Class

        Checker.StartAnew();

        start = findViewById(R.id.btnStart); //Start button object
        v1 = findViewById(R.id.loopingVideoView); //VideoView object

        //Code for Video****************************************************************
        Uri uri = Uri.parse(Checker.videoPaths[0]); //Making video URI
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
                //startActivity(new Intent(MainActivity.this,Print.class));
                finish();

            }
        });
        //------------------------------------------------------
    }
    boolean checkpermision(){

        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), permissions[0]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this.getApplicationContext(), permissions[1]) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        else{
            ActivityCompat.requestPermissions(MainActivity.this,permissions,1);
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(), permissions[0]) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this.getApplicationContext(), permissions[1]) == PackageManager.PERMISSION_GRANTED) {
                return true;
            }
        }

        return false;
    }
}
