package com.example.anika.newprojectzy;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.anika.newprojectzy.advertising.classes.InfoAdvertisingObject;
import com.example.anika.newprojectzy.advertising.classes.InfoResumeObject;
import com.example.anika.newprojectzy.advertising.classes.InfoVacansyObject;
import com.example.anika.newprojectzy.advertising.fragments.FragmentInfoAdvertising;
import com.example.anika.newprojectzy.advertising.fragments.FragmentInfoResume;
import com.example.anika.newprojectzy.advertising.fragments.FragmentInfoVacansy;
import com.example.anika.newprojectzy.db.DBHelper;
import com.example.anika.newprojectzy.db.Utilities;

public class AllInfoActivity extends AppCompatActivity {

    DBHelper dbHelper;
    SQLiteDatabase database;
    FragmentInfoAdvertising fragmentInfoAdvertising;
    FragmentInfoResume fragmentInfoResume;
    FragmentInfoVacansy fragmentInfoVacansy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();

        Intent intent = getIntent();
        String ids = intent.getStringExtra("ids");
        String pid = intent.getStringExtra("pid");
        String id = intent.getStringExtra("id");

        switch (ids){
            case "1":
                fragmentInfoAdvertising = new FragmentInfoAdvertising(this,Integer.parseInt(pid),Integer.parseInt(id) );
                if (fragmentInfoAdvertising != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, fragmentInfoAdvertising).commit();

                } else {
                    Log.e("MainActivity", "Error in creating fragment");
                }
                break;
            case "2":
                fragmentInfoResume = new FragmentInfoResume(this,Integer.parseInt(id));
                if (fragmentInfoResume != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, fragmentInfoResume).commit();

                } else {
                    Log.e("MainActivity", "Error in creating fragment");
                }
                break;
            case "3":
                fragmentInfoVacansy = new FragmentInfoVacansy(this, Integer.parseInt(id));
                if (fragmentInfoVacansy != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, fragmentInfoVacansy).commit();

                } else {
                    Log.e("MainActivity", "Error in creating fragment");
                }
                break;

        }


    }

    public void insertInToDBAdvertising(InfoAdvertisingObject object, Bitmap imgLogo){

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.KEY_NAME_A, object.getName().get());
        contentValues.put(DBHelper.KEY_DESCRIPTION_A, object.getDescription().get());
        contentValues.put(DBHelper.KEY_FIO_A, object.getDescription().get());
        contentValues.put(DBHelper.KEY_EMAIL_A, object.getEmail().get());
        contentValues.put(DBHelper.KEY_PHONE_A, object.getPhone().get());
        contentValues.put(DBHelper.KEY_IMG_A, Utilities.getBytes(imgLogo));
        contentValues.put(DBHelper.KEY_DATE_A, object.getDate().get());
        contentValues.put(DBHelper.KEY_TOWN_A, object.getTown().get());
        database.insert(DBHelper.TABLE_ADV, null, contentValues);
    }

    public void insertInToDBResume(InfoResumeObject object, Bitmap imgLogo){

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.KEY_NAME_A, object.getSpec().get());
        contentValues.put(DBHelper.KEY_DESCRIPTION_A, object.getDescription().get());
        contentValues.put(DBHelper.KEY_FIO_A, object.getDescription().get());
        contentValues.put(DBHelper.KEY_EMAIL_A, "");
        contentValues.put(DBHelper.KEY_PHONE_A, object.getPhone().get());
        contentValues.put(DBHelper.KEY_IMG_A, Utilities.getBytes(imgLogo));
        contentValues.put(DBHelper.KEY_DATE_A, object.getDate().get());
        contentValues.put(DBHelper.KEY_TOWN_A, "");
        database.insert(DBHelper.TABLE_ADV, null, contentValues);
    }

    public void insertInToDBVacansy(InfoVacansyObject object){
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DBHelper.KEY_NAME_A, object.getName().get());
            contentValues.put(DBHelper.KEY_DESCRIPTION_A, object.getDescription().get());
            contentValues.put(DBHelper.KEY_FIO_A, object.getDescription().get());
            contentValues.put(DBHelper.KEY_EMAIL_A, "");
            contentValues.put(DBHelper.KEY_PHONE_A, object.getPhone().get());
            contentValues.put(DBHelper.KEY_IMG_A, (byte) 'd');
            contentValues.put(DBHelper.KEY_DATE_A, object.getDate().get());
            contentValues.put(DBHelper.KEY_TOWN_A, object.getTown().get());
            database.insert(DBHelper.TABLE_ADV, null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_favorite:
                if(fragmentInfoAdvertising!=null) fragmentInfoAdvertising.favorite();
                else if (fragmentInfoVacansy!=null) fragmentInfoVacansy.favorite();
                else if(fragmentInfoResume!=null) fragmentInfoResume.favorite();
                break;

            default:
                break;
        }

        return true;
    }
}
