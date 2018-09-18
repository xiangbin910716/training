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
 * @Date Created in 2018年08月05日 08:57
 * @since 1.0
 */
public class ThreadLocalMap2Test {

    private static ThreadLocal<Integer> integerThreadLocal = new ThreadLocal<Integer>() {
        @Override
        public Integer initialValue() {
            return 0;
        }
    };

    public Integer nextInteger() {
        integerThreadLocal.set(integerThreadLocal.get() + 1);
        return integerThreadLocal.get();
    }

    private static ThreadLocal<String> stringThreadLocal = new ThreadLocal<String>() {
        @Override
        public String initialValue() {
            return "a";
        }
    };

    public String nextString() {
        stringThreadLocal.set(stringThreadLocal.get() + integerThreadLocal.get());
        return stringThreadLocal.get();
    }

    static class SQThread extends Thread {
        private ThreadLocalMap2Test threadLocalMap2Test;

        SQThread(ThreadLocalMap2Test threadLocalMap2Test) {
            this.threadLocalMap2Test = threadLocalMap2Test;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println("Integer is " + threadLocalMap2Test.nextInteger()
                        + "String is " + threadLocalMap2Test.nextString());
            }
        }
    }

    public static void main(String[] args) {
        ThreadLocalMap2Test threadLocalMap2Test = new ThreadLocalMap2Test();
        SQThread sqThread = new SQThread(threadLocalMap2Test);
        sqThread.start();
    }




}
