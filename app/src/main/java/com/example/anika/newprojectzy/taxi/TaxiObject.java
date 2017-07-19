package com.example.anika.newprojectzy.taxi;

import android.databinding.BaseObservable;

/**
 * Created by AnikA on 27.05.2017.
 */

public class TaxiObject extends BaseObservable {
    private String name;
    private String imageUrl;
    private String phone;
    public TaxiObject(String name, String imageUrl, String phone) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.phone = phone;

    }
    public String getName() {
        return name;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public String getPhone() {
        return phone;
    }
}