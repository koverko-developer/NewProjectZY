package com.example.anika.newprojectzy;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.anika.newprojectzy.advertising.classes.InfoAdvertisingObject;
import com.example.anika.newprojectzy.advertising.classes.InfoResumeObject;
import com.example.anika.newprojectzy.advertising.classes.InfoVacansyObject;
import com.example.anika.newprojectzy.advertising.fragments.FragmentInfoAdvertising;
import com.example.anika.newprojectzy.advertising.fragments.FragmentInfoResume;
import com.example.anika.newprojectzy.advertising.fragments.FragmentInfoVacansy;
import com.example.anika.newprojectzy.db.DBHelper;
import com.example.anika.newprojectzy.db.Utilities;
import com.example.anika.newprojectzy.favorite.classes.AdvertisingObjectFavorite;
import com.example.anika.newprojectzy.favorite.classes.VacansyObjectFavorite;

import org.apache.commons.codec.binary.Base64;

import java.util.ArrayList;
import java.util.List;

public class AllInfoActivity extends AppCompatActivity {

    DBHelper dbHelper;
    SQLiteDatabase database;
    FragmentInfoAdvertising fragmentInfoAdvertising;
    FragmentInfoResume fragmentInfoResume;
    FragmentInfoVacansy fragmentInfoVacansy;
    Menu mOptionsMenu;
    String pid, id;
    MenuItem playMenu;
    String ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();

        Intent intent = getIntent();
        ids = intent.getStringExtra("ids");
        pid = intent.getStringExtra("pid");
        id = intent.getStringExtra("id");
        String type = intent.getStringExtra("type");

        switch (ids){
            case "1":
                fragmentInfoAdvertising = new FragmentInfoAdvertising(this,Integer.parseInt(pid),Integer.parseInt(id), Integer.parseInt(type));
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
                fragmentInfoVacansy = new FragmentInfoVacansy(this, Integer.parseInt(id), Integer.parseInt(type));
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
        contentValues.put(DBHelper.KEY_ID, id);
        contentValues.put(DBHelper.KEY_PID_A, pid);
        contentValues.put(DBHelper.KEY_NAME_A, object.getName().get());
        contentValues.put(DBHelper.KEY_DESCRIPTION_A, object.getDescription().get());
        contentValues.put(DBHelper.KEY_FIO_A, object.getDescription().get());
        contentValues.put(DBHelper.KEY_EMAIL_A, object.getEmail().get());
        contentValues.put(DBHelper.KEY_PHONE_A, object.getPhone().get());
        contentValues.put(DBHelper.KEY_IMG_A, Utilities.getBytes(imgLogo));
        contentValues.put(DBHelper.KEY_DATE_A, object.getDate().get());
        contentValues.put(DBHelper.KEY_TOWN_A, object.getTown().get());
        database.insert(DBHelper.TABLE_ADV, null, contentValues);
       // Toast.makeText(this, "Объявление добавлено в избранное.", Toast.LENGTH_SHORT).show();
    }

    public void insertInToDBResume(InfoResumeObject object, Bitmap imgLogo){

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.KEY_ID_R, object.getId().get());
        contentValues.put(DBHelper.KEY_FIO_R, object.getFio().get());
        contentValues.put(DBHelper.KEY_SPECIALITY_R, object.getSpec().get());
        contentValues.put(DBHelper.KEY_OPIT_R, object.getOpit().get());
        contentValues.put(DBHelper.KEY_EDUCATION_R, object.getEducation().get());
        contentValues.put(DBHelper.KEY_SKILLS_R, object.getSkills().get());
        contentValues.put(DBHelper.KEY_PHONE_R, object.getPhone().get());
        contentValues.put(DBHelper.KEY_IMG_R, Utilities.getBytes(imgLogo));
        contentValues.put(DBHelper.KEY_DATE_R, object.getDate().get());
        contentValues.put(DBHelper.KEY_DESCRIPTION_R, object.getDescription().get());
        //contentValues.put(DBHelper.KEY_TOWN_R, object.get);

