package com.example.anika.newprojectzy.taxi;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by AnikA on 27.05.2017.
 */


public class CustomImageBinding {
    @BindingAdapter({"android:src"})
    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
}