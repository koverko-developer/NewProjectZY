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

public class Resume extends BaseObservable {

    private ObservableField<String> name;
    private ObservableField<String> spec;
    private ObservableField<String> opit;
    private ObservableField<String> education;
    private ObservableField<String> skills;
    private ObservableField<String> phone;
    private ObservableBoolean isValid;
    private ObservableField<Uri> img;
    private ObservableField<String> descr;
    private ObservableField<String> bitmap;
    private ObservableBoolean isShow;
    private ObservableField<String> town;

    public Resume(){
        name = new ObservableField<String>("");
        spec = new ObservableField<String>("");
        opit = new ObservableField<String>("");
        education = new ObservableField<String>("");
        skills = new ObservableField<String>("");
        phone = new ObservableField<String>("");
        isValid = new ObservableBoolean();
        img = new ObservableField<>();
        descr = new ObservableField<>("");
        bitmap = new ObservableField<>("");
        isShow = new ObservableBoolean(false);
        town = new ObservableField<>("");
    }

    public ObservableBoolean getIsValid() {
        getValid();
        return isValid;
    }

    public void setIsValid(ObservableBoolean pisValid) {
        isValid.set(pisValid.get());
    }

    private void getValid(){
        if(!getName().get().isEmpty() && !getEducation().get().isEmpty() &&
                !getOpit().get().isEmpty() && !getPhone().get().isEmpty() &&
                !getSkills().get().isEmpty() && !getSpec().get().isEmpty())
            setIsValid(new ObservableBoolean(true));
        else setIsValid(new ObservableBoolean(false));
    }

    public ObservableField<String> getName() {
        return name;
    }

    public void setName(ObservableField<String> pName) {
        name.set(pName.get());
    }

    public ObservableField<String> getSpec() {
        return spec;
    }

    public void setSpec(ObservableField<String> pSpec) {
        spec.set(pSpec.get());
    }

    public ObservableField<String> getOpit() {
        return opit;
    }

    public void setOpit(ObservableField<String> pOpit) {
        opit.set(pOpit.get());
    }

    public ObservableField<String> getEducation() {
        return education;
    }

    public void setEducation(ObservableField<String> pEducation) {
        education.set(pEducation.get());
    }

    public ObservableField<String> getSkills() {
        return skills;
    }

    public void setSkills(ObservableField<String> pSkills) {
        skills.set(pSkills.get());
    }

    public ObservableField<String> getPhone() {
        return phone;
    }

    public void setPhone(ObservableField<String> pPhone) {
        phone.set(pPhone.get());
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
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
