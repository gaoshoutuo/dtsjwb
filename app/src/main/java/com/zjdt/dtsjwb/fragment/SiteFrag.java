package com.zjdt.dtsjwb.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zjdt.dtsjwb.R;
import com.zjdt.dtsjwb.Util.ParseXml;

public class SiteFrag extends Fragment implements View.OnClickListener{
    private int layoutId;
    private String xmlstr;
    private View view;

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(layoutId,container,false);
        switch (layoutId){
            case R.layout.site_device_install:
                initInstallView();
                break;
            case R.layout.site_device_service:
                initServiceView();
                break;
                default:break;
        }
        return view;
    }


    public void initXmlStr(String filename){
        xmlstr= ParseXml.getFileString(getResources(), filename);
    }

    public String initReStr(String[]linkstr){
        return ParseXml.parseXMLWithPullArray(xmlstr,linkstr);
    }

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
//initXmlStr("site_service.xml");
    public void setText(int includeId,String type){
        View view1= getIncludeView(view, includeId);
        setViewText(
                view1, R.id.d_name,initReStr(
                        new String[]{type}));//initupstest_text
    }



    public void initInstallView(){
        initXmlStr("site_install.xml");
        init4View(R.id.install_edit,new String[]{"055","005","006","007"});
        setText(R.id.site_device_i_2,"008");
        setText(R.id.site_device_i_3,"009");
        setText(R.id.site_device_i_4,"010");
        setText(R.id.site_device_i_5,"011");
        Button button1=view.findViewById(R.id.device_i_engineer_sign);
        Button button2=view.findViewById(R.id.device_i_custom_sign);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

    }

    public void initServiceView(){
        initXmlStr("site_service.xml");
        setText(R.id.site_device_s_2,"005");
        setText(R.id.site_device_s_2,"014");
        setText(R.id.site_device_s_2,"015");
        setText(R.id.site_device_s_2,"016");
        Button button1=view.findViewById(R.id.device_s_engineer_sign);
        Button button2=view.findViewById(R.id.device_s_custom_sign);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    public void init4View(int includeId,String []type){
        View view1= getIncludeView(view, includeId);
        setViewEdit(view1, R.id.inspection_1,type[0]);
        setViewEdit(view1,R.id.inspection_2,type[1]);
        setViewEdit(view1,R.id.inspection_3,type[2]);
        setViewEdit(view1,R.id.inspection_4,type[1]);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.device_i_engineer_sign:
                    break;

            case R.id.device_i_custom_sign:
                break;

            case R.id.device_s_engineer_sign:
                break;

            case R.id.device_s_custom_sign:
                break;

                    default:break;
        }
    }
}
