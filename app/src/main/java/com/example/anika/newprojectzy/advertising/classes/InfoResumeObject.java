package com.example.anika.newprojectzy.advertising.classes;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.graphics.Typeface;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by x on 11.06.2017.
 */

public class InfoResumeObject extends BaseObservable {

    private ObservableField<String> fio;
    private ObservableField<String> spec;
    private ObservableField<String> opit;
    private ObservableField<String> education;
    private ObservableField<String> skills;
    private ObservableField<String> phone;
    private ObservableField<String> img;
    private ObservableField<String> date;
    private ObservableField<String> description;

    public InfoResumeObject() {
        fio = new ObservableField<>();
        spec = new ObservableField<>();
        opit = new ObservableField<>();
        education = new ObservableField<>();
        skills = new ObservableField<>();
        phone = new ObservableField<>();
        img = new ObservableField<>();
        date = new ObservableField<>();
        description = new ObservableField<>();

    }


    public ObservableField<String> getFio() {
        return fio;
    }

    public void setFio(ObservableField<String> pfio) {
        fio.set(pfio.get());
    }

    public ObservableField<String> getSpec() {
        return spec;
    }

    public void setSpec(ObservableField<String> pspec) {
        spec.set(pspec.get());
    }

    public ObservableField<String> getOpit() {
        return opit;
    }

    public void setOpit(ObservableField<String> popit) {
        opit.set(popit.get());
    }

    public ObservableField<String> getEducation() {
        return education;
    }

    public void setEducation(ObservableField<String> peducation) {
        education.set(peducation.get());
    }

    public ObservableField<String> getSkills() {
        return skills;
    }

    public void setSkills(ObservableField<String> pskills) {
        skills.set(pskills.get());
    }

    public ObservableField<String> getPhone() {
        return phone;
    }

    public void setPhone(ObservableField<String> pphone) {
        phone.set(pphone.get());
    }

    public ObservableField<String> getImg() {
        return img;
    }

    public void setImg(ObservableField<String> pimg) {
        img.set(pimg.get());
    }

    public ObservableField<String> getDate() {
        return date;
    }

    public void setDate(ObservableField<String> pdate) {
        date.set(pdate.get());
    }

    public ObservableField<String> getDescription() {
        return description;
    }

    public void setDescription(ObservableField<String> pdescription) {
        description.set(pdescription.get());
    }

    @BindingAdapter({"android:src"})
    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    @BindingAdapter({"bind:font"})
    public static void setFont(TextView textView, String fontName){
        textView.setTypeface(Typeface.createFromAsset(textView.getContext().getAssets(), "fonts/" + fontName));
    }
}
