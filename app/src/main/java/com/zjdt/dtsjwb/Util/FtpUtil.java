package com.zjdt.dtsjwb.Util;

import android.util.Log;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;

public class FtpUtil {

    /**
     * 做ftp的工具箱
     */

    public static FTPClient ftpClient=new FTPClient();
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
    public StringBuffer getAllPath(FTPFile ftpFile){
        StringBuffer sf=new StringBuffer();





        return sf;
    }

}
