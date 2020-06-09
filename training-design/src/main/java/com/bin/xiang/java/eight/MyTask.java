package com.bin.xiang.java.eight;

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
public class MyTask {

    private final int duration;

    public MyTask(int duration) {
        this.duration = duration;
    }

    public int calculate(){
        System.out.println(Thread.currentThread().getName());
        try{
            Thread.sleep(duration * 1000);
        }catch (final InterruptedException e){
            System.out.println("====InterruptedException======");
        }
        return duration;
    }
}
