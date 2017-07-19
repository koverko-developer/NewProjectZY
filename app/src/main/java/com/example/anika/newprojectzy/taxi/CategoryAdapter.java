package com.example.anika.newprojectzy.taxi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anika.newprojectzy.BR;
import com.example.anika.newprojectzy.R;

import java.util.List;

/**
 * Created by AnikA on 27.05.2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder>{
    private Context context;
    private List<TaxiObject> categoryObjectList;
    TaxiActivity activity;

    public CategoryAdapter(Context context, List<TaxiObject> categoryObjectList, TaxiActivity activity) {
        this.context = context;
        this.categoryObjectList = categoryObjectList;
        this.activity = activity;
    }
    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card_taxi, parent, false);
        return new CategoryViewHolder(view);
    }
    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {

        final TaxiObject categoryObject = categoryObjectList.get(position);
        holder.getBindings().setVariable(BR.taxi, categoryObject);
        MyHandlers handlers = new MyHandlers(activity, position);
        holder.getBindings().setVariable(BR.handlers,handlers);
        holder.getBindings().executePendingBindings();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return categoryObjectList.size();
    }


}