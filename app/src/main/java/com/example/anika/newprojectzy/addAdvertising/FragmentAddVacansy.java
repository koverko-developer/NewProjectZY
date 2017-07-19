package com.example.anika.newprojectzy.addAdvertising;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anika.newprojectzy.BR;
import com.example.anika.newprojectzy.R;
import com.example.anika.newprojectzy.addAdvertising.classes.Vacansy;
import com.example.anika.newprojectzy.addAdvertising.handlers.MyHandlerVacansy;
import com.example.anika.newprojectzy.retrofit.App;
import com.example.anika.newprojectzy.retrofit.Response;

import retrofit2.Call;
import retrofit2.Callback;

import static android.app.Activity.RESULT_OK;

/**
 * Created by AnikA on 29.05.2017.
 */

@SuppressLint("ValidFragment")
public class FragmentAddVacansy extends Fragment{

    private static final int RESULT_SELECT_IMAGE = 1;
    static Vacansy vacansy = new Vacansy();
    static View v;
    AddAdvertisingActivity activity;

    public FragmentAddVacansy(AddAdvertisingActivity activity) {
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewDataBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_vacansy, container, false);
        binding.setVariable(BR.vacansy, vacansy);
        MyHandlerVacansy handlerVacansy = new MyHandlerVacansy(this);
        binding.setVariable(BR.handlerVacansy,handlerVacansy);
        v = binding.getRoot();
        return v;
    }

    public void toast(){
        try {
            vacansy.setIsValid(new ObservableBoolean(true));
            App.getApi().setVacansy(vacansy.getName().get(),
                    vacansy.getDolshnost().get(),
                    vacansy.getTrebovaniya().get(),
                    vacansy.getAll().get(),
                    vacansy.getUnp().get(),
                    vacansy.getPhone().get(),
                    vacansy.getDescr().get(),
                    vacansy.getTown().get()
            ).enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    vacansy.setIsValid(new ObservableBoolean(false));
                    activity.toastSucsefull();

                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                    vacansy.setIsValid(new ObservableBoolean(false));
                    activity.toastError();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
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
            //vacansy.setImg(new ObservableField<Uri>(image));
//            imageView.setImageURI(image);
//            imageViewOb.setImageURI(image);

        }
    }
}
