package com.example.assessmenttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class SurveyQuestion1 extends AppCompatActivity {

    private Button button;
    private RatingBar ratingBar;
    private String ratingStorage, phone;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servay_question1);

        Counter.setTextView((TextView) findViewById(R.id.timeCountDownId_1));


        button = findViewById(R.id.question_1_nextButton_Id);
        ratingBar = findViewById(R.id.ratingBar_Id);
        textView = findViewById(R.id.timeCountDownId_1);

        //

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int noofstars = ratingBar.getNumStars();
                float getrating = ratingBar.getRating();
                ratingStorage = getrating + "/" + noofstars;

                if (TextUtils.isEmpty(ratingStorage)) {
                    Toast.makeText(SurveyQuestion1.this, "Please check your answer.", Toast.LENGTH_SHORT).show();
                }
                // Toast.makeText(SurveyQuestion1.this, ""+ ratingStorage ,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), SurveyQuestion2.class);
                intent.putExtra("question_1", ratingStorage);
                startActivity(intent);
            }
        });
    }

    public void CountDownTimer(long millisInFuture, long countDownInterval) {
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                textView.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                textView.setText("done!");
            }
        }.start();
    }
}