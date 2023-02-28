package com.example.geoquizz.model;

public class Player {
    private String username;
    private int score;

    public Player(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    public void roundWon() {
        score++;
    }
}
