package com.example.anika.newprojectzy.taxi;

import android.databinding.BaseObservable;

/**
 * Created by AnikA on 27.05.2017.
 */
public class CategoryObject extends BaseObservable {
    private String name;
    private String imageUrl;
    public CategoryObject(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;

    }
    public String getName() {
        return name;
    }
    public String getImageUrl() {
        return imageUrl;
    }
}
