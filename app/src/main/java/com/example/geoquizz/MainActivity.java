package com.example.geoquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    private String aux;

    private static final boolean[] ANSWERS =
            {true, false, true, false, false, false, false, true, true, true};
    public static final ArrayList<Player> players = new ArrayList<>();
    public static final ArrayList<Question> questions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_names);

        setQuestionsUp();

        Button addPlayerButton = findViewById(R.id.addPlayerButton);
        Button startButton = findViewById(R.id.saveAndStartButton);
        EditText editText = findViewById(R.id.textInputPlayer);
        TextView textView = findViewById(R.id.textView2);
        
        textView.setTextSize(25);

        addPlayerButton.setText(R.string.button_add);
        startButton.setText(R.string.button_sas);

        aux = "Enter player 1 name";
        textView.setText(aux);
        AtomicInteger playerNumber = new AtomicInteger(2);

        addPlayerButton.setOnClickListener(v -> {
            Player player = new Player(editText.getText().toString());
            players.add(player);
            aux = "Enter player " + playerNumber.getAndIncrement() + " name";
            textView.setText(aux);
            editText.setText("");
        });

        startButton.setOnClickListener(v -> {
            switchToRounds();
        });
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

    private void switchToRounds(){
        Intent switchToRounds = new Intent(this, Rounds.class);
        switchToRounds.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        switchToRounds.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(switchToRounds);
        overridePendingTransition (0, 0);
    }
}