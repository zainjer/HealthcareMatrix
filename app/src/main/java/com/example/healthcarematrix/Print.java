package com.example.healthcarematrix;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class Print extends AppCompatActivity {

    Button  btnFinish;
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
       
        setContentView(R.layout.activity_print2);

        createSessionFile();



        btnFinish = findViewById(R.id.btnFinish);

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Print.this,MainActivity.class));
                finish();

            }
        });

    }

   public void createSessionFile() {

        SessionData s1 = new SessionData(Checker.answersArray,System.currentTimeMillis());


        WriteToFile(s1.getSessionID()+".txt",s1.getQuestions(),Checker.answersArray,s1.getSessionID());


       Toast.makeText(this,"Saved in"+getFilesDir(),Toast.LENGTH_LONG).show();
        //Toast.makeText(this, e.toString()+"", Toast.LENGTH_SHORT).show();
    }
    public Exception WriteToFile(String filename, String[] question, String[] answers, long ID){


        FileOutputStream fos = null;
        FileWriter fileWriter;
        try {
            fos = openFileOutput("abced.txt",MODE_PRIVATE);
            fileWriter = new FileWriter(fos.getFD());
            fileWriter.append("ID");
            fileWriter.append(",");
            fileWriter.append(""+ID);
            fileWriter.append("\n");

            for(int i = 0; i<question.length; i++) {
                fileWriter.append(question[i]);
                fileWriter.append(",");
                fileWriter.append(answers[i]);
                fileWriter.append("\n");
            }
            fileWriter.flush();
            fileWriter.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return e;
        } catch (IOException e) {
            e.printStackTrace();
            return e;
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    return null;
    }
}
