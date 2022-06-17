package com.example.video;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class QuizActivity extends AppCompatActivity {

    private Timer quizTimer;
    private TextView questions;
    private TextView question;
    private AppCompatButton option1, option2, option3, option4;
    private  AppCompatButton nextBtn;
    private int totalTimeInMins = 1;
    private int seconds =0;
    private final List<QuestionList> questionList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        final TextView timer = findViewById(R.id.timer);

        questions = findViewById(R.id.questions);
        question = findViewById(R.id.question);

        option1 = findViewById(R.id.opçao1);
        option2 = findViewById(R.id.opçao2);
        option3 = findViewById(R.id.opçao3);
        option4 = findViewById(R.id.opçao4);

        nextBtn = findViewById(R.id.nextBtn);
        final String getselectedTopName = getIntent().getStringExtra("selectedTopic");

        startTimer(timer);

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void startTimer(TextView timerTextView){
        quizTimer = new Timer();
        quizTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                if (seconds ==0){
                totalTimeInMins--;
                seconds = 59;
            }
                else if (seconds == 0 && totalTimeInMins == 0){
                    quizTimer.purge();
                    quizTimer.cancel();
                    Toast.makeText(QuizActivity.this, "Tempo Acabou", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(QuizActivity.this, QuizResult.class);
                    intent.putExtra("correct",getCorrectAnswers());
                    intent.putExtra("incorreto",getInCorrectAnswers());
                    startActivity(intent);

                    finish();
                }
                else{
                    seconds--;
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        String finalMinutes = String.valueOf(totalTimeInMins);
                        String finalSeconds = String.valueOf(seconds);

                        if (finalMinutes.length() ==1){
                            finalMinutes = "0" +finalMinutes;
                        }
                        if(finalSeconds.length() == 1){
                            finalSeconds = "0"+finalSeconds;
                        }

                        timerTextView.setText(finalMinutes +":"+finalSeconds);
                    }
                });
            }

        },1000, 1000);

    }
    private int getCorrectAnswers(){

        int correctAnswers = 0;
        for (int i=0;i<questionList.size();i++){
            final String getUserSelectedAnswer = questionList.get(i).getUserSelectedAnswer();
            final String getAnswer = questionList.get(i).getAnswer();
            if (getUserSelectedAnswer.equals(getAnswer)){
                correctAnswers++;
            }
        }
        return correctAnswers;
    }
    private int getInCorrectAnswers(){

        int IncorrectAnswers = 0;
        for (int i=0;i<questionList.size();i++){
            final String getUserSelectedAnswer = questionList.get(i).getUserSelectedAnswer();
            final String getAnswer = questionList.get(i).getAnswer();
            if (getUserSelectedAnswer.equals(getAnswer)){
                IncorrectAnswers++;
            }
        }
        return IncorrectAnswers;
    }

    @Override
    public void onBackPressed() {
        quizTimer.purge();
        quizTimer.cancel();

        startActivity(new Intent(QuizActivity.this, cards.class));
        finish();
    }
}
