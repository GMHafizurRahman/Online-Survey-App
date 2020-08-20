package com.example.assessmenttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class SurveyQuestion3 extends AppCompatActivity {

    private Button button;
    private String question_3_Value, question_1, question_2;

    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_question3);

        Counter.setTextView((TextView) findViewById(R.id.timeCounterId));

        button = findViewById(R.id.question_3_nextButton_Id);

        checkBox1 = findViewById(R.id.question_3_CheckBoxId_1);
        checkBox2 = findViewById(R.id.question_3_CheckBoxId_2);
        checkBox3 = findViewById(R.id.question_3_CheckBoxId_3);
        checkBox4 = findViewById(R.id.question_3_CheckBoxId_4);

        //data passing receive
        question_1 = getIntent().getExtras().get("question_1").toString();
        question_2 = getIntent().getExtras().get("question_2").toString();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkBox1.isChecked() && checkBox2.isChecked() && checkBox3.isChecked() && checkBox4.isChecked()) {
                    question_3_Value = checkBox1.getText().toString() + "," + checkBox2.getText().toString() + "," + checkBox3.getText().toString() + "," + checkBox4.getText().toString();

                } else if (checkBox1.isChecked() && checkBox2.isChecked() && checkBox3.isChecked()) {
                    question_3_Value = checkBox1.getText().toString() + "," + checkBox2.getText().toString() + "," + checkBox3.getText().toString();

                } else if (checkBox1.isChecked() && checkBox2.isChecked() && checkBox4.isChecked()) {
                    question_3_Value = checkBox1.getText().toString() + "," + checkBox2.getText().toString() + "," + checkBox4.getText().toString();

                } else if (checkBox1.isChecked() && checkBox3.isChecked() && checkBox4.isChecked()) {
                    question_3_Value = checkBox1.getText().toString() + "," + checkBox3.getText().toString() + "," + checkBox4.getText().toString();

                } else if (checkBox2.isChecked() && checkBox3.isChecked() && checkBox4.isChecked()) {
                    question_3_Value = checkBox2.getText().toString() + "," + checkBox3.getText().toString() + "," + checkBox4.getText().toString();

                } else if (checkBox1.isChecked() && checkBox2.isChecked()) {
                    question_3_Value = checkBox1.getText().toString() + "," + checkBox2.getText().toString();

                } else if (checkBox1.isChecked() && checkBox3.isChecked()) {
                    question_3_Value = checkBox1.getText().toString() + "," + checkBox3.getText().toString();

                } else if (checkBox1.isChecked() && checkBox4.isChecked()) {
                    question_3_Value = checkBox1.getText().toString() + "," + checkBox4.getText().toString();

                } else if (checkBox2.isChecked() && checkBox3.isChecked()) {
                    question_3_Value = checkBox2.getText().toString() + "," + checkBox3.getText().toString();

                } else if (checkBox2.isChecked() && checkBox4.isChecked()) {
                    question_3_Value = checkBox2.getText().toString() + "," + checkBox4.getText().toString();

                } else if (checkBox3.isChecked() && checkBox4.isChecked()) {
                    question_3_Value = checkBox3.getText().toString() + "," + checkBox4.getText().toString();

                } else if (checkBox1.isChecked()) {
                    question_3_Value = checkBox1.getText().toString();

                } else if (checkBox2.isChecked()) {
                    question_3_Value = checkBox2.getText().toString();

                } else if (checkBox3.isChecked()) {
                    question_3_Value = checkBox3.getText().toString();

                } else if (checkBox4.isChecked()) {
                    question_3_Value = checkBox4.getText().toString();

                } else if (TextUtils.isEmpty(question_3_Value)) {
                    Toast.makeText(SurveyQuestion3.this, "Please check your answer.", Toast.LENGTH_SHORT).show();
                }

                // Toast.makeText(SurveyQuestion3.this, ""   + question_1 + question_2 + question_3_Value, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), SurveyQuestion4.class);
                intent.putExtra("question_1", question_1);
                intent.putExtra("question_2", question_2);
                intent.putExtra("question_3", question_3_Value);
                startActivity(intent);

            }
        });

    }
}