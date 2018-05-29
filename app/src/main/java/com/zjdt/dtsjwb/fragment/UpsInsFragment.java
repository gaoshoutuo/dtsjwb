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

public class UpsInsFragment extends Fragment implements View.OnClickListener{
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
            case R.layout.ups_inspection_report_head:
                initUpsInsHeadView();
                break;

            case R.layout.ups_inspection_report_body:
                initUpsInsBodyView();
                break;
                default:break;
        }
        return view;
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

    public void initUpsFixHead(int includeId,String type){

        View view1= getIncludeView(view, includeId);
        setViewText(
                view1, R.id.d_name,initReStr(
                        new String[]{type}));//initupstest_text
    }

    //head
    public void initUpsInsHeadView(){
        initXmlStr("vps_inspection_report.xml");
        //客户资料
        initUpsFixHead(R.id.fix_inspection_1,"100");
        init4View(R.id.fix_inspection_2,new String[]{"001","002","003","004"});

        //产品信息
        initUpsFixHead(R.id.fix_inspection_3,"200");
        init4View(R.id.fix_inspection_4,new String[]{"005","006","007","008"});
        init4View(R.id.fix_inspection_5,new String[]{"009","010","888","888"});

        //电池参数
        initUpsFixHead(R.id.fix_inspection_6,"300");
        init4View(R.id.fix_inspection_7,new String[]{"011","012","013","888"});
        init4View(R.id.fix_inspection_8,new String[]{"014","015","016","017"});
        init4View(R.id.fix_inspection_9,new String[]{"018","019","020","021"});

        //电气环境参数
        initUpsFixHead(R.id.fix_inspection_10,"400");
        init4View(R.id.fix_inspection_11,new String[]{"022","023","024","025"});
        init4View(R.id.fix_inspection_12,new String[]{"222","223","024","025"});
        init4View(R.id.fix_inspection_13,new String[]{"026","027","028","029"});

        //运行参数
        initUpsFixHead(R.id.fix_inspection_14,"500");
        init4View(R.id.fix_inspection_15,new String[]{"430","431","432","035"});
        init4View(R.id.fix_inspection_16,new String[]{"530","531","532","035"});
        init4View(R.id.fix_inspection_17,new String[]{"630","631","632","035"});
        init4View(R.id.fix_inspection_17_5,new String[]{"730","731","732","888"});
        init4View(R.id.fix_inspection_17_6,new String[]{"830","831","832","888"});

        //功能测试
        initUpsFixHead(R.id.fix_inspection_18,"600");
        init4View(R.id.fix_inspection_19,new String[]{"036","037","038","039"});
        init4View(R.id.fix_inspection_20,new String[]{"040","041","042","043"});


    }


    //body
    public void initUpsInsBodyView(){
        //硬件检测
        Button button1=view.findViewById(R.id.ups_ins_engineer_sign);
        Button button2=view.findViewById(R.id.ups_ins_custom_sign);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }
    //init4view
    public void init4View(int includeId,String []type){
        View view1= getIncludeView(view, includeId);
        setViewEdit(view1,R.id.inspection_1,type[0]);
        setViewEdit(view1,R.id.inspection_2,type[1]);
        setViewEdit(view1,R.id.inspection_3,type[2]);
        setViewEdit(view1,R.id.inspection_4,type[1]);
    }

    @Override
    public void onClick(View v) {

    }
}
