package com.example.anika.newprojectzy.addAdvertising;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anika.newprojectzy.BR;
import com.example.anika.newprojectzy.R;
import com.example.anika.newprojectzy.addAdvertising.classes.Adveristing;
import com.example.anika.newprojectzy.addAdvertising.classes.Model;
import com.example.anika.newprojectzy.addAdvertising.handlers.MyHandlerAdveristing;
import com.example.anika.newprojectzy.retrofit.App;
import com.example.anika.newprojectzy.retrofit.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;

import static android.app.Activity.RESULT_OK;


/**
 * Created by AnikA on 29.05.2017.
 */

@SuppressLint("ValidFragment")
public class FragmentAddAdvertising extends Fragment {

    private static final int RESULT_SELECT_IMAGE = 1;
    Adveristing adveristing = new Adveristing();
    static Model model = new Model();
    AddAdvertisingActivity activity;

    public FragmentAddAdvertising(AddAdvertisingActivity activity){
         this.activity = activity;
    }

    View v;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewDataBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_adv, container, false);


        binding.setVariable(BR.model, model);
        MyHandlerAdveristing handlerAdveristing = new MyHandlerAdveristing(this);
        binding.setVariable(BR.handlerAdveristing, handlerAdveristing);
        binding.setVariable(BR.adveristing, adveristing);
        //binding
        v = binding.getRoot();

        return v;
    }

    public void setAdv(){
        adveristing.setIsShow(new ObservableBoolean(true));
        String type = "";
        //switch (model.)
        App.getApi().setAdv(model.getPosition().get()+1,
                adveristing.getBitmap().get(),
                adveristing.getDescr().get(),
                adveristing.getFio().get(),
                adveristing.getEmail().get(),
                adveristing.getPhone().get(),
                adveristing.getTitle().get(),
                adveristing.getTown().get()).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                activity.toastSucsefull();
                adveristing.setIsShow(new ObservableBoolean(false));

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                adveristing.setIsShow(new ObservableBoolean(false));
                activity.toastError();
            }
        });

    }

    public void selectImage(){
        //open album to select image
        Intent gallaryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallaryIntent, RESULT_SELECT_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_SELECT_IMAGE && resultCode == RESULT_OK && data != null){
            //set the selected image to image variable
            Uri image = data.getData();
            String img = image.toString();
            adveristing.setImg(new ObservableField<Uri>(image));
            Bitmap bitmap;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), image);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                //compress the image to jpg format
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
            /*
            * encode image to base64 so that it can be picked by saveImage.php file
            * */
                String encodeImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(),Base64.DEFAULT);
                adveristing.setBitmap(new ObservableField<String>(encodeImage));
            } catch (IOException e) {
                e.printStackTrace();
            }
//            imageView.setImageURI(image);
//            imageViewOb.setImageURI(image);

        }
    }
}
