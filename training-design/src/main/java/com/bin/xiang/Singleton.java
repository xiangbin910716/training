package com.bin.xiang;

/**
 * <p></p>
 * <p> @see https://www.cnblogs.com/java-my-life/archive/2012/03/31/2425631.html
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author xiangbin
 * @version 1.0
 * @Date Created in 2018年07月07日 17:06
 * @since 1.0
 */
public class Singleton {

    private Singleton() {};//私有构造器

    /*懒汉式

    private static Singleton instance = null;

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    } */

     /*饿汉式
    private Singleton() {};//私有构造器

    private static Singleton instance = new Singleton();//私有成员变量

    public static Singleton getInstance() { //静态共有方法获取实例
        return instance;
    } */

     /*双重锁

    /**
     * 注意：在java1.4及以前版本中，很多JVM对于volatile关键字的实现的问题，
     *会导致“双重检查加锁”的失败，因此“双重检查加锁”机制只只能用在java5及以上的版本
     * volatile修饰保证多线程内存可见性，
    private volatile static Singleton instance = null;

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }*/

    /** Lazy initialization holder class模式
     *　这个模式综合使用了Java的类级内部类和多线程缺省同步锁的知识，很巧妙地同时实现了延迟加载和线程安全。
     *  类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例
     *  没有绑定关系，而且只有被调用到时才会装载，从而实现了延迟加载。
     */
    private static class SingletonHolder{
        /**
         * 静态初始化器，由JVM来保证线程安全
         */
        private static Singleton instance = new Singleton();
    }

    public static Singleton getInstance(){
        return SingletonHolder.instance;
    }

    /*
    * public enum Singleton {
     // 定义一个枚举的元素，它就代表了Singleton的一个实例。
    uniqueInstance;

    /**
     * 单例可以有自己的操作
        public void singletonOperation(){
            //功能处理
        }
    }*/

}
