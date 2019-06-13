package com.example.healthcarematrix;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.VideoView;

public class sessionActivity extends AppCompatActivity {

    VideoView v1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //To make Landscape layout
//******************************************************
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        setContentView(R.layout.activity_session);

        v1 = findViewById(R.id.VideoViewArray);
        String videopath = "android.resource://" + getPackageName() + "/" + R.raw.videoplayback;
        Uri uri = Uri.parse(videopath);


        //Video Loop
        v1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                v1.start(); //need to make transition seamless.
            }
        });

        v1.setVideoURI(uri);
        v1.requestFocus();
        v1.start();
    }
}
