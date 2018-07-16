package com.bin.xiang.Thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * <p></p>
 * <p>必须获取对象锁才能执行wait和notify方法
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author xiangbin
 * @version 1.0
 * @Date Created in 2018年07月16日 08:51
 * @since 1.0
 */
public class WaitThread {

    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) throws Exception {

        Thread wait = new Thread(new waitThreadNew(), "waitThread");
        Thread notify = new Thread(new notifyThread(), "notifyThread");
        wait.start();
        TimeUnit.SECONDS.sleep(3);
        notify.start();
    }


    static class waitThreadNew implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("enter waiting ....");
                while (flag) {
                    System.out.println("wait Thread is running...." + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                    try {
                        lock.wait();
                        System.out.println("enter waiting  exiting again....");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
//                synchronized (lock) {
//                    System.out.println("enter waiting  lock again....");
//                            lock.wait();//不能同一个锁中执行多次wait
//                }
                System.out.println("flag is false" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class notifyThread implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("notify Thread is running" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                flag = false;
                lock.notifyAll();
                try {
                    TimeUnit.SECONDS.sleep(3);
                    synchronized (lock) {
                        System.out.println("lock again " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        TimeUnit.SECONDS.sleep(3);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            synchronized (lock) {
                System.out.println("notify Thread is again synchronized" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }

        }
    }
}
