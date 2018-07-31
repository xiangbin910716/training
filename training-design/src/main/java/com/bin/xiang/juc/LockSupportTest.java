package com.bin.xiang.juc;

import java.util.concurrent.locks.LockSupport;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author xiangbin
 * @version 1.0
 * @Date Created in 2018年07月29日 09:14
 * @since 1.0
 */
public class LockSupportTest {
    /**
    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        LockSupport.unpark(thread);
        LockSupport.park();
        System.out.println("=======");
    }*/
    /**
    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        LockSupport.unpark(thread);
        System.out.println("aaaaaa");
        LockSupport.park();
        System.out.println("bbbb");
        LockSupport.park();
        System.out.println("CCCCCC");
        LockSupport.park();
        System.out.println("ddddd");

    }*/
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread is running");
                LockSupport.park();
                System.out.println("thrad is running again");
            }
        });

        thread.start();
        System.out.println("main start thread");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main is running");

    }

}
