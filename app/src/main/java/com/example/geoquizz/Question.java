package com.example.geoquizz;

public class Question {

    private String question;

    private boolean isTrue;

    public String getQuestion() {
        return question;
    }

    public boolean getIsTrue() {
        return isTrue;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setTrue(boolean aTrue) {
        isTrue = aTrue;
    }

    public boolean getAnswer() {
    }

    public void waitForAnswer() {
    }
}