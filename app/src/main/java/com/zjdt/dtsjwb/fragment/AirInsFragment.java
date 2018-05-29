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

public class AirInsFragment extends Fragment implements View.OnClickListener{
    private int layoutId;
    private View view;
    private String xmlstr;

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(layoutId,container,false);
        switch (layoutId){
            case R.layout.air_inspection_head:
                initAirInsHeadView();
                break;
            case R.layout.air_inspection_body:
                initAirInsBodyView();
                break;
            case R.layout.air_inspection_foot:
                initAirInsFootView();
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

    public void initUpsFixHead(int includeId,String type){
        View view1= getIncludeView(view, includeId);
        setViewText(
                view1, R.id.d_name,initReStr(
                        new String[]{type}));//initupstest_text
    }


    //head
    public void initAirInsHeadView(){
        initXmlStr("air_condition_inspection.xml");
        //客户资料
        initUpsFixHead(R.id.air_inspection_1,"100");
        init4View(R.id.air_inspection_2,new String[]{"001","002","003","004"});

        //产品信息
        initUpsFixHead(R.id.air_inspection_3,"200");
        init4View(R.id.air_inspection_4,new String[]{"005","006","007","008"});
        init4View(R.id.air_inspection_5,new String[]{"009","099","888","888"});

        //外观
        initUpsFixHead(R.id.air_inspection_6,"012");
        init4View(R.id.air_inspection_7,new String[]{"013","014","888","888"});

        //控制系统
        initUpsFixHead(R.id.air_inspection_8,"015");
        init4View(R.id.air_inspection_9,new String[]{"016","017","018","019"});
    }

    //body
    public void initAirInsBodyView(){
        //风机系统
        initUpsFixHead(R.id.air_inspection_b_1,"020");
        init4View(R.id.air_inspection_b_2,new String[]{"021","022","888","888"});
        init4View(R.id.air_inspection_b_3,new String[]{"023","024","024","024"});
        init4View(R.id.air_inspection_b_4,new String[]{"025","026","026","026"});
        init4View(R.id.air_inspection_b_5,new String[]{"027","028","028","028"});

        //制冷系统一
        initUpsFixHead(R.id.air_inspection_b_6,"300");
        init4View(R.id.air_inspection_b_7,new String[]{"029","030","031","032"});
        init4View(R.id.air_inspection_b_8,new String[]{"033","034","034","034"});
        init4View(R.id.air_inspection_b_9,new String[]{"035","036","036","036"});
        init4View(R.id.air_inspection_b_10,new String[]{"037","038","039","040"});

        //制冷系统二
        initUpsFixHead(R.id.air_inspection_b_6,"400");
        init4View(R.id.air_inspection_b_7,new String[]{"029","030","031","032"});
        init4View(R.id.air_inspection_b_8,new String[]{"033","034","034","034"});
        init4View(R.id.air_inspection_b_9,new String[]{"035","036","036","036"});
        init4View(R.id.air_inspection_b_10,new String[]{"037","038","039","040"});

    }

    //foot
    public void initAirInsFootView(){
        //加湿
        initUpsFixHead(R.id.air_inspection_f_1,"500");
        init4View(R.id.air_inspection_f_2,new String[]{"041","042","888","888"});
        init4View(R.id.air_inspection_f_3,new String[]{"043","044","044","044"});
        init4View(R.id.air_inspection_f_4,new String[]{"045","046","047","048"});

        //加热
        initUpsFixHead(R.id.air_inspection_f_5,"600");
        init4View(R.id.air_inspection_f_6,new String[]{"049","051","051","051"});
        init4View(R.id.air_inspection_f_7,new String[]{"052","051","051","051"});

        //除湿排水
        initUpsFixHead(R.id.air_inspection_f_8,"700");
        init4View(R.id.air_inspection_f_9,new String[]{"054","055","056","057"});

        Button button1=view.findViewById(R.id.air_inspection_engineer_sign);
        Button button2=view.findViewById(R.id.air_inspection_custom_sign);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

    }

    //init4
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
            case R.id.air_inspection_engineer_sign:

                break;
            case R.id.air_inspection_custom_sign:

                break;
                default:break;
        }
    }
}
