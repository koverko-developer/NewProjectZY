package com.example.anika.newprojectzy.valuta;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.anika.newprojectzy.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class ValutaActivity extends AppCompatActivity {

    ProgressBar progressBar;
    ArrayList<Banks> banks = new ArrayList<>();
    ListView listView;
    private static final String APP_PREFERENCES = "config";
    private static final String APP_PREFERENCES_NAME = "name";
    private static final String APP_PREFERENCES_CODE = "code";
    private static final String APP_PREFERENCES_ENG = "eng";
    private static final String APP_PREFERENCES_C = "c";
    private SharedPreferences mSettings;
    String engName, town;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valuta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressBar = (ProgressBar) findViewById(R.id.progressBarValuta);
        listView = (ListView) findViewById(R.id.listViewValuta);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        String c = mSettings.getString(APP_PREFERENCES_C,"");
        if(c.isEmpty()) c = "27";

        String name = mSettings.getString(APP_PREFERENCES_NAME,"");
        if(name.isEmpty()) name = "Минск";

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Курсы валют");
        toolbar.setSubtitle(name);

        new MyTaskGetCourse(c).execute();

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

    class MyTaskGetCourse extends AsyncTask<Void,Void,Void> {
        String codes;

        public MyTaskGetCourse(String codes) {
            this.codes = codes;
        }

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                String webPage = "https://admin.myfin.by/outer/authXml/"+codes;
                String name = "anika";
                String password = "kGnRc4h";

                String authString = name + ":" + password;
                System.out.println("auth string: " + authString);
                byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
                String authStringEnc = new String(authEncBytes);
                System.out.println("Base64 encoded auth string: " + authStringEnc);

                URL url = new URL(webPage);
                URLConnection urlConnection = url.openConnection();
                urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
                InputStream is = urlConnection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);


                int numCharsRead;
                char[] charArray = new char[1024];
                StringBuffer sb = new StringBuffer();
                while ((numCharsRead = isr.read(charArray)) > 0) {
                    sb.append(charArray, 0, numCharsRead);
                }

                String result = sb.toString();
                JSONObject dataJsonObj = null;
                dataJsonObj = XML.toJSONObject(result);

                JSONObject sd = dataJsonObj.getJSONObject("root");
                JSONArray friends = sd.getJSONArray("bank");

                final GsonBuilder builder = new GsonBuilder();
                builder.setVersion(1.0);

                final Gson gson = builder.create();
                String s = "";
                for(int i =0; i<friends.length(); i++) {
                    JSONObject friend = friends.getJSONObject(i);
                    String ff = friend.toString();
                    JSONObject data = new JSONObject(ff);

                    Banks companyJSON = null;
                    companyJSON = gson.fromJson(friend.toString(),Banks.class);

                    //progressDialog.setMessage(String.valueOf(i));

                    banks.add(companyJSON);
                    String ss1 ="";

                }



            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            float usdMin = getMinUsd(banks);
            float eurMin = getMinEur(banks);
            float rubMin = getMinRub(banks);

            float usdMax = getMaxUsd(banks);
            float eurMax = getMaxEur(banks);
            float rubMax = getMaxRub(banks);

            progressBar.setVisibility(View.GONE);
            listView.setAdapter(new BanksListAdapter(ValutaActivity.this, banks, usdMin, usdMax, eurMin, eurMax, rubMin, rubMax));

        }

        private float getMinUsd(ArrayList<Banks> arrayList){
            float getMinUSD  = Float.parseFloat(banks.get(0).getUsd_buy());
            try {
                int i = 0;
                for (Banks bank : banks) {
                    i++;
                    try {
                        if (Float.parseFloat(bank.getUsd_buy()) < getMinUSD) {
                            getMinUSD = Float.parseFloat(bank.getUsd_buy());
                        }

                    }catch (Exception e){
                        e.printStackTrace();}
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return getMinUSD;
        }

        private float getMinEur(ArrayList<Banks> arrayList){
            float getMinEur  = Float.parseFloat(banks.get(0).getEur_buy());
            try {
                int i = 0;
                for (Banks bank : banks) {
                    i++;
                    try {
                        if (Float.parseFloat(bank.getEur_buy()) < getMinEur) {
                            getMinEur = Float.parseFloat(bank.getEur_buy());
                        }

                    }catch (Exception e){
                        e.printStackTrace();}
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return getMinEur;
        }

        private float getMinRub(ArrayList<Banks> arrayList){
            float getMinRub  = Float.parseFloat(banks.get(0).getRur_buy());
            try {
                int i = 0;
                for (Banks bank : banks) {
                    i++;
                    try {
                        if (Float.parseFloat(bank.getRur_buy()) < getMinRub) {
                            getMinRub = Float.parseFloat(bank.getRur_buy());
                        }

                    }catch (Exception e){
                        e.printStackTrace();}
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return getMinRub;
        }


        private float getMaxUsd(ArrayList<Banks> arrayList){
            float getMinUSD  = Float.parseFloat(banks.get(0).getUsd_sell());
            try {
                int i = 0;
                for (Banks bank : banks) {
                    i++;
                    try {
                        if (Float.parseFloat(bank.getUsd_sell()) > getMinUSD) {
                            getMinUSD = Float.parseFloat(bank.getUsd_sell());
                        }

                    }catch (Exception e){
                        e.printStackTrace();}
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return getMinUSD;
        }

        private float getMaxEur(ArrayList<Banks> arrayList){
            float getMinEur  = Float.parseFloat(banks.get(0).getEur_sell());
            try {
                int i = 0;
                for (Banks bank : banks) {
                    i++;
                    try {
                        if (Float.parseFloat(bank.getEur_sell()) > getMinEur) {
                            getMinEur = Float.parseFloat(bank.getEur_sell());
                        }

                    }catch (Exception e){
                        e.printStackTrace();}
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return getMinEur;
        }

        private float getMaxRub(ArrayList<Banks> arrayList){
            float getMinRub  = Float.parseFloat(banks.get(0).getRur_sell());
            try {
                int i = 0;
                for (Banks bank : banks) {
                    i++;
                    try {
                        if (Float.parseFloat(bank.getRur_sell()) > getMinRub) {
                            getMinRub = Float.parseFloat(bank.getRur_sell());
                        }

                    }catch (Exception e){
                        e.printStackTrace();}
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return getMinRub;
        }
    }


}
