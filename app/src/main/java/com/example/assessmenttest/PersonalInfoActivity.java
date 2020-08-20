package com.example.assessmenttest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class PersonalInfoActivity extends AppCompatActivity {

    DatabaseHelperClass databaseHelperClass;

    private Button PersonalBtn;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private String radioValue, checkBoxValue, numberEditTextValue, dropDownValue, cityValue, numberEditTextValueForIntend, nameEditTextValueForIntend;

    private CheckBox checkBox1, checkBox2;
    private EditText editTextNumber, editTextLivingCity;
    private Spinner spinner;
    private ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        PersonalBtn = findViewById(R.id.personal_question_nextButton_Id);
        radioGroup = findViewById(R.id.radio_group_1);

        checkBox1 = findViewById(R.id.personal_check_box_1);
        checkBox2 = findViewById(R.id.personal_check_box_2);

        editTextNumber = findViewById(R.id.contact_numberEditText_Id);
        editTextLivingCity = findViewById(R.id.living_placeEditText_Id);
        spinner = findViewById(R.id.spinner_id);

        //getData by putExtras from Main Class
        numberEditTextValueForIntend = getIntent().getExtras().get("phone").toString();
        editTextNumber.setText(numberEditTextValueForIntend);

        nameEditTextValueForIntend = getIntent().getExtras().get("name").toString();

        //DataBase Helper class start
        databaseHelperClass = new DatabaseHelperClass(this);


        loadingBar = new ProgressDialog(this);

        PersonalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Radio Button
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(selectedId);
                radioValue = radioButton.getText().toString();

                // Check Box
                if (checkBox1.isChecked() && checkBox2.isChecked()) {
                    checkBoxValue = checkBox1.getText().toString() + ", " + checkBox2.getText().toString();

                } else if (checkBox1.isChecked()) {
                    checkBoxValue = checkBox1.getText().toString();

                } else if (checkBox2.isChecked()) {
                    checkBoxValue = checkBox2.getText().toString();

                }

                //number get from edit text
                numberEditTextValue = editTextNumber.getText().toString();


                //city get edit text
                cityValue = editTextLivingCity.getText().toString();

                //spinner
                dropDownValue = spinner.getSelectedItem().toString();


                //validity check and Reserve data
                if (TextUtils.isEmpty(radioValue)) {
                    Toast.makeText(PersonalInfoActivity.this, "Please check radio button", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(checkBoxValue)) {
                    Toast.makeText(PersonalInfoActivity.this, "Please check the field", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(numberEditTextValue)) {
                    Toast.makeText(PersonalInfoActivity.this, "Please check the field", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(cityValue)) {
                    Toast.makeText(PersonalInfoActivity.this, "Please check the field", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(dropDownValue)) {
                    Toast.makeText(PersonalInfoActivity.this, "Please check the field", Toast.LENGTH_SHORT).show();
                } else {
//                    Toast.makeText(PersonalInfoActivity.this, "all is ok", Toast.LENGTH_SHORT).show();

                    loadingBar.setTitle("Starting Survey");
                    loadingBar.setMessage("Please wait, we are getting ready for survey.");
                    loadingBar.setCanceledOnTouchOutside(false);
                    loadingBar.show();


                    //--------------------------------
                    long rowId = databaseHelperClass.insertPersonalData(radioValue, checkBoxValue, numberEditTextValue, dropDownValue, cityValue);
//                    long rowId = databaseHelperClass.insertData("name22", "phone22");
                    if (rowId == -1) {
                        loadingBar.dismiss();
                        Toast.makeText(getApplicationContext(), " Not inserted. ", Toast.LENGTH_LONG).show();
                    } else {
                       // Toast.makeText(getApplicationContext(), "Row :" + rowId + " is successfully inserted. ", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getApplicationContext(), StartSurveyActivity.class);
                        intent.putExtra("phone", numberEditTextValueForIntend);
                        intent.putExtra("name", nameEditTextValueForIntend);
                        startActivity(intent);
                        loadingBar.dismiss();

                    }

                }

            }
        });
    }
}