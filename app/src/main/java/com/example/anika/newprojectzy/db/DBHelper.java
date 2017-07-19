package com.example.anika.newprojectzy.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mobi app on 18.07.2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "mycompany";
    public static final String TABLE_ADV = "company";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //      ADVERTISING
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME_A = "_name";
    public static final String KEY_DESCRIPTION_A = "_description";
    public static final String KEY_FIO_A = "_fio";
    public static final String KEY_EMAIL_A = "_email";
    public static final String KEY_PHONE_A = "_phone";
    public static final String KEY_IMG_A = "_img";
    public static final String KEY_DATE_A = "_date";
    public static final String KEY_TOWN_A = "_town";
    //**********************************************************************************

    //      VACANSY
    public static final String KEY_ID_V = "_id";
    public static final String KEY_NAME_V = "_name";
    public static final String KEY_DESCRIPTION_V = "_description";
    public static final String KEY_FIO_V = "_fio";
    public static final String KEY_EMAIL_V = "_email";
    public static final String KEY_PHONE_V = "_phone";
    public static final String KEY_IMG_V = "_img";
    public static final String KEY_DATE_V = "_date";
    public static final String KEY_TOWN_V = "_town";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_ADV + "(" + KEY_ID
                + " integer primary key,"
                + KEY_NAME_A + " text,"
                + KEY_DESCRIPTION_A + " text,"
                + KEY_FIO_A + " text,"
                + KEY_EMAIL_A + " text,"
                + KEY_PHONE_A + " text,"
                + KEY_IMG_A + " blob,"
                + KEY_DATE_A + " text,"
                + KEY_TOWN_A + " text"
                +");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_ADV);
        onCreate(db);
    }


}
