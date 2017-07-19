package com.example.anika.newprojectzy.addAdvertising.classes;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.net.Uri;
import android.widget.ImageView;

/**
 * Created by x on 01.06.2017.
 */

public class Ogranization extends BaseObservable {

    private ObservableField<String> name;
    private ObservableField<String> unp;
    private ObservableField<String> director;
    private ObservableField<String> rs;
    private ObservableField<String> osnova;
    private ObservableField<String> forma;
    private ObservableField<String> email;
    private ObservableField<String> phone;
    public ObservableField<Uri> img;
    private ObservableField<String> descr;
    private ObservableField<String> bitmap;
    private ObservableBoolean isShow;

    public Ogranization() {
        name = new ObservableField<>("");
        unp = new ObservableField<>("");
        director= new ObservableField<>("");
        rs = new ObservableField<>("");
        osnova = new ObservableField<>("");
        forma = new ObservableField<>("");
        email = new ObservableField<>("");
        phone = new ObservableField<>("");
        img = new ObservableField<>();
        descr = new ObservableField<>("");
        bitmap = new ObservableField<>();
        isShow = new ObservableBoolean(false);
    }

    public ObservableField<String> getName() {
        return name;
    }

    public void setName(ObservableField<String> pname) {
        name.set(pname.get());
    }

    public ObservableField<String> getUnp() {
        return unp;
    }

    public void setUnp(ObservableField<String> punp) {
        unp.set(punp.get());
    }

    public ObservableField<String> getDirector() {
        return director;
    }

    public void setDirector(ObservableField<String> pdirector) {
        director.set(pdirector.get());
    }

    public ObservableField<String> getRs() {
        return rs;
    }

    public void setRs(ObservableField<String> prs) {
        rs.set(prs.get());
    }

    public ObservableField<String> getOsnova() {
        return osnova;
    }

    public void setOsnova(ObservableField<String> posnova) {
        osnova.set(posnova.get());
    }

    public ObservableField<String> getForma() {
        return forma;
    }

    public void setForma(ObservableField<String> pforma) {
        forma.set(pforma.get());
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


    @BindingAdapter({"android:src"})
    public static void setImageURI(ImageView imageView, ObservableField<Uri> uri) {
        try {
         imageView.setImageURI(uri.get());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
