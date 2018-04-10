package com.zjdt.dtsjwb.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.zjdt.dtsjwb.App.AppApplication;
import com.zjdt.dtsjwb.Bean.RollBean;

import java.util.ArrayList;

/**
 * 图片轮播适配器
 * Created by 71568 on 2018/4/4.
 */

public class TestAdapter extends StaticPagerAdapter {
   // private String[]imageUrl=new String[5];
    private ArrayList list;

    /**
     * 先get 再解析 然后glide
     * @param container
     * @param position
     * @return
     */
    @Override
    public View getView(ViewGroup container, int position) {
        ImageView imageView=new ImageView(container.getContext());

        Glide.with(AppApplication.getApp()).load(((RollBean)list.get(position)).getUrl()).into(imageView);

        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return imageView;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public TestAdapter(ArrayList list) {
        this.list = list;
    }
}
