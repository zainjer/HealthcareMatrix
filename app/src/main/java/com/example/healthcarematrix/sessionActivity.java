package com.example.healthcarematrix;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
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
import android.widget.EditText;
import android.widget.VideoView;

public class sessionActivity extends AppCompatActivity {

    VideoView v1;
    Button btnenter,btnrestart;
    EditText txtanswer;


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


        setContentView(R.layout.activity_session);

//******************************************************
        //Beginning Code
//******************************************************
        v1 = findViewById(R.id.VideoViewArray);
        String videopath = "android.resource://" + getPackageName() + "/" + R.raw.videoplayback;
        Uri uri = Uri.parse(videopath);
        btnenter = findViewById(R.id.btnEnter);
        btnrestart = findViewById(R.id.btnRestart);
        txtanswer = findViewById(R.id.txtanswer);
        //Enter Button Code---------------------------------

        btnenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(sessionActivity.this,QuestionActivity.class));
            }
        });
        //--------------------------------------------------




        //Restart Button Code-------------------------------

        btnrestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v1.stopPlayback();
                Intent mStartActivity = new Intent(sessionActivity.this, sessionActivity.class);
                int mPendingIntentId = 123456;
                PendingIntent mPendingIntent = PendingIntent.getActivity(sessionActivity.this, mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
                AlarmManager mgr = (AlarmManager)sessionActivity.this.getSystemService(Context.ALARM_SERVICE);
                mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 10, mPendingIntent);
                System.exit(0);
            }
        });


        //Video Array Code--------------------------------------




        //Video Loop
        v1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                v1.start(); //need to make transition seamless.
            }
        });

        v1.setVideoURI(uri);
        v1.requestFocus();
        v1.start();
        //-------------------------------------------------------



    }

}
