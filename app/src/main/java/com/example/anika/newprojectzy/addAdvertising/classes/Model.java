package com.example.anika.newprojectzy.addAdvertising.classes;

import android.databinding.BaseObservable;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableInt;
import android.widget.Spinner;

/**
 * Created by x on 01.06.2017.
 */


@InverseBindingMethods({
        @InverseBindingMethod(type = Spinner.class, attribute = "android:selectedItemPosition"),
})
public class Model extends BaseObservable {

    private ObservableArrayList<String> arrayList;
    private ObservableInt position ;
    String[] type = new String[]{"Куплю","Продам","Обменяю","Разное"};

    public ObservableInt getPosition() {
        return position;
    }

    public void setPosition(ObservableInt pposition) {
        position.set(pposition.get());
    }

    public Model(){

        arrayList = new ObservableArrayList<>();
        arrayList.add(0,"Куплю");
        arrayList.add(1,"Продам");
        arrayList.add(2,"Обменяю");
        arrayList.add(3,"Разное");
        position = new ObservableInt();


    }

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
    }
}
