package com.zjdt.dtsjwb.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public abstract class UnionAdapter extends RecyclerView.Adapter <UnionViewHolder> {
    private ArrayList<UnionBean>list;
    private int layoutId;
    private Activity context;

    public UnionAdapter(ArrayList<UnionBean> list, int layoutId, Activity context) {
        this.list = list;
        this.layoutId = layoutId;
        this.context = context;
    }

    @Override
    public abstract UnionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) ;

    public abstract void setViewbid(UnionBean ub,UnionViewHolder uvh);
    public abstract void onBindViewHolder(UnionViewHolder holder, int position) ;


    @Override
    public int getItemCount() {
        return list.size();
    }
}
