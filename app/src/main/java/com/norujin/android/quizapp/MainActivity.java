package com.norujin.android.quizapp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView start;
    private Button tryAgain, answerButton1, answerButton2,answerButton3,answerButton4;
    private TextView time, rate, question, result;
    private GridLayout gridLayout;
    private ArrayList<Integer> answers = new ArrayList<>();
    int locationOfRightAnswer;
    int score;
    int totalQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (ImageView) findViewById(R.id.startBtnId);
        tryAgain = (Button) findViewById(R.id.tryAgainBtnId);
        answerButton1 = (Button) findViewById(R.id.answer1BtnId);
        answerButton2 = (Button) findViewById(R.id.answer2BtnId);
        answerButton3 = (Button) findViewById(R.id.answer3BtnId);
        answerButton4 = (Button) findViewById(R.id.answer4BtnId);

        time = (TextView) findViewById(R.id.timeTextViewId);
        rate = (TextView) findViewById(R.id.rateTextViewId);
        question = (TextView) findViewById(R.id.questionTextViewId);
        result = (TextView) findViewById(R.id.resultTextViewId);
        tryAgain = (Button) findViewById(R.id.tryAgainBtnId);

        gridLayout = (GridLayout) findViewById(R.id.answersGridLayoutId);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                start.setVisibility(View.INVISIBLE);

                time.setVisibility(View.VISIBLE);
                rate.setVisibility(View.VISIBLE);
                question.setVisibility(View.VISIBLE);
                gridLayout.setVisibility(View.VISIBLE);
                result.setVisibility(View.VISIBLE);
                timeCountdown();
            }
        });

        newQuestion();

        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MainActivity.class));
                finish();

            }
        });


        }

    public void choseAnswer(View view){

        if (Integer.toString(locationOfRightAnswer).equals(view.getTag())){

            result.setText("correct!!");
            score++;

        }

        else {

            result.setText("wrong!!");

        }
        totalQuestions++;

        rate.setText(Integer.toString(score)+"/"+Integer.toString(totalQuestions));

        answers.clear();

        newQuestion();

    }

    public void newQuestion (){

        Random random = new Random();

        int a = random.nextInt(11);
        int b = random.nextInt(11);

        question.setText(Integer.toString(a) + "+" + Integer.toString(b));

        locationOfRightAnswer = random.nextInt(4);

        for (int i=0; i<4; i++){

            if (i == locationOfRightAnswer){

                answers.add(a+b);

            }

            else {

                int wrongAnswers = random.nextInt(21);

                while (wrongAnswers == a+b){

                    wrongAnswers = random.nextInt(21);

                }

                answers.add(wrongAnswers);

            }

        }

        answerButton1.setText(Integer.toString(answers.get(0)));
        answerButton2.setText(Integer.toString(answers.get(1)));
        answerButton3.setText(Integer.toString(answers.get(2)));
        answerButton4.setText(Integer.toString(answers.get(3)));




    }

    public void timeCountdown(){

        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long l) {
                time.setText(String.valueOf(l/1000) + "s");
            }

            @Override
            public void onFinish() {

                result.setText("Done!!");

                answerButton1.setClickable(false);
                answerButton2.setClickable(false);
                answerButton3.setClickable(false);
                answerButton4.setClickable(false);
                tryAgain.setVisibility(View.VISIBLE);

            }
        }.start();

    }

}
