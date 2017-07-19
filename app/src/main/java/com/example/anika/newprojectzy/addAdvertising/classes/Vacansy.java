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

public class Vacansy extends BaseObservable {

    private ObservableField<String> name;
    private ObservableField<String> dolshnost;
    private ObservableField<String> trebovaniya;
    private ObservableField<String> all;
    private ObservableField<String> unp;
    private ObservableField<String> phone;
    private ObservableBoolean isValid;
    private ObservableField<String> town;
    private ObservableField<String> descr;
    private ObservableBoolean isShow;


    public Vacansy(){
        name = new ObservableField<String>("");
        dolshnost = new ObservableField<String>("");
        trebovaniya = new ObservableField<String>("");
        all = new ObservableField<String>("");
        unp = new ObservableField<String>("");
        phone = new ObservableField<String>("");
        isValid = new ObservableBoolean();

        descr = new ObservableField<>("");
        isShow = new ObservableBoolean(false);
        town = new ObservableField<>("");
    }
    public ObservableBoolean getIsValid() {
        //getValid();
        return new ObservableBoolean(getValid());
    }

    public void setIsValid(ObservableBoolean pisValid) {
        isValid.set(pisValid.get());
    }

    private boolean getValid(){
        String s = name.get();
        String dsd = "";
        return true;

        //else return true;

    }

    public ObservableField<String> getName() {
        return name;
    }

    public void setName(ObservableField<String> pName) {
        name.set(pName.get());
    }

    public ObservableField<String> getDolshnost() {
        return dolshnost;
    }

    public void setDolshnost(ObservableField<String> pDolshnost) {
        dolshnost.set(pDolshnost.get());
    }

    public ObservableField<String> getTrebovaniya() {
        return trebovaniya;
    }

    public void setTrebovaniya(ObservableField<String> pTrebovaniya) {
        trebovaniya.set(pTrebovaniya.get());
    }

    public ObservableField<String> getAll() {
        return all;
    }

    public void setAll(ObservableField<String> pAll) {
        all.set(pAll.get());
    }

    public ObservableField<String> getUnp() {
        return unp;
    }

    public void setUnp(ObservableField<String> pUnp) {
        unp.set(pUnp.get());
    }

    public ObservableField<String> getPhone() {
        return phone;
    }

    public void setPhone(ObservableField<String> pPhone) {
        phone.set(pPhone.get());
    }


    public ObservableField<String> getDescr() {
        return descr;
    }

    public void setDescr(ObservableField<String> pdescr) {
        descr.set(pdescr.get());
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
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
