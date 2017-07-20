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
 * Created by mobi app on 19.07.2017.
 */

public class AdvertisingObjectFavorite extends BaseObservable {

    private ObservableField<String> img;
    private ObservableField<String> title;
    private ObservableField<String> town;
    private ObservableField<String> id;
    private ObservableField<String> pid;
    private ObservableField<String> date;
    private ObservableField<String> description;
    private ObservableField<String> email;
    private ObservableField<String> phone;

    public AdvertisingObjectFavorite() {
        title = new ObservableField<>("");
        img = new ObservableField<>();
        town = new ObservableField<>("");
        id = new ObservableField<>();
        date = new ObservableField<>();
        pid = new ObservableField<>();
        description = new ObservableField<>();
        email = new ObservableField<>();
        phone = new ObservableField<>();
    }

    public ObservableField<String> getImg() {
        return img;
    }

    public void setImg(ObservableField<String> pimg) {
        img.set(pimg.get());
    }

    public ObservableField<String> getTitle() {
        return title;
    }

    public void setTitle(ObservableField<String> ptitle) {
        title.set(ptitle.get());
    }

    public ObservableField<String> getTown() {
        return town;
    }

    public void setTown(ObservableField<String> ptown) {
        town.set(ptown.get());
    }

    public ObservableField<String> getId() {
        return id;
    }

    public void setId(ObservableField<String> pid) {
        id.set(pid.get());
    }


    public ObservableField<String> getDate() {
        return date;
    }

    public void setDate(ObservableField<String> pdate) {
        date.set(pdate.get());
    }

    public ObservableField<String> getPid() {
        return pid;
    }

    public void setPid(ObservableField<String> ppid) {
        pid.set(ppid.get());
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
