package com.example.geoquizz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;

public class Rounds extends AppCompatActivity {

    private TextView playerLabel;
    private TextView questionLabel;
    private int currentPlayerNum;
    private int currentQuestionNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);

        playerLabel = findViewById(R.id.player_label);
        questionLabel = findViewById(R.id.question_label);
        Button trueButton = findViewById(R.id.true_button);
        Button falseButton = findViewById(R.id.false_button);

        trueButton.setText(R.string.button_true);
        falseButton.setText(R.string.button_false);

        playerLabel.setText(MainActivity.players.get(0).getUsername());
        questionLabel.setText(MainActivity.questions.get(0).getQuestion());

        trueButton.setOnClickListener(v -> {
            questionAnswered(true);
        });

        falseButton.setOnClickListener(v -> {
            questionAnswered(false);
        });
    }

    private void questionAnswered(boolean answer) {

        Player currentPlayer = MainActivity.players.get(currentPlayerNum);
        Question currentQuestion = MainActivity.questions.get(currentQuestionNum);

        currentPlayer.updateScore(answer, currentQuestion.getAnswer());

        if (currentQuestionNum + 1 == 10) {
            if (currentPlayerNum == MainActivity.players.size() - 1) {
                switchToResults();
            }
            else {
                Collections.shuffle(MainActivity.questions);
                playerLabel.setText(currentPlayer.getUsername());
                currentQuestionNum = 0;
                currentPlayerNum++;
            }
        }
        else {
            questionLabel.setText(currentQuestion.getQuestion());
            currentQuestionNum++;
        }
    }

    private void switchToResults(){
        Intent switchToResults = new Intent(this, Results.class);
        startActivity(switchToResults);
    }
}


