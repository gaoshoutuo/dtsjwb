package com.zjdt.dtsjwb.Util;

import android.util.Log;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FtpUtil {

    /**
     * 做ftp的工具箱
     */
    private static StringBuffer sf=new StringBuffer();
    public static FTPClient ftpClient;
    public static FTPClient getFtpClient(){
        return ftpClient;
    }

    public static void ftpInit(String url, String port, String username,String password){
        ftpClient=new FTPClient();
        try {
            ftpClient.connect(url,Integer.parseInt(port));
            boolean request=ftpClient.login(username,password);
            Log.e("requestftp",request+"");
            int x= ftpClient.getReplyCode();
            if(request&& FTPReply.isPositiveCompletion(x)){
                Log.e("登陆成功","登录成功");
                ftpClient.setBufferSize(1024);
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回ftp文件遍历目录
     * @param path
     * @return
     * 修改 ftpfile 成file
     */
    public static StringBuffer getAllPath(String path){




        return sf;
    }


    /**
     * 遇到困难 没法在1 没结束直接跳2
     * @param ftpFile
     * @return
     */
    public static String getAllPath(FTPFile []ftpFile){

        for(FTPFile ftp:ftpFile){
            try {
                if(ftp.isDirectory()){
                    ftpClient.changeWorkingDirectory(ftp.getName());
                    FTPFile[] ftpFileNew = ftpClient.listFiles();
                    if (null==ftpFileNew[0]) {
                        ftpClient.changeToParentDirectory();
                       // continue; 似乎必要不大
                    } else {
                        getAllPath(ftpFileNew);
                    }
                }else if(ftp.isFile()){
                   // if (ftp.getName().contains(".mp4")){
                        sf.append(ftp.getName()).append("---");
                 //   }

                }
            }catch (IOException e) {
                e.printStackTrace();
            }

        }

        return sf.toString();
    }
    /**
     * 上传
     *
     */


    public static void uploadFile(String filename,String filepath) {
        FileInputStream fiss = null;
            try {
                fiss = new FileInputStream(filename);//这里多此一举 不要file
               // ftpClient.enterLocalPassiveMode();
              boolean istrue=  ftpClient.storeFile(filepath, fiss);
              Log.e("upload",istrue+"");
                //   Log.e("workd",ftpClient.printWorkingDirectory());
                fiss.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    /**
     * 下载
     * 这中间必然修改workdir
     */
    public static void downloadFile(String filename){
        FileOutputStream out=null;
        try {
            out = new FileOutputStream(new File(filename));
            ftpClient.retrieveFile(filename, out);
            //   Log.e("workd",ftpClient.printWorkingDirectory());
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建文件夹
     *
     *   ftpClient.makeDirectory(lagalfile);
         ftpClient.changeWorkingDirectory(lagalfile);
     */
    private static void ftpMkdir(String filename){
        try {
            ftpClient.sendCommand("XMKD " + filename);
            ftpClient.changeWorkingDirectory(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 删除文件
     */
    private static void deleteFile(){

    }

    public static void cleanSf(){
        sf.setLength(0);
    }

    public static void disconnectFtp(){
        try {
            ftpClient.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
