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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
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
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

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
                    if(Checker.QuestionNumber>=8){

                        startActivity(new Intent(QuestionActivity.this,Print.class));
                        finish();


                        //-------------------For 8th Question
                        int selectedId;
                        RadioButton rdbtn;
                        Checker.answersArray[7]=txtanswer.getText().toString();
                        //Toast.makeText(getApplicationContext(),Checker.answersArray[5],Toast.LENGTH_SHORT).show();
                    }
                    else {
                        int selectedId;
                        RadioButton rdbtn;
                        switch(Checker.QuestionNumber){

                            case 0:
                                break;
                            case 1:
                                selectedId = rdAgeGroup.getCheckedRadioButtonId();
                                rdbtn = findViewById(selectedId);
                                Checker.answersArray[0]=  rdbtn.getText().toString();
                               // Toast.makeText(getApplicationContext(),Checker.answersArray[1],Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                Checker.answersArray[1]=txtanswer.getText().toString();
                              //  Toast.makeText(getApplicationContext(),Checker.answersArray[2],Toast.LENGTH_LONG).show();
                                break;
                            case 3:
                                Checker.answersArray[2]=txtanswer.getText().toString();
                              //  Toast.makeText(getApplicationContext(),Checker.answersArray[3],Toast.LENGTH_LONG).show();
                                break;
                            case 4:
                                selectedId = rdshit.getCheckedRadioButtonId();
                                rdbtn = findViewById(selectedId);
                                Checker.answersArray[3]=  rdbtn.getText().toString();
                              //  Toast.makeText(getApplicationContext(),Checker.answersArray[4],Toast.LENGTH_SHORT).show();
                                break;
                            //---This case is in the if clause-----------------------------------------
                            case 5:
                                selectedId = rdgender.getCheckedRadioButtonId();
                                rdbtn = findViewById(selectedId);
                                Checker.answersArray[4]=  rdbtn.getText().toString();
                                //Toast.makeText(getApplicationContext(),Checker.answersArray[5],Toast.LENGTH_SHORT).show();
                                break;
                            case 6:
                                Checker.answersArray[5]=txtanswer.getText().toString();
                            case 7:
                                Checker.answersArray[6]=txtanswer.getText().toString();
                            case 8:
                                Checker.answersArray[7]=txtanswer.getText().toString();
                        }
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
                rdgender.setVisibility(View.VISIBLE);
                break;
            case 6:
                txtanswer.setVisibility(View.VISIBLE);
                break;
            case 7:
                txtanswer.setVisibility(View.VISIBLE);
                break;
            case 8:
                txtanswer.setVisibility(View.VISIBLE);
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
            case 6:
                txtQuestions.setText(Checker.questionsArray[6]);
            case 7:
                txtQuestions.setText(Checker.questionsArray[7]);
            case 8:
                txtQuestions.setText(Checker.questionsArray[8]);
        }
    }
}
