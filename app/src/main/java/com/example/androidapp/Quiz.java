package com.example.androidapp;

public class Quiz {
    private int question;
    private boolean trueQuestion;

    Quiz(int question,boolean tureQuestion){
        this.question = question;
        this.trueQuestion = trueQuestion;
    }
    public int getQuestion(){
        return question;
    }
    public void setQuestion(int question){
        this.question = question;
    }

    public void setTrueQuestion(boolean trueQuestion) {
        this.trueQuestion = trueQuestion;
    }

    public boolean isTrueQuestion(){
        return trueQuestion;
    }
}
