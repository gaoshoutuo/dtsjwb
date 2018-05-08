package com.zjdt.dtsjwb.Util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.zjdt.dtsjwb.Bean.HandlerFinal;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MediumUtil {
    /**
     * 涵盖 音频 视频 照片 所有方方面面的大类
     */
    /**
     * 拍照
     * @param context
     */
    public static Uri imageUri;
    public static void getPhoto(Activity context){
        File photo=new File(context.getExternalCacheDir(),"output_image.jpg");
        if (photo.exists()){
            photo.delete();
        }
        try {
            photo.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(Build.VERSION.SDK_INT>24){
            imageUri= FileProvider.getUriForFile(context,"com.zjdt.dtsjwb.fileprovider",photo);
        }else{
            imageUri=Uri.fromFile(photo);
        }
        Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        Log.e("imageUri",imageUri.toString());
        context.startActivityForResult(intent, HandlerFinal.MEDIA_PHOTO);

    }
    /**
     * 存储 bitmap
     */

    private void saveBitmap(){

    }

    public static String saveBitmap(Bitmap mBitmap) {
        File filePic;
        try {

            filePic = new File("/sdcard/tuo/camera/" +System.currentTimeMillis()+".jpg");
            if (!filePic.exists()) {
                filePic.getParentFile().mkdirs();
                filePic.createNewFile();
            } else {
                filePic.delete();
                filePic.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(filePic);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

        return filePic.getAbsolutePath();
    }


}
