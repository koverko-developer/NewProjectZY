package com.example.anika.newprojectzy.classes;

/**
 * Created by AnikA on 24.05.2017.
 */

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.anika.newprojectzy.MainActivity;
import com.example.anika.newprojectzy.MainFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class API {
    static Handler h;

    public static void getBannersImage(final MainFragment fragment) {


        h = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                //String date = bundle.getString("ball");
                ArrayList<String> arrName = new ArrayList<>();
                ArrayList<String> arrImg = new ArrayList<>();
                ArrayList<String> arrUnp = new ArrayList<>();
                arrImg = bundle.getStringArrayList("img");
                arrName = bundle.getStringArrayList("name");
                arrUnp = bundle.getStringArrayList("unp");
                fragment.setUpSlider(arrName, arrImg,arrUnp);
                //activity.showGetFriendsDialog(date, Integer.parseInt(id));
            }
        };
        Runnable runnable = new Runnable() {
            public void run() {

                ArrayList<String> nameBanner = new ArrayList<>();
                ArrayList<String> imgBanner = new ArrayList<>();
                ArrayList<String> unpBanner = new ArrayList<>();

                Message msg = h.obtainMessage();
                Bundle bundle = new Bundle();
                String Tag = "tag";
                BufferedReader reader = null;
                String resultJson = "";
                HttpURLConnection urlConnection = null;
                JSONObject dataJsonObj = null;
                String link;
                JSONArray friends = null;
                try {
                    link  = "http://anika-cs.by/server/zy/get_banner.php?";
                    URL url1 = new URL(link);
                    urlConnection = (HttpURLConnection) url1.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.connect();

                    InputStream inputStream = urlConnection.getInputStream();
                    StringBuffer buffer1 = new StringBuffer();

                    reader = new BufferedReader(new InputStreamReader(inputStream));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        buffer1.append(line);
                    }
                    resultJson = buffer1.toString();

                    dataJsonObj = new JSONObject(resultJson);

                    friends = dataJsonObj.getJSONArray("xxx");

                    for(int i=0; i<friends.length(); i++){
                        JSONObject friend = friends.getJSONObject(i);
                        String text = friend.getString("text");
                        String img = friend.getString("img");
                        String unp = friend.getString("unp");

                        nameBanner.add(text);
                        imgBanner.add(img);
                        unpBanner.add(unp);

                    }

                    bundle.putStringArrayList("name",nameBanner);
                    bundle.putStringArrayList("img",imgBanner);
                    bundle.putStringArrayList("unp",unpBanner);
                    //bundle.putString("ball", s);
                    msg.setData(bundle);
                    h.sendMessage(msg);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

    }
//    public static void setOrganizations(final AddResumeActivity activity, final String town, final String unp,
//                                        final String address, final String name, final String phone, final String email,
//                                        final String desc) {
//
//
//
//
//        h = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//
//            }
//        };
//        Runnable runnable = new Runnable() {
//
//
//            public void run() {
//
//                ArrayList<String> nameBanner = new ArrayList<>();
//                ArrayList<String> imgBanner = new ArrayList<>();
//
//                Message msg = h.obtainMessage();
//                Bundle bundle = new Bundle();
//                String Tag = "tag";
//                BufferedReader reader = null;
//                String resultJson = "";
//                HttpURLConnection urlConnection = null;
//                JSONObject dataJsonObj = null;
//                String link;
//                JSONArray friends = null;
//
//                try {
//                    link  = "http://178.172.172.82/zy/set_organizations.php?town="+ URLEncoder.encode(town, "utf-8")+"&address="+URLEncoder.encode(address, "utf-8")+
//                            "&unp="+URLEncoder.encode(unp, "utf-8")
//                            +"&name="+URLEncoder.encode(name, "utf-8")+"&phone="+URLEncoder.encode(phone, "utf-8")+"&email="+URLEncoder.encode(email, "utf-8")+
//                            "&desc="+URLEncoder.encode(desc, "utf-8");
//                    URL url1 = new URL(link);
//                    urlConnection = (HttpURLConnection) url1.openConnection();
//                    urlConnection.setRequestMethod("GET");
//                    urlConnection.connect();
//
//                    InputStream inputStream = urlConnection.getInputStream();
//                    StringBuffer buffer1 = new StringBuffer();
//
//                    reader = new BufferedReader(new InputStreamReader(inputStream));
//
//                    String line;
//                    while ((line = reader.readLine()) != null) {
//                        buffer1.append(line);
//                    }
//                    resultJson = buffer1.toString();
//
//
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        };
//        Thread thread = new Thread(runnable);
//        thread.start();
//
//    }

