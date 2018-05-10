package com.zjdt.dtsjwb.App;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.zjdt.dtsjwb.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class SignView extends View {

    private Paint linePaint;// 画笔

    private ArrayList<Path> lines;// 写字的笔迹，支持多笔画
    private int lineCount;// 记录笔画数目

    private final int DEFAULT_LINE_WIDTH = 10;// 默认笔画宽度

    private int lineColor = Color.BLACK;// 默认字迹颜色（黑色）
    private float lineWidth = DEFAULT_LINE_WIDTH;// 笔画宽度

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SignView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        if (attrs != null) {
            TypedArray tArray = context.obtainStyledAttributes(attrs, R.styleable.SignView, defStyleAttr, defStyleRes);
            parseTyepdArray(tArray);
        }
        initLinePaint();
        lines = new ArrayList<Path>();
    }

    public SignView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null) {
            TypedArray tArray = context.obtainStyledAttributes(attrs, R.styleable.SignView, defStyleAttr, 0);
            parseTyepdArray(tArray);
        }
        initLinePaint();
        lines = new ArrayList<Path>();
    }

    /**
     * @param context
     * @param attrs
     */
    public SignView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (attrs != null) {
            TypedArray tArray = context.obtainStyledAttributes(attrs, R.styleable.SignView);
            parseTyepdArray(tArray);
        }
        initLinePaint();
        lines = new ArrayList<Path>();
    }

    /**
     * @param context
     */
    public SignView(Context context) {
        super(context);
        initLinePaint();
        lines = new ArrayList<Path>();
    }
    private void parseTyepdArray(TypedArray tArray) {
        lineColor = tArray.getColor(R.styleable.SignView_lineColor, Color.BLACK);
        lineWidth = tArray.getDimension(R.styleable.SignView_lineWidth, DEFAULT_LINE_WIDTH);
    }
    public Bitmap getImage() {
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        /**
         * 绘制背景
         */
        Drawable bgDrawable = getBackground();
        if (bgDrawable != null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        draw(canvas);// 绘制view视图的内容
        return bitmap;
    }//Content.getExternalCacheDir();
    public boolean saveImageToFile(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            getImage().compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }
    public void clearPath() {
        lines.removeAll(lines);
        invalidate();
    }

    private void initLinePaint() {
        linePaint = new Paint();
        linePaint.setColor(lineColor);
        linePaint.setStrokeWidth(lineWidth);
        linePaint.setStrokeCap(Paint.Cap.ROUND);
        linePaint.setPathEffect(new CornerPathEffect(50));
        linePaint.setStyle(Paint.Style.STROKE);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (lines != null && lines.size() > 0) {
            for (Path path : lines)
                canvas.drawPath(path, linePaint);
        }
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
        linePaint.setColor(lineColor);
    }


    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
        linePaint.setStrokeWidth(lineWidth);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /**
         * 考虑到这个view会出现在lib工程里，因此使用if else
         */
        if (event.getAction() == MotionEvent.ACTION_DOWN) {// 按下这个屏幕
            Path path = new Path();
            path.moveTo(event.getX(), event.getY());
            lines.add(path);
            lineCount = lines.size();
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {// 在屏幕上移动
            lines.get(lineCount - 1).lineTo(event.getX(), event.getY());
            invalidate();
        } else {

        }
        return true;
    }
    }
