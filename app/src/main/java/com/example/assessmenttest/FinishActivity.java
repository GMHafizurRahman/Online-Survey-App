package com.example.assessmenttest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinishActivity extends AppCompatActivity {

    DatabaseHelperClass databaseHelperClass;

    private TextView textView1, textView2, textView3, textView4, textView5;
    private String question_1, question_2, question_3, question_4, question_5;
    private Button takeAnotherTestBtn, displayButton, exitBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);


        //DataBase Helper class start
        databaseHelperClass = new DatabaseHelperClass(this);

        textView1 = findViewById(R.id.question1_answer);
        textView2 = findViewById(R.id.question2_answer);
        textView3 = findViewById(R.id.question3_answer);
        textView4 = findViewById(R.id.question4_answer);
        textView5 = findViewById(R.id.question5_answer);

        //data passing receive
        question_1 = getIntent().getExtras().get("question_1").toString();
        question_2 = getIntent().getExtras().get("question_2").toString();
        question_3 = getIntent().getExtras().get("question_3").toString();
        question_4 = getIntent().getExtras().get("question_4").toString();
        question_5 = getIntent().getExtras().get("question_5").toString();

        //set answer
        textView1.setText("Question 1 : " + question_1);
        textView2.setText("Question 2 : " + question_2);
        textView3.setText("Question 3 : " + question_3);
        textView4.setText("Question 4 : " + question_4);
        textView5.setText("Question 5 : " + question_5);

        //------------------------------------

        takeAnotherTestBtn = findViewById(R.id.anotherTestId);
        displayButton = findViewById(R.id.viewAllDataProvidedId);
        exitBtn = findViewById(R.id.exitId);

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // finish();
                System.exit(0);
            }
        });
//        takeAnotherTestBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(FinishActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });

        displayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (view.getId() == R.id.viewAllDataProvidedId) {
                    Cursor resultSet = databaseHelperClass.displayUsersPersonalInfo();

                    if (resultSet.getCount() == 0) {
                        //there is no data
                        showData("Error", " No Data Found");
                        return;
                    }
                    StringBuffer stringBuffer = new StringBuffer();

                    while (resultSet.moveToNext()) {
                        stringBuffer.append("How are you? : " + resultSet.getString(0) + "\n");
                        stringBuffer.append("Do you like our product ?: " + resultSet.getString(1) + "\n");
                        stringBuffer.append("Your contact number ?: " + resultSet.getString(2) + "\n");
                        stringBuffer.append("How frequent you use our product ?: " + resultSet.getString(3) + "\n");
                        stringBuffer.append("Where do you live ? : " + resultSet.getString(4) + "\n");
                    }
                    showData("ResultSet", stringBuffer.toString());

                }

            }
        });


    }

    public void showData(String title, String data) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(data);
        builder.setCancelable(true);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }
    public void dialog1(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setCancelable(false);

        dialog.setTitle("You are taking another survey!");

        dialog.setMessage("Please use new phone number, we have some limitations. But promise, we will do better service next time. Thanks again");

        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(FinishActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

        });
        dialog.show();

    }
}