        database.insert(DBHelper.TABLE_R, null, contentValues);
    }

    public void insertInToDBVacansy(InfoVacansyObject object){
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DBHelper.KEY_ID_V, object.getId().get());
            contentValues.put(DBHelper.KEY_NAME_V, object.getName().get());
            contentValues.put(DBHelper.KEY_DOLZHNOST_V, object.getDolzhnost().get());
            contentValues.put(DBHelper.KEY_TREBOVANIYA_V, object.getTrebovaniya().get());
            contentValues.put(DBHelper.KEY_ALLDESCR_V, object.getAll().get());
            contentValues.put(DBHelper.KEY_UNP_V, object.getUnp().get());
            contentValues.put(DBHelper.KEY_PHONE_V, object.getPhone().get());
            contentValues.put(DBHelper.KEY_DESCRIPTION_V, object.getDescription().get());
            contentValues.put(DBHelper.KEY_DATE_V, object.getDate().get());
            contentValues.put(DBHelper.KEY_TOWN_V, object.getTown().get());
            database.insert(DBHelper.TABLE_V, null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.main, menu );
        playMenu = menu.findItem(R.id.action_favorite);
        if(Integer.parseInt(ids)==1){
            if(readIndDBAdvertising()) changeMenuIcon();
        }
        else if(Integer.parseInt(ids)==3){
            if(readIndDBVacansy()) changeMenuIcon();
        }

        return super.onCreateOptionsMenu(menu);
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

    public void changeMenuIcon(){

        try {
            playMenu.setIcon(getResources().getDrawable(R.drawable.ic_calendar_range));
            playMenu.setEnabled(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean readIndDBAdvertising(){

        boolean isInDB = false;
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

                String ids = c.getString(_id);
                String pids = c.getString(_pid);
                if(Integer.parseInt(ids) == Integer.parseInt(id) &&
                        Integer.parseInt(pids) == Integer.parseInt(pid) ) isInDB = true;
                else isInDB = false;
                c.moveToNext();
                //cursor.close();
            }

            c.close();
            //dbHelper.close();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return isInDB;
    }
    public boolean readIndDBVacansy(){

        boolean isInDB = false;

        try {
            Cursor c = database.query(DBHelper.TABLE_V, null, null,null, null, null, null, null);
            c.moveToFirst();
            while (!c.isAfterLast()) {

                int _id = c.getColumnIndex("_id");
//                int _pid = c.getColumnIndex("_pid");
//                int _name = c.getColumnIndex("_name");
//                int _descr = c.getColumnIndex("_description");
//                int _fio = c.getColumnIndex("_fio");
//                int _email = c.getColumnIndex("_email");
//                int _phone = c.getColumnIndex("_phone");
//                int _img = c.getColumnIndex("_img");
//                int _date = c.getColumnIndex("_date");
//                int _town = c.getColumnIndex("_town");

                String ids = c.getString(_id);
                if(Integer.parseInt(ids) == Integer.parseInt(id)) isInDB = true;
                else isInDB = false;
                c.moveToNext();
                //cursor.close();
            }

            c.close();
            //dbHelper.close();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return isInDB;
    }

    public List<AdvertisingObjectFavorite> readDBAdvertising(){


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
                objectFavorite.setDescription(new ObservableField<String>(descr));
                objectFavorite.setEmail(new ObservableField<String>(email));
                objectFavorite.setPhone(new ObservableField<String>(phone));
                String ids = c.getString(_id);
                String pids = c.getString(_pid);
                if(Integer.parseInt(ids) == Integer.parseInt(id) &&
                        Integer.parseInt(pids) == Integer.parseInt(pid) ) organizationList.add(objectFavorite);


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

    public VacansyObjectFavorite readDBVacansy(){

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
                String dolzhnost = c.getString(_dolzhnost);
                String trebovaniya = c.getString(_trebovaniya);
                String alldescr = c.getString(_alldescr);
                String unp = c.getString(_unp);
                String phone = c.getString(_phone);
                String description = c.getString(_description);
                VacansyObjectFavorite objectFavorite = new VacansyObjectFavorite();

                objectFavorite.setId(new ObservableField<String>(ids));
                objectFavorite.setName(new ObservableField<String>(name));
                objectFavorite.setTown(new ObservableField<String>(town));
                objectFavorite.setDate(new ObservableField<String>(date));
                objectFavorite.setDolzhnost(new ObservableField<String>(dolzhnost));
                objectFavorite.setTrebovaniya(new ObservableField<String>(trebovaniya));
                objectFavorite.setAll(new ObservableField<String>(alldescr));
                objectFavorite.setUnp(new ObservableField<String>(unp));
                objectFavorite.setPhone(new ObservableField<String>(phone));
                objectFavorite.setDescription(new ObservableField<String>(description));

                if(Integer.parseInt(ids)==Integer.parseInt(id))list.add(objectFavorite);

                c.moveToNext();
                //cursor.close();
            }

            c.close();
            //dbHelper.close();
        }catch (Exception e){
            e.printStackTrace();

        }

        return list.get(0);
    }

}
