package com.bin.xiang.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
 * @Date Created in 2018年07月29日 14:44
 * @since 1.0
 */
public class ConditionTest {

    private int storage;
    private int putCounter;
    private int getCounter;
    private Lock lock = new ReentrantLock();
    private Condition putCondition = lock.newCondition();
    private Condition getCondition = lock.newCondition();

    public void put() throws InterruptedException {
        try {
            lock.lock();
            if (storage > 0) {
                putCondition.await();
            }
            storage++;
            System.out.println("put=>" + ++putCounter);
            getCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void get() throws InterruptedException {
        try {
//            lock.lock();
            lock.lock();
            if (storage <= 0) {
                getCondition.await();
            }
            storage--;
            System.out.println("get=>" + ++getCounter);
            putCondition.signal();
        } finally {
//            lock.unlock();
            lock.unlock();
        }

    }

    public class PutThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    put();
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public class GetThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    get();
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public static void main(String[] args) {
        final ConditionTest test = new ConditionTest();
        Thread put = test.new PutThread();
        Thread get = test.new GetThread();
        put.start();
        get.start();
    }

}
