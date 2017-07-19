package com.example.anika.newprojectzy.advertising;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

/**
 * Created by x on 10.06.2017.
 */

public class InfoAdveristingObject extends BaseObservable {

    private ObservableField<String> titleToolbar;

    public InfoAdveristingObject() {
        titleToolbar = new ObservableField<>();
    }

    public ObservableField<String> getTitleToolbar() {
        return titleToolbar;
    }

    public void setTitleToolbar(ObservableField<String> ptitleToolbar) {
        titleToolbar.set(ptitleToolbar.get());
    }
}
