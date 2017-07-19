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
import android.widget.Toast;

import com.example.anika.newprojectzy.BR;
import com.example.anika.newprojectzy.R;
import com.example.anika.newprojectzy.addAdvertising.classes.Resume;
import com.example.anika.newprojectzy.addAdvertising.handlers.MyHandlerResume;
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
public class FragmentAddResume extends Fragment {

    private static final int RESULT_SELECT_IMAGE = 1;
    static Resume resume = new Resume();
    AddAdvertisingActivity activity;

    @SuppressLint("ValidFragment")
    public FragmentAddResume(AddAdvertisingActivity activity) {
        this.activity = activity;
    }

    public void setResume(){
        resume.setIsValid(new ObservableBoolean(true));
        App.getApi().setResume(resume.getName().get(),
                resume.getSpec().get(),
                resume.getOpit().get(),
                resume.getEducation().get(),
                resume.getSkills().get(),
                resume.getPhone().get(),
                resume.getBitmap().get(),
                resume.getDescr().get(),
                resume.getTown().get()).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Toast.makeText(getActivity(),"Ваша вакансия отправлена на модерацию!",Toast.LENGTH_SHORT).show();
                resume.setIsShow(new ObservableBoolean(false));
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(getActivity(),"Проверьте подключение к интернету...",Toast.LENGTH_SHORT).show();
                resume.setIsShow(new ObservableBoolean(false));
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewDataBinding binding = DataBindingUtil.inflate(inflater, R.layout.frgment_resume, container, false);

        //user.name.set("Dima");

        binding.setVariable(BR.resume, resume);
        MyHandlerResume handlerResume = new MyHandlerResume(this);
        binding.setVariable(BR.handler, handlerResume);



        View v = binding.getRoot();

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
            resume.setImg(new ObservableField<Uri>(image));
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
                resume.setBitmap(new ObservableField<String>(encodeImage));
            } catch (IOException e) {
                e.printStackTrace();
            }
//            imageView.setImageURI(image);
//            imageViewOb.setImageURI(image);

        }
    }



}
