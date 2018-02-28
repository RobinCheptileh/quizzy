package com.cognition.android.quizzy;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private AppCompatImageView imgFlower;
    private AppCompatTextView txtQuestion;
    private AppCompatRadioButton rdoFirstChoice;
    private AppCompatRadioButton rdoSecondChoice;
    private AppCompatRadioButton rdoThirdChoice;
    private AppCompatButton btnSubmit;

    private List<Question> questionList;
    private int currentQuestion;
    private int correctAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgFlower = findViewById(R.id.imgFlower);
        txtQuestion = findViewById(R.id.txtQuestion);
        rdoFirstChoice = findViewById(R.id.rdoFirstChoice);
        rdoSecondChoice = findViewById(R.id.rdoSecondChoice);
        rdoThirdChoice = findViewById(R.id.rdoThirdChoice);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getSelectedChoice().equals(questionList.get(currentQuestion).getmChoices()[questionList.get(currentQuestion).getmAnswer()])) {
                    correctAnswers++;
                    Snackbar.make(findViewById(R.id.lytParent), "Correct!", Snackbar.LENGTH_SHORT).show();
                }
                currentQuestion++;
                if (currentQuestion < questionList.size()) {
                    setQuestion(questionList.get(currentQuestion));
                } else
                    showResults();
            }
        });

        questionList = new ArrayList<>();
        questionList.add(new Question(R.drawable.almond, "Name the flower", new String[]{"Almond", "Anthurium", "Aster"}, 0));
        questionList.add(new Question(R.drawable.alstroemeria, "Name the flower", new String[]{"Anthurium", "Alstroemeria", "Anemone"}, 1));
        questionList.add(new Question(R.drawable.anemone, "Name the flower", new String[]{"Aster", "Alstroemeria", "Anemone"}, 2));
        questionList.add(new Question(R.drawable.anthurium, "Name the flower", new String[]{"Anthurium", "Anemone", "Aster"}, 0));
        questionList.add(new Question(R.drawable.aster, "Name the flower", new String[]{"Anemone", "Aster", "Alstroemeria"}, 1));

        currentQuestion = 0;
        correctAnswers = 0;
        setQuestion(questionList.get(currentQuestion));
    }

    private void setQuestion(Question question) {
        imgFlower.setImageResource(question.getmImage());
        txtQuestion.setText(question.getmQuestion());
        rdoFirstChoice.setChecked(true);
        rdoFirstChoice.setText(question.getmChoices()[0]);
        rdoSecondChoice.setText(question.getmChoices()[1]);
        rdoThirdChoice.setText(question.getmChoices()[2]);
    }

    private String getSelectedChoice() {
        String choice;

        if (rdoFirstChoice.isChecked()) choice = rdoFirstChoice.getText().toString();
        else if (rdoSecondChoice.isChecked()) choice = rdoSecondChoice.getText().toString();
        else if (rdoThirdChoice.isChecked()) choice = rdoThirdChoice.getText().toString();
        else choice = rdoFirstChoice.getText().toString();

        return choice;
    }

    private void showResults() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setCancelable(false)
                .setMessage(String.format(Locale.ENGLISH, "Congratulations! You got %d out of %d correct.", correctAnswers, questionList.size()))
                .setTitle("You're Done!")
                .setIcon(R.mipmap.ic_launcher);

        builder.setNegativeButton("Restart", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                currentQuestion = 0;
                correctAnswers = 0;
                setQuestion(questionList.get(currentQuestion));

                dialog.cancel();
            }
        });

        builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
                ActivityCompat.finishAffinity(MainActivity.this);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
