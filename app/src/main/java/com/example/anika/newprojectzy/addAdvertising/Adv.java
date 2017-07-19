package com.example.anika.newprojectzy.addAdvertising;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;

/**
 * Created by AnikA on 29.05.2017.
 */

public class Adv extends BaseObservable {

    @Bindable
    public final ObservableField<String> name = new ObservableField<>();
}
