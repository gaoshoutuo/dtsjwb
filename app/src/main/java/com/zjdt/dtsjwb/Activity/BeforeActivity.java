package com.zjdt.dtsjwb.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
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
import com.zjdt.dtsjwb.R;

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
        mDotsView = (DotsView) findViewById(R.id.dotsview_main);
        mDotsView.setDotRessource(R.drawable.dot_selected, R.drawable.dot_unselected);
        mDotsView.setNumberOfPage(3);


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
                      /*try {
                            InputStream ims = getAssets().open("mountain_1.jpg");
                            Drawable firstDrawable=Drawable.createFromStream(ims, null);
                            mViewPager.setBackground(firstDrawable);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }*/
                     // mViewPager.setBackground(castto(R.layout.item_bl_third));//drawable
                       // mViewPager.addView(LayoutInflater.from(BeforeActivity.this).inflate(R.layout.item_bl_third,null,false));
                        break;

                    case 1:
                        break;

                    case 2:
                        into=findViewById(R.id.in_to);
                        into.setVisibility(View.VISIBLE);
                        final int i=position;
                        into.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(BeforeActivity.this,i+"",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(BeforeActivity.this,LoginActivity.class);
                                startActivity(intent);
                            }
                        });

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



      /*  View view = findViewById(R.id.textview_to_animate);
        SCViewAnimation viewAnimation = new SCViewAnimation(view);
        viewAnimation.startToPosition((int)(size.x*1.5), null);
        viewAnimation.addPageAnimation(new SCPositionAnimation(this, 0, -(int)(size.x*1.5), 0));
        mViewPager.addAnimation(viewAnimation);*/

        View djangoView = findViewById(R.id.before_first_image);
        SCViewAnimation djangoAnimation = new SCViewAnimation(djangoView);
        djangoAnimation.startToPosition(size.x, -size.y);
        djangoAnimation.addPageAnimation(new SCPositionAnimation(this, 0, 0, size.y));
        djangoAnimation.addPageAnimation(new SCPositionAnimation(this, 1, 0, size.y));
        mViewPager.addAnimation(djangoAnimation);

        View commonlyView = findViewById(R.id.before_second_image);
        SCViewAnimation commonlyAnimation = new SCViewAnimation(commonlyView);
        commonlyAnimation.startToPosition(size.x, null);
        commonlyAnimation.addPageAnimation(new SCPositionAnimation(this, 0, -size.x, 0));
        commonlyAnimation.addPageAnimation(new SCPositionAnimation(this, 1, -size.x, 0));
        mViewPager.addAnimation(commonlyAnimation);

        View butView = findViewById(R.id.before_third_image);
        SCViewAnimation butAnimation = new SCViewAnimation(butView);
        butAnimation.startToPosition(size.x, null);
        butAnimation.addPageAnimation(new SCPositionAnimation(this, 0, -size.x,0));
        butAnimation.addPageAnimation(new SCPositionAnimation(this, 1, -size.x,0));
        mViewPager.addAnimation(butAnimation);

        View diplomeView = findViewById(R.id.before_four_image);
        SCViewAnimation diplomeAnimation = new SCViewAnimation(diplomeView);
        diplomeAnimation.startToPosition((size.x *2), null);
        diplomeAnimation.addPageAnimation(new SCPositionAnimation(this, 1, -size.x*2,0));
        diplomeAnimation.addPageAnimation(new SCPositionAnimation(this, 2, -size.x*2 ,0));
        mViewPager.addAnimation(diplomeAnimation);

        View whyView = findViewById(R.id.before_five_image);
        SCViewAnimation whyAnimation = new SCViewAnimation(whyView);
        whyAnimation.startToPosition(size.x, null);
        whyAnimation.addPageAnimation(new SCPositionAnimation(this, 1, -size.x, 0));
        whyAnimation.addPageAnimation(new SCPositionAnimation(this, 2, -size.x, 0));
        mViewPager.addAnimation(whyAnimation);

        View futureView = findViewById(R.id.before_six_image);
        SCViewAnimation futureAnimation = new SCViewAnimation(futureView);
        futureAnimation.startToPosition(null, -size.y);
        futureAnimation.addPageAnimation(new SCPositionAnimation(this, 0, 0, size.y));
        futureAnimation.addPageAnimation(new SCPositionAnimation(this, 1, -size.x, 0));
        mViewPager.addAnimation(futureAnimation);

        View arduinoView = findViewById(R.id.before_seven_image);
        SCViewAnimation arduinoAnimation = new SCViewAnimation(arduinoView);
        arduinoAnimation.startToPosition(size.x * 2, null);
        arduinoAnimation.addPageAnimation(new SCPositionAnimation(this, 1, - size.x *2, 0));
        arduinoAnimation.addPageAnimation(new SCPositionAnimation(this, 2, - size.x, 0));
        mViewPager.addAnimation(arduinoAnimation);

        View raspberryView = findViewById(R.id.before_eight_image);
        SCViewAnimation raspberryAnimation = new SCViewAnimation(raspberryView);
        raspberryAnimation.startToPosition(-size.x, null);
        raspberryAnimation.addPageAnimation(new SCPositionAnimation(this, 0, size.x, 0));
        raspberryAnimation.addPageAnimation(new SCPositionAnimation(this, 1, -size.x, 0));
        mViewPager.addAnimation(raspberryAnimation);

        View connectedDeviceView = findViewById(R.id.before_nine_image);
        SCViewAnimation connectedDeviceAnimation = new SCViewAnimation(connectedDeviceView);
        connectedDeviceAnimation.startToPosition((int)(size.x *1.5), null);
        connectedDeviceAnimation.addPageAnimation(new SCPositionAnimation(this, 1, -(int) (size.x * 1.5), 0));
        connectedDeviceAnimation.addPageAnimation(new SCPositionAnimation(this, 2,  - size.x, 0));
        mViewPager.addAnimation(connectedDeviceAnimation);
        //mViewPager.setAdapter();
        Toast.makeText(BeforeActivity.this,mViewPager.getCurrentItem()+"",Toast.LENGTH_SHORT).show();
     /*  if( mViewPager.getCurrentItem()==2){

       }*/

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

        BeforeBean bb2=new BeforeBean();
        bb2.setLayoutId(R.layout.item_bl_second);
        list.add(bb2);

        BeforeBean bb3=new BeforeBean();
        bb3.setLayoutId(R.layout.item_bl_third);
        list.add(bb3);

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
                    view= LayoutInflater.from(context).inflate(bb.getLayoutId(),null,false);
                    ImageView secondImage=view.findViewById(R.id.bl_second_image);
                    try {
                        InputStream ims = getAssets().open("mountain.png");
                        Drawable secondDrawable=Drawable.createFromStream(ims, null);
                       secondImage.setImageDrawable(secondDrawable);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    container.addView(view);
                    break;

                case R.layout.item_bl_third:
                    view= LayoutInflater.from(context).inflate(bb.getLayoutId(),null,false);
                    ImageView thirdImage=view.findViewById(R.id.bl_third_image);
                    try {
                        InputStream ims = getAssets().open("sea_house.png");
                        Drawable thirdDrawable=Drawable.createFromStream(ims, null);
                        thirdImage.setImageDrawable(thirdDrawable);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    container.addView(view);
                    break;
                    default:break;
            }


            return view;
        }
    }

}
