package com.example.anika.newprojectzy.addAdvertising.handlers;

import android.view.View;

import com.example.anika.newprojectzy.addAdvertising.FragmentAddOrganizations;

/**
 * Created by x on 01.06.2017.
 */

public class MyHandlerOrganizations {
    FragmentAddOrganizations fragmentAddOrganizations;

    public MyHandlerOrganizations(FragmentAddOrganizations fragmentAddOrganizations){
        this.fragmentAddOrganizations = fragmentAddOrganizations;
    }

    public void onClick(View v ){
        fragmentAddOrganizations.setOrg();
    }
    public void onClickSelectImage(View v){
        fragmentAddOrganizations.selectImage();
    }

}
