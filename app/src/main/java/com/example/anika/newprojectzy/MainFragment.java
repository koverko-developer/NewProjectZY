package com.example.anika.newprojectzy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.anika.newprojectzy.action.ListActionActivity;
import com.example.anika.newprojectzy.classes.API;
import com.example.anika.newprojectzy.favorite.FavoriteActivity;
import com.example.anika.newprojectzy.reminder.AddNoteActivity;
import com.example.anika.newprojectzy.taxi.TaxiActivity;
import com.example.anika.newprojectzy.valuta.ValutaActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by AnikA on 24.05.2017.
 */
/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class MainFragment extends Fragment implements View.OnClickListener{

    View view;
    MainActivity activity;
    String eng;

    @SuppressLint("ValidFragment")
    public MainFragment(MainActivity mainActivity, String eng) {
        this.activity = mainActivity;
        this.eng = eng;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);

        RelativeLayout relValuta = (RelativeLayout) view.findViewById(R.id.relBTNValuta);
        RelativeLayout relActions = (RelativeLayout) view.findViewById(R.id.relBTNActions);
        RelativeLayout relTaxi = (RelativeLayout) view.findViewById(R.id.relTaxi);
        LinearLayout linNotes = (LinearLayout) view.findViewById(R.id.linNotes);
        LinearLayout linFavorite = (LinearLayout) view.findViewById(R.id.linFavorite);
        relValuta.setOnClickListener(this);
        relActions.setOnClickListener(this);
        relTaxi.setOnClickListener(this);
        linNotes.setOnClickListener(this);
        linFavorite.setOnClickListener(this);

        setUpDate();

        API.getBannersImage(this);
        return view;
    }

    private void setUpDate() {

        Date date = new Date();
        long millis = date.getTime();//EEE, d MMM yyyy HH:mm:ss Z
        String current = getDate(millis, "EEEE, d, MMM, yyyy, HH:mm:ss, Z");

        String[] arrDte = current.split(",");

        TextView tvDate = (TextView) view.findViewById(R.id.textView2);
        TextView tvDay = (TextView) view.findViewById(R.id.textView5);
        String days = arrDte[2];
        tvDate.setText(arrDte[1] + " " + days.toUpperCase());
        tvDay.setText(arrDte[0].toUpperCase());

        String dsd = "";

    }

    public static String getDate(long milliSeconds, String dateFormat)
    {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }


    public void setUpSlider(ArrayList<String> arrayListName, ArrayList<String> arrayListImg, ArrayList<String> arrayListUnp){
        try{
        SliderLayout mDemoSlider = (SliderLayout) view.findViewById(R.id.slider);

        final HashMap<String, String> url_maps = new HashMap<String, String>();
        final HashMap<String, String> unp_maps = new HashMap<String, String>();
        for(int i=0; i<arrayListName.size(); i++){
            url_maps.put(arrayListName.get(i), arrayListImg.get(i));
            unp_maps.put(arrayListName.get(i), arrayListUnp.get(i));
        }


            for (final String name : url_maps.keySet()) {
                TextSliderView textSliderView = new TextSliderView(getActivity());
                // initialize a SliderLayout
                textSliderView
                        .description(name)
                        .image(url_maps.get(name))
                        .setScaleType(BaseSliderView.ScaleType.Fit)
                        .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                            @Override
                            public void onSliderClick(BaseSliderView slider) {
//                            Intent intent = new Intent(MainActivity.this, InfoActivity.class);
//                            intent.putExtra("unp",unp_maps.get(name));
//                            intent.putExtra("img",url_maps.get(name));
//                            intent.putExtra("name", name);
//                            startActivity(intent);
                                //Toast.makeText(MainActivity.this, unp_maps.get(name),Toast.LENGTH_SHORT).show();
                            }
                        });

                //add your extra information
                textSliderView.bundle(new Bundle());
                textSliderView.getBundle()
                        .putString("extra", name);

                mDemoSlider.addSlider(textSliderView);
            }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.ZoomIn);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Top);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(3000);}
        catch (Exception e){
            e.printStackTrace();
        }

        API.getWeather(activity,eng);
        //mDemoSlider.addOnPageChangeListener(this);

        //API.getWeather(MainActivity.this, engName);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()){

            case R.id.relBTNValuta:
                intent = new Intent(activity, ValutaActivity.class);
                break;
            case R.id.relBTNActions:
                intent = new Intent(activity, ListActionActivity.class);
                break;
            case R.id.relTaxi:
                intent = new Intent(activity, TaxiActivity.class);
                break;
            case R.id.linNotes:
                intent = new Intent(activity, AddNoteActivity.class);
                break;
            case R.id.linFavorite:
                intent = new Intent(activity, FavoriteActivity.class);
                break;
        }
        startActivity(intent);
    }
//
//    @Override
//    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//    }
//
//    @Override
//    public void onPageSelected(int position) {
//
//    }
//
//    @Override
//    public void onPageScrollStateChanged(int state) {
//
//    }
}
