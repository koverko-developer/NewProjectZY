package com.example.anika.newprojectzy.favorite.holder;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by mobi app on 19.07.2017.
 */

public class AdvertisingFavoriteHolder extends RecyclerView.ViewHolder {

    private ViewDataBinding bindings;
    public AdvertisingFavoriteHolder(View itemView) {
        super(itemView);
        bindings = DataBindingUtil.bind(itemView);
    }
    public ViewDataBinding getBindings(){
        return bindings;
    }

}
