package com.example.anika.newprojectzy.advertising;

import android.util.Log;
import android.view.View;

import com.example.anika.newprojectzy.taxi.MyHandlers;

/**
 * Created by x on 08.06.2017.
 */

public class AdvertisingHandlers {
    AdvertisingListActivity activity;
    int position;

    public AdvertisingHandlers(AdvertisingListActivity activity, int position) {
        this.activity = activity;
        this.position = position;
    }

    public void onClick(View view){
        Log.i(MyHandlers.class.getSimpleName(),"Now Friend");
        activity.startInfo(position);
    }

    public void onClickEnemy(View view){
        Log.i(MyHandlers.class.getSimpleName(),"Now Enemy");
    }
}