//    public static void getHome(final AFragment fragment, final String unp){
//        h = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                Bundle bundle = msg.getData();
//                String unp = bundle.getString("unp");
//                String img = bundle.getString("img");
//                String director = bundle.getString("director");
//                String address = bundle.getString("address");
//                AFragment fragmentA = fragment;
//                fragment.changeText(unp, img, director, address);
//
//            }
//        };
//        Runnable runnable = new Runnable() {
//            public void run() {
//
//                ArrayList<String> nameBanner = new ArrayList<>();
//                ArrayList<String> imgBanner = new ArrayList<>();
//
//                Message msg = h.obtainMessage();
//                Bundle bundle = new Bundle();
//                String Tag = "tag";
//                BufferedReader reader = null;
//                String resultJson = "";
//                HttpURLConnection urlConnection = null;
//                JSONObject dataJsonObj = null;
//                String link;
//                JSONArray friends = null;
//                try {
//                    link  = "http://178.172.172.82/zy/get_info.php?q="+unp;
//                    URL url1 = new URL(link);
//                    urlConnection = (HttpURLConnection) url1.openConnection();
//                    urlConnection.setRequestMethod("GET");
//                    urlConnection.connect();
//
//                    InputStream inputStream = urlConnection.getInputStream();
//                    StringBuffer buffer1 = new StringBuffer();
//
//                    reader = new BufferedReader(new InputStreamReader(inputStream));
//
//                    String line;
//                    while ((line = reader.readLine()) != null) {
//                        buffer1.append(line);
//                    }
//                    resultJson = buffer1.toString();
//
//                    dataJsonObj = new JSONObject(resultJson);
//
//                    friends = dataJsonObj.getJSONArray("xxx");
//                    JSONObject friend = friends.getJSONObject(0);
//
//
//                    String unp = friend.getString("unp");
//                    String img = friend.getString("img");
//                    String dir = friend.getString("dir");
//                    String address = friend.getString("address");
//
//                    bundle.putString("unp",unp);
//                    bundle.putString("img",img);
//                    bundle.putString("director",dir);
//                    bundle.putString("address",address);
//                    //bundle.putString("ball", s);
//                    msg.setData(bundle);
//                    h.sendMessage(msg);
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        };
//        Thread thread = new Thread(runnable);
//        thread.start();
//    }

    public static void getWeather(final MainActivity activity, final String name) {


        h = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                //String img = bundle.getString("img");
                String data = bundle.getString("data");
                String img = bundle.getString("img");
                //tv.setText(data);
                activity.setWeather(data, img);
            }
        };
        Runnable runnable = new Runnable() {
            public void run() {



                Message msg = h.obtainMessage();
                Bundle bundle = new Bundle();
                String Tag = "tag";
                BufferedReader reader = null;
                Document doc = null;
                String link = "http://www.weather.by/"+name;

                try {
                    doc = Jsoup.connect(link).get();
                    Element span = doc.select("span.temperature").first();
                    String data = span.text().toString();

                    Element table = doc.getElementById("weatherBlock");
                    Element img = table.select("img").first();
                    String src = "http://www.weather.by"+img.attr("src");
                    String s = "";


                    bundle.putString("data", data);
                    bundle.putString("img", src);
                    msg.setData(bundle);
                    h.sendMessage(msg);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

    }

}