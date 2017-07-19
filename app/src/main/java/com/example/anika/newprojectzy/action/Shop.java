package com.example.anika.newprojectzy.action;

/**
 * Created by AnikA on 26.05.2017.
 */

public class Shop {

    String name;
    String img;
    String href;

    public Shop(String name, String img, String href) {
        this.name = name;
        this.img = img;
        this.href = href;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public String getHref() {
        return href;
    }
}
