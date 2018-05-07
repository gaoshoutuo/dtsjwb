package com.zjdt.dtsjwb.fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zjdt.dtsjwb.Activity.BaseActivity;

public class UpsFragment extends Fragment{
    private int layoutId;
    private BaseActivity context;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=LayoutInflater.from(context).inflate(layoutId,null,false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public int getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    @Override
    public Context getContext() {
        return context;
    }

    public void setContext(BaseActivity context) {
        this.context = context;
    }
    public void replace(Fragment fragment){
        android.support.v4.app.FragmentManager fm=context.getSupportFragmentManager();
        FragmentTransaction transaction=fm.beginTransaction();
      //  transaction.replace(,fragment);
        transaction.commit();
    }
    public class UpsView1 implements AnybeanUps{
        public UpsView1(View view) {

        }
    }



}
