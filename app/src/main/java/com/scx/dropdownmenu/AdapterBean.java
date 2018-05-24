package com.scx.dropdownmenu;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Stephen Sun on 2017/7/6 0006.
 * Email:suncunx@qq.com
 */

public class AdapterBean {

    private RecyclerView.LayoutManager layoutManager;

    private RecyclerView.Adapter adapter;

    private RecyclerView.ItemDecoration itemDecoration;

    public AdapterBean(RecyclerView.LayoutManager layoutManager, RecyclerView.Adapter adapter, RecyclerView.ItemDecoration itemDecoration) {
        this.layoutManager = layoutManager;
        this.adapter = adapter;
        this.itemDecoration = itemDecoration;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
    }

    public RecyclerView.ItemDecoration getItemDecoration() {
        return itemDecoration;
    }

    public void setItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        this.itemDecoration = itemDecoration;
    }
}
