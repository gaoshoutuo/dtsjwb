package com.zjdt.dtsjwb.Util;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zjdt.dtsjwb.Activity.BaseActivity;

import java.util.ArrayList;

public class AdapterUtil <T extends RecyclerView.ViewHolder>extends RecyclerView.Adapter<RecyclerView.ViewHolder>{//泛型是为了指定类型的 可以是反射  但是继承类没法去定义泛型
    /**
     * 我也很好奇 有这玩意存在吗  我尝试尝试
     */
    private RecyclerView recyclerView;
    private ArrayList arrayList;
    private int layoutId;
    private Context context;
   // public T vhv;

    //@Override
   /* public ? onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(layoutId,null,false);
        RecyclerView.ViewHolder vh=null;
        switch (view.getId()){
            case 0:
                break;
        }

        return vh;
    }*/


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(layoutId,null,false);
        RecyclerView.ViewHolder viewHolder;//算了 先不写了  回头再看吧 丑陋就丑陋一点吧 反射调构造器
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    /*  @Override
                public T onCreateViewHolder(ViewGroup parent, int viewType) {
                    return null;
                }

                @Override
                public void onBindViewHolder(T holder, int position) {
                  holder=  (T)holder; 对了 反过来思考 当做值传递 而不是过程式的决定
                  holder..........
                    holder=  (T)holder;

                }
            */
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Vh extends RecyclerView.ViewHolder{//黑科技  玩一玩类型系统

        public Vh(View itemView) {
            super(itemView);
        }

    }

    public class vhHelper1 extends RecyclerView.ViewHolder implements Helper{
        public vhHelper1(View itemView) {
            super(itemView);
        }

        //根据不同的view 创建多个这种class
        public void initview(){

        }

    }

    public class vhHelper2 extends RecyclerView.ViewHolder implements Helper{
        public vhHelper2(View itemView) {
            super(itemView);
        }

        //根据不同的view 创建多个这种class
        public void initview(){

        }
    }

    public AdapterUtil(int layoutId, BaseActivity activity) {
        this.layoutId=layoutId;
        this.context=activity;

    }

    public interface Helper{
        void initview();

    }
    public void parseVh(Helper vh){
     vh.initview();
    }
}
