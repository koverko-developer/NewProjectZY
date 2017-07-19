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
import com.example.anika.newprojectzy.advertising.classes.InfoAdvertisingObject;
import com.example.anika.newprojectzy.retrofit.Advertising;
import com.example.anika.newprojectzy.retrofit.App;

import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by x on 11.06.2017.
 */

@SuppressLint("ValidFragment")
public class FragmentInfoAdvertising extends Fragment {

    View v;
    AllInfoActivity allInfoActivity;
    InfoAdvertisingObject object = new InfoAdvertisingObject();
    int pid, id;

    public FragmentInfoAdvertising(AllInfoActivity allInfoActivity, int pid, int id){
        this.allInfoActivity = allInfoActivity;
        this.pid = pid;
        this.id = id;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, R.layout.advertising_info_fragmnet, container, false);
//
        object.setImg(new ObservableField<String>("http://www.prisnilos.su/kcfinder/upload/image/articles1/mashina13.jpg"));
        object.setDate(new ObservableField<String>("23-06-2017"));
        object.setDescription(new ObservableField<String>("sdfffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff"));
        object.setEmail(new ObservableField<String>("kowerkodeveloper@gmail.com"));
        object.setPhone(new ObservableField<String>("+37529292-05-38"));


        binding.setVariable(BR.adveristingInfo, object);

        getInfo();

        v = binding.getRoot();
        return v;
    }

    private void getInfo(){
        App.getApi().getAdvertisingID(id).enqueue(new Callback<List<Advertising>>() {
            @Override
            public void onResponse(Call<List<Advertising>> call, retrofit2.Response<List<Advertising>> response) {
                List<Advertising> list = response.body();
                Advertising advertising = list.get(0);

                object.setImg(new ObservableField<String>(advertising.getImg()));
                object.setDate(new ObservableField<String>(advertising.getDate()));
                object.setDescription(new ObservableField<String>(advertising.getDescription()));
                object.setEmail(new ObservableField<String>(advertising.getEmail()));
                object.setPhone(new ObservableField<String>(advertising.getPhone()));
                object.setName(new ObservableField<String>(advertising.getName()));
                object.setTown(new ObservableField<String>(advertising.getTown()));
            }

            @Override
            public void onFailure(Call<List<Advertising>> call, Throwable t) {

            }
        });
    }

    public void favorite(){
        new myTaskToDb(allInfoActivity, object.getImg().get(), object).execute();
    }

    class myTaskToDb extends AsyncTask<Void, Void, Void> {

        AllInfoActivity activity;
        String img, imgC;
        InfoAdvertisingObject object;

        public myTaskToDb(AllInfoActivity activity, String img, InfoAdvertisingObject object){
            this.activity = activity;
            this.img = img;
            this.object = object;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Bitmap bitmap = Imagehandler(img);
            activity.insertInToDBAdvertising(object, bitmap);

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
