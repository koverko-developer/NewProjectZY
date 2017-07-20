package com.example.anika.newprojectzy.favorite.handler;

import android.util.Log;
import android.view.View;

import com.example.anika.newprojectzy.favorite.FavoriteActivity;
import com.example.anika.newprojectzy.taxi.MyHandlers;

/**
 * Created by mobi app on 20.07.2017.
 */

public class VacansyHandlerFavorite {
    FavoriteActivity activity;
    int position;

    public VacansyHandlerFavorite(FavoriteActivity activity, int position) {
        this.activity = activity;
        this.position = position;
    }

    public void onClick(View view){
        Log.i(MyHandlers.class.getSimpleName(),"Now Friend");
        activity.startInfoVacansy(position);
    }

    public void onClickEnemy(View view){
        Log.i(MyHandlers.class.getSimpleName(),"Now Enemy");
    }
}
