package com.example.anika.newprojectzy.advokat;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.anika.newprojectzy.R;

public class FullActivity extends AppCompatActivity {


    int[] arr = new int[]{R.drawable.book,R.drawable.book1,R.drawable.book2,R.drawable.book3,R.drawable.book4,
            R.drawable.book5,R.drawable.book6,R.drawable.book7,R.drawable.book8,R.drawable.book9};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent intent = getIntent();
        String s = intent.getStringExtra("i");
        int i = Integer.parseInt(s);

        ImageView im = (ImageView) findViewById(R.id.imageView30);
        im.setImageDrawable(getResources().getDrawable(arr[i]));
        //im.setBackgroundResource(R.drawable.book);

    }

}
