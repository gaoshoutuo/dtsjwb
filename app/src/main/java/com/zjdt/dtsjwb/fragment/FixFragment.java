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
    private String xmlstr;
    private int numGroup,presentNum;
    public void setRid(int rid) {
        this.rid = rid;
    }
    public void setNumGroup(int numGroup) {
        this.numGroup = numGroup;
    }

    public void setPresentNum(int presentNum) {
        this.presentNum = presentNum;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(rid,container,false);
        //initview 环节
        switch (rid){
            case R.layout.ups_test_report_head:
                initUpsTestHeadView();
                break;

            case R.layout.ups_test_report_body:
                break;

            case R.layout.ups_test_report_foot:
                break;



                default:break;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * xmlstr 的init
     */
    public void initXmlStr(String filename){
        xmlstr= ParseXml.getFileString(getResources(), filename);
    }

    public String initReStr(String[]linkstr){
        return ParseXml.parseXMLWithPullArray(xmlstr,linkstr);
    }

    public void initUpsTestHead(int includeId,String []type){
        initXmlStr("vps_test_report.xml");
        View view1= getIncludeView(view, includeId);
        setViewText(
                view1,R.id.d_name,initReStr(
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



    //ups电池检测头
     private void initUpsTestHeadView(){
        //view.findViewById(R.id)

       /* View view1= getIncludeView(view, R.id.ups_head_1);
         setViewText(
          view1,R.id.d_name,initReStr(
           new String[]{"001","002","003"}));//initupstest_text

         setViewEdit(
          view1,R.id.ups_test_head1,initReStr(
           new String[]{"001"}));//initupstest_text

         setViewEdit(
          view1,R.id.ups_test_head1,initReStr(
           new String[]{"002"}));//initupstest_text

         setViewEdit(
          view1,R.id.ups_test_head1,initReStr(
           new String[]{"003"}));//initupstest_text*/
         initUpsTestHead(R.id.ups_head_1,new String[]{"001","002","003"});
         initUpsTestHead(R.id.ups_head_2,new String[]{"004","005","006"});
         initUpsTestHead(R.id.ups_head_3,new String[]{"007","008","010"});
         initUpsTestHead(R.id.ups_head_4,new String[]{"007","008","011"});
         initUpsTestHead(R.id.ups_head_5,new String[]{"007","009","010"});
         initUpsTestHead(R.id.ups_head_6,new String[]{"007","009","011"});
    }

    public void initUpsTestBody(int includeId,int group){
        View view1=view.findViewById(includeId);

        setViewText(view1,R.id.ups_body_h,initReStr(
                new String[]{"012"})+group+"#");

    }

    public void initUpsTestBodyView(){

            initUpsTestBody(R.id.ups_body_1,1+presentNum);
            initUpsTestBody(R.id.ups_body_2,2+presentNum);
            initUpsTestBody(R.id.ups_body_3,3+presentNum);
            initUpsTestBody(R.id.ups_body_4,4+presentNum);
            initUpsTestBody(R.id.ups_body_5,5+presentNum);
            initUpsTestBody(R.id.ups_body_6,6+presentNum);
            initUpsTestBody(R.id.ups_body_7,7+presentNum);
            initUpsTestBody(R.id.ups_body_8,8+presentNum);
            initUpsTestBody(R.id.ups_body_9,9+presentNum);
            initUpsTestBody(R.id.ups_body_10,10+presentNum);

    }


  /*  //ups电池检测体

    private void initUpsTestBodyView(){

    }

    //ups电池检测尾

    private void initUpsTestFootView(){

    }*/




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
  /*  private View f(View view,int rid){
        return view.findViewById(rid);
    }
*/
   //private void init


}
