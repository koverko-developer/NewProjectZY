package com.example.anika.newprojectzy.addAdvertising.handlers;

import android.view.View;

import com.example.anika.newprojectzy.addAdvertising.FragmentAddResume;

/**
 * Created by x on 31.05.2017.
 */

public class MyHandlerResume {

    FragmentAddResume fragmentAddResume;

    public MyHandlerResume(FragmentAddResume fragmentAddResume){
        this.fragmentAddResume = fragmentAddResume;
    }

    public void onClick(View v){
        fragmentAddResume.setResume();
    }
    public void onClickSelectImage(View v){
        fragmentAddResume.selectImage();
    }

}
