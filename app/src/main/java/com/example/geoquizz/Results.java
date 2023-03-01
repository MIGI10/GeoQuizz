package com.example.geoquizz;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);

        String aux;

        TextView winnerLabel = findViewById(R.id.winnerLabel);
        TextView message = findViewById(R.id.textView3);
        TextView results = findViewById(R.id.results);

        winnerLabel.setTextSize(40);
        message.setTextSize(25);
        results.setTextSize(25);

        sortPlayersByScore();

        aux = MainActivity.players.get(0).getUsername() + " is the winner!!!";
        winnerLabel.setText(aux);
        message.setText(R.string.final_results);

        StringBuilder finalScores = new StringBuilder();

        for (int k=0; k< MainActivity.players.size(); k++){

            finalScores.append(MainActivity.players.get(k).getUsername());
            finalScores.append(": ").append(MainActivity.players.get(k).getScore()).append("/10\n");
        }
        results.setText(finalScores.toString());
    }

    private void sortPlayersByScore(){

        boolean inOrder = false;

        while (!inOrder) {

            inOrder = true;
            for (int k = 1; k < MainActivity.players.size(); k++) {

                if (MainActivity.players.get(k - 1).getScore() < MainActivity.players.get(k).getScore()) {

                    MainActivity.players.get(k - 1).swapPlayers(MainActivity.players.get(k));
                    inOrder = false;
                }
            }
        }
    }
}
