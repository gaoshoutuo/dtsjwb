package com.zjdt.dtsjwb.Adapter;

import android.content.Context;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.zjdt.dtsjwb.App.AppApplication;
import com.zjdt.dtsjwb.Bean.RollBean;
import com.zjdt.dtsjwb.R;


import java.util.ArrayList;

/**
 * 图片轮播适配器
 * Created by 71568 on 2018/4/4.
 */

public class TestAdapter extends StaticPagerAdapter {
   // private String[]imageUrl=new String[5];
    private ArrayList list;
    private Context context;
    private int layoutId;

    /**
     * 先get 再解析 然后glide
     * 但凡加载view 有item法 也有在view里面添加点击事件的方法，对于  如何让点击的页面与之关联难
     * @param container
     * @param position
     * @return
     */
    @Override
    public View getView(ViewGroup container, final int position) {
       View view= LayoutInflater.from(context).inflate(layoutId,container,false);
       ImageView imageView= view.findViewById(R.id.rollpagerview_image);
        //ImageView imageView=new ImageView(container.getContext());//此处 imageview 可以来自inflate 并且加上可以记录（图点击链接意思）的view

        Glide.with(AppApplication.getApp()).load(((RollBean)list.get(position)).getUrl()).into(imageView);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                packToast(((RollBean)list.get(position)).getUrl()+"...."+position);
            }
        });
        return imageView;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public TestAdapter(ArrayList list,int layoutId,Context context) {
        this.layoutId=layoutId;
        this.list = list;
        this.context=context;
    }

    private void packToast(String xxs){
        Toast.makeText(AppApplication.getApp(),xxs,Toast.LENGTH_SHORT).show();
    }
}
