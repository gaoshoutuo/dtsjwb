package com.zjdt.dtsjwb.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.zjdt.dtsjwb.App.AppApplication;
import com.zjdt.dtsjwb.Bean.DevicePara;
import com.zjdt.dtsjwb.Bean.HandlerFinal;
import com.zjdt.dtsjwb.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class EsAssit extends Fragment {

    private JSONArray ja=new JSONArray();
    private View view;
    private EditText idcNumber;String idctext;

  /*  public EsAssit() {
    }


    @SuppressLint("ValidFragment")
    public EsAssit(int rid) {
        this.rid = rid;
    }
    注入有风险*/

    /**
     *  {
     "sys":{

     "ele":{
     "ups":[
     {"name":"Rufus","breed":"labrador","count":1,"twoFeet":false},
     {"name":"Marty","breed":"whippet","count":1,"twoFeet":false}
     ],
     "ups":[
     {"name":"Rufus","breed":"labrador","count":1,"twoFeet":false},
     {"name":"Marty","breed":"whippet","count":1,"twoFeet":false}
     ]

     },
     "air":{
     "ups":[
     {"name":"Rufus","breed":"labrador","count":1,"twoFeet":false},
     {"name":"Marty","breed":"whippet","count":1,"twoFeet":false}
     ],
     "ups":[
     {"name":"Rufus","breed":"labrador","count":1,"twoFeet":false},
     {"name":"Marty","breed":"whippet","count":1,"twoFeet":false}
     ]

     }


     }

     }
     正而八经的json应该长这个样子
     WatchInfo watchMap = new WatchInfo();
     watchMap.setHphm("冀A12345");
     watchMap.setCpys("xxxx");
     watchMap.setCb("BMW");
     Object json = JSONObject.toJSON(watchMap);
     // ===========================

     JSONObject objData = new JSONObject();
     objData.put("GuoChe", json);
     objData.put("code", 200);
     objData.put("msg", "success");

     System.out.println(objData.toString());  生成长json的一种方式  那我怎么解析呢 em..
     然而我选择 50种 jsonArray 这样
     arraylist 不应该想着删除 而是
     * @param rid
     */


  public void setRid(int rid){
      this.rid=rid;
  }
    private int rid;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       //View view=inflater.inflate(R.layout.assit_es,container,false);
        view=inflater.inflate(rid,container,false);
        switch (rid){
            case R.layout.assit_es:
                initEleview();
                break;

            case R.layout.assit_air:
                initAirview();
                break;

            case R.layout.assit_emi:
                initEmiview();
                break;

            case R.layout.assit_mon_soft:
                initSoftview();
                break;

            case R.layout.assit_mon_interface:
                initSIview();
                break;

            case R.layout.assit_mon_hard:
                initSHview();
                break;

            case R.layout.assit_mon_ac:
                initACview();
                break;

            case R.layout.assit_mon_video:
                initVideoview();
                break;

            case R.layout.assit_cabient:
                initCabientview();
                break;

                default:break;
        }

        return view;
    }


    /**
     * 还有一点就是反复切换碎片是新生成呢还是
     *
     * @return
     */



    private void viewSet(int rid,String name){
        View viewFrameUps=view.findViewById(rid);//这种寻址是不是特别慢
        TextView tv= viewFrameUps.findViewById(R.id.d_name);
        tv.setText(name);
    }

    public JSONObject makeJson(DevicePara dp){
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("device_name",dp.getDeviceN());
            jsonObject.put("device_para",dp.getDeviceP());
            jsonObject.put("device_type",dp.getDeviceT());
            jsonObject.put("device_brand",dp.getDeviceB());
            jsonObject.put("device_num",dp.getDeviceNum());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    private DevicePara beanGet(int rid){
        View viewOne=view.findViewById(rid);
        String nameEd=((EditText)viewOne.findViewById(R.id.ed_d_name)).getText().toString();
        String paraEd=((EditText)viewOne.findViewById(R.id.ed_d_para)).getText().toString();
        String typeEd=((EditText)viewOne.findViewById(R.id.ed_d_type)).getText().toString();
        String brandEd=((EditText)viewOne.findViewById(R.id.ed_d_brand)).getText().toString();
        String numEd=((EditText)viewOne.findViewById(R.id.ed_d_num)).getText().toString();
      /* EditText nameEd= (EditText)viewOne.findViewById(R.id.ed_d_name);
       nameEd.getText();*/
      return new DevicePara(nameEd,paraEd,typeEd,brandEd,numEd);
    }

    private void putJsonArray(int rid){
        DevicePara dp= beanGet(rid);
        JSONObject jsonObject= makeJson(dp);
        ja.put(jsonObject);
    }




    private void initEleview(){
       viewSet(R.id.es_head1, HandlerFinal.upsString[0]);
       viewSet(R.id.es_head2,HandlerFinal.upsString[1]);
       viewSet(R.id.es_head3,HandlerFinal.upsString[2]);
        EditText ev=view.findViewById(R.id.idc_num_ok);
        ev.getText();
    }



    private void getEvEle(){
        /*View viewOne=view.findViewById(rid);这种寻址是不是特别慢
        viewOne.getId();*/

      /* JSONObject jsonObject=new JSONObject();
       jsonObject.put()*/
        putJsonArray(R.id.es_body1);

        putJsonArray(R.id.es_body2);

        putJsonArray(R.id.es_body3);

    }

    private void initAirview(){
        viewSet(R.id.air_head1,HandlerFinal.airString[0]);
        viewSet(R.id.air_head2,HandlerFinal.airString[1]);
        viewSet(R.id.air_head3,HandlerFinal.airString[2]);
        viewSet(R.id.air_head4,HandlerFinal.airString[3]);
    }

    private void getEvAir(){
        putJsonArray(R.id.air_body1);

        putJsonArray(R.id.air_body2);

        putJsonArray(R.id.air_body3);

        putJsonArray(R.id.air_body4);
    }

    private void initEmiview(){
        viewSet(R.id.emi_head1,HandlerFinal.emiString[0]);
        viewSet(R.id.emi_head1,HandlerFinal.emiString[1]);
    }

    private void getEvEmi(){
        putJsonArray(R.id.emi_body1);

        putJsonArray(R.id.emi_body2);
    }

    private void initSoftview(){
        viewSet(R.id.mon_soft_head1,HandlerFinal.softString[0]);
        viewSet(R.id.mon_soft_head2,HandlerFinal.softString[1]);
        viewSet(R.id.mon_soft_head3,HandlerFinal.softString[2]);
        viewSet(R.id.mon_soft_head4,HandlerFinal.softString[3]);
    }

    private void getEvSoft(){
        putJsonArray(R.id.mon_soft_body1);

        putJsonArray(R.id.mon_soft_body2);

        putJsonArray(R.id.mon_soft_body3);

        putJsonArray(R.id.mon_soft_body4);
    }

    private void initSIview(){
        viewSet(R.id.mon_interface_head1,HandlerFinal.interfaceString[0]);
        viewSet(R.id.mon_interface_head2,HandlerFinal.interfaceString[1]);
        viewSet(R.id.mon_interface_head3,HandlerFinal.interfaceString[2]);
        viewSet(R.id.mon_interface_head4,HandlerFinal.interfaceString[3]);
        viewSet(R.id.mon_interface_head5,HandlerFinal.interfaceString[4]);
        viewSet(R.id.mon_interface_head6,HandlerFinal.interfaceString[5]);
    }

    private void getEvInterface(){
        putJsonArray(R.id.mon_interface_body1);

        putJsonArray(R.id.mon_interface_body2);

        putJsonArray(R.id.mon_interface_body3);

        putJsonArray(R.id.mon_interface_body4);

        putJsonArray(R.id.mon_interface_body5);

        putJsonArray(R.id.mon_interface_body6);
    }

    private void initSHview(){
        viewSet(R.id.mon_hard_head1,HandlerFinal.hardwareString[0]);
        viewSet(R.id.mon_hard_head2,HandlerFinal.hardwareString[1]);
        viewSet(R.id.mon_hard_head3,HandlerFinal.hardwareString[2]);
        viewSet(R.id.mon_hard_head4,HandlerFinal.hardwareString[3]);
    }

    private void getEvHardware(){
        putJsonArray(R.id.mon_hard_body1);

        putJsonArray(R.id.mon_hard_body2);

        putJsonArray(R.id.mon_hard_body3);

        putJsonArray(R.id.mon_hard_body4);
    }

    private void initACview(){
        viewSet(R.id.mon_ac_head1,HandlerFinal.acString[0]);
        viewSet(R.id.mon_ac_head2,HandlerFinal.acString[1]);
        viewSet(R.id.mon_ac_head3,HandlerFinal.acString[2]);
        viewSet(R.id.mon_ac_head4,HandlerFinal.acString[3]);
        viewSet(R.id.mon_ac_head5,HandlerFinal.acString[4]);
        viewSet(R.id.mon_ac_head6,HandlerFinal.acString[5]);
        viewSet(R.id.mon_ac_head7,HandlerFinal.acString[6]);
        viewSet(R.id.mon_ac_head8,HandlerFinal.acString[7]);
    }

    private void getEvAc(){
        putJsonArray(R.id.mon_ac_body1);

        putJsonArray(R.id.mon_ac_body2);

        putJsonArray(R.id.mon_ac_body3);

        putJsonArray(R.id.mon_ac_body4);

        putJsonArray(R.id.mon_ac_body5);

        putJsonArray(R.id.mon_ac_body6);

        putJsonArray(R.id.mon_ac_body7);

        putJsonArray(R.id.mon_ac_body8);
    }

    private void initVideoview(){
        viewSet(R.id.mon_video_head1,HandlerFinal.videoString[0]);
        viewSet(R.id.mon_video_head2,HandlerFinal.videoString[1]);
        viewSet(R.id.mon_video_head3,HandlerFinal.videoString[2]);
        viewSet(R.id.mon_video_head4,HandlerFinal.videoString[3]);
    }

    private void getEvVideo(){
        putJsonArray(R.id.mon_video_body1);

        putJsonArray(R.id.mon_video_body2);

        putJsonArray(R.id.mon_video_body3);

        putJsonArray(R.id.mon_video_body4);
    }

    private void initCabientview(){
        viewSet(R.id.cabient_head1,HandlerFinal.cabientString[0]);
        viewSet(R.id.cabient_head2,HandlerFinal.cabientString[1]);
        viewSet(R.id.cabient_head3,HandlerFinal.cabientString[2]);
        viewSet(R.id.cabient_head4,HandlerFinal.cabientString[3]);
        viewSet(R.id.cabient_head5,HandlerFinal.cabientString[4]);
        viewSet(R.id.cabient_head6,HandlerFinal.cabientString[5]);
        viewSet(R.id.cabient_head7,HandlerFinal.cabientString[6]);
        viewSet(R.id.cabient_head8,HandlerFinal.cabientString[7]);
        viewSet(R.id.cabient_head9,HandlerFinal.cabientString[8]);
        viewSet(R.id.cabient_head10,HandlerFinal.cabientString[9]);
        viewSet(R.id.cabient_head11,HandlerFinal.cabientString[10]);
        viewSet(R.id.cabient_head12,HandlerFinal.cabientString[11]);
        viewSet(R.id.cabient_head13,HandlerFinal.cabientString[12]);
        viewSet(R.id.cabient_head14,HandlerFinal.cabientString[13]);
        viewSet(R.id.cabient_head15,HandlerFinal.cabientString[14]);
    }

    private void getEvCabient(){
        putJsonArray(R.id.cabient_body1);

        putJsonArray(R.id.cabient_body2);

        putJsonArray(R.id.cabient_body3);

        putJsonArray(R.id.cabient_body4);

        putJsonArray(R.id.cabient_body5);

        putJsonArray(R.id.cabient_body6);

        putJsonArray(R.id.cabient_body7);

        putJsonArray(R.id.cabient_body8);

        putJsonArray(R.id.cabient_body9);

        putJsonArray(R.id.cabient_body10);

        putJsonArray(R.id.cabient_body11);

        putJsonArray(R.id.cabient_body12);

        putJsonArray(R.id.cabient_body13);

        putJsonArray(R.id.cabient_body14);

        putJsonArray(R.id.cabient_body15);
    }


    //不是碎片不持有view  而是应该从编程语言的角度来考虑 对象它有没有在这里面实例化过
}