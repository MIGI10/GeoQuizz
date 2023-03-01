package com.example.geoquizz;

import java.util.concurrent.atomic.AtomicInteger;

public class Question {

    private String question;

    private boolean answer;

    public String getQuestion() {
        return question;
    }

    public boolean getAnswer() {
        return answer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(boolean aTrue) {
        answer = aTrue;
    }

    public void waitForAnswer(int counter, int numClicks) {
        while (counter == numClicks){}
    }
}