package com.example.healthcarematrix;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;


public class SessionData implements Serializable {

    private String[] answers;
    private long sessionID;

    public SessionData(String[] answer,long id){

        answers = answer;
        sessionID = id;
    }


    public String[] getAnswers(){
        return answers;
    }

    public long getSessionID(){
        return  sessionID;
    }
     public String[] getQuestions(){

        String[] questions = new String[5];

        questions[0] = "Age Group";
        questions[1] = "Body Temperature";
        questions[2] = "Pulse Rate";
        questions[3] = "Feces Type";
        questions[4] = "Gender";

        return questions;
    }


}
