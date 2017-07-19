package com.example.anika.newprojectzy.favorite;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anika.newprojectzy.R;

/**
 * Created by mobi app on 18.07.2017.
 */

public class FragmentFavoriteAdv extends Fragment {

    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_list_favorite_adv, null);

        return v;
    }
}
