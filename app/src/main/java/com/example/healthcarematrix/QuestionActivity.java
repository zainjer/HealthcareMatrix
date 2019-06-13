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
import android.widget.Toast;

public class QuestionActivity extends AppCompatActivity {

    Button btnrestart,btnSubmit;
    EditText txtanswer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //To make Landscape layout
//******************************************************
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_question);

        btnrestart = findViewById(R.id.btnRestart);
        btnSubmit = findViewById(R.id.btnSubmit);
        txtanswer = findViewById(R.id.txtanswer);

        btnrestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuestionActivity.this,MainActivity.class));
                finish();

            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s;
                s = txtanswer.getText().toString();
                if(s!=""){
                    Checker.answersArray[0] = s;
                    Toast t = Toast.makeText(getApplicationContext(),Checker.answersArray[0],Toast.LENGTH_SHORT);
                    t.show();
                    startActivity(new Intent(QuestionActivity.this,sessionActivity.class));
                    finish();
               }
            }
        });

    }
}
