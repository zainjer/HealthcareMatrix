package com.example.healthcarematrix;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import io.palaima.smoothbluetooth.Device;
import io.palaima.smoothbluetooth.SmoothBluetooth;


public class Print extends AppCompatActivity {


    Button  btnFinish;
    TextView statuss;

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


        String path = createSessionFile();
        btnFinish = findViewById(R.id.btnFinish);
        statuss = findViewById(R.id.txtStatus);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Print.this,MainActivity.class));
                finish();
            }
        });

        statuss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Print.this,bluetoothstatus.class));

            }
        });
    }
   public String createSessionFile() {

        SessionData s1 = new SessionData(Checker.answersArray,System.currentTimeMillis());
        String filepath = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)+"/"+s1.getSessionID()+".csv";
       //String filepath = Environment.getExternalStorageDirectory()+"/"+s1.getSessionID()+".csv";

        Toast.makeText(this, filepath, Toast.LENGTH_LONG).show();
        WriteToFile(s1.getSessionID()+".csv",s1.getQuestions(),Checker.answersArray,s1.getSessionID());
       //Toast.makeText(this,"Saved in "+Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),Toast.LENGTH_LONG).show();
    return filepath;
    }
    public void WriteToFile(String filename, String[] question, String[] answers, long ID){


        FileOutputStream fos = null;
        FileWriter fileWriter;
        try {
            //fos = openFileOutput("abcd.txt" ,MODE_APPEND);
            fos = new FileOutputStream(new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),filename),true);
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
            Toast.makeText(this, e.toString()+"", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, e.toString()+"", Toast.LENGTH_SHORT).show();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
