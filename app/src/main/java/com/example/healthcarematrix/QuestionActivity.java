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
                        int selectedId = rdshit.getCheckedRadioButtonId();
                        RadioButton rdbtn;
                        rdbtn = findViewById(selectedId);
                        String s2 =rdbtn.getText().toString();
                        if(s2.equals("Watery")){
                            Checker.answersArray[7]="Q1";
                        }
                        else if (s2.equals("Frequent Stool")) {
                            Checker.answersArray[7] = "Q2";
                        }else{

                        }
                    }
                    else {
                        int selectedId;
                        boolean Empty = false;
                        RadioButton rdbtn;
                        switch(Checker.QuestionNumber){

                            case 0:
                                break;
                            case 1:
                                //gender
                                selectedId = rdgender.getCheckedRadioButtonId();
                                rdbtn = findViewById(selectedId);
                                String s = rdbtn.getText().toString();
                                if (s.equals("Male") ){
                                    Checker.answersArray[0]= "!0";
                                }else if(s.equals("Female")){
                                    Checker.answersArray[0]= "!1";
                                }else {
                                    Checker.answersArray[0]="!";
                                }
                                break;
                            case 2:
                                //age
                                selectedId = rdAgeGroup.getCheckedRadioButtonId();
                                rdbtn = findViewById(selectedId);
                                String s1 =rdbtn.getText().toString();
                                if(s1.equals("Child")){
                                    Checker.answersArray[1]="@0";
                                }else if(s1.equals("Adult")) {
                                    Checker.answersArray[1]="@1";
                                }
                                break;
                            case 3:
                                //temp
                                Empty = checkIfEmptyOrNot();
                                Checker.answersArray[2]="#"+txtanswer.getText().toString();
                                break;
                            case 4:
                                //weight
                                Empty = checkIfEmptyOrNot();
                                Checker.answersArray[3]="W"+txtanswer.getText().toString();
                                break;
                            case 5:
                                //height
                                Empty = checkIfEmptyOrNot();
                                Checker.answersArray[4]="%"+txtanswer.getText().toString();
                                //Toast.makeText(getApplicationContext(),Checker.answersArray[5],Toast.LENGTH_SHORT).show();
                                break;
                            case 6:
                                //Glucometer
                                Empty = checkIfEmptyOrNot();
                                Checker.answersArray[5]="&"+txtanswer.getText().toString();
                                break;
                            case 7:
                                //PulseRate
                                Empty = checkIfEmptyOrNot();
                                Checker.answersArray[6]="*"+txtanswer.getText().toString();
                                break;
                            case 8:
                                //stool
                                selectedId = rdshit.getCheckedRadioButtonId();
                                rdbtn = findViewById(selectedId);
                                String s2 =rdbtn.getText().toString();
                                if(s2.equals(R.string.watery)){
                                    Checker.answersArray[7]="Q1";
                            }
                                else if (s2.equals(R.string.frequent_stool)) {
                                    Checker.answersArray[7] = "Q2";
                                }else{

                                }
                            break;
                        }
                        if(!Empty){
                            startActivity(new Intent(QuestionActivity.this, sessionActivity.class));
                        finish();
                        }else{
                            Toast.makeText(QuestionActivity.this, "Empty answer not Accepted", Toast.LENGTH_SHORT).show();
                        }
                    }
            }

            private boolean checkIfEmptyOrNot() {

            if(txtanswer.getText().toString().equals(""))
                return true;
            else
                return false;
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
                rdgender.setVisibility(View.VISIBLE);
                break;
            case 2:
                rdAgeGroup.setVisibility(View.VISIBLE);
                break;
            case 3:
                txtanswer.setVisibility(View.VISIBLE);
                break;
            case 4:
                txtanswer.setVisibility(View.VISIBLE);
                break;
            case 5:
                txtanswer.setVisibility(View.VISIBLE);
                break;
            case 6:
                txtanswer.setVisibility(View.VISIBLE);
                break;
            case 7:
                txtanswer.setVisibility(View.VISIBLE);
                break;
            case 8:
                rdshit.setVisibility(View.VISIBLE);
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
                break;
            case 7:
                txtQuestions.setText(Checker.questionsArray[7]);
                break;
            case 8:
                txtQuestions.setText(Checker.questionsArray[8]);
                break;
        }
    }
}
