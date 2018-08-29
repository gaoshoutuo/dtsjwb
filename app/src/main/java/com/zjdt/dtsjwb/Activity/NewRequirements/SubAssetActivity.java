package com.zjdt.dtsjwb.Activity.NewRequirements;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zjdt.dtsjwb.Activity.BaseActivity;
import com.zjdt.dtsjwb.R;
import com.zjdt.dtsjwb.Util.ThreadUtil;
import com.zjdt.dtsjwb.fragment.EsAssit;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class SubAssetActivity extends BaseActivity {
    private Button btnSend;
    private String json;
    private EsAssit eaFragment;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_asset);
        btnSend=f(R.id.sub_ass_send);
        btnSend.setOnClickListener(listener);
       // HashMap map = (HashMap<String, String>) (getIntent().getExtras()).getSerializable("key");
            json=   getIntent().getStringExtra("json");
            type=getIntent().getStringExtra("type");
          eaFragment=new EsAssit();
            eaFragment.setIdcid(getIntent().getStringExtra("idc_id"));
        JSONObject jsonobj=new JSONObject(json);

        switch (type){
            case "sub_idc_ups":
                eaFragment.setRid(R.layout.assit_es);
                setFragment(eaFragment);

               // jsonobj.put("asset",EsAssit.getJsonStr());
               // Log.e("debug","123"+EsAssit.getJsonStr());
               //
                break;

            case "sub_idc_air":
                eaFragment.setRid(R.layout.assit_air);
                setFragment(eaFragment);
                break;

            case "sub_idc_emi":
                eaFragment.setRid(R.layout.assit_emi);
                setFragment(eaFragment);
                break;

            case "sub_idc_ms":
                eaFragment.setRid(R.layout.assit_mon_soft);
                setFragment(eaFragment);
                break;

            case "sub_idc_mi":
                eaFragment.setRid(R.layout.assit_mon_interface);
                setFragment(eaFragment);
                break;

            case "sub_idc_mh":
                eaFragment.setRid(R.layout.assit_mon_hard);
                setFragment(eaFragment);
                break;

            case "sub_idc_mac":
                eaFragment.setRid(R.layout.assit_mon_ac);
                setFragment(eaFragment);
                break;

            case "sub_idc_mv":
                eaFragment.setRid(R.layout.assit_mon_video);
                setFragment(eaFragment);
                break;

            case "sub_idc_cab":
                eaFragment.setRid(R.layout.assit_cabient);
                setFragment(eaFragment);
                break;

            default:break;

        }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Fragment setFragment(EsAssit fragment){
        FragmentManager fm=this.getSupportFragmentManager();
        FragmentTransaction transaction=fm.beginTransaction();
        transaction.replace(R.id.assit_framelayout,fragment);
        transaction.commit();
        return fragment;
    }

    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
            switch (type){
                case "sub_idc_ups":
                    JSONObject upsobj=new JSONObject(json);
                    eaFragment.getEvEle();
                    upsobj.put("asset",EsAssit.getJsonStr());
                    Log.e("debug",upsobj.toString());
                    ThreadUtil.sat(upsobj);
                    eaFragment.setJsonStr();
                    Toast.makeText(SubAssetActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                    break;

                case "sub_idc_air":
                    JSONObject airobj=new JSONObject(json);
                    eaFragment.getEvAir();
                    airobj.put("asset",EsAssit.getJsonStr());
                    Log.e("debug",airobj.toString());
                    ThreadUtil.sat(airobj);
                    eaFragment.setJsonStr();
                    Toast.makeText(SubAssetActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                    break;

                case "sub_idc_emi":
                    JSONObject emiobj=new JSONObject(json);
                    eaFragment.getEvEmi();
                    emiobj.put("asset",EsAssit.getJsonStr());
                    Log.e("debug",emiobj.toString());
                    ThreadUtil.sat(emiobj);
                    eaFragment.setJsonStr();
                    Toast.makeText(SubAssetActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                    break;

                case "sub_idc_ms":
                    JSONObject msobj=new JSONObject(json);
                    eaFragment.getEvSoft();
                    msobj.put("asset",EsAssit.getJsonStr());
                    Log.e("debug",msobj.toString());
                    ThreadUtil.sat(msobj);
                    eaFragment.setJsonStr();
                    Toast.makeText(SubAssetActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                    break;

                case "sub_idc_mi":
                    JSONObject miobj=new JSONObject(json);
                    eaFragment.getEvInterface();
                    miobj.put("asset",EsAssit.getJsonStr());
                    Log.e("debug",miobj.toString());
                    ThreadUtil.sat(miobj);
                    eaFragment.setJsonStr();
                    Toast.makeText(SubAssetActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                    break;

                case "sub_idc_mh":
                    JSONObject mhobj=new JSONObject(json);
                    eaFragment.getEvHardware();
                    mhobj.put("asset",EsAssit.getJsonStr());
                    Log.e("debug",mhobj.toString());
                    ThreadUtil.sat(mhobj);
                    eaFragment.setJsonStr();
                    Toast.makeText(SubAssetActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                    break;

                case "sub_idc_mac":
                    JSONObject macobj=new JSONObject(json);
                    eaFragment.getEvAc();
                    macobj.put("asset",EsAssit.getJsonStr());
                    Log.e("debug",macobj.toString());
                    ThreadUtil.sat(macobj);
                    eaFragment.setJsonStr();
                    Toast.makeText(SubAssetActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                    break;

                case "sub_idc_mv":
                    JSONObject mvobj=new JSONObject(json);
                    eaFragment.getEvVideo();
                    mvobj.put("asset",EsAssit.getJsonStr());
                    Log.e("debug",mvobj.toString());
                    ThreadUtil.sat(mvobj);
                    eaFragment.setJsonStr();
                    Toast.makeText(SubAssetActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                    break;

                case "sub_idc_cab":
                    JSONObject cabobj=new JSONObject(json);
                    eaFragment.getEvCabient();
                    cabobj.put("asset",EsAssit.getJsonStr());
                    Log.e("debug",cabobj.toString());
                    ThreadUtil.sat(cabobj);
                    eaFragment.setJsonStr();
                    Toast.makeText(SubAssetActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                    break;

                    default:break;
            }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };
}
