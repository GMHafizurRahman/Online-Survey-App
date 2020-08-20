package com.example.assessmenttest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assessmenttest.model.PhoneClass;

public class StartSurveyActivity extends AppCompatActivity {

    PhoneClass phoneClass;
    DatabaseHelperClass databaseHelperClass;
    private Button button;
    private TextView userName, userPassword, headingName;
    private String name, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_survey);

        userName = findViewById(R.id.textViewUserName);
        userPassword = findViewById(R.id.textViewIdForPassword);
        headingName = findViewById(R.id.textViewIdForName);
        button = findViewById(R.id.startButtonSurveyId);

        name = getIntent().getExtras().get("name").toString();
        password = getIntent().getExtras().get("phone").toString();

        headingName.setText(name);
        userName.setText(name.replace(" ", "").toLowerCase() + 123);
        userPassword.setText(password);

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(getApplicationContext(), SurveyQuestion1.class);
//                startActivity(intent);
//            }
//        });

        //DataBase Helper class start
        databaseHelperClass = new DatabaseHelperClass(this);
    }

    public void dialog1(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setCancelable(false);

        dialog.setTitle("Your Opinion in 3 minutes ");

        dialog.setMessage("By answering this 3 minutes survey, you help us improve our service even better for you.");

        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //personalDataDatabaseHelper.getPhone(password);
                Counter.start();
                phoneClass.phone = password;

                Log.d("tttt",phoneClass.phone);

                Intent intent = new Intent(getApplicationContext(), SurveyQuestion1.class);
                startActivity(intent);

            }

        });
        dialog.show();

    }
}