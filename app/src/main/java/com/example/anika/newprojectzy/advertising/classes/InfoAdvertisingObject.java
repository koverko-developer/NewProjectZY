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

public class InfoAdvertisingObject extends BaseObservable {

    private ObservableField<String> date;
    private ObservableField<String> img;
    private ObservableField<String> description;
    private ObservableField<String> email;
    private ObservableField<String> phone;
    private ObservableField<String> name;
    private ObservableField<String> town;

    public InfoAdvertisingObject(){

        date = new ObservableField<>();
        img = new ObservableField<>();
        description = new ObservableField<>();
        email = new ObservableField<>();
        phone = new ObservableField<>();
        name = new ObservableField<>();
        town = new ObservableField<>();


    }

    public ObservableField<String> getDate() {
        return date;
    }

    public void setDate(ObservableField<String> pdate) {
        date.set(pdate.get());
    }

    public ObservableField<String> getImg() {
        return img;
    }

    public void setImg(ObservableField<String> pimg) {
        img.set(pimg.get());
    }

    public ObservableField<String> getDescription() {
        return description;
    }

    public void setDescription(ObservableField<String> pdescription) {
        description.set(pdescription.get());
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

    public ObservableField<String> getName() {
        return name;
    }

    public void setName(ObservableField<String> pname) {
        name.set(pname.get());
    }

    public ObservableField<String> getTown() {
        return town;
    }

    public void setTown(ObservableField<String> ptown) {
        town.set(ptown.get());
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
