package com.example.healthcarematrix;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.file.StandardWatchEventKinds;

public class QuestionActivity extends AppCompatActivity {

    Button btnrestart,btnSubmit;
    EditText txtanswer;
    TextView txtQuestions;
    RadioGroup rdgender,rdAgeGroup,rdshit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //To make Landscape layout
//******************************************************
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_question);
        txtQuestions = findViewById(R.id.txtQuestion);
        btnrestart = findViewById(R.id.btnRestart);
        btnSubmit = findViewById(R.id.btnSubmit);
        txtanswer = findViewById(R.id.txtanswer);
        rdgender = findViewById(R.id.radioButtonGroupQ5);
        rdshit = findViewById(R.id.radioButtonGroupQ4);
        rdAgeGroup = findViewById(R.id.radioButtonGroupQ1);
        setQuestion();//Setting the question for Text View
        setAppropriateView(); // Setting the view with respect to Question?


        //Restart Button Code----------------------------------
        btnrestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuestionActivity.this,MainActivity.class));
                finish();
            }
        });

        //Submit Button Code----------------------------------
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(Checker.QuestionNumber>=5){

                        startActivity(new Intent(QuestionActivity.this, print.class));
                        finish();

                    }
                    else {
                        startActivity(new Intent(QuestionActivity.this, sessionActivity.class));
                        finish();
                    }
            }
        });



    }

    public void setAppropriateView(){
        txtanswer.setVisibility(View.INVISIBLE);
        rdgender.setVisibility(View.INVISIBLE);
        rdshit.setVisibility(View.INVISIBLE);
        rdAgeGroup.setVisibility(View.INVISIBLE);
        switch (Checker.QuestionNumber){
            case 1:
                rdAgeGroup.setVisibility(View.VISIBLE);
                break;
            case 2:
                txtanswer.setVisibility(View.VISIBLE);
                break;
            case 3:
                txtanswer.setVisibility(View.VISIBLE);
                break;
            case 4:
                rdshit.setVisibility(View.VISIBLE);
                break;
            case 5:
                rdAgeGroup.setVisibility(View.VISIBLE);
                break;
        }
    }
    public void setQuestion() {
        switch (Checker.QuestionNumber){
            case 0:

                break;
            case 1:
                txtQuestions.setText(Checker.questionsArray[1]);
                break;
            case 2:
                txtQuestions.setText(Checker.questionsArray[2]);
                break;
            case 3:
                txtQuestions.setText(Checker.questionsArray[3]);
                break;
            case 4:
                txtQuestions.setText(Checker.questionsArray[4]);
                break;
            case 5:
                txtQuestions.setText(Checker.questionsArray[5]);
                break;
        }
    }
}
