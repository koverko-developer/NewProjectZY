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

public class VacansyObjectFavorite extends BaseObservable {

    private ObservableField<String> id;
    private ObservableField<String> name;
    private ObservableField<String> dolzhnost;
    private ObservableField<String> trebovaniya;
    private ObservableField<String> all;
    private ObservableField<String> unp;
    private ObservableField<String> phone;
    private ObservableField<String> town;
    private ObservableField<String> description;
    private ObservableField<String> date;

    public VacansyObjectFavorite(){
        id = new ObservableField<>();
        name = new ObservableField<>();
        dolzhnost = new ObservableField<>();
        trebovaniya = new ObservableField<>();
        all = new ObservableField<>();
        unp = new ObservableField<>();
        phone = new ObservableField<>();
        town = new ObservableField<>();
        description = new ObservableField<>();
        date = new ObservableField<>();
    }

    public ObservableField<String> getName() {
        return name;
    }

    public void setName(ObservableField<String> pname) {
        name.set(pname.get());
    }

    public ObservableField<String> getDolzhnost() {
        return dolzhnost;
    }

    public void setDolzhnost(ObservableField<String> pdolzhnost) {
        dolzhnost.set(pdolzhnost.get());
    }

    public ObservableField<String> getTrebovaniya() {
        return trebovaniya;
    }

    public void setTrebovaniya(ObservableField<String> ptrebovaniya) {
        trebovaniya.set(ptrebovaniya.get());
    }

    public ObservableField<String> getAll() {
        return all;
    }

    public void setAll(ObservableField<String> pall) {
        all.set(pall.get());
    }

    public ObservableField<String> getUnp() {
        return unp;
    }

    public void setUnp(ObservableField<String> punp) {
        unp.set(punp.get());
    }

    public ObservableField<String> getPhone() {
        return phone;
    }

    public void setPhone(ObservableField<String> pphone) {
        phone.set(pphone.get());
    }

    public ObservableField<String> getTown() {
        return town;
    }

    public void setTown(ObservableField<String> ptown) {
        town.set(ptown.get());
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

    public ObservableField<String> getDate() {
        return date;
    }

    public void setDate(ObservableField<String> pdate) {
        date.set(pdate.get());
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
