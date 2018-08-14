package com.zjdt.dtsjwb.Util;

import com.zjdt.dtsjwb.Bean.HandlerFinal;
import com.zjdt.dtsjwb.NetUtil.SocketUtil;

import org.json.JSONObject;

import java.sql.Timestamp;

public class ThreadUtil<T> extends Thread{
    /**
     * 线程池 threadCurrent 或者继承线程
     * 搞懂线程大工程   java书  揭秘java web
     *
     *
     * 一个新的线程类 里面插入新的 函数Runable对象
     * 在我封装函数之前应该明确好自己要的究竟是什么， Thread.go(){  要么就是重写go方法
     * 1 函数盖上线程 2线程也就是回调  3最好形式参数
     * }
     */

    public CallBack callBack;
/*    CallBack callBack=  new CallBack(){

        @Override
        public void run() {
           go();
        }

        @Override
        public void exec() {

        }
    };*/
    public void execute(){//2
        new ThreadUtil(callBack).start();
    }
    public static void execute(CallBack c){
        new ThreadUtil(c).start();//单独2
    }
    public static void execute(CallBack c,String json){
        new ThreadUtil(c).start();//单独2
    }

    public interface CallBack extends Runnable {
        void exec();
    }
    public class CallBack2 implements Runnable {//这个电路没有意义3
        void exec(CallBack2 callBack){
            new ThreadUtil(callBack).start();
        }

        @Override
        public void run() {
            exec(new CallBack2());
        }

        public CallBack2() {
        }

    }
    /**
     * new THread(new Runable(){
     *
     *     public void run *(){
     *
     *     }
     *
     * }).start();
     * DI  但是也
     */
    public ThreadUtil(CallBack callBack) {
        super(callBack);
        this.callBack=callBack;
    }

    public ThreadUtil(CallBack2 target) {
        super(target);
    }

    public static void timeStamp(){
        HandlerFinal.timeStamp=new Timestamp(System.currentTimeMillis());
    }

    public static void sat(final JSONObject object){
        ThreadUtil.execute(new CallBack() {
            @Override
            public void exec() {

            }
            @Override
            public void run() {
                SocketUtil.sendMessageAdd("218.108.146.98",3333,object.toString());
            }
        });

    }

    public void sat2(final JSONObject object,T p){// 可惜是静态的没法  你只能先确定它能够被调用 但是没法等到确定对象 之后再确定调用对象的方法
        ThreadUtil.execute(new CallBack() {
            @Override
            public void exec() {

            }
            @Override
            public void run() {
                exec();
                SocketUtil.sendMessageAdd("218.108.146.98",3333,object.toString());
            }
        });

    }

    public static void threadCase(){
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }

}
