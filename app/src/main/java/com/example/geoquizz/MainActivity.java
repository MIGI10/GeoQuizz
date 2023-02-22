package com.example.geoquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button trueButton, falseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}