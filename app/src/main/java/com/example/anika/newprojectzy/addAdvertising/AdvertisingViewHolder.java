package com.example.anika.newprojectzy.addAdvertising;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by x on 08.06.2017.
 */

public class AdvertisingViewHolder extends RecyclerView.ViewHolder {
    private ViewDataBinding bindings;
    public AdvertisingViewHolder(View itemView) {
        super(itemView);
        bindings = DataBindingUtil.bind(itemView);
    }
    public ViewDataBinding getBindings(){
        return bindings;
    }
}
