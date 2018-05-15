package com.zjdt.dtsjwb.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zjdt.dtsjwb.App.AppApplication;
import com.zjdt.dtsjwb.R;

public class EsAssit extends Fragment {
    private View view;
  /*  public EsAssit() {
    }


    @SuppressLint("ValidFragment")
    public EsAssit(int rid) {
        this.rid = rid;
    }
    注入有风险*/
  public void setRid(int rid){
      this.rid=rid;
  }
    private int rid;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       //View view=inflater.inflate(R.layout.assit_es,container,false);
        view=inflater.inflate(rid,container,false);
        return view;
    }
    private void initEleview(){
        View viewFrameUps=view.findViewById(R.id.es_head1);
    }

    private void initAirview(){
        View viewFrameUps=view.findViewById(R.id.es_head1);
    }

    private void initEmiview(){
        View viewFrameUps=view.findViewById(R.id.es_head1);
    }

    private void initSoftview(){
        View viewFrameUps=view.findViewById(R.id.es_head1);
    }

    private void initSIview(){
        View viewFrameUps=view.findViewById(R.id.es_head1);
    }

    private void initSHview(){
        View viewFrameUps=view.findViewById(R.id.es_head1);
    }

    private void initACeview(){
        View viewFrameUps=view.findViewById(R.id.es_head1);
    }

    private void initVideoview(){
        View viewFrameUps=view.findViewById(R.id.es_head1);
    }

    private void initCabientview(){
        View viewFrameUps=view.findViewById(R.id.es_head1);
    }


    //不是碎片不持有view  而是应该从编程语言的角度来考虑 对象它有没有在这里面实例化过
}
