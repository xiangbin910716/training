package com.bin.xiang.juc;

import java.util.PriorityQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *  先看下源码，之后有时间自己实现一个类似功能，照着写也行，感受一下编码的思路
 * @author xiangbin
 * @version 1.0
 * @Date Created in 2018年08月18日 09:45
 * @since 1.0
 */
public class ArrayBlockingQueueX {



    public static void main(String[] args) {
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(3);


        PriorityBlockingQueue priorityBlockingQueue = new PriorityBlockingQueue();

        SynchronousQueue synchronousQueue = new SynchronousQueue();


    }


}
