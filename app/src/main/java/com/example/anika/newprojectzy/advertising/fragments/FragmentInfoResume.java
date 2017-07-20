package com.example.anika.newprojectzy.advertising.fragments;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anika.newprojectzy.AllInfoActivity;
import com.example.anika.newprojectzy.BR;
import com.example.anika.newprojectzy.R;
import com.example.anika.newprojectzy.advertising.classes.InfoResumeObject;
import com.example.anika.newprojectzy.retrofit.App;
import com.example.anika.newprojectzy.retrofit.Resume;

import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by x on 11.06.2017.
 */

@SuppressLint("ValidFragment")
public class FragmentInfoResume extends Fragment {

    AllInfoActivity activity;
    int id;
    View v;

    InfoResumeObject object = new InfoResumeObject();

    public FragmentInfoResume(AllInfoActivity activity, int id){

        this.activity = activity;
        this.id = id;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, R.layout.resume_info_fragment, container, false);

        binding.setVariable(BR.resumeInfo, object);
        getInfo();
        v = binding.getRoot();

        return  v;
    }

    public void favorite(){
        new myTaskToDb(activity, object.getImg().get(), object).execute();
    }

    private void getInfo(){


        try {
            App.getApi().getResumeID(id).enqueue(new Callback<List<Resume>>() {
                @Override
                public void onResponse(Call<List<Resume>> call, retrofit2.Response<List<Resume>> response) {
                    List<Resume> resume1 = new ArrayList<Resume>();
                    resume1 = response.body();
                    Resume resume = resume1.get(0);
                    object.setId(new ObservableField<String>(resume.getId()));
                    object.setDate(new ObservableField<String>(resume.getDate()));
                    object.setImg(new ObservableField<String>(resume.getImg()));
                    object.setFio(new ObservableField<String>(resume.getFio()));
                    object.setSpec(new ObservableField<String>(resume.getSpeciality()));
                    object.setOpit(new ObservableField<String>(resume.getOpit()));
                    object.setEducation(new ObservableField<String>(resume.getEducation()));
                    object.setSkills(new ObservableField<String>(resume.getSkills()));
                    object.setDescription(new ObservableField<String>(resume.getDescription()));
                    object.setPhone(new ObservableField<String>(resume.getPhone()));

                }

                @Override
                public void onFailure(Call<List<Resume>> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class myTaskToDb extends AsyncTask<Void, Void, Void> {

        AllInfoActivity activity;
        String img, imgC;
        InfoResumeObject object;

        public myTaskToDb(AllInfoActivity activity, String img, InfoResumeObject object){
            this.activity = activity;
            this.img = img;
            this.object = object;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Bitmap bitmap = Imagehandler(img);
            activity.insertInToDBResume(object, bitmap);

            return null;
        }
    }

    protected Bitmap Imagehandler(String url) {
        try {
            url=url.replaceAll(" ", "%20");
            InputStream is = (InputStream)this.fetch(url);
            Drawable d = Drawable.createFromStream(is, "src");
            Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] bitmapdata = stream.toByteArray();
            String base64 = new String(Base64.encodeBase64(bitmapdata));
            String s = "";
            return bitmap;
        } catch (MalformedURLException e)
        {
            System.out.println(url);
            System.out.println("error at URI"+e);
            return null;
        }
        catch (IOException e)
        {
            System.out.println("io exception: "+e);
            System.out.println("Image NOT FOUND");
            return null;
        }
    }
    protected Object fetch(String address) throws MalformedURLException,IOException {
        URL url = new URL(address);
        Object content = url.getContent();
        return content;
    }
}
