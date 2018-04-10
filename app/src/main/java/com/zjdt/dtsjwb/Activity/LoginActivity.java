package com.zjdt.dtsjwb.Activity;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zjdt.dtsjwb.R;

public class LoginActivity extends BaseActivity implements View.OnTouchListener{
    private EditText userEdit,passwordEdit;
    private Button register,login;
    private boolean isVisible=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView(){
        userEdit=f(R.id.user_ed);
        passwordEdit=f(R.id.password_ed);
        register=f(R.id.register);
        login=f(R.id.login);
        register.setOnClickListener(listener);
        login.setOnClickListener(listener);
        userEdit.setOnTouchListener(this);
        passwordEdit.setOnTouchListener(this);
    }
    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.register:
                    actionActivity(LoginActivity.this,RegisterActivity.class,null);
                    break;
                case R.id.login:
                    actionActivity(LoginActivity.this,MenuActivity.class,null);
                    break;
                    default:break;
            }
        }
    };

    /**
     * 这个 其实应该弄一个接口继承好的 左上右下四个图片被设计好的放在
     * @param view
     * @param motionEvent
     * @return
     */

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Drawable []drawables=userEdit.getCompoundDrawables();
        Drawable drawable=drawables[2];
        if(drawable==null){
            return false;
        }
        if(motionEvent.getX()>userEdit.getWidth()-userEdit.getPaddingRight()-drawable.getIntrinsicWidth()){
            switch (view.getId()){
                case R.id.user_ed:
                    userEdit.setText("");
                    break;
                case R.id.password_ed:
                    //判断密码显示标志 虽然只是记忆这样太重复
                    if(isVisible){
                        isVisible=false;
                        passwordEdit.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    }else{
                        isVisible=true;
                        //这样一或就129le
                        passwordEdit.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        Log.e("dfgh",passwordEdit.getInputType()+"");
                    }
                     break;
                    default:break;

            }
        }
        return false;
    }
}
