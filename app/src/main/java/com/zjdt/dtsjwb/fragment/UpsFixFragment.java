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

public class UpsFixFragment extends Fragment implements View.OnClickListener{
    private int viewId;
    private View view;
    private String xmlstr;

    public void setViewId(int viewId) {
        this.viewId = viewId;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(viewId,container,false);
        switch (viewId){
            case R.layout.ups_fix_report_head:
                initUpsFixHeadView();
                break;
            case R.layout.ups_fix_report_body:
                initUpsFixBodyView();
                break;
            case R.layout.ups_fix_report_foot:
                initUpsFixFootView();
                break;
                default:break;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    //util

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

    public void initUpsFixHead(int includeId,String []type){

        View view1= getIncludeView(view, includeId);
        setViewText(
                view1, R.id.d_name,initReStr(
                        new String[]{type[0],type[1],type[2]}));//initupstest_text

        setViewEdit(
                view1,R.id.ups_test_head1,initReStr(
                        new String[]{type[0]}));//initupstest_text

        setViewEdit(
                view1,R.id.ups_test_head1,initReStr(
                        new String[]{type[1]}));//initupstest_text

        setViewEdit(
                view1,R.id.ups_test_head1,initReStr(
                        new String[]{type[2]}));//initupstest_text
    }

    public void initUpsFixbody(int includeId,String type){
        View view1= getIncludeView(view, includeId);
        setViewText(
                view1, R.id.ups_fix_four_text,initReStr(
                        new String[]{type}));//initupstest_text

    }

    //head
    public void initUpsFixHeadView(){
        initXmlStr("vps_fix_report.xml");
        initUpsFixHead(R.id.ups_fix_head1,new String[]{"002","003","004"});
        initUpsFixHead(R.id.ups_fix_head2,new String[]{"005","006","007"});
        initUpsFixHead(R.id.ups_fix_head2,new String[]{"008","009","088"});
        initUpsFixHead(R.id.ups_fix_head2,new String[]{"010","011","012"});
    }


    //body

    public void initUpsFixBodyView(){
        initUpsFixbody(R.id.ups_fix_body1,"014");
        initUpsFixbody(R.id.ups_fix_body2,"013");
        initUpsFixbody(R.id.ups_fix_body3,"015");
        initUpsFixbody(R.id.ups_fix_body4,"016");
        View view1= getIncludeView(view, R.id.ups_fix_body5);
    }


    //foot
    public void initUpsFixFootView(){
        Button button1=view.findViewById(R.id.ups_fix_engineer_sign);
        Button button2=view.findViewById(R.id.ups_fix_custom_sign);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    //cost
    public void initCostView(View view){
       // view.findViewById()
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ups_fix_engineer_sign:

                break;
            case R.id.ups_fix_custom_sign:

                break;

                default:break;
        }
    }
}
