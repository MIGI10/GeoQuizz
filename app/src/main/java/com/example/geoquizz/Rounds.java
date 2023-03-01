package com.example.geoquizz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

public class Rounds extends AppCompatActivity {
    private Player currentPlayer;
    private Question currentQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);

        TextView playerLabel = findViewById(R.id.playerLabel);
        TextView questionLabel = findViewById(R.id.textLabelQuestion);
        Button trueButton = findViewById(R.id.true_button);
        Button falseButton = findViewById(R.id.false_button);

        trueButton.setText(R.string.button_true);
        falseButton.setText(R.string.button_false);

        AtomicInteger numClicks = new AtomicInteger();

        trueButton.setOnClickListener(v -> {
            currentPlayer.updateScore(true, currentQuestion.getAnswer());
            numClicks.getAndIncrement();
        });

        falseButton.setOnClickListener(v -> {
            currentPlayer.updateScore(false, currentQuestion.getAnswer());
            numClicks.getAndIncrement();
        });

        for (int i = 0; i < MainActivity.players.size(); i++) {
            numClicks.set(0);
            currentPlayer = MainActivity.players.get(i);
            Collections.shuffle(MainActivity.questions);
            playerLabel.setText(currentPlayer.getUsername());
            for (int j = 0; j < MainActivity.questions.size(); j++) {
                currentQuestion = MainActivity.questions.get(j);
                questionLabel.setText(currentQuestion.getQuestion());
                currentQuestion.waitForAnswer(j, numClicks.intValue());
            }
        }
        switchToResults();
    }

    private void switchToResults(){
        Intent switchToResults = new Intent(this, Results.class);
        startActivity(switchToResults);
    }
}


