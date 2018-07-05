package com.zjdt.dtsjwb.Activity;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.zjdt.dtsjwb.Bean.HandlerFinal;
import com.zjdt.dtsjwb.Bean.Password;
import com.zjdt.dtsjwb.NetUtil.SocketUtil;
import com.zjdt.dtsjwb.R;
import com.zjdt.dtsjwb.Util.SPUtil;
import com.zjdt.dtsjwb.Util.ThreadUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class RegisterActivity extends BaseActivity implements View.OnTouchListener{
    private EditText userEdRe,passwordEdRe;
    private EditText editName,editLocation,editCompany;
    private Button button;
    private Spinner spinner;
    private String []data={"企业客户","维保人员","销售人员"};
    private String sData="企业客户";

    private void initView(){
        editLocation=f(R.id.edit_location);
        editCompany=f(R.id.edit_company);
        editName=f(R.id.edit_name);
        userEdRe=f(R.id.user_ed_register);
        passwordEdRe=f(R.id.password_ed_register);
        button=f(R.id.send);
        spinner=f(R.id.hehe);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //提交 注册的账号密码逻辑。
                final JSONObject jsonObject=new JSONObject();
                String userText=userEdRe.getText().toString();
                String password=passwordEdRe.getText().toString();
                String name=editName.getText().toString();
                String location=editLocation.getText().toString();
                String company=editCompany.getText().toString();
                Password jp=null;
                if (sData.equals(data[1])){
                    jp=new Password(userText,password,false,"1");
                }else if(sData.equals(data[0])){
                    jp=new Password(userText,password,false,"2");
                }

                SPUtil.getInstance().spDataSet(jp,"login_passowrd");

               // HashMap<String,String>map=new HashMap<>();
                try {
                    jsonObject.put("au", HandlerFinal.AU_REGISTER);
                    jsonObject.put("user",userText);
                    jsonObject.put("pwd",password);
                    jsonObject.put("location",location);
                    jsonObject.put("company",company);
                   //此处测试到底是不是我的问题
                    jsonObject.put("identity",sData);
                    jsonObject.put("name",name);
// final 后不能直向其他对象，但是字段可以设置
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e("register",jsonObject.toString());
                ThreadUtil.execute(new ThreadUtil.CallBack() {
                    @Override
                    public void exec() {

                    }

                    @Override
                    public void run() {
                       // SocketUtil.sendMessageAdd("192.168.1.102",3333,jsonObject.toString());
                        SocketUtil.sendMessageAdd("218.108.146.98",3333,jsonObject.toString());

                    }
                });


            }
        });
        userEdRe.setOnTouchListener(this);
        passwordEdRe.setOnTouchListener(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
      // final TextView tv=f(R.id.tv);
        spinner.setAdapter(new ArrayAdapter<String>(this,R.layout.spinner_display_style,R.id.txtvwSpinner,data));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            //  tv.setText(data[i]);
              switch (i){
                  case 0:
                      Log.e("top",data[i]);
                      sData=data[i];
                      break;
                  case 1:
                      Log.e("top",data[i]);
                      sData=data[i];
                      break;
                  case 2:
                      Log.e("top",data[i]);
                      sData=data[i];
                      break;
                      default:break;
              }
          }

          @Override
          public void onNothingSelected(AdapterView<?> adapterView) {

          }
      });
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //此处有些不严谨 但是鉴于两个ed的drawable 尺寸一致，view的设置也一致也就没关系了
        Drawable []drawables=userEdRe.getCompoundDrawables();
        Drawable drawable=drawables[2];
        if(drawables[2]==null){
            return false;
        }//这样写代码就是搬砖啊
        if(motionEvent.getAction()!=MotionEvent.ACTION_UP){
            return false;
        }
        if(motionEvent.getX()>userEdRe.getWidth()-drawable.getIntrinsicWidth()-userEdRe.getPaddingRight()){
            switch (view.getId()){
                case R.id.user_ed_register:
                    userEdRe.setText("");
                    break;
                case R.id.password_ed_register:
                    passwordEdRe.setText("");
                    break;
                    default:break;
            }
        }
        return false;
    }

}
