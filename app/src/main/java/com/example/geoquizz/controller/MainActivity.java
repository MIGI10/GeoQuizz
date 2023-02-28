package com.example.geoquizz.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.geoquizz.R;
import com.example.geoquizz.model.Player;
import com.example.geoquizz.model.Question;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private static final boolean[] ANSWERS =
            {true, false, true, false, false, false, false, true, true, true};
    private final ArrayList<Player> players = new ArrayList<>();
    private final ArrayList<Question> questions = new ArrayList<>();
    private Player currentPlayer;
    private Question currentQuestion;
    private int currentRound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);
        //setContentView(R.layout.activity_data);

        Button addPlayerButton = findViewById(R.id.addPlayerButton);
        Button startButton = findViewById(R.id.saveAndStartButton);
        TextView text = findViewById(R.id.textInputPlayer);

        addPlayerButton.setOnClickListener(v -> {
            Player player = new Player((String) text.getText());
            players.add(player);
        });

        startButton.setOnClickListener(v -> {
            setContentView(R.layout.questions);
            startGame();
        });

        Resources res = getResources();
        String[] questionHeaders = res.getStringArray(R.array.questions);

        for (int i=0; i<10; i++) {

            Question q = new Question();

            q.setQuestion(questionHeaders[i]);
            q.setIsTrue(ANSWERS[i]);

            questions.add(q);
        }

        Button trueButton = findViewById(R.id.true_button);
        Button falseButton = findViewById(R.id.false_button);

        trueButton.setOnClickListener(v -> {
            if (currentQuestion.getAnswer()) {
                currentPlayer.updateScore(currentRound, true);
            }
            else {
                currentPlayer.updateScore(currentRound, false);
            }
        });

        falseButton.setOnClickListener(v -> {
            if (currentQuestion.getAnswer()) {
                currentPlayer.updateScore(currentRound, false);
            }
            else {
                currentPlayer.updateScore(currentRound, true);
            }
        });
    }

    private void startGame() {

        TextView playerLabel = findViewById(R.id.player_label);
        TextView questionLabel = findViewById(R.id.question_label);

        for (int i = 0; i < players.size(); i++) {
            currentPlayer = players.get(i);
            Collections.shuffle(questions);
            playerLabel.setText(String.format(R.string.player_turn, i + 1));
            for (int j = 0; j < questions.size(); j++) {
                currentRound = j;
                currentQuestion = questions.get(j);
                questionLabel.setText(currentQuestion.getQuestion());
                currentQuestion.waitForAnswer();

            }
        }
        showWinner();
    }

    private void showWinner() {

        setContentView(R.layout.activity_results);

        TextView winnerLabel = findViewById(R.id.winner_label);

        int bestScore = 0;
        int bestPlayer;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getScore() > bestScore) {
                bestScore = players.get(i).getScore();
                bestPlayer = i;
            }
        }
        results.setText();
    }
}