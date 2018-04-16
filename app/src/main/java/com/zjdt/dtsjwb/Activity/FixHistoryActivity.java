package com.zjdt.dtsjwb.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.zjdt.dtsjwb.R;
import com.zjdt.dtsjwb.Util.PopWindowUtil;

public class FixHistoryActivity extends BaseActivity implements View.OnClickListener{

    private ImageView historyAdd;
    /**
     * 也是recyclerview
     * 谁什么时候帮助哪里的客户修理了什么故障，客户态度。
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_history);
    }
    private void initview(){
        historyAdd=f(R.id.history_add);
        historyAdd.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.history_add:
                //唤醒popwindow  pop 也是addview
                PopWindowUtil popWindowUtil=   new PopWindowUtil(listener,R.layout.item_device,this);
                popWindowUtil.show();
                break;
                default:break;

        }
    }

    /**
     *  fix_send=itemView.findViewById(R.id.send_fix);
     device_spinner=itemView.findViewById(R.id.device_spinner);
     deviceID=itemView.findViewById(R.id.device_id);
     coustomerID=itemView.findViewById(R.id.coustomer_id);
     reason=itemView.findViewById(R.id.reason);
     location=itemView.findViewById(R.id.location);
     delete=itemView.findViewById(R.id.device_delete);
     */

    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.send_fix:
                    packToast("提交");break;
                case R.id.device_id:
                    packToast("设备id"); break;
                case R.id.coustomer_id:
                    packToast("客户名称");break;
                case R.id.reason:
                    packToast("坏原因");
                    break;
                case R.id.location:
                    packToast("坏地点");
                    break;
                case R.id.device_delete:
                    packToast("删除");
                    break;


                default:break;
            }
        }
    };

    private void packToast(String xxs){
        Toast.makeText(this,xxs,Toast.LENGTH_SHORT).show();
    }
}
