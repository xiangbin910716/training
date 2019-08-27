package com.bin.xiang.guaua;

import com.google.common.cache.*;

import java.util.concurrent.TimeUnit;

/**
 * <p>guava cache是一个本地缓存</p>
 * <p>优点
    线程安全的缓存，与ConcurrentMap相似，但前者增加了更多的元素失效策略，后者只能显示的移除元素。
    提供了三种基本的缓存回收方式：基于容量回收、定时回收和基于引用回收。定时回收有两种：按照写入时间，最早写入的最先回收；按照访问时间，最早访问的最早回收。
    监控缓存加载/命中情况。
    集成了多部操作，调用get方式，可以在未命中缓存的时候，从其他地方获取数据源（DB，redis），并加载到缓存中。
 * <PRE>
 *  <p>   缺点
    Guava Cache的超时机制不是精确的。
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author xiangb
 * @version 1.0
 * @Date Created in 2019年02月27日 11:40
 * @since 1.0
 */
public class LoadingCacheTest {

    public static void main(String[] args) {

        LoadingCache<Integer,Student> studentCache =
                 CacheBuilder.newBuilder()
                .concurrencyLevel(8)
                .expireAfterWrite(8, TimeUnit.SECONDS)
                .initialCapacity(10)
                .maximumSize(100)
                .recordStats()
                .removalListener(new RemovalListener<Object, Object>() {
                    @Override
                    public void onRemoval(RemovalNotification<Object, Object> removalNotification) {
                        System.out.println(removalNotification.getKey() + "was removed cause by " + removalNotification.getCause());
                    }
                }).build(new CacheLoader<Integer, Student>() {
                     @Override
                     public Student load(Integer integer) throws Exception {
                         System.out.println("load student by new");
                         Student student = new Student();
                         student.setId(integer);
                         student.setName("name" + integer);
                         return student;
                     }
                 });


    }
}
