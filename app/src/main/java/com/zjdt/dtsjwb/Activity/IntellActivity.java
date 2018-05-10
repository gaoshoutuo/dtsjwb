package com.zjdt.dtsjwb.Activity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zjdt.dtsjwb.Adapter.IntellAdapter;
import com.zjdt.dtsjwb.Bean.HandlerFinal;
import com.zjdt.dtsjwb.Bean.MenuBean;
import com.zjdt.dtsjwb.R;
import com.zjdt.dtsjwb.Util.DatabaseUtil;
import com.zjdt.dtsjwb.Util.DialogUtil;
import com.zjdt.dtsjwb.Util.FtpUtil;
import com.zjdt.dtsjwb.Util.MediumUtil;
import com.zjdt.dtsjwb.Util.ThreadUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 *          ┌─┐       ┌─┐
 *       ┌──┘ ┴───────┘ ┴──┐
 *       │                 │
 *       │       ───       │
 *       │  ─┬┘       └┬─  │
 *       │                 │
 *       │       ─┴─       │
 *       │                 │
 *       └───┐         ┌───┘
 *           │         │
 *           │         │
 *           │         │
 *           │         └──────────────┐
 *           │                        │
 *           │                        ├─┐
 *           │                        ┌─┘
 *           │                        │
 *           └─┐  ┐  ┌───────┬──┐  ┌──┘
 *             │ ─┤ ─┤       │ ─┤ ─┤
 *             └──┴──┘       └──┴──┘
 *                 神兽保佑
 *                 代码无BUG!
 *
 */

public class IntellActivity extends BaseActivity implements View.OnClickListener{
    //故障上报
    private Button sendIntell;
    private EditText errorIntell;
    private ImageView gridView;
    private ImageView addButtom;
    private String imageurl;
    // private MenuBean []menuBeans=new MenuBean[4];为啥不用string 可能因为string 的不可变性质
    private ArrayList<MenuBean>list=new ArrayList<>();
    private IntellAdapter intellAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intell);
        initView();
    }

    private void initView(){
        sendIntell=f(R.id.send_intell);
        errorIntell=f(R.id.editview4);
        gridView=f(R.id.gridview);
        addButtom=f(R.id.imageView);
        sendIntell.setOnClickListener(this);
        addButtom.setOnClickListener(this);
        gridView.setOnClickListener(this);
      /*  gridView.setColumnWidth(2);
        gridView.setColumnWidth(getColumns());
        gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        gridView.setOnItemClickListener(listener);*/

       /* intellAdapter=new IntellAdapter(list,gridView,IntellActivity.this);
        gridView.setAdapter(intellAdapter);*/
    }

    private AdapterView.OnItemClickListener listener=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_intell:
                //提交到数据库  这里可能是socketio
               String  errText=errorIntell.getText().toString();
              if(imageurl!=null){
                  imageurl="/001";
              }
               String p[]=imageurl.split("/");
               final String pathname=p[p.length-1];//今天写到这里  明天记得登录 关联那几个 静态变量
Log.e("pathname",pathname);
if (pathname=="001"){
    ThreadUtil.execute(new ThreadUtil.CallBack() {
        @Override
        public void exec() {

        }

        @Override
        public void run() {
            FtpUtil.ftpInit("192.168.1.102","21","root","123456");
            FtpUtil.uploadFile(imageurl,pathname);
        }
    });
}     //ftp模块 就不传了

                break;
            case R.id.imageView:
                //拍照
                MediumUtil.getPhoto(this);
                break;
            case R.id.gridview:
                DialogUtil.AlertDialogUtil alertDialogUtil= DialogUtil.getDialogUtil().new AlertDialogUtil(IntellActivity.this);
                alertDialogUtil.setAlertDialog("删除","取消","警告","是否需要删除！",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which==-2){
                            Toast.makeText(IntellActivity.this,"保存",Toast.LENGTH_SHORT).show();
                        }else if(which==-1){
                            Glide.with(IntellActivity.this).load("").into(gridView);
                            fileDelete("/sdcard/tuo/camera/");
                            gridView.setVisibility(View.INVISIBLE);
                        }
                    }
                });

                default:break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case HandlerFinal.MEDIA_PHOTO:
                if(resultCode==RESULT_OK){
                    Bitmap bitmap = null;
                    try {
                        bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(MediumUtil.imageUri));//data.getData();这个也不知道为啥不行  搞程序就像绣花，是个电路女红
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                   // addButtom.setImageBitmap(bitmap);

                    imageurl= MediumUtil.saveBitmap(bitmap);
                    gridView.setVisibility(View.VISIBLE);
                    Glide.with(IntellActivity.this).load(imageurl).into(gridView);//有时候也是需要全局变量的知识不要那么滥用
                    Log.e("pathimageurl",imageurl);
                /*  if(list.size()<4){
                      MenuBean menuBean=new MenuBean();
                      menuBean.setMenuname(imageurl);
                      list.add(menuBean);
                  }*/
                   /* intellAdapter=new IntellAdapter(list,gridView,IntellActivity.this);
                    gridView.setAdapter(intellAdapter);
                    intellAdapter.notifyDataSetChanged();*/
                    //gridView.setAdapter(IntellActivity.this,,); 这种notify changed 有时候会没用

                }
                break;
        }
    }

    /**
     * 获取屏幕宽度
     * @return
     */
    private int getColumns() {
        Resources resource = this.getResources();
        DisplayMetrics dm = resource.getDisplayMetrics();
        int columnWidth = dm.widthPixels / 2;
        return columnWidth;
    }
    private  void fileDelete(String path) {

        File dir = new File(path);
        if (dir.exists()) {
            File[] tmp = dir.listFiles();
            for (int i = 0; i < tmp.length; i++) {
                if (tmp[i].isDirectory()) {
                    fileDelete(path + "/" + tmp[i].getName());
                } else {
                    tmp[i].delete();
                }
            }
            dir.delete();
        }
    }
}
