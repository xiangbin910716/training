package com.bin.xiang.juc;

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
 * @Date Created in 2018年08月04日 09:50
 * @since 1.0
 */
public class ThreadLocalTest {
    private static ThreadLocal<Integer> integerThreadLocal = new ThreadLocal<Integer>() {
        @Override
        public Integer initialValue() {
            return 0;
        }
    };

    public int nexSeq(){
        integerThreadLocal.set(integerThreadLocal.get() +1);
        return integerThreadLocal.get();
    }

    private static class SeqTHread extends Thread {
        private ThreadLocalTest threadLocalTest;

        SeqTHread(ThreadLocalTest threadLocalTest){
            this.threadLocalTest = threadLocalTest;
        }

        @Override
        public void run(){
            for (int i = 0; i < 5; i++) {
                System.out.println("current thread " + Thread.currentThread().getName()
                        + "threadLocal get " + threadLocalTest.nexSeq());
            }
        }
    }

    public static void main(String[] args) {
        ThreadLocalTest threadLocalTest = new ThreadLocalTest();


        SeqTHread seqTHread1 = new SeqTHread(threadLocalTest);
        SeqTHread seqTHread2 = new SeqTHread(threadLocalTest);
        SeqTHread seqTHread3 = new SeqTHread(threadLocalTest);
        SeqTHread seqTHread4 = new SeqTHread(threadLocalTest);
        SeqTHread seqTHread5 = new SeqTHread(threadLocalTest);
        SeqTHread seqTHread6 = new SeqTHread(threadLocalTest);

        seqTHread1.start();
        seqTHread2.start();
        seqTHread3.start();
        seqTHread4.start();
        seqTHread5.start();
        seqTHread6.start();


    }




}
