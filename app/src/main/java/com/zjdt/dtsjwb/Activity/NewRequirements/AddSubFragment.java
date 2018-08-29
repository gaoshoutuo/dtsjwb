package com.zjdt.dtsjwb.Activity.NewRequirements;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.zjdt.dtsjwb.App.AppApplication;
import com.zjdt.dtsjwb.R;
import com.zjdt.dtsjwb.Util.BadgeUtil;
import com.zjdt.dtsjwb.Util.JsonUtil;
import com.zjdt.dtsjwb.Util.ThreadUtil;
import com.zjdt.dtsjwb.fragment.EsAssit;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.logging.Level;

public class AddSubFragment extends Fragment {
    private View view;
    private int rid;
    private Activity mactivity;
    private String idcId;

    public String getIdcId() {
        return idcId;
    }

    public void setIdcId(String idcId) {
        this.idcId = idcId;
    }

    public Activity getMactivity() {
        return mactivity;
    }

    public void setMactivity(Activity mactivity) {
        this.mactivity = mactivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(rid,container,false);
        initViewNine(rid);
        return view;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    private void initViewNine(int rid){
        switch (rid){
            case R.layout.item_nine_select:
                initViewNS();
                break;

            case R.layout.item_nine_query:
                initViewNQ();
                break;
                default:break;
        }
    }


    private void initViewNS(){
        Button selectAssitEs,selectAssitAir,selectAssitEmi,selectAssitMonSoft,
                selectAssitMonInterface,selectAssitMonHard,selectMonAc,selectAssitMonVideo,selectAssitCabient;
      /*  Button hackAngry;
        hackAngry=view.findViewById(R.id.hack_angry);
        hackAngry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject obj=new JSONObject();
                try {
                    obj.put("au","hack");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ThreadUtil.sat(obj);
            }
        });*/
      /*  selectAssitEs=view.findViewById(R.id.select_assit_es);
        selectAssitEs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getMactivity(),"123",Toast.LENGTH_SHORT).show();
            }
        });*/


        //initview
        selectAssitEs=view.findViewById(R.id.select_assit_es);
        selectAssitAir=view.findViewById(R.id.select_assit_air);
        selectAssitEmi=view.findViewById(R.id.select_assit_emi);
        selectAssitMonSoft=view.findViewById(R.id.select_assit_mon_soft);
        selectAssitMonInterface=view.findViewById(R.id.select_assit_mon_interface);
        selectAssitMonHard=view.findViewById(R.id.select_assit_mon_hard);
        selectMonAc=view.findViewById(R.id.select_mon_ac);
        selectAssitMonVideo=view.findViewById(R.id.select_assit_mon_video);
        selectAssitCabient=view.findViewById(R.id.select_assit_cabient);

        //set listener

        selectAssitEs.setOnClickListener(listener);
        selectAssitAir.setOnClickListener(listener);
        selectAssitEmi.setOnClickListener(listener);
        selectAssitMonSoft.setOnClickListener(listener);
        selectAssitMonInterface.setOnClickListener(listener);
        selectAssitMonHard.setOnClickListener(listener);
        selectMonAc.setOnClickListener(listener);
        selectAssitMonVideo.setOnClickListener(listener);
        selectAssitCabient.setOnClickListener(listener);
    }

    private void initViewNQ(){
        Button queryAssitEs,queryAssitAir,queryAssitEmi,queryAssitMonSoft,
                queryAssitMonInterface,queryAssitMonHard,queryMonAc,queryAssitMonVideo,queryAssitCabient;
      /*  Button hackAngry;
        hackAngry=view.findViewById(R.id.hack_angry);
        hackAngry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject obj=new JSONObject();
                try {
                    obj.put("au","hack");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ThreadUtil.sat(obj);
            }
        });*/
      /*  selectAssitEs=view.findViewById(R.id.select_assit_es);
        selectAssitEs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getMactivity(),"123",Toast.LENGTH_SHORT).show();
            }
        });*/

        //initview
        queryAssitEs=view.findViewById(R.id.query_assit_es);
        queryAssitAir=view.findViewById(R.id.query_assit_air);
        queryAssitEmi=view.findViewById(R.id.query_assit_emi);
        queryAssitMonSoft=view.findViewById(R.id.query_assit_mon_soft);
        queryAssitMonInterface=view.findViewById(R.id.query_assit_mon_interface);
        queryAssitMonHard=view.findViewById(R.id.query_assit_mon_hard);
        queryMonAc=view.findViewById(R.id.query_mon_ac);
        queryAssitMonVideo=view.findViewById(R.id.query_assit_mon_video);
        queryAssitCabient=view.findViewById(R.id.query_assit_cabient);

        //set listener

        queryAssitEs.setOnClickListener(listener2);
        queryAssitAir.setOnClickListener(listener2);
        queryAssitEmi.setOnClickListener(listener2);
        queryAssitMonSoft.setOnClickListener(listener2);
        queryAssitMonInterface.setOnClickListener(listener2);
        queryAssitMonHard.setOnClickListener(listener2);
        queryMonAc.setOnClickListener(listener2);
        queryAssitMonVideo.setOnClickListener(listener2);
        queryAssitCabient.setOnClickListener(listener2);
    }

    private View.OnClickListener listener=new View.OnClickListener() {//其实我应该给他一个  固定的模板令他自己设置
        @Override
        public void onClick(View v) {
            try {
                Intent intent=new Intent(AddSubFragment.this.getActivity(),SubAssetActivity.class);
                intent.putExtra("idc_id",idcId);
            switch (v.getId()){
                case R.id.select_assit_es://ups资产添加按钮
                    //JsonObject upsJson=new JsonObject();
                    JSONObject upsObject=new JSONObject();
                    upsObject.put("au","sub_idc");
                    upsObject.put("update","sub_idc_ups");
                    upsObject.put("idcid",idcId);
                    //此处再次打开一个碎片 返回json  拆开
                    //EsAssit esAssit  重新设计Activity 来承接这玩意
                    intent.putExtra("type","sub_idc_ups");
                    intent.putExtra("json",upsObject.toString());

                    AddSubFragment.this.startActivity(intent);
                   // upsObject.put("asset","ups资产");
                    //ThreadUtil.sat(upsObject);

                    break;

                case R.id.select_assit_air://空调资产添加按钮
                    JSONObject airObject=new JSONObject();
                    airObject.put("au","sub_idc");
                    airObject.put("update","sub_idc_air");
                    airObject.put("idcid",idcId);
                    intent.putExtra("type","sub_idc_air");
                    intent.putExtra("json",airObject.toString());
                    AddSubFragment.this.startActivity(intent);
                    //ThreadUtil.sat(airObject);
                    break;

                case R.id.select_assit_emi:
                    JSONObject emiObject=new JSONObject();
                    emiObject.put("au","sub_idc");
                    emiObject.put("update","sub_idc_emi");
                    emiObject.put("idcid",idcId);
                    intent.putExtra("type","sub_idc_emi");
                    intent.putExtra("json",emiObject.toString());
                    AddSubFragment.this.startActivity(intent);
                    //ThreadUtil.sat(emiObject);
                    break;

                case R.id.select_assit_mon_soft:
                    JSONObject msObject=new JSONObject();
                    msObject.put("au","sub_idc");
                    msObject.put("update","sub_idc_ms");
                    msObject.put("idcid",idcId);
                    intent.putExtra("type","sub_idc_ms");
                    intent.putExtra("json",msObject.toString());
                    AddSubFragment.this.startActivity(intent);
                    //ThreadUtil.sat(msObject);
                    break;

                case R.id.select_assit_mon_interface:
                    JSONObject miObject=new JSONObject();
                    miObject.put("au","sub_idc");
                    miObject.put("update","sub_idc_mi");
                    miObject.put("idcid",idcId);
                    intent.putExtra("type","sub_idc_mi");
                    intent.putExtra("json",miObject.toString());
                    AddSubFragment.this.startActivity(intent);
                    break;

                case R.id.select_assit_mon_hard:
                    JSONObject mhObject=new JSONObject();
                    mhObject.put("au","sub_idc");
                    mhObject.put("update","sub_idc_mh");
                    mhObject.put("idcid",idcId);
                    intent.putExtra("type","sub_idc_mh");
                    intent.putExtra("json",mhObject.toString());
                    AddSubFragment.this.startActivity(intent);
                    break;

                case R.id.select_mon_ac:
                    JSONObject macObject=new JSONObject();
                    macObject.put("au","sub_idc");
                    macObject.put("update","sub_idc_mac");
                    macObject.put("idcid",idcId);
                    intent.putExtra("type","sub_idc_mac");
                    intent.putExtra("json",macObject.toString());
                    AddSubFragment.this.startActivity(intent);

                    break;

                case R.id.select_assit_mon_video:
                    JSONObject mvObject=new JSONObject();
                    mvObject.put("au","sub_idc");
                    mvObject.put("update","sub_idc_mv");
                    mvObject.put("idcid",idcId);
                    intent.putExtra("type","sub_idc_mv");
                    intent.putExtra("json",mvObject.toString());
                    AddSubFragment.this.startActivity(intent);
                    break;

                case R.id.select_assit_cabient:
                    JSONObject cabObject=new JSONObject();
                    cabObject.put("au","sub_idc");
                    cabObject.put("update","sub_idc_cab");
                    cabObject.put("idcid",idcId);
                    intent.putExtra("type","sub_idc_cab");
                    intent.putExtra("json",cabObject.toString());
                    AddSubFragment.this.startActivity(intent);
                    break;

                    default:break;
            }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    ;

    private View.OnClickListener listener2=new View.OnClickListener() {//其实我应该给他一个  固定的模板令他自己设置
        @Override
        public void onClick(View v) {
            try {
                Intent intent=new Intent(AddSubFragment.this.getActivity(),WatchAssetActivity.class);
                JSONObject obj=new JSONObject();obj.put("au","sub_query");
                obj.put("idcid",idcId);
                switch (v.getId()){
                    case R.id.query_assit_es:// 九的查询
                        obj.put("query","sub_ups");
                        ThreadUtil.sat(obj);
                        AddSubFragment.this.startActivity(intent);
                        break;

                    case R.id.query_assit_air:
                        obj.put("query","sub_air");
                        ThreadUtil.sat(obj);
                        AddSubFragment.this.startActivity(intent);
                        break;

                    case R.id.query_assit_emi:
                        obj.put("query","sub_emi");
                        ThreadUtil.sat(obj);
                        AddSubFragment.this.startActivity(intent);
                        break;

                    case R.id.query_assit_mon_soft:
                        obj.put("query","sub_ms");
                        ThreadUtil.sat(obj);
                        AddSubFragment.this.startActivity(intent);
                        break;

                    case R.id.query_assit_mon_interface:
                        obj.put("query","sub_mi");
                        ThreadUtil.sat(obj);

                        AddSubFragment.this.startActivity(intent);
                        break;

                    case R.id.query_assit_mon_hard:
                        obj.put("query","sub_mh");
                        ThreadUtil.sat(obj);

                        AddSubFragment.this.startActivity(intent);
                        break;

                    case R.id.query_mon_ac:
                        obj.put("query","sub_mac");
                        ThreadUtil.sat(obj);

                        AddSubFragment.this.startActivity(intent);
                        break;

                    case R.id.query_assit_mon_video:
                        obj.put("query","sub_mv");
                        ThreadUtil.sat(obj);

                        AddSubFragment.this.startActivity(intent);
                        break;

                    case R.id.query_assit_cabient:
                        obj.put("query","sub_cab");
                        ThreadUtil.sat(obj);

                        AddSubFragment.this.startActivity(intent);
                        break;

                    default:break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
}
