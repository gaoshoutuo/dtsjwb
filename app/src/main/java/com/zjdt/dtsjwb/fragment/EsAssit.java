package com.zjdt.dtsjwb.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.zjdt.dtsjwb.App.AppApplication;
import com.zjdt.dtsjwb.Bean.DevicePara;
import com.zjdt.dtsjwb.Bean.HandlerFinal;
import com.zjdt.dtsjwb.NetUtil.SocketUtil;
import com.zjdt.dtsjwb.R;
import com.zjdt.dtsjwb.Util.ThreadUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class EsAssit extends Fragment {//添加寿命只能外开手术刀来做手术了  修改view 拿对象 获取json  寿命展示view 也要跟着改
    private String idcid;


    private JSONArray ja=new JSONArray();//不能用jsonArray  不具备标识
    private static JSONObject json;
    private View view;
    private EditText idcNumber;String idctext;
    public static String getJsonStr(){
        try {
            json.put("au",HandlerFinal.STR_ASSET);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    public String getIdcid() {
        return idcid;
    }

    public void setIdcid(String idcid) {
        this.idcid = idcid;
    }

    public static void setJsonStr(){
        json=new JSONObject();
    }


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
        if (json==null)
            initJson();

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
private static void initJson(){
        json=new JSONObject();
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

            jsonObject.put("device_life",dp.getLife());
            jsonObject.put("device_yyyy",dp.getYyyy());
            jsonObject.put("device_mm",dp.getMm());
            jsonObject.put("device_dd",dp.getDd());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    private DevicePara beanGet(int rid){//这里似乎要修改的多一点了哟
        View viewOne=view.findViewById(rid);
        String nameEd=((EditText)viewOne.findViewById(R.id.ed_d_name)).getText().toString();
        String paraEd=((EditText)viewOne.findViewById(R.id.ed_d_para)).getText().toString();
        String typeEd=((EditText)viewOne.findViewById(R.id.ed_d_type)).getText().toString();
        String brandEd=((EditText)viewOne.findViewById(R.id.ed_d_brand)).getText().toString();
        String numEd=((EditText)viewOne.findViewById(R.id.ed_d_num)).getText().toString();

        String life=((EditText)viewOne.findViewById(R.id.ed_d_alive)).getText().toString();
        String yyyy=((EditText)viewOne.findViewById(R.id.ed_d_yyyy)).getText().toString();
        String mm=((EditText)viewOne.findViewById(R.id.ed_d_mm)).getText().toString();
        String dd=((EditText)viewOne.findViewById(R.id.ed_d_dd)).getText().toString();
      /* EditText nameEd= (EditText)viewOne.findViewById(R.id.ed_d_name);
       nameEd.getText();*/
      return new DevicePara(nameEd,paraEd,typeEd,brandEd,numEd,life,yyyy,mm,dd);
    }

    private void putJsonArray(int rid){
        DevicePara dp= beanGet(rid);
        JSONObject jsonObject= makeJson(dp);
        ja.put(jsonObject);
    }

    private void putJsonObj(int rid,String keyName){
        DevicePara dp= beanGet(rid);
        JSONObject jsonObject= makeJson(dp);
        try {
            this.json.put(keyName,jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // .put(jsonObject);
    }


    private void initEleview(){
       viewSet(R.id.es_head1, HandlerFinal.upsString[0]);
       viewSet(R.id.es_head2,HandlerFinal.upsString[1]);
       viewSet(R.id.es_head3,HandlerFinal.upsString[2]);
        EditText ev=view.findViewById(R.id.idc_num_ok);
        ev.getText();
    }



    public void getEvEle(){
        /*View viewOne=view.findViewById(rid);这种寻址是不是特别慢
        viewOne.getId();*/

      /* JSONObject jsonObject=new JSONObject();
       jsonObject.put()*/
        putJsonObj(R.id.es_body1,"es_body_1");

        putJsonObj(R.id.es_body2,"es_body_2");

        putJsonObj(R.id.es_body3,"es_body_3");

        // 蓄电池数量独立出来
        //DevicePara dp= beanGet(rid);
        DevicePara dp= beanGet(R.id.es_body3);
        final JSONObject jsonObject= makeJson(dp);
        try {
            jsonObject.put("au","battery_number");
            jsonObject.put("cus_id",HandlerFinal.userId);
            jsonObject.put("auau","add");
            //jsonObject.put("device_num","add");
            jsonObject.put("idc_id",getIdcid());

            Log.e("idcic_frag",getIdcid());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ThreadUtil.execute(new ThreadUtil.CallBack() {
            @Override
            public void exec() {

            }

            @Override
            public void run() {
                Log.e("batt",jsonObject.toString());
                SocketUtil.sendMessageAdd("218.108.146.98",3333,jsonObject.toString());
            }
        });
    }

    private void initAirview(){
        viewSet(R.id.air_head1,HandlerFinal.airString[0]);
        viewSet(R.id.air_head2,HandlerFinal.airString[1]);
        viewSet(R.id.air_head3,HandlerFinal.airString[2]);
        viewSet(R.id.air_head4,HandlerFinal.airString[3]);
    }

    public void getEvAir(){
        putJsonObj(R.id.air_body1,"air_body_1");

        putJsonObj(R.id.air_body2,"air_body_2");

        putJsonObj(R.id.air_body3,"air_body_3");

        putJsonObj(R.id.air_body4,"air_body_4");
    }

    private void initEmiview(){
        viewSet(R.id.emi_head1,HandlerFinal.emiString[0]);
        viewSet(R.id.emi_head1,HandlerFinal.emiString[1]);
    }

    public void getEvEmi(){
        putJsonObj(R.id.emi_body1,"emi_body_1");

        putJsonObj(R.id.emi_body2,"emi_body_2");
    }

    private void initSoftview(){
        viewSet(R.id.mon_soft_head1,HandlerFinal.softString[0]);
        viewSet(R.id.mon_soft_head2,HandlerFinal.softString[1]);
        viewSet(R.id.mon_soft_head3,HandlerFinal.softString[2]);
        viewSet(R.id.mon_soft_head4,HandlerFinal.softString[3]);
    }

    public void getEvSoft(){
        putJsonObj(R.id.mon_soft_body1,"mon_soft_body_1");

        putJsonObj(R.id.mon_soft_body2,"mon_soft_body_2");

        putJsonObj(R.id.mon_soft_body3,"mon_soft_body_3");

        putJsonObj(R.id.mon_soft_body4,"mon_soft_body_4");
    }

    private void initSIview(){
        viewSet(R.id.mon_interface_head1,HandlerFinal.interfaceString[0]);
        viewSet(R.id.mon_interface_head2,HandlerFinal.interfaceString[1]);
        viewSet(R.id.mon_interface_head3,HandlerFinal.interfaceString[2]);
        viewSet(R.id.mon_interface_head4,HandlerFinal.interfaceString[3]);
        viewSet(R.id.mon_interface_head5,HandlerFinal.interfaceString[4]);
        viewSet(R.id.mon_interface_head6,HandlerFinal.interfaceString[5]);
    }

    public void getEvInterface(){
        putJsonObj(R.id.mon_interface_body1,"mon_interface_body_1");

        putJsonObj(R.id.mon_interface_body2,"mon_interface_body_2");

        putJsonObj(R.id.mon_interface_body3,"mon_interface_body_3");

        putJsonObj(R.id.mon_interface_body4,"mon_interface_body_4");

        putJsonObj(R.id.mon_interface_body5,"mon_interface_body_5");

        putJsonObj(R.id.mon_interface_body6,"mon_interface_body_6");
    }

    private void initSHview(){
        viewSet(R.id.mon_hard_head1,HandlerFinal.hardwareString[0]);
        viewSet(R.id.mon_hard_head2,HandlerFinal.hardwareString[1]);
        viewSet(R.id.mon_hard_head3,HandlerFinal.hardwareString[2]);
        viewSet(R.id.mon_hard_head4,HandlerFinal.hardwareString[3]);
    }

    public void getEvHardware(){
        putJsonObj(R.id.mon_hard_body1,"mon_hard_body_1");

        putJsonObj(R.id.mon_hard_body2,"mon_hard_body_2");

        putJsonObj(R.id.mon_hard_body3,"mon_hard_body_3");

        putJsonObj(R.id.mon_hard_body4,"mon_hard_body_4");
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

    public void getEvAc(){
        putJsonObj(R.id.mon_ac_body1,"mon_ac_body_1");

        putJsonObj(R.id.mon_ac_body2,"mon_ac_body_2");

        putJsonObj(R.id.mon_ac_body3,"mon_ac_body_3");

        putJsonObj(R.id.mon_ac_body4,"mon_ac_body_4");

        putJsonObj(R.id.mon_ac_body5,"mon_ac_body_5");

        putJsonObj(R.id.mon_ac_body6,"mon_ac_body_6");

        putJsonObj(R.id.mon_ac_body7,"mon_ac_body_7");

        putJsonObj(R.id.mon_ac_body8,"mon_ac_body_8");
    }

    private void initVideoview(){
        viewSet(R.id.mon_video_head1,HandlerFinal.videoString[0]);
        viewSet(R.id.mon_video_head2,HandlerFinal.videoString[1]);
        viewSet(R.id.mon_video_head3,HandlerFinal.videoString[2]);
        viewSet(R.id.mon_video_head4,HandlerFinal.videoString[3]);
    }

    public void getEvVideo(){
        putJsonObj(R.id.mon_video_body1,"mon_video_body_1");

        putJsonObj(R.id.mon_video_body2,"mon_video_body_2");

        putJsonObj(R.id.mon_video_body3,"mon_video_body_3");

        putJsonObj(R.id.mon_video_body4,"mon_video_body_4");
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

    public void getEvCabient(){
        putJsonObj(R.id.cabient_body1,"cabient_body_1");

        putJsonObj(R.id.cabient_body2,"cabient_body_2");

        putJsonObj(R.id.cabient_body3,"cabient_body_3");

        putJsonObj(R.id.cabient_body4,"cabient_body_4");

        putJsonObj(R.id.cabient_body5,"cabient_body_5");

        putJsonObj(R.id.cabient_body6,"cabient_body_6");

        putJsonObj(R.id.cabient_body7,"cabient_body_7");

        putJsonObj(R.id.cabient_body8,"cabient_body_8");

        putJsonObj(R.id.cabient_body9,"cabient_body_9");

        putJsonObj(R.id.cabient_body10,"cabient_body_10");

        putJsonObj(R.id.cabient_body11,"cabient_body_11");

        putJsonObj(R.id.cabient_body12,"cabient_body_12");

        putJsonObj(R.id.cabient_body13,"cabient_body_13");

        putJsonObj(R.id.cabient_body14,"cabient_body_14");

        putJsonObj(R.id.cabient_body15,"cabient_body_15");

    }
    //不是碎片不持有view  而是应该从编程语言的角度来考虑 对象它有没有在这里面实例化过
}
