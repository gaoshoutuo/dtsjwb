package com.zjdt.dtsjwb.Activity;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zjdt.dtsjwb.Bean.HandlerFinal;
import com.zjdt.dtsjwb.Bean.Password;
import com.zjdt.dtsjwb.NetUtil.OkhttpUtil;
import com.zjdt.dtsjwb.R;
import com.zjdt.dtsjwb.Util.JsonUtil;
import com.zjdt.dtsjwb.Util.SPUtil;

public class LoginActivity extends BaseActivity implements View.OnTouchListener{
    private EditText userEdit,passwordEdit;
    private Button register,login;
    private boolean isVisible=true;
    String []jsonField={"username","password"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        Password ppo=SPUtil.getInstance().spDataget("login_passowrd");
      if(ppo.isMarried()&&(ppo .getPassword()+ppo.getUsername()).length()>11){
          actionActivity(LoginActivity.this,MenuActivity.class,null);
      }
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

    /**
     * 当login_password为空或与它不同时 错误  当相同并且不为空  当为空时去获取网络上的数据
     *
     */
    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.register:
                    actionActivity(LoginActivity.this,RegisterActivity.class,null);
                    break;
                case R.id.login:
                    //此处需要用到一定的activity回调 卸软件要熟练 不是拼图 虽然代码是拼出来的


                    String username=userEdit.getText().toString();
                    String password=passwordEdit.getText().toString();
                    Password passwordObject= SPUtil.getInstance().spDataget("login_passowrd");
                    Log.e("jps",passwordObject.isMarried()+"");
                   // Log.e("jp",username+password);
                   if((passwordObject.getUsername()+passwordObject.getPassword()).equals("dtsj")){
                       OkhttpUtil.getUrl("http://176.122.185.2/picture/password.json");
                     //  Log.e("jp",passwordObject.getUsername()+passwordObject.getPassword()+"123123");
                       try {
                           Thread.sleep(1000);
                           //线程调度可以加快速度
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                       //假数据成功了  但是为啥会获取不到呢
                      /* String json="[{\"username\":\"18768349255\",\"password\":\"loveyqing\"},\n" +
                               "{\"username\":\"18768349255\",\"password\":\"loveyxiaoyu\"}\n" +
                               "]";*/
                      String json=HandlerFinal.json;
                       Log.e("jps",json+"111........................");
                      Password jp= JsonUtil.getInstance().parseJson2(json,jsonField);
                       Log.e("",jp.getUsername()+jp.getPassword().equals(password)+"120..................");
                       if(jp.getUsername().equals(username)&&jp.getPassword().equals(password)){

                           //记录并且登录 这是第一次登录
                           SPUtil.getInstance().spDataSet(jp,"login_passowrd");
                           actionActivity(LoginActivity.this,MenuActivity.class,null);
                       }
                       //算了记住密码还是先算了吧
                   }else if (passwordObject.isMarried()){
                       Log.e("passwordObject",11+"");
                       actionActivity(LoginActivity.this,MenuActivity.class,null);
                   }else if(passwordObject.getPassword().equals(password)&&username.equals(passwordObject.getUsername())){
                       Log.e("passwordObject",passwordObject.getUsername()+passwordObject.getPassword());
                       actionActivity(LoginActivity.this,MenuActivity.class,null);
                   }else{
                       Toast.makeText(LoginActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
                   }


                   //SPUtil.spDataSet();
                    // android不能懈怠

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
