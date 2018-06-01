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

import org.json.JSONException;
import org.json.JSONObject;

public class AirInsFragment extends Fragment implements View.OnClickListener{
    private int layoutId;
    private View view;
    private String xmlstr;
    private static JSONObject json;

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }
    public static String getJsonStr() {
        return json.toString();
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






    private void initJson(){
        json=new JSONObject();
    }

    private void singleStr(JSONObject json,String jsonKey,String jsonValue){
        try {
            json.put(jsonKey,jsonValue);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void singleJson(JSONObject json,String jsonKey,JSONObject jsonValue){
        try {
            json.put(jsonKey,jsonValue);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private JSONObject jsonObjMake(String[] edit){//反正str1 str2 这样子
        JSONObject jsonObject=new JSONObject();
        //jsonObject.put("str1",edit);
        for(int i=0;i<edit.length;i++){
            try {
                jsonObject.put("hard"+i,edit[i]);//完美  字符串怎么就不行呢
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }

    public String getEditData(EditText text){//获取 editView 中的文字信息
        return text.getText().toString();
    }

    private void initHeadJson(String typ,String []type,int includeId){
        JSONObject jsonObject=new JSONObject();
        View view1= getIncludeView(view, includeId);
        EditText text1= view1.findViewById(R.id.inspection_1);
        String str1= getEditData(text1);
        singleStr(jsonObject,type[0],str1);

        EditText text2= view1.findViewById(R.id.inspection_2);
        String str2= getEditData(text2);
        singleStr(jsonObject,type[1],str2);

        EditText text3= view1.findViewById(R.id.inspection_3);
        String str3= getEditData(text3);
        singleStr(jsonObject,type[2],str3);

        EditText text4= view1.findViewById(R.id.inspection_4);
        String str4= getEditData(text4);
        singleStr(jsonObject,type[3],str4);

        singleJson(this.json,typ,jsonObject);
    }

    /**
     * {
     "cus_data":{"custom_name":"123","custom_location":"123","custom_contacts":"123","phone_num":"123"},
     "product_info_1":{"brand":"123","type":"123","power":"123","pattern":"123","bar_code":"123","word_way":"123"},
     "product_info_2":{"bar_code":"123","word_way":"123","kong":"123","kong":"123"},
     "exterior":{"door_plank":"123","door_lock":"123","kong":"123","kong":"123"},
     "con_sys":{"button_alarm":"","indicator_light":"","display_record":"","menu_set":""},
     "fan_sys1":{"fan_phase_sequence":"","filter":"","kong":"","kong":""},
     "fan_sys2":{"fan_belt":"","fan1_ele_1":"","fan1_ele_2":"","fan1_ele_3":""},
     "fan_sys3":{"fan_bearing":"","fan2_ele_1":"","fan2_ele_2":"","fan2_ele_3":""},
     "fan_sys4":{"fan_contactor":"","fan3_ele_1":"","fan3_ele_2":"","fan3_ele_3":""},
     "cold_sys1_1":{"fan_control_type":"","high_pressure":"","condenser":"","low_pressure":""},
     "cold_sys1_2":{"expansion_valve":"","compressor_current_1":"","compressor_current_2":"","compressor_current_3":""},
     "cold_sys1_3":{"solenoid_valve":"","external_fan_current_1":"","external_fan_current_2":"","external_fan_current_3":""},
     "cold_sys1_4":{"liquid_lens_shows":"","high_voltage_protection_settings":"","frozen_oil_level":"","low_voltage_protection_settings":""},
     "cold_sys2_1":{"fan_control_type":"","high_pressure":"","condenser":"","low_pressure":""},
     "cold_sys2_2":{"expansion_valve":"","compressor_current_1":"","compressor_current_2":"","compressor_current_3":""},
     "cold_sys2_3":{"solenoid_valve":"","external_fan_current_1":"","external_fan_current_2":"","external_fan_current_3":""},
     "cold_sys2_4":{"liquid_lens_shows":"","high_voltage_protection_settings":"","frozen_oil_level":"","low_voltage_protection_settings":""},
     "hum_sys1":{"humidification_water":"","humidifying_steam_pipe":"","kong":"","kong":""},
     "hum_sys2":{"drain_solenoid_valve":"","humidification_current_1":"","humidification_current_2":"","humidification_current_3":""},
     "hum_sys3":{"humidifying_tank_electrode":"","Wet_the_tank":"","infrared_humidifying_lamp_tube":"","infrared_humidifying_tray":""},
     "warm_sys1":{"the_heating_switch":"","level_of_current_1":"","level_of_current_2":"","level_of_current_3":""},
     "warm_sys2":{"the_insulation_protection":"","the_secondary_current_1":"","the_secondary_current_2":"","the_secondary_current_3":""},
     "remove_hum_water":{"dehumidifying_solenoid_valve_status":"","contactor":"","humidifying_drainage":"","condensate_drain":""},
     "other":{"str1":"","str2":"","str3":"","str4":"","str5":"","str6":"","str7":""}
     }
     */
    private void makeAirInsHeadJson(){
       // initHeadJson();//1211
        initHeadJson("cus_data",new String[]{"custom_name","custom_location","custom_contacts","phone_num"},R.id.air_inspection_2);

        initHeadJson("product_info_1",new String[]{"brand","type","power","pattern"},R.id.air_inspection_4);
        initHeadJson("product_info_2",new String[]{"bar_code","word_way","kong","kong"},R.id.air_inspection_5);

        initHeadJson("exterior",new String[]{"door_plank","door_lock","kong","kong"},R.id.air_inspection_7);

        initHeadJson("con_sys",new String[]{"button_alarm","indicator_light","display_record","menu_set"},R.id.air_inspection_9);
    }

    private void makeAirInsBodyJson(){//4443
        initHeadJson("fan_sys1",new String[]{"fan_phase_sequence","filter","kong","kong"},R.id.air_inspection_b_2);
        initHeadJson("fan_sys2",new String[]{"fan_belt","fan1_ele_1","fan1_ele_2","fan1_ele_3"},R.id.air_inspection_b_3);
        initHeadJson("fan_sys3",new String[]{"fan_bearing","fan2_ele_1","fan2_ele_2","fan2_ele_3"},R.id.air_inspection_b_4);
        initHeadJson("fan_sys4",new String[]{"fan_contactor","fan3_ele_1","fan3_ele_2","fan3_ele_3"},R.id.air_inspection_b_5);

        initHeadJson("cold_sys1_1",new String[]{"fan_control_type","high_pressure","condenser","low_pressure"},R.id.air_inspection_b_7);
        initHeadJson("cold_sys1_2",new String[]{"expansion_valve","compressor_current_1","compressor_current_2","compressor_current_3"},R.id.air_inspection_b_8);
        initHeadJson("cold_sys1_3",new String[]{"solenoid_valve","external_fan_current_1","external_fan_current_2","external_fan_current_3"},R.id.air_inspection_b_9);
        initHeadJson("cold_sys1_4",new String[]{"liquid_lens_shows","high_voltage_protection_settings","frozen_oil_level","low_voltage_protection_settings"},R.id.air_inspection_b_10);

        initHeadJson("cold_sys2_1",new String[]{"fan_control_type","high_pressure","condenser","low_pressure"},R.id.air_inspection_b_12);
        initHeadJson("cold_sys2_2",new String[]{"expansion_valve","compressor_current_1","compressor_current_2","compressor_current_3"},R.id.air_inspection_b_13);
        initHeadJson("cold_sys2_3",new String[]{"solenoid_valve","external_fan_current_1","external_fan_current_2","external_fan_current_3"},R.id.air_inspection_b_14);
        initHeadJson("cold_sys2_4",new String[]{"liquid_lens_shows","high_voltage_protection_settings","frozen_oil_level","low_voltage_protection_settings"},R.id.air_inspection_b_15);


    }

    private void makeAirInsFootJson(){//321
        initHeadJson("hum_sys1",new String[]{"humidification_water","humidifying_steam_pipe","kong","kong"},R.id.air_inspection_f_2);
        initHeadJson("hum_sys2",new String[]{"drain_solenoid_valve","humidification_current_1","humidification_current_2","humidification_current_3"},R.id.air_inspection_f_3);
        initHeadJson("hum_sys3",new String[]{"humidifying_tank_electrode","wet_the_tank","infrared_humidifying_lamp_tube","infrared_humidifying_tray"},R.id.air_inspection_f_4);

        initHeadJson("warm_sys1",new String[]{"the_heating_switch","level_of_current_1","level_of_current_2","level_of_current_3"},R.id.air_inspection_b_6);
        initHeadJson("warm_sys2",new String[]{"the_insulation_protection","the_secondary_current_1","the_secondary_current_2","the_secondary_current_3"},R.id.air_inspection_b_7);

        initHeadJson("remove_hum_water",new String[]{"dehumidifying_solenoid_valve_status","contactor","humidifying_drainage","condensate_drain"},R.id.air_inspection_b_9);
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
