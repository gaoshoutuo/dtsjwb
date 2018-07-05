package com.zjdt.dtsjwb.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract  class UnionViewHolder extends RecyclerView.ViewHolder {
    private View view;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public UnionViewHolder(View itemView) {
        super(itemView);
        view=itemView;
    }



    /**
     * //这样两个好处 一个是继承上面的多态 另一个是abstract的等到需要才被写入具体的逻辑
     * 咔咔咔不看源码也有这样的设计
     * 要生成对象 调用到这个方法也是非常的不容易  反射试一下
     * @param view
     * @return
     */
    public abstract UnionViewHolder getUnionVH(View view);
    public abstract void makeViewbid(View itemView);



    public UnionViewHolder mmmmm(){//造轮子之前要经过细致的分析 我零碎的tij基础并不能应对这些
        UnionViewHolder vh= getUnionVH(view);

        return vh;
    }
}
