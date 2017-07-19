package com.example.anika.newprojectzy.favorite;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.anika.newprojectzy.R;
import com.example.anika.newprojectzy.db.DBHelper;
import com.example.anika.newprojectzy.favorite.adapter.AdvertisingAdapterFavorite;
import com.example.anika.newprojectzy.favorite.classes.AdvertisingObjectFavorite;

import org.apache.commons.codec.binary.Base64;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity {

    DBHelper dbHelper;
    SQLiteDatabase database;
    RecyclerView recyclerView;
    AdvertisingAdapterFavorite adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();

        setRecyclerView(readDB());

    }

    private void setRecyclerView(List<AdvertisingObjectFavorite> lists){

        recyclerView = (RecyclerView) findViewById(R.id.recyclerFavorite);
        final LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
        adapter = new AdvertisingAdapterFavorite(recyclerView,this,lists,this);
        recyclerView.setAdapter(adapter);

    }

    public List<AdvertisingObjectFavorite> readDB(){
        boolean isDownload = false;

        List<AdvertisingObjectFavorite> organizationList = new ArrayList<>();

        try {
            Cursor c = database.query(DBHelper.TABLE_ADV, null, null,null, null, null, null, null);
            c.moveToFirst();
            while (!c.isAfterLast()) {

                int _id = c.getColumnIndex("_id");
                int _name = c.getColumnIndex("_name");
                int _descr = c.getColumnIndex("_description");
                int _fio = c.getColumnIndex("_fio");
                int _email = c.getColumnIndex("_email");
                int _phone = c.getColumnIndex("_phone");
                int _img = c.getColumnIndex("_img");
                int _date = c.getColumnIndex("_date");
                int _town = c.getColumnIndex("_town");

                String name = c.getString(_name);
                String descr = c.getString(_descr);
                String fio = c.getString(_fio);
                String email = c.getString(_email);
                String phone = c.getString(_phone);
                String date = c.getString(_date);
                String town = c.getString(_town);
                byte[] byteImgLogo = c.getBlob(_img);
                String base64 = new String(Base64.encodeBase64(byteImgLogo));

                AdvertisingObjectFavorite objectFavorite = new AdvertisingObjectFavorite();
                objectFavorite.setTitle(new ObservableField<String>(name));
                objectFavorite.setTown(new ObservableField<String>(town));
                objectFavorite.setDate(new ObservableField<String>(date));
                objectFavorite.setImg(new ObservableField<String>(base64));

                organizationList.add(objectFavorite);
                c.moveToNext();
                //cursor.close();
            }

            c.close();
            //dbHelper.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return organizationList;
    }


}
