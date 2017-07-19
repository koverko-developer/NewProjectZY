package com.example.anika.newprojectzy;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

/**
 * Created by AnikA on 29.05.2017.
 */

public class User extends BaseObservable {

    private ObservableField<String> someNumber;
    private ObservableField<String> someStr;
    public User() {
        someNumber = new ObservableField<String>();
        someStr = new ObservableField<>();
    }
    public ObservableField<String> getSomeNumber() {
        return someNumber;
    }
    public void setSomeNumber(ObservableField<String> pSomeNumber) {
        someNumber.set(pSomeNumber.get());
    }
    public ObservableField<String> getSomeStr() {
        return someStr;
    }
    public void setSomeStr(ObservableField<String> pSomeStr) {
        someStr.set(pSomeStr.get());
    }


}
