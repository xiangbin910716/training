package com.bin.xiang;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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
 * @Date Created in 2018年07月10日 12:50
 * @since 1.0
 */
public class ProxyFactory {
    private Object  userDao;

    public ProxyFactory(Object  userDao){
        this.userDao = userDao;
    }

    /**
     *  给目标对象生成代理对象
     */
    public Object getProxyInstance(){
        return Proxy.newProxyInstance(userDao.getClass().getClassLoader(),
                userDao.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("=====执行前======");
                        /**
                         * 注意这里是写userDao，而不是proxy
                         */
                        Object returnValue = method.invoke(userDao,args);
                        System.out.println("======执行后=====");
                        return returnValue;
                    }
                });
        /**
         * (proxy, method, args) -> {
         System.out.println("=====执行前======");
        Object returnValue = method.invoke(userDao,args);
        System.out.println("======执行后=====");
        return returnValue;
        });
         */

    }


}
