package com.example.anika.newprojectzy.taxi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.anika.newprojectzy.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


public class TaxiActivity extends AppCompatActivity  {

    private RecyclerView recyclerView;
    List<TaxiObject> categoryList = new ArrayList<>();
    private static final String APP_PREFERENCES = "config";
    private static final String APP_PREFERENCES_NAME = "name";
    private static final String APP_PREFERENCES_CODE = "code";
    private static final String APP_PREFERENCES_ENG = "eng";
    private static final String APP_PREFERENCES_C = "c";
    private SharedPreferences mSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_taxi);

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
            String engName = mSettings.getString(APP_PREFERENCES_ENG,"");
            if(engName.isEmpty()) engName = "minsk";

            String name = mSettings.getString(APP_PREFERENCES_NAME,"");
            if(name.isEmpty()) name = "Минск";

            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            getSupportActionBar().setTitle("Такси");
            toolbar.setSubtitle(name);

            recyclerView = (RecyclerView) findViewById(R.id.food_category);
            mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
            GridLayoutManager mGrid = new GridLayoutManager(this, 1);
            recyclerView.setLayoutManager(mGrid);
            recyclerView.setHasFixedSize(true);
            CategoryAdapter mAdapter = new CategoryAdapter(this, categoryList, this);

            recyclerView.setAdapter(mAdapter);

            new MyTaskGetTaxi(TaxiActivity.this, engName).execute();



        }catch (Exception e){e.printStackTrace();}
    }
    private List<TaxiObject> testData() {
        List<TaxiObject> categoryList = new ArrayList<>();
        categoryList.add(new TaxiObject("Pizza", "https://content2.onliner.by/catalog/device/main/34920f060b4fd8e8b9569f8c0723b747.jpeg", ""));
        categoryList.add(new TaxiObject("Pizza", "https://content2.onliner.by/catalog/device/main/34920f060b4fd8e8b9569f8c0723b747.jpeg", ""));
        categoryList.add(new TaxiObject("Soup", "https://content2.onliner.by/catalog/device/main/34920f060b4fd8e8b9569f8c0723b747.jpeg", ""));
        return categoryList;
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


    public void toast(){
        Toast.makeText(TaxiActivity.this, "dsdsdsd", Toast.LENGTH_SHORT).show();
    }
    public void call(int position){
        String phone = categoryList.get(position).getPhone();
        if(!phone.isEmpty()){
            phone = phone.replaceAll("[(-]","");
            phone = phone.trim();
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:"+ phone));
            startActivity(Intent.createChooser(intent, "Call"));
        }else Toast.makeText(this, "Не указан номер телефона",Toast.LENGTH_SHORT).show();
    }

    class MyTaskGetTaxi extends AsyncTask<Void,Void,Void> {

        String col, name;
        TaxiActivity activity;

        public MyTaskGetTaxi(TaxiActivity activity, String name){
            this.activity = activity;
            this.name = name;
        }

        @Override
        protected void onPreExecute() {
            //relativeLayout.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            HttpURLConnection urlConnection;
            BufferedReader reader;
            String resultJson = null;
            String ling = "Пинск";
            Document doc = null;
            try {
                ling = URLEncoder.encode(ling, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            String link = "http://taxi1.by/"+name;
            try {
                doc = Jsoup.connect(link).get();
                Element table = doc.getElementById("bl");
                Elements rows = table.getElementsByTag("tr");
                int col = 0;
                for(int i =0; i<rows.size(); i++){
                    Elements name = rows.get(i).select("td.blname");
                    String linkText = name.select("a").first().text().toString();
                    Elements tel = rows.get(i).select("td.bltel");
                    String linkTexttel = tel.select("a").first().text().toString();
                    String ss = "";
                    switch (col) {
                        case 0:
                            categoryList.add(new TaxiObject(linkText, "http://178.172.172.82/zy/images/taxi5.png", linkTexttel));
                            col++;
                            break;
                        case 1:
                            categoryList.add(new TaxiObject(linkText, "http://178.172.172.82/zy/images/taxi6.png", linkTexttel));
                            col++;
                            break;
                        case 2:
                            categoryList.add(new TaxiObject(linkText, "http://178.172.172.82/zy/images/taxi7.png", linkTexttel));
                            col++;
                            break;
                        case 3:
                            categoryList.add(new TaxiObject(linkText, "http://178.172.172.82/zy/images/taxi8.png", linkTexttel));
                            col=0;
                            break;
                    }


                }
                String s = "";


            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //relativeLayout.setVisibility(View.GONE);
            //listView.setAdapter(new TaxiAdapter(AllAdvertisingActivity.this, arrayListTaxi));
            CategoryAdapter mAdapter = new CategoryAdapter(TaxiActivity.this, categoryList, TaxiActivity.this);
            recyclerView.setAdapter(mAdapter);
        }
    }

}
