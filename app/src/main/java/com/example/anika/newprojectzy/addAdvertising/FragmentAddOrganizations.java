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
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anika.newprojectzy.BR;
import com.example.anika.newprojectzy.R;
import com.example.anika.newprojectzy.addAdvertising.classes.Ogranization;
import com.example.anika.newprojectzy.addAdvertising.handlers.MyHandlerOrganizations;
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
public class FragmentAddOrganizations extends Fragment {

    private static final int RESULT_SELECT_IMAGE = 1;
    static Ogranization organization;
    View v;
    AddAdvertisingActivity activity;

    public FragmentAddOrganizations(AddAdvertisingActivity activity){
        this.activity = activity;
    }

    public void setOrg(){
        organization.setIsShow(new ObservableBoolean(true));
        App.getApi().setOrg(organization.getName().get(),
                organization.getUnp().get(),
                organization.getDirector().get(),
                organization.getRs().get(),
                organization.getOsnova().get(),
                organization.getForma().get(),
                organization.getEmail().get(),
                organization.getPhone().get(),
                organization.getBitmap().get(),
                organization.getDescr().get()).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                activity.toastSucsefull();
                organization.setIsShow(new ObservableBoolean(false));
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                organization.setIsShow(new ObservableBoolean(false));
                activity.toastError();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_organizations, container, false);

        organization = new Ogranization();
        binding.setVariable(BR.organizations,organization);
        MyHandlerOrganizations handlerOrganizations = new MyHandlerOrganizations(this);
        binding.setVariable(BR.handlerOrganizations, handlerOrganizations);
        //organization.setImg(new ObservableField<Uri>(Uri.parse("null")));


        v = binding.getRoot();

        return v;
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
            organization.setImg(new ObservableField<Uri>(image));
            String xx = "";
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
                organization.setBitmap(new ObservableField<String>(encodeImage));
            } catch (IOException e) {
                e.printStackTrace();
            }

//            imageView.setImageURI(image);
//            imageViewOb.setImageURI(image);

        }
    }
}
