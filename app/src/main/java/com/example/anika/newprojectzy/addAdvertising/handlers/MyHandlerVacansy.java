package com.example.anika.newprojectzy.addAdvertising.handlers;

import android.view.View;

import com.example.anika.newprojectzy.addAdvertising.FragmentAddVacansy;

/**
 * Created by x on 01.06.2017.
 */

public class MyHandlerVacansy {

    FragmentAddVacansy fragmentAddVacansy;

    public MyHandlerVacansy(FragmentAddVacansy fragmentAddVacansy){
        this.fragmentAddVacansy = fragmentAddVacansy;
    }

    public void onClick(View v){
        fragmentAddVacansy.toast();
    }
    public void onClickSelectImage(View v){
        fragmentAddVacansy.selectImage();
    }

}
