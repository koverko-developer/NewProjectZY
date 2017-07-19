package com.example.anika.newprojectzy.taxi;

import android.util.Log;
import android.view.View;

/**
 * Created by AnikA on 29.05.2017.
 */

public class MyHandlers {
    TaxiActivity activity;
    int position;

    public MyHandlers(TaxiActivity activity, int position) {
        this.activity = activity;
        this.position = position;
    }

    public void onClickFriend(View view){
        Log.i(MyHandlers.class.getSimpleName(),"Now Friend");
        activity.call(position);
    }

    public void onClickEnemy(View view){
        Log.i(MyHandlers.class.getSimpleName(),"Now Enemy");
    }
}