package com.example.assessmenttest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.assessmenttest.model.PhoneClass;

public class DatabaseHelperClass extends SQLiteOpenHelper {


    private static final int VERSION_NUMBER = 1;
    private static final String DATABASE_NAME = "surveyClients.db";

    // USERS_DETAILS COLUMNS
    private static final String USER_NAME = "Name";
    private static final String USER_PHONE = "Phone";

    // USERS_PERSONAL_INFO COLUMNS
    private static final String PERSONALINFO_1 = "How_are_you";
    private static final String PERSONALINFO_2 = "Do_you_like_our_product";
    private static final String PERSONALINFO_3 = "Your_contact_number";
    private static final String PERSONALINFO_4 = "How_frequent_you_use_our_product";
    private static final String PERSONALINFO_5 = "Where_do_you_live";


// TABLE USERS_DETAILS

    private static final String TABLE_NAME_USERS_DETAILS = "USERS_DETAILS";
    private static final String CREATE_TABLE_USERS_DETAILS = "CREATE TABLE USERS_DETAILS (Name VARCHAR(60),Phone  PRIMARY KEY );";
    private static final String DROP_TABLE_USERS_DETAILS = " DROP TABLE IF EXISTS USERS_DETAILS";
    private static final String DISPLAY_TABLE_USERS_DETAILS = " SELECT * FROM USERS_DETAILS";

    // TABLE USERS_PERSONAL_INFO
    private static final String TABLE_NAME_USERS_PERSONAL_INFO = "USERS_PERSONAL_INFO";
    private static final String CREATE_TABLE_USERS_PERSONAL_INFO = "CREATE TABLE USERS_PERSONAL_INFO(How_are_you VARCHAR(100),Do_you_like_our_product VARCHAR(69), Your_contact_number VARCHAR(69) PRIMARY KEY,How_frequent_you_use_our_product VARCHAR(20) ,Where_do_you_live VARCHAR(20));";
    private static final String DROP_TABLE_USERS_PERSONAL_INFO = " DROP TABLE IF EXISTS USERS_PERSONAL_INFO";
    private static final String DISPLAY_TABLE_USERS_PERSONAL_INFO = " SELECT * FROM USERS_PERSONAL_INFO ";


    private Context context;
    PhoneClass phoneClass;

    public DatabaseHelperClass(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE_USERS_DETAILS);
            db.execSQL(CREATE_TABLE_USERS_PERSONAL_INFO);
        } catch (Exception e) {
            //  Log.d("db error","DatabaseHelperClass Exception 1");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            Toast.makeText(context, "onUpgrade is called ", Toast.LENGTH_LONG).show();
            db.execSQL(DROP_TABLE_USERS_DETAILS);
            db.execSQL(DROP_TABLE_USERS_PERSONAL_INFO);

            onCreate(db);

        } catch (Exception e) {
            //  Log.e("db error","DatabaseHelperClass Exception 2");

        }
    }

    public long insertUserDetails(String name, String phone) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME, name);
        contentValues.put(USER_PHONE, phone);
        long rowId = sqLiteDatabase.insert(TABLE_NAME_USERS_DETAILS, null, contentValues);
        return rowId;
    }


    public long insertPersonalData(String INFO_1, String INFO_2, String INFO_3, String INFO_4, String INFO_5) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(PERSONALINFO_1, INFO_1);
        contentValues.put(PERSONALINFO_2, INFO_2);
        contentValues.put(PERSONALINFO_3, INFO_3);
        contentValues.put(PERSONALINFO_4, INFO_4);
        contentValues.put(PERSONALINFO_5, INFO_5);
        long rowId = -1;
        try {
            rowId = sqLiteDatabase.insert(TABLE_NAME_USERS_PERSONAL_INFO, null, contentValues);
        } catch (Exception ex) {
            // Log.d("db error","PersonalDataDatabaseHelper exception 3 ");
            ex.printStackTrace();
        }
        return rowId;
    }


    public Cursor displayUserDetails() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(DISPLAY_TABLE_USERS_DETAILS, null);
        return cursor;
    }

    public Cursor displayUsersPersonalInfo() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Log.d("tttt_D_Info",PhoneClass.phone);
        Cursor cursor = sqLiteDatabase.rawQuery(DISPLAY_TABLE_USERS_PERSONAL_INFO + " WHERE " + PERSONALINFO_3 + " = '" + phoneClass.phone +"';",null);
        return cursor;
    }

}
