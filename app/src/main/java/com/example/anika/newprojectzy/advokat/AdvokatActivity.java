package com.example.anika.newprojectzy.advokat;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.anika.newprojectzy.R;
import com.tdscientist.shelfview.BookModel;
import com.tdscientist.shelfview.ShelfView;

import java.util.ArrayList;

public class AdvokatActivity extends AppCompatActivity implements ShelfView.BookClickListener {

    ArrayList<BookModel> models = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advokat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ShelfView shelfView = (ShelfView) findViewById(R.id.shelfView);
        shelfView.setOnBookClicked(this);


        models.add(new BookModel("book", "1", "book"));
        models.add(new BookModel("book1", "2", "book"));
        models.add(new BookModel("book2", "3", "book"));
        models.add(new BookModel("book3", "4", "book"));
        models.add(new BookModel("book4", "5", "book"));
        models.add(new BookModel("book5", "6", "book"));
        models.add(new BookModel("book6", "7", "book"));
        models.add(new BookModel("book7", "8", "book"));
        models.add(new BookModel("book8", "9", "book"));
        models.add(new BookModel("book9", "10", "book"));

        shelfView.loadData(models, ShelfView.BOOK_SOURCE_DRAWABLE_FOLDER);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onBookClicked(int i, String s, String s1) {

        Intent intent = new Intent(AdvokatActivity.this, FullActivity.class);
        intent.putExtra("i",String.valueOf(i));
        startActivity(intent);
    }
}
