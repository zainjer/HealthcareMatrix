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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class sessionActivity extends AppCompatActivity {

    VideoView v1;
    Button btnenter,btnrestart,btnPlay,btnback;
    TextView currentStep,nextStep;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //To make app fullscreen
// *******************************************************
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //To make Landscape layout
//******************************************************
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        setContentView(R.layout.activity_session);

//******************************************************
        //Beginning Code
//******************************************************
        v1 = findViewById(R.id.VideoViewArray);
        Uri uri = Uri.parse(getVideoPath());
        btnenter = findViewById(R.id.btnEnter);
        btnrestart = findViewById(R.id.btnRestart);
        btnPlay = findViewById(R.id.btnPlay);
        btnback = findViewById(R.id.btnBack);
        currentStep = findViewById(R.id.txtCurrentStep);
        nextStep = findViewById(R.id.txtNextStep);


        //Enter Button Code---------------------------------

        btnenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checker.QuestionNumber++;
                Checker.videoNumber++;
                startActivity(new Intent(sessionActivity.this,QuestionActivity.class));
                finish();
            }
        });
        //--------------------------------------------------

        //Back button Code----------------------------------
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Checker.videoNumber<=1){
                    Toast.makeText(getApplicationContext(),"Cannot Go Back",Toast.LENGTH_SHORT).show();
                }else{
                    Checker.videoNumber--;
                    Checker.QuestionNumber--;
                    startActivity(new Intent(sessionActivity.this,sessionActivity.class));
                    finish();
                }
            }
        });

        //Restart Button Code-------------------------------
        btnrestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v1.stopPlayback();
                startActivity(new Intent(sessionActivity.this,MainActivity.class));
                finish();
            }
        });
        //--------------------------------------------------

        //Play Button Code---------------------------------
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(sessionActivity.this,sessionActivity.class));
                finish();
            }
        });

        v1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                v1.start();
            }
        });
        nextStep.setText(getCurrentStep(Checker.videoNumber+1)+" >>");
        currentStep.setText(getCurrentStep(Checker.videoNumber));
        v1.setVideoURI(uri);
        v1.requestFocus();
        v1.start();

        //-------------------------------------------------------



    }
    public String getVideoPath(){

        switch (Checker.videoNumber){
            case 0:
                return Checker.videoPaths[9];

            case 1:
                return Checker.videoPaths[1];
            case 2:
                return Checker.videoPaths[2];
            case 3:
                return Checker.videoPaths[3];
            case 4:
                return Checker.videoPaths[4];
            case 5:
                return Checker.videoPaths[5];
            case 6:
                return Checker.videoPaths[6];
            case 7:
                return Checker.videoPaths[7];
            case 8:
                return Checker.videoPaths[8];
        }
        return "";
    }
    public String getCurrentStep(int num){
        switch (num){
            case 0:
                return Checker.StepNames[0];
            case 1:
                return Checker.StepNames[1];
            case 2:
                return Checker.StepNames[2];
            case 3:
                return Checker.StepNames[3];
            case 4:
                return Checker.StepNames[4];
            case 5:
                return Checker.StepNames[5];
            case 6:
                return Checker.StepNames[6];
            case 7:
                return Checker.StepNames[7];
            case 8:
                return Checker.StepNames[8];

        }
        return "";
    }

}
