package com.example.anika.newprojectzy.addAdvertising.handlers;

import android.view.View;

import com.example.anika.newprojectzy.addAdvertising.FragmentAddAdvertising;

/**
 * Created by x on 01.06.2017.
 */

public class MyHandlerAdveristing {

    FragmentAddAdvertising fragmentAddAdvertising;

    public MyHandlerAdveristing(FragmentAddAdvertising fragmentAddAdvertising){
        this.fragmentAddAdvertising = fragmentAddAdvertising;
    }

    public void onClick(View v){
        fragmentAddAdvertising.setAdv();
    }
    public void onClickSelectImage(View v){
        fragmentAddAdvertising.selectImage();
    }

}
