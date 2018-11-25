package com.zjdt.dtsjwb.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dev.sacot41.scviewpager.DotsView;
import com.dev.sacot41.scviewpager.SCPositionAnimation;
import com.dev.sacot41.scviewpager.SCViewAnimation;
import com.dev.sacot41.scviewpager.SCViewAnimationUtil;
import com.dev.sacot41.scviewpager.SCViewPager;
import com.dev.sacot41.scviewpager.SCViewPagerAdapter;
import com.zjdt.dtsjwb.Bean.BeforeBean;
import com.zjdt.dtsjwb.Bean.HandlerFinal;
import com.zjdt.dtsjwb.Bean.Password;
import com.zjdt.dtsjwb.R;
import com.zjdt.dtsjwb.Util.HandlerUtil;
import com.zjdt.dtsjwb.Util.SPUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BeforeActivity extends AppCompatActivity {
   /* private ViewPager viewPager;*/
   private SCViewPager mViewPager;
   private ArrayList<BeforeBean>list=new ArrayList<>();
    private DotsView mDotsView;
    private Button into;

    //为了mainLoop
    public static BeforeActivity mainLoopActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before);
        initView();
    }

    private void initView(){
       /* viewPager=findViewById(R.id.before_viewpager);*/
        initData();
        mViewPager = (SCViewPager) findViewById(R.id.viewpager_main_activity);
   /*     mDotsView = (DotsView) findViewById(R.id.dotsview_main);
        mDotsView.setDotRessource(R.drawable.dot_selected, R.drawable.dot_unselected);
        mDotsView.setNumberOfPage(3);
*/

        Point size = SCViewAnimationUtil.getDisplaySize(this);


        PagerAdapter pagerAdapter=new AdapterScv(this,list);
        mViewPager.setAdapter(pagerAdapter);
/*
        final SCViewPagerAdapter mPageAdapter = new SCViewPagerAdapter(getSupportFragmentManager());
        mPageAdapter.setNumberOfPage(3);
        mPageAdapter.setFragmentBackgroundColor(R.color.theme_100);
        */
        mViewPager.setAdapter(pagerAdapter);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position){
                    case 0:
                        into=findViewById(R.id.in_to);
                        into.setVisibility(View.VISIBLE);
                        final int i=position;
                        into.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                           /*     Toast.makeText(BeforeActivity.this,i+"",Toast.LENGTH_SHORT).show();
                                //要删除
                                //SPUtil.getInstance().spDataSet(new Password("18768349255","loveyqing",true,"1"),"login_passowrd");
                                Password ppo= SPUtil.getInstance().spDataget("login_passowrd");

                                if ((ppo.getPassword()+ppo.getUsername()).length()<11){//加入 shared 为空或者什么 则调到注册
                                    Intent intent=new Intent(BeforeActivity.this,RegisterActivity.class);
                                    startActivity(intent);
                                }else {//
                                    Intent intent=new Intent(BeforeActivity.this,LoginActivity.class);
                                    startActivity(intent);
                                }*/
                            }
                        });
                        break;
                    case 1:
                        break;
                    case 2:
                        break;

                        default:break;
                }
            }

            @Override
            public void onPageSelected(int position) {
               // Toast.makeText(BeforeActivity.this,"被选择？？？",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
               // Toast.makeText(BeforeActivity.this,"状态改变",Toast.LENGTH_SHORT).show();
            }
        });

           //Thread.sleep(1000);

            //要删除
            //SPUtil.getInstance().spDataSet(new Password("18768349255","loveyqing",true,"1"),"login_passowrd");
        mainLoopActivity=this;
       // HandlerUtil.handler.sendMessage();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message mainLoopMsg=HandlerUtil.handler.obtainMessage();
                mainLoopMsg.what=HandlerFinal.MAIN_LOOP;
                //HandlerUtil.doHandler(mainLoopMsg);
                mainLoopMsg.sendToTarget();
            }
        }).start();

        //Lambda expressions are not supported at language level 7  好吧 android 版本不够吧 难受  一身武艺yongb

    }
    public void mainLoop(){
        Password ppo= SPUtil.getInstance().spDataget("login_passowrd");
        if ((ppo.getPassword()+ppo.getUsername()).length()<11){//加入 shared 为空或者什么 则调到注册
            Intent intent=new Intent(BeforeActivity.this,RegisterActivity.class);
            startActivity(intent);
        }else {//
            Intent intent=new Intent(BeforeActivity.this,LoginActivity.class);
            startActivity(intent);
        }
    }

    public Drawable LayoutToDrawable( int  layout_id ){

        LayoutInflater inflator = getLayoutInflater();
        View viewHelp = inflator.inflate(/*R.layout.test */ layout_id, null);

        View textView = findViewById(R.id.viewpager_main_activity);
        int size =100;
        Bitmap snapshot = convertViewToBitmap(viewHelp, size);
        Drawable drawable = (Drawable)new BitmapDrawable(snapshot);

        return drawable;

    }

    public static Bitmap convertViewToBitmap(View view, int size) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        int width = size*40;
        view.layout(0, 0, width, view.getMeasuredHeight());  //根据字符串的长度显示view的宽度
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public Drawable castto(int layoutId){
        View view=LayoutInflater.from(this).inflate(layoutId,null,false);
        view.setDrawingCacheEnabled(true);
        Bitmap ma=view.getDrawingCache();
      /*  DisplayMetrics dm = new DisplayMetrics();
        ma.setWidth(dm.widthPixels);
        ma.setHeight( dm.heightPixels);*/
        //Bitmap bitmap=Bitmap.createBitmap(ma);

        //bitmap转为Drawable对象
        Drawable drawable=new BitmapDrawable(null,ma);
/*
        RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(0, 0, 0, 0);
        view.setLayoutParams(layoutParams);*/

        //view.setBackground(drawable);
        return drawable;
    }


    private void initData(){
        BeforeBean bb1=new BeforeBean();
        bb1.setLayoutId(R.layout.item_bl_first);
        list.add(bb1);

      /*  BeforeBean bb2=new BeforeBean();
        bb2.setLayoutId(R.layout.item_bl_second);
        list.add(bb2);

        BeforeBean bb3=new BeforeBean();
        bb3.setLayoutId(R.layout.item_bl_third);
        list.add(bb3);*/

    }

    private void setDrawable(int layoutId){
        View view=LayoutInflater.from(this).inflate(layoutId,null,false);
        view.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
//下面的代码和上面的是一样的
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap=Bitmap.createBitmap(view.getDrawingCache(true));
        Drawable drawable=new BitmapDrawable(null,bitmap);
        view.destroyDrawingCache();
        view.setDrawingCacheEnabled(false);
        view.setBackground(drawable);
        //对于这种未显示的View，如果View的宽和高在布局文件中设置，在一些Android版本上getDrawingCache获取的可能为null，可以通过下面的方式解决，在代码中设置View的宽和高参数：
        RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(0, 0, 0, 0);
        view.setLayoutParams(layoutParams);
    }

    class AdapterScv extends PagerAdapter{//SCViewPagerAdapter
        private Activity context;

        private ArrayList<BeforeBean> list;


        @Override
        public int getCount() {
            return list.size();
        }

        public AdapterScv(Activity context, ArrayList list) {
           // this();
            this.context = context;
            this.list = list;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewGroup) container).removeView((View) object);

            object=null;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BeforeBean bb=list.get(position);
            View view=null;
            switch (bb.getLayoutId()){
                case R.layout.item_bl_first:
                    view= LayoutInflater.from(context).inflate(bb.getLayoutId(),null,false);

                 /*   try {
                        InputStream ims = getAssets().open("mountain.png");
                        Drawable firstDrawable=Drawable.createFromStream(ims, null);
                        firstImage.setImageDrawable(firstDrawable);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/
                    container.addView(view);
                    break;

                case R.layout.item_bl_second:
               /*     view= LayoutInflater.from(context).inflate(bb.getLayoutId(),null,false);
                    ImageView secondImage=view.findViewById(R.id.bl_second_image);
                    try {
                        InputStream ims = getAssets().open("mountain.png");
                        Drawable secondDrawable=Drawable.createFromStream(ims, null);
                       secondImage.setImageDrawable(secondDrawable);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    container.addView(view);*/
                    break;

                case R.layout.item_bl_third:
                  /*  view= LayoutInflater.from(context).inflate(bb.getLayoutId(),null,false);
                    ImageView thirdImage=view.findViewById(R.id.bl_third_image);
                    try {
                        InputStream ims = getAssets().open("sea_house.png");
                        Drawable thirdDrawable=Drawable.createFromStream(ims, null);
                        thirdImage.setImageDrawable(thirdDrawable);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    container.addView(view);*/
                    break;
                    default:break;
            }


            return view;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainLoopActivity=null;
    }
}
