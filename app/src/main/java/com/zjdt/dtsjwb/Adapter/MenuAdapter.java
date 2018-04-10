package com.zjdt.dtsjwb.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjdt.dtsjwb.Bean.MenuBean;
import com.zjdt.dtsjwb.R;

import java.util.ArrayList;


/**
 * Created by 71568 on 2018/4/3.
 */

public class MenuAdapter extends BaseAdapter {
    private ArrayList list;
    private LayoutInflater layoutInflater;
    private GridView gridView;
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MenuBean menuBean= (MenuBean) list.get(i);
        Log.e("haik",i+"");
        ViewHolder viewHolder;
         if(view==null){
             view=layoutInflater.inflate(R.layout.item_menu,null,false);
             viewHolder=new ViewHolder();
             viewHolder.menuImageIv= view.findViewById(R.id.menu_item_image);
             viewHolder.menuNameTv=view.findViewById(R.id.menu_item_text);
             view.setTag(viewHolder);
         }else{
              viewHolder=(ViewHolder) view.getTag();
         }
              viewHolder.menuImageIv.setImageResource(menuBean.getMenuIconRid());
              viewHolder.menuNameTv.setText(menuBean.getMenuname());
              AbsListView.LayoutParams params=new AbsListView.LayoutParams(
                      gridView.getColumnWidth(),gridView.getHeight()/3
        );
        view.setLayoutParams(params);
        return view;
    }

    public MenuAdapter(ArrayList list, Context context,GridView gridView) {
        this.list=list;
        this.layoutInflater=LayoutInflater.from(context);
        this.gridView=gridView;
    }

    static class ViewHolder {
        ImageView menuImageIv;
        TextView menuNameTv;
    }
}
