package com.example.anika.newprojectzy.favorite;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.anika.newprojectzy.AllInfoActivity;
import com.example.anika.newprojectzy.R;
import com.example.anika.newprojectzy.db.DBHelper;
import com.example.anika.newprojectzy.favorite.adapter.AdvertisingAdapterFavorite;
import com.example.anika.newprojectzy.favorite.adapter.VacansyAdapter;
import com.example.anika.newprojectzy.favorite.classes.AdvertisingObjectFavorite;
import com.example.anika.newprojectzy.favorite.classes.VacansyObjectFavorite;

import org.apache.commons.codec.binary.Base64;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity {

    DBHelper dbHelper;
    SQLiteDatabase database;
    RecyclerView recyclerView;
    AdvertisingAdapterFavorite adapter;
    VacansyAdapter adapterV;
    List<AdvertisingObjectFavorite> list = new ArrayList<>();
    List<VacansyObjectFavorite> listV = new ArrayList<>();

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
        list = lists;
        recyclerView = (RecyclerView) findViewById(R.id.recyclerFavorite);
        final LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
        adapter = new AdvertisingAdapterFavorite(recyclerView,this,lists,this);
        recyclerView.setAdapter(adapter);


        setRecyclerVacansy(readDBVacansy());

    }

    private void setRecyclerVacansy(List<VacansyObjectFavorite> lists){
        listV = lists;
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerVacansy);
        final LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
        adapterV = new VacansyAdapter(recyclerView,this,listV,this);
        recyclerView.setAdapter(adapterV);
    }

    public void startInfo(int position){
        Intent intent = new Intent(FavoriteActivity.this, AllInfoActivity.class);
        intent.putExtra("pid", list.get(position).getPid().get());
        intent.putExtra("id", list.get(position).getId().get());
        intent.putExtra("ids","1");
        intent.putExtra("type","1");
        startActivity(intent);
    }

    public void startInfoVacansy(int position){
        Intent intent = new Intent(FavoriteActivity.this, AllInfoActivity.class);
        intent.putExtra("id", listV.get(position).getId().get());
        intent.putExtra("ids","3");
        intent.putExtra("type","1");
        startActivity(intent);
    }

    public List<AdvertisingObjectFavorite> readDB(){


        List<AdvertisingObjectFavorite> organizationList = new ArrayList<>();

        try {
            Cursor c = database.query(DBHelper.TABLE_ADV, null, null,null, null, null, null, null);
            c.moveToFirst();
            while (!c.isAfterLast()) {

                int _id = c.getColumnIndex("_id");
                int _pid = c.getColumnIndex("_pid");
                int _name = c.getColumnIndex("_name");
                int _descr = c.getColumnIndex("_description");
                int _fio = c.getColumnIndex("_fio");
                int _email = c.getColumnIndex("_email");
                int _phone = c.getColumnIndex("_phone");
                int _img = c.getColumnIndex("_img");
                int _date = c.getColumnIndex("_date");
                int _town = c.getColumnIndex("_town");

                String id = c.getString(_id);
                String pid = c.getString(_pid);
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
                objectFavorite.setId(new ObservableField<String>(id));
                objectFavorite.setPid(new ObservableField<String>(pid));
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

    public List<VacansyObjectFavorite> readDBVacansy(){

       List<VacansyObjectFavorite> list = new ArrayList<>();

        try {
            Cursor c = database.query(DBHelper.TABLE_V, null, null,null, null, null, null, null);
            c.moveToFirst();
            while (!c.isAfterLast()) {

                int _id = c.getColumnIndex("_id");
                int _name = c.getColumnIndex("_name");
                int _dolzhnost = c.getColumnIndex("_dolzhnost");
                int _trebovaniya = c.getColumnIndex("_trebovaniya");
                int _alldescr = c.getColumnIndex("_alldescr");
                int _unp = c.getColumnIndex("_unp");
                int _phone = c.getColumnIndex("_phone");
                int _description = c.getColumnIndex("_description");
                int _date = c.getColumnIndex("_date");
                int _town = c.getColumnIndex("_town");

                String ids = c.getString(_id);
                String name = c.getString(_name);
                String town = c.getString(_town);
                String date = c.getString(_date);
                VacansyObjectFavorite objectFavorite = new VacansyObjectFavorite();

                objectFavorite.setId(new ObservableField<String>(ids));
                objectFavorite.setName(new ObservableField<String>(name));
                objectFavorite.setTown(new ObservableField<String>(town));
                objectFavorite.setDate(new ObservableField<String>(date));
                list.add(objectFavorite);

                c.moveToNext();
                //cursor.close();
            }

            c.close();
            //dbHelper.close();
        }catch (Exception e){
            e.printStackTrace();

        }

        return list;
    }


}
