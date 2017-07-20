package com.example.anika.newprojectzy.favorite.classes;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

/**
 * Created by mobi app on 20.07.2017.
 */

public class ResumeObjectFavorite extends BaseObservable {

    private ObservableField<String> id;
    private ObservableField<String> fio;
    private ObservableField<String> spec;
    private ObservableField<String> opit;
    private ObservableField<String> education;
    private ObservableField<String> skills;
    private ObservableField<String> phone;
    private ObservableField<String> img;
    private ObservableField<String> date;
    private ObservableField<String> description;

    public ResumeObjectFavorite() {
        id = new ObservableField<>();
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

    public ObservableField<String> getId() {
        return id;
    }

    public void setId(ObservableField<String> pid) {
        id.set(pid.get());
    }
    @BindingAdapter({"bind:srcs"})
    public static void setImageUrl(ImageView imageView, String url) {

        byte[] decodedString = Base64.decode(url, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imageView.setImageBitmap(decodedByte);

    }

    @BindingAdapter({"bind:font"})
    public static void setFont(TextView textView, String fontName){
        textView.setTypeface(Typeface.createFromAsset(textView.getContext().getAssets(), "fonts/" + fontName));
    }

    public static byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        return stream.toByteArray();
    }


}
