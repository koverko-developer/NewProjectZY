package com.example.anika.newprojectzy.advertising;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

/**
 * Created by x on 08.06.2017.
 */

public class AdvertisingObject extends BaseObservable {

    private ObservableField<String> img;
    private ObservableField<String> title;
    private ObservableField<String> town;
    private ObservableField<String> id;
    private ObservableField<String> date;

    public AdvertisingObject() {
        title = new ObservableField<>("");
        img = new ObservableField<>();
        town = new ObservableField<>("");
        id = new ObservableField<>();
        date = new ObservableField<>();
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

    @BindingAdapter({"android:src"})
    public static void setImageUrl(ImageView imageView, String url) {
        Picasso.with(imageView.getContext())
                        .load(url)
                        .into(imageView);

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
