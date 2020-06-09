package com.bin.xiang.java.eight;

import java.util.concurrent.*;

/**
 * <p>
 *
 * </p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>
 * </PRE>
 *
 * @author xiangbin
 * @version 1.0
 * @since 1.0
 */
public class CallableTest {

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Double> doubleFuture = executorService.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                System.out.println("do sleep ....start");
                Thread.sleep(1000 * 3);
                System.out.println("do sleep ....end");
                return 1.0;
            }
        });
        System.out.println("do others ....");
        System.out.println("===============");
        System.out.println(doubleFuture.get(5, TimeUnit.SECONDS));
        executorService.shutdown();
    }
}
