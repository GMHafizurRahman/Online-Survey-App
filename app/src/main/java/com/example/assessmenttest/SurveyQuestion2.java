package com.example.assessmenttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SurveyQuestion2 extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button button;
    private String question_2_Value, question_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_question2);

        Counter.setTextView((TextView) findViewById(R.id.counter_Id2));

        button = findViewById(R.id.question_2_nextButton_Id);
        radioGroup = findViewById(R.id.radioGroupQuestion_2_Id);

        question_1 = getIntent().getExtras().get("question_1").toString();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(selectedId);
                question_2_Value = radioButton.getText().toString();

                if (TextUtils.isEmpty(question_2_Value)) {
                    Toast.makeText(SurveyQuestion2.this, "Please check your answer.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), SurveyQuestion3.class);
                    intent.putExtra("question_1", question_1);
                    intent.putExtra("question_2", question_2_Value);
                    startActivity(intent);
                }

                //Toast.makeText(SurveyQuestion2.this, "" + question_2_Value + question_1, Toast.LENGTH_SHORT).show();


            }
        });

    }
}