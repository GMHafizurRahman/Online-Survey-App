package com.example.assessmenttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SurveyQuestion4 extends AppCompatActivity {

    private Button button;
    private String question_4_Value, question_1, question_2, question_3, question_4;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_question4);

        Counter.setTextView((TextView) findViewById(R.id.timeCountDownId_4));

        button = findViewById(R.id.question_4_nextButton_Id);

        //data passing receive
        question_1 = getIntent().getExtras().get("question_1").toString();
        question_2 = getIntent().getExtras().get("question_2").toString();
        question_3 = getIntent().getExtras().get("question_3").toString();

        spinner = findViewById(R.id.spinner_id_qsn_4);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                question_4_Value = spinner.getSelectedItem().toString();

                if (TextUtils.isEmpty(question_4_Value)) {
                    Toast.makeText(SurveyQuestion4.this, "Select an item", Toast.LENGTH_SHORT).show();
                } else {
                    // Toast.makeText(SurveyQuestion4.this, "1="+question_1 +"2="+question_2 + "3="+question_3 + "4="+question_4_Value, Toast.LENGTH_SHORT).show();


                    //data passing receive
                    Intent intent = new Intent(getApplicationContext(), SurveyQuestion5.class);

                    intent.putExtra("question_1", question_1);
                    intent.putExtra("question_2", question_2);
                    intent.putExtra("question_3", question_3);
                    intent.putExtra("question_4", question_4_Value);

                    startActivity(intent);
                }
            }
        });

    }
}