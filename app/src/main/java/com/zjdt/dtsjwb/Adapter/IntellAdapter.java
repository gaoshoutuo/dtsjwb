package com.zjdt.dtsjwb.Adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zjdt.dtsjwb.Bean.MenuBean;
import com.zjdt.dtsjwb.R;

import java.util.ArrayList;

public class IntellAdapter extends BaseAdapter{

    private ArrayList<MenuBean>list;
    private Activity activity;
    private GridView gridView;
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MenuBean str=list.get(position);
        View view=convertView;
        if (convertView==null){
            view=LayoutInflater.from(activity).inflate(R.layout.item_roll_pager_image,null,false);
        }else{

        }
    //   Bitmap bitmap = BitmapFactory.decodeStream(activity.getContentResolver().openInputStream());
        ImageView image= view.findViewById(R.id.rollpagerview_image);
        Log.e("imageInAdapter",str.getMenuname());
        Glide.with(activity).load(str.getMenuname()).into(image);
        AbsListView.LayoutParams params=new AbsListView.LayoutParams(
                gridView.getColumnWidth(),gridView.getHeight()/2
        );
        view.setLayoutParams(params);
        return view;
    }

    public IntellAdapter(ArrayList<MenuBean>list, GridView gridView, Activity activity) {
        this.list = list;
        this.gridView = gridView;
        this.activity=activity;
    }
}
