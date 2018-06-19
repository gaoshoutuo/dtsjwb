package com.zjdt.dtsjwb.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pnikosis.materialishprogress.ProgressWheel;
import com.zjdt.dtsjwb.R;

public class ProgressFragment extends Fragment{

    private int layoutId;
    private Activity context;

    public int getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    @Override
    public Activity getContext() {
        return context;
    }

    public void setContext(Activity context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(layoutId,container,false);
       // ProgressWheel progressWheel=view.findViewById(R.id.progress_wheel_1);
       /* ProgressWheel progressWheel=new ProgressWheel(context);
        progressWheel.setBarColor(Color.YELLOW);
        container.addView(progressWheel);*/
       //container 是context 的view
        return view;
    }
    private void initView(){
       /* ProgressWheel progressWheel=new ProgressWheel(context);

        progressWheel.setBarColor(Color.YELLOW);*/

    }
}
