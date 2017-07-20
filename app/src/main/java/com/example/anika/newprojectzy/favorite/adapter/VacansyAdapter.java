package com.example.anika.newprojectzy.favorite.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anika.newprojectzy.BR;
import com.example.anika.newprojectzy.OnLoadMoreListener;
import com.example.anika.newprojectzy.R;
import com.example.anika.newprojectzy.favorite.FavoriteActivity;
import com.example.anika.newprojectzy.favorite.classes.VacansyObjectFavorite;
import com.example.anika.newprojectzy.favorite.handler.VacansyHandlerFavorite;
import com.example.anika.newprojectzy.favorite.holder.AdvertisingFavoriteHolder;

import java.util.List;

/**
 * Created by mobi app on 20.07.2017.
 */
public class VacansyAdapter extends RecyclerView.Adapter<AdvertisingFavoriteHolder> {

    private Context context;
    private List<VacansyObjectFavorite> categoryObjectList;
    FavoriteActivity activity;
    private OnLoadMoreListener onLoadMoreListener;
    private int visibleThreshold = 10;
    private int lastVisibleItem, totalItemCount;
    private boolean isLoading;

    public VacansyAdapter(RecyclerView recyclerView, Context context, List<VacansyObjectFavorite> categoryObjectList, FavoriteActivity activity) {
        this.context = context;
        this.categoryObjectList = categoryObjectList;
        this.activity = activity;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (lastVisibleItem == totalItemCount-1) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }
    @Override
    public AdvertisingFavoriteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card_vacansy_favorite, parent, false);
        return new AdvertisingFavoriteHolder(view);
    }
    @Override
    public void onBindViewHolder(AdvertisingFavoriteHolder holder, int position) {

        final VacansyObjectFavorite categoryObject = categoryObjectList.get(position);
        holder.getBindings().setVariable(BR.vacansyFavorite, categoryObject);
        VacansyHandlerFavorite handlers = new VacansyHandlerFavorite(activity, position);
        holder.getBindings().setVariable(BR.handlersVFavorite,handlers);
        holder.getBindings().executePendingBindings();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return categoryObjectList.size();
    }

    public void setLoaded() {
        isLoading = false;
    }

}
