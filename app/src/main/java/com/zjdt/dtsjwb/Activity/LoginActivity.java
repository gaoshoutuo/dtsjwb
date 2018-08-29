package com.zjdt.dtsjwb.Activity;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import com.zjdt.dtsjwb.NetUtil.SocketUtil;
import com.zjdt.dtsjwb.R;
import com.zjdt.dtsjwb.Util.JsonUtil;
import com.zjdt.dtsjwb.Util.SPUtil;
import com.zjdt.dtsjwb.Util.ThreadUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginActivity extends BaseActivity implements View.OnTouchListener{
    private Password jp;
    private ArrayList<Password>customList;
    private HashMap<String,String>authorthy=new HashMap<>();
    private EditText userEdit,passwordEdit;
    private Button register,login;
    private boolean isVisible=true;
    String []jsonField={"username","password","authorty"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        Password ppo=SPUtil.getInstance().spDataget("login_passowrd");
        //原来我一直改的地方错误  如果debug 直接跳出 那么说明不执行这玩意
      if(ppo.isMarried()&&(ppo .getPassword()+ppo.getUsername()).length()>11){//这里加一道关卡
          authorthy.put("au",ppo.getAuthorthm());
          Log.e("ppo",ppo.getPassword()+ppo.getUsername());
          //final String jsonLogin=ppo .getPassword()+ppo.getUsername();
          String userStr=ppo.getUsername();
          String pwdStr=ppo.getPassword();
          final JSONObject jsonLogin=new JSONObject();
          try {
              jsonLogin.put("au","login");
              jsonLogin.put("user_str",userStr);
              jsonLogin.put("pwd_str",pwdStr);
          } catch (JSONException e) {
              e.printStackTrace();
          }
          ThreadUtil.execute(new ThreadUtil.CallBack() {
              @Override
              public void exec() {

              }

              @Override
              public void run() {
                  SocketUtil.sendMessageAdd("218.108.146.98",3333,jsonLogin.toString());
              }
          });
          actionActivity(LoginActivity.this,MenuActivity.class,authorthy);
      }

      //现在的逻辑 就是登陆把 本地sp 送服务器验证 并回执


    }


    /**
     * progress material
     *
     */
    private void addFragment(int id, Fragment addFragment, Fragment removeFragment){
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.add(id, addFragment).hide(removeFragment).commit();
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
    /*
    check userid&&password
     */
    private void login(final String user,final String pass){
        ThreadUtil.execute(new ThreadUtil.CallBack() {
            @Override
            public void exec() {

            }

            @Override
            public void run() {
                JSONObject jsonLogin2=new JSONObject();
                try {
                    jsonLogin2.put("au","check_identity");
                    jsonLogin2.put("user_id",user);
                    jsonLogin2.put("password",pass);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                SocketUtil.sendMessageAdd("218.108.146.98",3333,jsonLogin2.toString());
            }
        });
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
                    Log.e("jps",passwordObject.getAuthorthm()+"");
                   // Log.e("jp",username+password);

                    OkhttpUtil.getUrl("http://218.108.146.98:88/password.json");

                    //OkhttpUtil.getUrl("http://176.122.185.2/picture/password.json");
                   if((passwordObject.getUsername()+passwordObject.getPassword()).equals("dtsj")){

                     //  Log.e("jp",passwordObject.getUsername()+passwordObject.getPassword()+"123123");
                       while (HandlerFinal.json!=null)
                       try {
                           Thread.sleep(100);
                           //线程调度可以加快速度
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                      String json=HandlerFinal.json;
                       Log.e("jps",json+"111........................");
                       //jp = JsonUtil.getInstance().parseJson2(json,jsonField);
                       customList=JsonUtil.getInstance().parseJson2(json,jsonField);
                      // Log.e("",jp.getUsername()+jp.getPassword().equals(password)+jp.getPassword()+"120..................");
                       if((jp=isInList(username,password))!=null){
                           //authorthy.clear();
                           authorthy.remove("au");
                            authorthy.put("au",jp.getAuthorthm());
                           Log.e("2yy",jp.getAuthorthm());
                           //记录并且登录 这是第一次登录
                           SPUtil.getInstance().spDataSet(jp,"login_passowrd");
                           actionActivity(LoginActivity.this,MenuActivity.class,authorthy);
                       }
                       //算了记住密码还是先算了吧
                   //}else if ((jp=isInList(passwordObject.getUsername(),passwordObject.getPassword()))!=null){
                   //}else if ((passwordObject.getPassword().equals(password)&&passwordObject.getUsername().equals(username))){
                   }/*else if(username.equals("18768349254")){

                       authorthy.put("au","2");
                       Log.e("arrou",authorthy.toString()+",,,"+passwordObject.toString());
                       actionActivity(LoginActivity.this,MenuActivity.class,authorthy);
                   }else if (passwordObject.getUsername().equals("18768349255")){
                      // authorthy.clear();
                      // SPUtil.getInstance().spDataSet(jp,"login_passowrd");
                       //authorthy.put("au",passwordObject.getAuthorthm());
                       authorthy.put("au","1");
                       Log.e("arrou",authorthy.toString()+",,,"+passwordObject.toString());
                       actionActivity(LoginActivity.this,MenuActivity.class,authorthy);
                   }*/

                   else{

                      // 1账号密码验证  2  3  4
                       JSONObject jsonObj=new JSONObject();
                       try {
                           jsonObj.put("user_id",username);
                           jsonObj.put("password",password);
                           jsonObj.put("au","check_identity");
                       } catch (JSONException e) {
                           e.printStackTrace();
                       }
                       ThreadUtil.sat(jsonObj);
                       int step=0;
                       while (HandlerFinal.isCheckUP){//这种是有弊端的  因为如果后台卡住 也就没有办法来置正  使用handler 来改变把  handler 得深入了解 并且继承
                           try {
                               step++;
                               if (step>=10){
                                   break;
                               }
                               Thread.sleep(100);
                           } catch (InterruptedException e) {
                               e.printStackTrace();
                           }
                       }
                       Log.e("arrou",authorthy.toString()+",,,"+passwordObject.toString());
                       step=0;
                       while (HandlerFinal.ide==null){//这种是有弊端的  因为如果后台卡住 也就没有办法来置正  使用handler 来改变把  handler 得深入了解 并且继承
                           try {
                               step++;
                               if (step>=50){
                                   Toast.makeText(LoginActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
                                   break;
                               }
                               Thread.sleep(100);
                           } catch (InterruptedException e) {
                               e.printStackTrace();
                           }
                       }
                       Password pwd=null;
                       if (HandlerFinal.ide.equals("企业客户")){
                           pwd=new Password(username,password,true,"2");
                           authorthy.put("au","2");
                       }

                       else if (HandlerFinal.ide.equals("维保人员")){
                           pwd=new Password(username,password,true,"1");
                           authorthy.put("au","1");
                       }

                       SPUtil.getInstance().spDataSet(pwd,"login_passowrd");

                       actionActivity(LoginActivity.this,MenuActivity.class,authorthy);
                       HandlerFinal.ide=null;
                       //Toast.makeText(LoginActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
        }
        // android不能懈怠
                    break;
        default:break;
    }
        }
    };
    private Password isInList(String username,String password){
       // if(password.getPassword())
        for(int i=0;i<customList.size();i++){
            Password temp=customList.get(i);
            if(temp.getPassword().equals(password)&&temp.getUsername().equals(username)){
                return temp;
            }
        }
        return null;
    }

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
