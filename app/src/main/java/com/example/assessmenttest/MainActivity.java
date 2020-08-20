package com.example.assessmenttest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelperClass databaseHelperClass;

    private EditText userName, userPhoneNumber;
    private Button submitButton, clearButton, exitButton;
    private String name, phone;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.inputEditTextName_Id);
        userPhoneNumber = findViewById(R.id.inputEditTextPhoneNumber_Id);

        submitButton = findViewById(R.id.personalQuerySubmitButton_Id);
        clearButton = findViewById(R.id.personalQueryClearButton_Id);
        exitButton = findViewById(R.id.personalQueryExitButton_ID);

        loadingBar = new ProgressDialog(this);


        //DataBase Helper class start
        databaseHelperClass = new DatabaseHelperClass(this);
//        SQLiteDatabase sqLiteDatabase = databaseHelperClass.getWritableDatabase();

        //DataBase Helper class end


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = userName.getText().toString();
                phone = userPhoneNumber.getText().toString();

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(MainActivity.this, "Please sir, Input Name......", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(MainActivity.this, "Please sir, Input phone Number......", Toast.LENGTH_SHORT).show();
                } else {
                    loadingBar.setTitle("Starting Survey");
                    loadingBar.setMessage("Please wait, we are getting ready for survey.");
                    loadingBar.setCanceledOnTouchOutside(true);
                    loadingBar.show();


                    //--------------------------------
                    long rowId = databaseHelperClass.insertUserDetails(name, phone);

                    if (rowId == -1) {

                        Toast.makeText(getApplicationContext(), "Phone Number Exist.Try new one.", Toast.LENGTH_LONG).show();
                    } else {

                        try {
                            databaseHelperClass.close();

                        } catch (Exception ex) {
                            //Log.d("tttt", "database close ex");
                            ex.printStackTrace();

                        }

                        //Toast.makeText(getApplicationContext(), "Row :" + rowId + " is successfully inserted. ", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getApplicationContext(), PersonalInfoActivity.class);
                        intent.putExtra("phone", phone);
                        intent.putExtra("name", name);
                        startActivity(intent);
                        loadingBar.dismiss();

                    }


                    //--------------------------------
                }
            }

        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName.setText("");
                userPhoneNumber.setText("");
            }
        });
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });

    }

}