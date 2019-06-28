package com.example.healthcarematrix;

public class Checker {

    public static int QuestionNumber=0;
    public static int videoNumber=1;
    public static String[] answersArray = new String[8];
    public static String[] questionsArray = new String[10];
    public static String[] videoPaths = new String[10];
    public static String[] StepNames = new String[10];

    public static void populater(){

        videoPaths[0] = "android.resource://com.example.healthcarematrix/"+R.raw.v0;
        videoPaths[1] = "android.resource://com.example.healthcarematrix/"+R.raw.v1;
        videoPaths[2] = "android.resource://com.example.healthcarematrix/"+R.raw.v2;
        videoPaths[3] = "android.resource://com.example.healthcarematrix/"+R.raw.v3;
        videoPaths[4] = "android.resource://com.example.healthcarematrix/"+R.raw.v4;
        videoPaths[5] = "android.resource://com.example.healthcarematrix/"+R.raw.v5;
        videoPaths[6] = "android.resource://com.example.healthcarematrix/"+R.raw.v6;
        videoPaths[7] = "android.resource://com.example.healthcarematrix/"+R.raw.v7;
        videoPaths[8] = "android.resource://com.example.healthcarematrix/"+R.raw.v8;

        questionsArray[1]="Please Select your age group";
        questionsArray[2]="Please Enter your Body Temperature";
        questionsArray[3]="Please Enter your Pulse Rate";
        questionsArray[4]="Please Select your Feces Type";
        questionsArray[5]="Please Select your Gender";
        questionsArray[6]="Please Enter your Body Weight";
        questionsArray[7]="Please Enter your Height";
        questionsArray[8]="Please Enter your Glucose Level";



        StepNames[0]="";
        StepNames[1]="Selecting Age Group";
        StepNames[2]="Obtaining Body Temperature";
        StepNames[3]="Obtaining Pulse Rate";
        StepNames[4]="Selecting Feces Type";
        StepNames[5]="Selecting Gender";
        StepNames[6]="Obtaining Body Weight";
        StepNames[7]="Obtaining Height";
        StepNames[8]="Obtaining Glucose Level";
        StepNames[9] = "";
    }

    public static void StartAnew(){

        QuestionNumber= 0;
        videoNumber = 1;
    }

    }



