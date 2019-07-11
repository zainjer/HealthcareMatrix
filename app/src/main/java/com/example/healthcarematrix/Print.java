package com.example.healthcarematrix;


import android.bluetooth.BluetoothAdapter;
import android.content.Context;
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


    SmoothBluetooth  mSmoothBluetooth;
    Button  btnFinish;
    TextView statuss;
    String datastring;

    public SmoothBluetooth.Listener mListener = new SmoothBluetooth.Listener() {
        @Override
        public void onBluetoothNotSupported() {
            Toast.makeText(Print.this, "Bluetooth Not Found", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onBluetoothNotEnabled() {
            if(BluetoothAdapter.getDefaultAdapter() == null){
                Toast.makeText(Print.this, "Bluetooth is not supported", Toast.LENGTH_SHORT).show();
            }else{
                Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivity(i);
            }
        }
        @Override
        public void onConnecting(Device device) {
            Toast.makeText(Print.this, "Trying To Connect", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onConnected(Device device) {
            Toast.makeText(Print.this, "Connection Successful!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onDisconnected() {
            Toast.makeText(Print.this, "Disconnected", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onConnectionFailed(Device device) {
            Toast.makeText(Print.this, "Connection Failed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onDiscoveryStarted() {

        }

        @Override
        public void onDiscoveryFinished() {

        }

        @Override
        public void onNoDevicesFound() {

        }

        @Override
        public void onDevicesFound(List<Device> deviceList, SmoothBluetooth.ConnectionCallback connectionCallback) {

            String[] btDeviceNames = new String[deviceList.size()];
            String[] btDeviceMAC = new String[deviceList.size()];
            int index=0;
            for(Device d :deviceList){
                btDeviceNames[index] = d.getName();
                btDeviceMAC[index] = d.getAddress();
                index++;
            }
            int i =0,pos=999;
            for (String s:btDeviceNames){
                if(s.equals("HC-05")){
                    pos = i;
                    Toast.makeText(getApplicationContext(),"Found Device to connect",Toast.LENGTH_SHORT);
                    break;
                }
                i++;
            }
            connectionCallback.connectTo(deviceList.get(pos));
        }
        @Override
        public void onDataReceived(int data) {


        }
    };

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

        datastring = "**##";
        for(int i =0; i<4; i++){
            datastring = "" + datastring + "\\" +Checker.answersArray[i];
        }
        datastring = datastring + "##**";

        statuss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(Print.this,bluetoothstatus.class));
                Toast.makeText(getApplicationContext(), datastring, Toast.LENGTH_LONG).show();

                mSmoothBluetooth.send(datastring);
            }
        });

        Context  x = getApplication();
        mSmoothBluetooth = new SmoothBluetooth(x, SmoothBluetooth.ConnectionTo.OTHER_DEVICE, SmoothBluetooth.Connection.INSECURE, mListener);
        mSmoothBluetooth.doDiscovery();
        mSmoothBluetooth.tryConnection();


    }
   public String createSessionFile() {

        SessionData s1 = new SessionData(Checker.answersArray,System.currentTimeMillis());
        String filepath = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)+"/"+s1.getSessionID()+".csv";
       //String filepath = Environment.getExternalStorageDirectory()+"/"+s1.getSessionID()+".csv";

        //Toast.makeText(this, filepath, Toast.LENGTH_LONG).show();
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
