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
    public static final String TABLE_V = "vacansy";
    public static final String TABLE_R = "resume";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //      ADVERTISING
    public static final String KEY_ID = "_id";
    public static final String KEY_PID_A = "_pid";
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
    public static final String KEY_DOLZHNOST_V = "_dolzhnost";
    public static final String KEY_TREBOVANIYA_V = "_trebovaniya";
    public static final String KEY_ALLDESCR_V = "_alldescr";
    public static final String KEY_UNP_V = "_unp";
    public static final String KEY_PHONE_V = "_phone";
    public static final String KEY_DESCRIPTION_V = "_description";
    public static final String KEY_DATE_V = "_date";
    public static final String KEY_TOWN_V = "_town";

    //--------------------------------------------------------

    //      RESUME

    public static final String KEY_ID_R = "_id";
    public static final String KEY_FIO_R = "_fio";
    public static final String KEY_SPECIALITY_R = "_speciality";
    public static final String KEY_OPIT_R = "_opit";
    public static final String KEY_EDUCATION_R = "_education";
    public static final String KEY_SKILLS_R = "_skills";
    public static final String KEY_PHONE_R = "_phone";
    public static final String KEY_IMG_R = "_img";
    public static final String KEY_DATE_R = "_date";
    public static final String KEY_DESCRIPTION_R = "_description";
    //public static final String KEY_TOWN_R = "_town";

    //-----------------------------------------------------------



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_ADV + "(" + KEY_ID
                + " text,"
                + KEY_PID_A + " text,"
                + KEY_NAME_A + " text,"
                + KEY_DESCRIPTION_A + " text,"
                + KEY_FIO_A + " text,"
                + KEY_EMAIL_A + " text,"
                + KEY_PHONE_A + " text,"
                + KEY_IMG_A + " blob,"
                + KEY_DATE_A + " text,"
                + KEY_TOWN_A + " text"
                +");");

        db.execSQL("create table " + TABLE_V + "(" + KEY_ID_V
                + " text,"
                + KEY_NAME_V + " text,"
                + KEY_DOLZHNOST_V + " text,"
                + KEY_TREBOVANIYA_V + " text,"
                + KEY_ALLDESCR_V + " text,"
                + KEY_UNP_V + " text,"
                + KEY_PHONE_V + " text,"
                + KEY_DESCRIPTION_V + " text,"
                + KEY_DATE_V + " text,"
                + KEY_TOWN_V + " text"
                +");");

        db.execSQL("create table " + TABLE_R + "(" + KEY_ID_R
                + " text,"
                + KEY_FIO_R + " text,"
                + KEY_SPECIALITY_R + " text,"
                + KEY_OPIT_R + " text,"
                + KEY_EDUCATION_R + " text,"
                + KEY_SKILLS_R + " text,"
                + KEY_PHONE_R + " text,"
                + KEY_IMG_R + " blob,"
                + KEY_DATE_R + " text,"
                + KEY_DESCRIPTION_R + " text"
               // + KEY_TOWN_R + " text"
                +");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_ADV);
        db.execSQL("drop table if exists " + TABLE_V);
        db.execSQL("drop table if exists " + TABLE_R);
        onCreate(db);
    }


}
