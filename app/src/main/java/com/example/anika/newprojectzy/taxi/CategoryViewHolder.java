package com.example.anika.newprojectzy.taxi;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by AnikA on 27.05.2017.
 */
public class CategoryViewHolder extends RecyclerView.ViewHolder {
    private ViewDataBinding bindings;
    public CategoryViewHolder(View itemView) {
        super(itemView);
        bindings = DataBindingUtil.bind(itemView);
    }
    public ViewDataBinding getBindings(){
        return bindings;
    }


}
