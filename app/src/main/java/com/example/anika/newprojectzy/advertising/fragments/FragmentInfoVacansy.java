package com.example.anika.newprojectzy.advertising.fragments;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anika.newprojectzy.AllInfoActivity;
import com.example.anika.newprojectzy.BR;
import com.example.anika.newprojectzy.R;
import com.example.anika.newprojectzy.advertising.classes.InfoVacansyObject;
import com.example.anika.newprojectzy.retrofit.App;
import com.example.anika.newprojectzy.retrofit.Vacansy;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by x on 12.06.2017.
 */

@SuppressLint("ValidFragment")
public class FragmentInfoVacansy extends Fragment {

    AllInfoActivity activity;
    int id;
    InfoVacansyObject object = new InfoVacansyObject();
    View v;

    public FragmentInfoVacansy(AllInfoActivity activity, int id){

        this.activity = activity;
        this.id = id;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, R.layout.vacansy_info_fragment, container, false);

        getInfo();
        binding.setVariable(BR.vacansyInfo,object);
        v= binding.getRoot();

        return v;
    }

    private void getInfo(){
        App.getApi().getVacansyID(id).enqueue(new Callback<List<Vacansy>>() {
            @Override
            public void onResponse(Call<List<Vacansy>> call, retrofit2.Response<List<Vacansy>> response) {
                List<Vacansy> list = new ArrayList<Vacansy>();
                list = response.body();
                Vacansy vacansy = list.get(0);

                object.setName(new ObservableField<String>(vacansy.getName()));
                object.setDolzhnost(new ObservableField<String>(vacansy.getDolzhnost()));
                object.setTrebovaniya(new ObservableField<String>(vacansy.getTrebovaniya()));
                object.setAll(new ObservableField<String>(vacansy.getAlldescr()));
                object.setUnp(new ObservableField<String>(vacansy.getUnp()));
                object.setPhone(new ObservableField<String>(vacansy.getPhone()));
                object.setDescription(new ObservableField<String>(vacansy.getDescription()));
            }

            @Override
            public void onFailure(Call<List<Vacansy>> call, Throwable t) {

            }
        });
    }

    public void favorite(){
       activity.insertInToDBVacansy(object);
    }
}
