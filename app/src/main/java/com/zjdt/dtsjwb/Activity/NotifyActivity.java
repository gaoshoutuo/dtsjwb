package com.zjdt.dtsjwb.Activity;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.zjdt.dtsjwb.Adapter.NotifyAdapter;
import com.zjdt.dtsjwb.Bean.NotifyBean;
import com.zjdt.dtsjwb.NetUtil.SocketUtil;
import com.zjdt.dtsjwb.R;
import com.zjdt.dtsjwb.Service.NotificationService;
import com.zjdt.dtsjwb.Util.ThreadUtil;

import java.util.ArrayList;

public class NotifyActivity extends BaseActivity {
    /**
     * 与nodejs客户端 与本地服务有关  此处notify 显示服务器表的所有的notify消息
     * @param savedInstanceState
     */

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);
        initview();
    }
    private void initview(){
        recyclerView=f(R.id.notify_recyclerview);
        LinearLayoutManager lm=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lm);
        ArrayList<NotifyBean>list=getListData();
        NotifyAdapter notifyAdapter=new NotifyAdapter(list,this,R.layout.item_notify);
        recyclerView.setAdapter(notifyAdapter);
    }

    private ArrayList<NotifyBean> getListData(){
        ThreadUtil.execute(new ThreadUtil.CallBack() {
            @Override
            public void exec() {

            }

            @Override
            public void run() {
                SocketUtil.sendMessageAdd("218.108.146.98",3333,"{\"au\":\"select\",\"age\":\"88\",\"name\":\"hetao\"}");//此处涉及线程调度 主线程要先阻塞 然后子线程办好事情 主线程再
            }
        });

        return null;
    }

}
