package com.example.anika.newprojectzy.action;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

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

public class ListActionActivity extends AppCompatActivity {

    ProgressBar progressBar;
    ListView listView;
    private static final String APP_PREFERENCES = "config";
    private static final String APP_PREFERENCES_NAME = "name";
    private static final String APP_PREFERENCES_CODE = "code";
    private static final String APP_PREFERENCES_ENG = "eng";
    private static final String APP_PREFERENCES_C = "c";
    private SharedPreferences mSettings;
    ArrayList<Shop> arrayListShop = new ArrayList<>();
    String engName, town;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_action);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        engName = mSettings.getString(APP_PREFERENCES_ENG,"");
        if(engName.isEmpty()) engName = "minsk";

        String name = mSettings.getString(APP_PREFERENCES_NAME,"");
        if(name.isEmpty()) name = "Минск";

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Акции");
        toolbar.setSubtitle(name);

        listView = (ListView) findViewById(R.id.listAllAction);
        progressBar = (ProgressBar) findViewById(R.id.progressBarActionAll);
        new MyTaskGetShops(this,engName).execute();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent1 = new Intent(ListActionActivity.this, InfoShopsActivity.class);
                intent1.putExtra("href",arrayListShop.get(i).getHref());
                intent1.putExtra("name", arrayListShop.get(i).getName());
                startActivity(intent1);
            }
        });

    }
    class MyTaskGetShops extends AsyncTask<Void,Void,Void> {

        String col, name;
        ListActionActivity activity;

        public MyTaskGetShops(ListActionActivity activity, String name){
            this.activity = activity;
            this.name = name;
        }
        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
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
            String link = "http://gazetki.by/"+name;
            try {
                doc = Jsoup.connect(link).get();
                Elements ulArr = doc.select("ul.dropdown-menu");
                Elements liArr = ulArr.get(1).select("ul.dropdown-menu").select("li");

                for(int i=1; i< liArr.size(); i++){
                    Element li = liArr.get(i).select("li").first();
                    Element a = li.select("a").first();
                    String name = a.select("span.logo").text().toString();
                    Element imgs = a.select("span.holder").first();
                    Element img = imgs.select("img").first();
                    String imgText = img.attr("src");
                    String href = a.attr("href");
                    String ads = "";

                    arrayListShop.add(new Shop(name, imgText, href));
                }

                String s = "";


            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressBar.setVisibility(View.GONE);
            listView.setAdapter(new ShopsAdapter(ListActionActivity.this, arrayListShop));
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


}
