package com.example.anika.newprojectzy.addAdvertising;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.anika.newprojectzy.R;

public class AddAdvertisingActivity extends AppCompatActivity implements View.OnClickListener {

    RadioButton rb1, rb2, rb3, rb4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_advertising);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Каталог Беларуси");
        toolbar.setSubtitle("Добавить объявление");


        rb1 = (RadioButton) findViewById(R.id.radioButtonOrg);
        rb2 = (RadioButton) findViewById(R.id.radioButtonAdv);
        rb3 = (RadioButton) findViewById(R.id.radioButtonVac);
        rb4 = (RadioButton) findViewById(R.id.radioButtonRes);
        rb1.setOnClickListener(this);
        rb2.setOnClickListener(this);
        rb3.setOnClickListener(this);
        rb4.setOnClickListener(this);

        Fragment fragment = new FragmentAddOrganizations(this);

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                // do what you want to be done on home button click event
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onClick(View view) {
        Fragment fragment = null;
        rb1.setChecked(false);
        rb2.setChecked(false);
        rb3.setChecked(false);
        rb4.setChecked(false);
        switch (view.getId()){
            case R.id.radioButtonOrg:
                rb1.setChecked(true);
                fragment = new FragmentAddOrganizations(this);
                break;
            case R.id.radioButtonAdv:
                rb2.setChecked(true);
                fragment = new FragmentAddAdvertising(this);
                break;
            case R.id.radioButtonVac:
                rb3.setChecked(true);
                fragment = new FragmentAddVacansy(this);
                break;
            case R.id.radioButtonRes:
                fragment = new FragmentAddResume(this);
                rb4.setChecked(true);
                break;
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    public void toastSucsefull(){
        Toast.makeText(this,"Ваша заявка отправлена на модерацию!",Toast.LENGTH_SHORT).show();
    }
    public void toastError(){
        Toast.makeText(this,"Проверьте подключение к интернету...",Toast.LENGTH_SHORT).show();
    }
}
