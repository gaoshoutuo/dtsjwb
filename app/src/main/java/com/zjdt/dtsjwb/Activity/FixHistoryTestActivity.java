package com.zjdt.dtsjwb.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.zjdt.dtsjwb.NetUtil.OkhttpUtil;
import com.zjdt.dtsjwb.NetUtil.SocketUtil;
import com.zjdt.dtsjwb.R;
import com.zjdt.dtsjwb.Util.Algorithm;
import com.zjdt.dtsjwb.Util.DatabaseUtil;
import com.zjdt.dtsjwb.Util.FtpUtil;
import com.zjdt.dtsjwb.Util.PopWindowUtil;
import com.zjdt.dtsjwb.Util.ThreadUtil;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

public class FixHistoryTestActivity extends BaseActivity implements View.OnClickListener{

    private ImageView historyAdd;
    private Button addDatabase;
    private DatabaseUtil.MyDatabase myDatabase;
    private Button testFtp;
    private Button testwebview,testSocketUtil,testPost,testSelect,testUpdate;

    /**
     * 也是recyclerview
     * 谁什么时候帮助哪里的客户修理了什么故障，客户态度。
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_history);
        initview();
    }
    private void initview(){
        testSelect=f(R.id.test_select);
        testUpdate=f(R.id.test_update);
        testSelect.setOnClickListener(this);
        testUpdate.setOnClickListener(this);

        testSocketUtil=f(R.id.test_socketutil);
        testSocketUtil.setOnClickListener(this);
        testPost=f(R.id.test_post);
        testPost.setOnClickListener(this);

        testFtp=f(R.id.test_ftp);
        testFtp.setOnClickListener(this);
        historyAdd=f(R.id.history_add);
        historyAdd.setOnClickListener(this);
        addDatabase=f(R.id.database_add);
        addDatabase.setOnClickListener(this);
        testwebview=f(R.id.test_webview);
        testwebview.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.history_add:
                //唤醒popwindow  pop 也是addview
                PopWindowUtil popWindowUtil=   new PopWindowUtil(listener,R.layout.item_device,this);
                popWindowUtil.show();
                break;
            case R.id.database_add:
              //  myDatabase=new DatabaseUtil.MyDatabase(this,"DTSJ.db",null,1); 内部成员类是静态  内部类需要先创建外部类对象 静态对象照样调用方法 只不过如果那个静态对象是4构造器生成的 而不是5构造器生成 缺少一个 参数就会出错吧
                //回去做一个 4构造 5构造 单例实验
                myDatabase=DatabaseUtil.getInstance().new MyDatabase(this,"DTSJ.db",null,1);
                myDatabase.getWritableDatabase();
                break;
            case R.id.test_ftp:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FtpUtil.cleanSf();
                            FtpUtil.ftpInit("176.122.185.2","21","zth1","zth1");
                            FTPClient ftpClient= FtpUtil.getFtpClient();


                           // ftpClient.changeWorkingDirectory("video1");
                           FTPFile []ftpFile= ftpClient.listFiles();
                           // Log.e("ftputil",Arrays.toString(ftpFile));//没问题啊 确实就是当前目录
                            Log.e("ftputil",FtpUtil.getAllPath(ftpFile));
                          /*  int []a={95, 45, 15, 78, 84, 51, 24, 12};
                            Algorithm.sort(a);
                            Log.e("heheheh", Arrays.toString(a));*/
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                break;
            case R.id.test_webview:
                FixHistoryTestActivity.this.startActivity(new Intent(FixHistoryTestActivity.this,WebviewActivity.class));
                break;

            case R.id.test_socketutil:
                new Thread(new Runnable() {
                    @Override
                    public void run() {// 需要一个回调的线程放置我的方法
                        String json="{\"au\":\"add\",\"pwd\":\"123456\",\"name\":\"dtsj\",\"email\":\"dtsj@qq.com\"}";
                       SocketUtil.sendMessageAdd("192.168.1.102",3333,json);
                    }
                }).start();
                break;
            case R.id.test_post:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        /* String json="[{\"doctor_pic\":\"http://176.122.185.2/picture/doctor.jpg\",\"doctor_intelligence\":\"name|age|job\"},\n" +
                                 "{\"doctor_pic\":\"http://176.122.185.2/picture/doctor-001.jpg\",\"doctor_intelligence\":\"name|age|job\"},\n" +
                                 "{\"doctor_pic\":\"http://176.122.185.2/picture/doctor-002.jpg\",\"doctor_intelligence\":\"name|age|job\"},\n" +
                                 "{\"doctor_pic\":\"http://176.122.185.2/picture/doctor-003.jpg\",\"doctor_intelligence\":\"name|age|job\"},\n" +
                                 "{\"doctor_pic\":\"http://176.122.185.2/picture/doctor-004.jpg\",\"doctor_intelligence\":\"name|age|job\"},\n" +
                                 "{\"doctor_pic\":\"http://176.122.185.2/picture/doctor-005.jpg\",\"doctor_intelligence\":\"name|age|job\"},\n" +
                                 "{\"doctor_pic\":\"http://176.122.185.2/picture/doctor-006.jpg\",\"doctor_intelligence\":\"name|age|job\"},\n" +
                                 "{\"doctor_pic\":\"http://176.122.185.2/picture/doctor-007.jpg\",\"doctor_intelligence\":\"name|age|job\"},\n" +
                                 "{\"doctor_pic\":\"http://176.122.185.2/picture/doctor-008.jpg\",\"doctor_intelligence\":\"name|age|job\"}]";*/
                        String json="{\"au\":\"add\",\"age\":\"88\",\"name\":\"hetao\"}";
                        String url="http://192.168.1.102:3333";
                        OkhttpUtil.postJson(json,url);
                  //  OkhttpUtil.getUrl("http://192.168.1.68:3333");  okhttp post 有时候能收到 有时候收不到 时延好久  通过socket很快  通过okhttp很慢
                    }
                }).start();
                break;
            case R.id.test_select:
                ThreadUtil.execute(new ThreadUtil.CallBack() {
                    @Override
                    public void exec() {

                    }

                    @Override
                    public void run() {
                        //这就是函数不能当值传递的弊了
                        SocketUtil.sendMessageAdd("192.168.1.102",3333,"{\"au\":\"select\",\"age\":\"88\",\"name\":\"hetao\"}");
                    }
                });

                break;


            case R.id.test_update:
             /*   ThreadUtil.execute(new ThreadUtil.CallBack() {
                    @Override
                    public void exec() {

                    }

                    @Override
                    public void run() {
                        JSONObject jsonObject=new JSONObject();
                        try {
                            jsonObject.put("au","register2");
                            jsonObject.put("data","中文中文");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        SocketUtil.sendMessageAdd("192.168.1.102",3333,jsonObject.toString());
                    }
                });*/
                actionActivity(FixHistoryTestActivity.this,SignActivity.class,null);


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
