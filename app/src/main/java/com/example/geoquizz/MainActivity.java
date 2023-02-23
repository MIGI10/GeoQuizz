package com.example.geoquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    Button addPlayerButton, startButton, trueButton, falseButton;
    boolean startGame = false;
    ArrayList<String> players = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setContentView(R.layout.activity_data);

        addPlayerButton = findViewById(R.id.add_player);
        startButton = findViewById(R.id.start_button);
        TextView text = findViewById(R.id.playerUsernameField);

        addPlayerButton.setOnClickListener(v -> {
            String username = (String) text.getText();
            players.add(username);
        });

        startButton.setOnClickListener(v -> {
            setContentView(R.layout.activity_main);
            startGame();
        });

        ArrayList<Question> questions = new ArrayList<>();
        boolean[] isTrue = {true, false, true, false, false, false, false, true, true, true};
        Resources res = getResources();
        String[] questionHeaders = res.getStringArray(R.array.questions);

        for (int i=0; i<10; i++){

            Question q = new Question();

            q.setQuestion(questionHeaders[i]);
            q.setTrue(isTrue[i]);

            questions.add(q);
        }



        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);

        trueButton.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, R.string.correct, Toast.LENGTH_SHORT).show();
        });

        falseButton.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, R.string.incorrect, Toast.LENGTH_SHORT).show();
        });
    }

    private void startGame() {

        Collections.shuffle(questions);

        for (int i = 0; i < players.size(); i++) {
            TextView playerLabel = findViewById(R.id.player_label);
            playerLabel.setText(getString(R.string.player_turn, i+1));

            TextView questionLabel = findViewById(R.id.question_label);
            questionLabel.setText(getString(questions, i+1));

            questions.a
        }
    }
}