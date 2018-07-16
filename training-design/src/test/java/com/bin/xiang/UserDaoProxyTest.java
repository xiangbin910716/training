package com.bin.xiang;

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
 * @Date Created in 2018年07月10日 12:43
 * @since 1.0
 */
public class UserDaoProxyTest {

    /*
    public static void main(String[] args) {
        //静态代理
        UserDao userDao = new UserDao();
        UserDaoProxy proxy = new UserDaoProxy(userDao);
        proxy.save();
    } */
    /*
    public static void main(String[] args) {
        IUserDao userDao = new UserDao();
        //class com.bin.xiang.UserDao
        System.out.println(userDao.getClass());
         //注意：这里只能转为IUserDao接口，不能转为UserDao
        IUserDao proxy = (IUserDao)new ProxyFactory(userDao).getProxyInstance();
        //class com.sun.proxy.$Proxy0
        System.out.println(proxy.getClass());
        proxy.save();
    }*/

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        //class com.bin.xiang.UserDao
        System.out.println(userDao.getClass());
        UserDao proxy = (UserDao) new CglibProxyFactory(userDao).getInstance();
        //class com.bin.xiang.UserDao$$EnhancerByCGLIB$$c701ea51
        System.out.println(proxy.getClass());
        System.out.println(proxy.getClass().getSuperclass().equals(UserDao.class));
        proxy.save();
    }

}
