package com.bin.xiang;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * <p></p>
 * <p>Cglib子类代理工厂
 * <p>对UserDao在内存中动态构建一个子类对象
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 * @see  https://www.cnblogs.com/cenyu/p/6289209.html
 * @author xiangbin
 * @version 1.0
 * @Date Created in 2018年07月11日 12:42
 * @since 1.0
 */
public class CglibProxyFactory implements MethodInterceptor {
    //维护目标对象
    private UserDao userDao;

    public CglibProxyFactory(UserDao userDao){
        this.userDao = userDao;
    }
    //给目标对象创建一个代理对象
    public Object getInstance(){
        //1.工具类
        Enhancer enhancer = new Enhancer();
        //2.设置父类
        enhancer.setSuperclass(userDao.getClass());
        //3.设置回调函数
        enhancer.setCallback(this);
        //4.创建子类(代理对象)
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("========事务开始=========");
        //执行目标对象的方法
        Object returnValue = method.invoke(userDao,objects);

        System.out.println("========事务结束=========");
        return returnValue;
    }
}
