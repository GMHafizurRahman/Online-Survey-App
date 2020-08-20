package com.example.assessmenttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SurveyQuestion5 extends AppCompatActivity {

    private Button button;
    private String question_5_Value, question_1, question_2, question_3, question_4;
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_question5);

        Counter.setTextView((TextView) findViewById(R.id.timeCountDownId_5));

        button = findViewById(R.id.question_5_nextButton_Id);
        radioGroup = findViewById(R.id.radioGroupQuestion_5_Id);

        //data passing receive
        question_1 = getIntent().getExtras().get("question_1").toString();
        question_2 = getIntent().getExtras().get("question_2").toString();
        question_3 = getIntent().getExtras().get("question_3").toString();
        question_4 = getIntent().getExtras().get("question_4").toString();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(selectedId);
                question_5_Value = radioButton.getText().toString();

                //  Toast.makeText(SurveyQuestion5.this, "1="+question_1 +" \n 2="+question_2 + " \n 3="+question_3 + " \n 4="+question_4+ " \n 5="+question_5_Value, Toast.LENGTH_LONG).show();


                Intent intent = new Intent(SurveyQuestion5.this, FinishActivity.class);

                intent.putExtra("question_1", question_1);
                intent.putExtra("question_2", question_2);
                intent.putExtra("question_3", question_3);
                intent.putExtra("question_4", question_4);
                intent.putExtra("question_5", question_5_Value);

                startActivity(intent);
            }
        });

    }
}