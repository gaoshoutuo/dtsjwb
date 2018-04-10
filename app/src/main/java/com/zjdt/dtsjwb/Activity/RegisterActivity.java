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

import com.zjdt.dtsjwb.R;

public class RegisterActivity extends BaseActivity implements View.OnTouchListener{
    private EditText userEdRe,passwordEdRe;
    private Button button;
    private Spinner spinner;
    private String []data={"企业客户","维保人员","管理员"};

    private void initView(){
        userEdRe=f(R.id.user_ed_register);
        passwordEdRe=f(R.id.password_ed_register);
        button=f(R.id.send);
        spinner=f(R.id.hehe);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //提交 注册的账号密码逻辑。
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
        spinner.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            //  tv.setText(data[i]);
              switch (i){
                  case 0:
                      Log.e("top",data[i]);
                      break;
                  case 1:
                      Log.e("top",data[i]);
                      break;
                  case 2:
                      Log.e("top",data[i]);
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
