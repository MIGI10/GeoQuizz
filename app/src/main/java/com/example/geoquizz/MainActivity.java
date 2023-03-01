package com.example.geoquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    private static final boolean[] ANSWERS =
            {true, false, true, false, false, false, false, true, true, true};
    private final ArrayList<Player> players = new ArrayList<>();
    private final ArrayList<Question> questions = new ArrayList<>();
    private Player currentPlayer;
    private Question currentQuestion;
    private int currentRound;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_names);
        //setContentView(R.layout.activity_data);

        setQuestionsUp();

        Button addPlayerButton = findViewById(R.id.addPlayerButton);
        Button startButton = findViewById(R.id.saveAndStartButton);
        EditText editText = findViewById(R.id.textInputPlayer);
        TextView textView = findViewById(R.id.textView2);

        addPlayerButton.setText(R.string.button_add);
        startButton.setText(R.string.button_sas);

        textView.setText("Enter player 1 name");
        AtomicInteger playerNumber = new AtomicInteger(2);

        addPlayerButton.setOnClickListener(v -> {
            Player player = new Player(editText.getText().toString());
            players.add(player);
            textView.setText("Enter player " + playerNumber.getAndIncrement() + " name");
        });

        startButton.setOnClickListener(v -> {
            setContentView(R.layout.questions);
            startGame();
        });

        setContentView(R.layout.activity_results);

        showResults();
    }

    private void setQuestionsUp(){

        Resources res = getResources();
        String[] questionHeaders = res.getStringArray(R.array.questions);

        for (int i=0; i<10; i++) {

            Question q = new Question();

            q.setQuestion(questionHeaders[i]);
            q.setAnswer(ANSWERS[i]);

            questions.add(q);
        }
    }

    private void startGame() {

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

        for (int i = 0; i < players.size(); i++) {
            numClicks.set(0);
            currentPlayer = players.get(i);
            Collections.shuffle(questions);
            playerLabel.setText(currentPlayer.getUsername());
            for (int j = 0; j < questions.size(); j++) {
                currentRound = j;
                currentQuestion = questions.get(j);
                questionLabel.setText(currentQuestion.getQuestion());
                currentQuestion.waitForAnswer(j, numClicks.intValue());
            }
        }
    }
    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
    private void showResults() {

        TextView winnerLabel = findViewById(R.id.winnerLabel);
        TextView message = findViewById(R.id.textView3);
        TextView results = findViewById(R.id.results);

        sortPlayersByScore();

        winnerLabel.setText(players.get(0).getUsername() + " is the winner!!!");
        message.setText(R.string.final_results);

        String finalScores = "";

        for (int k=0; k< players.size(); k++){

            finalScores.concat(players.get(k).getUsername());
            finalScores = finalScores + ": " + players.get(k).getScore() + "/10\n";
        }
        results.setText(finalScores);
    }

    private void sortPlayersByScore(){

        boolean inOrder = false;

        while (!inOrder) {

            inOrder = true;
            for (int k = 1; k < players.size(); k++) {

                if (players.get(k - 1).getScore() < players.get(k).getScore()) {

                    players.get(k - 1).swapPlayers(players.get(k));
                    inOrder = false;
                }
            }
        }
    }
}