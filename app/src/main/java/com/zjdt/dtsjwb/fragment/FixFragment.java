package com.zjdt.dtsjwb.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.zjdt.dtsjwb.R;
import com.zjdt.dtsjwb.Util.ParseXml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FixFragment extends Fragment{
    private View view;
    private int rid;

    public void setRid(int rid) {
        this.rid = rid;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(rid,container,false);
        //initview 环节
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    //ups电池检测头
     private void initUpsTestHeadView(){
        //view.findViewById(R.id)
        View view1= getIncludeView(view, R.id.ups_head_1);


       // String tvname= ParseXml.parseXMLWithPull(,"001");
    }

    //ups电池检测体

    private void initUpsTestBodyView(){

    }

    //ups电池检测尾

    private void initUpsTestFootView(){

    }

    //ups电池现场维修


    //ups巡检


    //工具还得重新写

    /**
     * 传入 含有include id 的view
     * @param view
     * @param rid
     * @return
     */
    private View getIncludeView(View view,int rid){
       View view2= view.findViewById(rid);
       return view2;
    }

    /**
     * 修改 textview 文字
     * @param view
     * @param name
     */

    private void setViewText(View view,int rid,String name){
       TextView tv= view.findViewById(rid);
       tv.setText(name);
    }

    /**
     *  editview hint  设置哪个include view 的editview hint
     * @param view
     * @param name
     */
    private void setViewEdit(View view,int rid, String name){
        EditText et=view.findViewById(rid);//如果findviewbyid 也是一次扫描xml的话 能不能一次扫出很多东西类
        et.setHint(name);
    }


}
