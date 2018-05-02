package com.zjdt.dtsjwb.Adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.zjdt.dtsjwb.Activity.MenuActivity;
import com.zjdt.dtsjwb.Activity.WebviewActivity;
import com.zjdt.dtsjwb.App.AppApplication;
import com.zjdt.dtsjwb.Bean.RollBean;
import com.zjdt.dtsjwb.R;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * 图片轮播适配器
 * Created by 71568 on 2018/4/4.
 */

public class TestAdapter extends StaticPagerAdapter {
   // private String[]imageUrl=new String[5];
    private ArrayList list;
    private Activity context;
    private ArrayList<View> viewlist;


    /**
     * 先get 再解析 然后glide
     * 但凡加载view 有item法 也有在view里面添加点击事件的方法，对于  如何让点击的页面与之关联难
     * @param container
     * @param position
     * @return
     */
    @Override
    public View getView(ViewGroup container, final int position) {

     //  View view= LayoutInflater.from(context).inflate(layoutId,container,false);//不需要有parent  我估计必须要传view 进来
        View view=viewlist.get(position);
        ImageView imageView= view.findViewById(R.id.rollpagerview_image);
       // ImageView imageView=new ImageView(container.getContext());//此处 imageview 可以来自inflate 并且加上可以记录（图点击链接意思）的view
        Glide.with(AppApplication.getApp()).load(((RollBean)list.get(position)).getUrl()).into(imageView);
       // imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                packToast(((RollBean)list.get(position)).getUrl()+"...."+position);
                Log.e("开图","..............1");
                HashMap<String,String>map=new HashMap<>();

                map.put("url",((RollBean)list.get(position)).getUrl());

                MenuActivity.actionActivity(context, WebviewActivity.class,map);
//怪不得不行 原来是搬家了
            }
        });
        return imageView;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public TestAdapter(ArrayList list,ArrayList<View>viewlist,Activity context) {
        this.viewlist=viewlist;
        this.list = list;
        this.context=context;
    }

    private void packToast(String xxs){
        Toast.makeText(AppApplication.getApp(),xxs,Toast.LENGTH_SHORT).show();
    }

    /**
     * 试试
     * 这里的解决方式普遍使用viewlist
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        //View v=LayoutInflater.from(context).inflate(layoutId,container,false);
        View v=viewlist.get(position);


       ImageView imageView= v.findViewById(R.id.rollpagerview_image);
        Glide.with(AppApplication.getApp()).load(((RollBean)list.get(position)).getUrl()).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                packToast(((RollBean)list.get(position)).getUrl()+"...."+position);
                HashMap<String,String>map=new HashMap<>();

                map.put("url",((RollBean)list.get(position)).getUrl());

                MenuActivity.actionActivity(context, WebviewActivity.class,map);
            }
        });

        ViewGroup parent = (ViewGroup)v.getParent();

        if (parent != null) {
            parent.removeAllViews();
        }//这样成了 不需要再andview 里面弄
        container.addView(v);

        return v;
    }
}
