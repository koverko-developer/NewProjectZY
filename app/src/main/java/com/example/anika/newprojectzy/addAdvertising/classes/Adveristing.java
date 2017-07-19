package com.example.anika.newprojectzy.addAdvertising.classes;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.net.Uri;
import android.widget.ImageView;

/**
 * Created by x on 01.06.2017.
 */

public class Adveristing extends BaseObservable {
    private ObservableField<String> title;
    private ObservableField<String> description;
    private ObservableField<String> fio;
    private ObservableField<String> email;
    private ObservableField<String> phone;
    private ObservableInt id;
    private ObservableField<Uri> img;
    private ObservableField<String> descr;
    private ObservableField<String> bitmap;
    private ObservableBoolean isShow;
    private ObservableField<String> town;


    public Adveristing() {
        title = new ObservableField<>("");
        description = new ObservableField<>("");
        fio = new ObservableField<>("");
        email = new ObservableField<>("");
        phone = new ObservableField<>("");
        id = new ObservableInt();
        img = new ObservableField<>();
        descr = new ObservableField<>("");
        bitmap = new ObservableField<>();
        isShow = new ObservableBoolean(false);
        town = new ObservableField<>("");
    }

    public ObservableInt getId() {
        return id;
    }

    public void setId(ObservableInt pid) {
        id.set(pid.get());
    }

    public ObservableField<String> getTitle() {
        return title;
    }

    public void setTitle(ObservableField<String> ptitle) {
        title.set(ptitle.get());
    }

    public ObservableField<String> getDescription() {
        return description;
    }

    public void setDescription(ObservableField<String> pdescription) {
        description.set(pdescription.get());
    }

    public ObservableField<String> getFio() {
        return fio;
    }

    public void setFio(ObservableField<String> pfio) {
        fio.set(pfio.get());
    }

    public ObservableField<String> getEmail() {
        return email;
    }

    public void setEmail(ObservableField<String> pemail) {
        email.set(pemail.get());
    }

    public ObservableField<String> getPhone() {
        return phone;
    }

    public void setPhone(ObservableField<String> pphone) {
        phone.set(pphone.get());
    }

    public ObservableField<Uri> getImg() {
        return img;
    }

    public void setImg(ObservableField<Uri> pImg) {
        img.set(pImg.get());
    }

    public ObservableField<String> getDescr() {
        return descr;
    }

    public void setDescr(ObservableField<String> pdescr) {
        descr.set(pdescr.get());
    }

    public ObservableField<String> getBitmap() {
        return bitmap;
    }

    public void setBitmap(ObservableField<String> pbitmap) {
        bitmap.set(pbitmap.get());
    }

    public ObservableBoolean getIsShow() {
        return isShow;
    }

    public void setIsShow(ObservableBoolean pisShow) {
        isShow.set(pisShow.get());
    }

    public ObservableField<String> getTown() {
        return town;
    }

    public void setTown(ObservableField<String> ptown) {
        town.set(ptown.get());
    }

    @BindingAdapter({"android:src"})
    public static void setImageURI(ImageView imageView, ObservableField<Uri> uri) {
        try {
            imageView.setImageURI(uri.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
