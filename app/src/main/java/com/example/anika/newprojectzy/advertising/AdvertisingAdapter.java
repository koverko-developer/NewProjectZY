package com.example.anika.newprojectzy.advertising;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anika.newprojectzy.BR;
import com.example.anika.newprojectzy.OnLoadMoreListener;
import com.example.anika.newprojectzy.R;
import com.example.anika.newprojectzy.taxi.CategoryViewHolder;

import java.util.List;

/**
 * Created by x on 08.06.2017.
 */

public class AdvertisingAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private Context context;
    private List<AdvertisingObject> categoryObjectList;
    AdvertisingListActivity activity;
    private OnLoadMoreListener onLoadMoreListener;
    private int visibleThreshold = 10;
    private int lastVisibleItem, totalItemCount;
    private boolean isLoading;

    public AdvertisingAdapter(RecyclerView recyclerView,Context context, List<AdvertisingObject> categoryObjectList, AdvertisingListActivity activity) {
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
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card_advertising, parent, false);
        return new CategoryViewHolder(view);
    }
    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {

        final AdvertisingObject categoryObject = categoryObjectList.get(position);
        holder.getBindings().setVariable(BR.advertising, categoryObject);
        AdvertisingHandlers handlers = new AdvertisingHandlers(activity, position);
        holder.getBindings().setVariable(BR.handlersAdv,handlers);
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
