package com.zjdt.dtsjwb.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjdt.dtsjwb.Bean.FDBean;
import com.zjdt.dtsjwb.R;

import java.util.ArrayList;

public class FixDeviceAdapter extends UnionAdapter{
    private ArrayList<UnionBean>list;
    private int layoutId;
    private Activity context;
    private UnionViewHolder holder;//我的方法反而复杂了

    public FixDeviceAdapter(ArrayList<UnionBean> list, int layoutId, Activity context) {//继承之后 必须指定子类默认构造器对应的父类默认构造器
        super(list, layoutId, context);
        this.list = list;
        this.layoutId = layoutId;
        this.context = context;
    }

    @Override
    public UnionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {//貌似也万能不到那里去
        View view= LayoutInflater.from(context).inflate(layoutId,null,false);
        UnionViewHolder uvh=new VH(view);
        return uvh;
    }

    @Override
    public void setViewbid(UnionBean ub,UnionViewHolder uvh) {//完全是强制类型转换搞的丑陋实现
        FDBean fdBean=(FDBean)ub;
        VH vh=(VH)uvh;
        vh.imageView.setBackgroundResource(fdBean.getImageUrl());
        vh.textView.setText(fdBean.getText());
    }

    @Override
    public void onBindViewHolder(UnionViewHolder holder, int position) {
        FDBean fdBean= (FDBean) list.get(position);//强制类型转换是怎么做到的   无法模块化智能代持了
       // this.holder=holder;
        setViewbid(fdBean,holder);
    }

    public class VH extends UnionViewHolder {
        TextView textView;
        ImageView imageView;
        public VH(View itemView) {
            super(itemView);
            makeViewbid(itemView);
        }

        @Override
        public UnionViewHolder getUnionVH(View view) {
            UnionViewHolder vh=new VH(view);

            return vh;
        }

        @Override
        public void makeViewbid(View itemView) {
            textView=itemView.findViewById(R.id.business_type);
            imageView=imageView.findViewById(R.id.newdevice_image);
        }


    }
}
