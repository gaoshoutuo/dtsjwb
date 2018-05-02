package com.zjdt.dtsjwb.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zjdt.dtsjwb.Bean.NotifyBean;
import com.zjdt.dtsjwb.R;
import com.zjdt.dtsjwb.Service.NotificationService;

import java.util.ArrayList;

public class NotifyAdapter extends RecyclerView.Adapter<NotifyAdapter.Vh> {
    private ArrayList<NotifyBean>list;
    private Context context;
    private int layoutId;
    @Override
    public NotifyAdapter.Vh onCreateViewHolder(ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(layoutId,null,false);
       Vh vh=new Vh(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(NotifyAdapter.Vh holder, int position) {
       NotifyBean notifyBean= list.get(position);
       holder.title.setText(notifyBean.getTitle());
       holder.context.setText(notifyBean.getContext());
       holder.time.setText(notifyBean.getTime());
        Glide.with(context).load(R.drawable.my_assets).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public NotifyAdapter(ArrayList list, Context context,int layoutId) {
        super();
        this.list=list;
        this.context=context;
        this.layoutId=layoutId;
    }
    class Vh extends RecyclerView.ViewHolder {
        TextView title,time,context;
        ImageView image;
        public Vh(View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.notify_title);
            time=itemView.findViewById(R.id.notify_time);
            context=itemView.findViewById(R.id.notify_context);
            image=itemView.findViewById(R.id.notify_image);
        }
    }
}
