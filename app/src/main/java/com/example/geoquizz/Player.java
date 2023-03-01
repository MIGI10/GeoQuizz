package com.example.geoquizz;

public class Player {
    private String username;
    private int score;

    public Player(String username) {

        this.username = username;
        this.score = 0;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    public boolean updateScore(boolean playerAnswer, boolean questionSolution){

        if (playerAnswer == questionSolution){
            score++;
            return true;
        }
        return false;
    }

    public void swapPlayers(Player that){

        String userNameAux = that.username;
        int scoreAux = that.score;

        that.username = this.username;
        that.score = this.score;

        this.username = userNameAux;
        this.score = scoreAux;
    }
}
