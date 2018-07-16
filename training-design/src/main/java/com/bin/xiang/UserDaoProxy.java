package com.bin.xiang;

/**
 * <p></p>
 * <p> 代理对象,静态代理
 * <p>静态代理总结:
     1.可以做到在不修改目标对象的功能前提下,对目标功能扩展.
     2.缺点:
     因为代理对象需要与目标对象实现一样的接口,所以会有很多代理类,类太多.同时,一旦接口增加方法,目标对象与代理对象都要维护.
     如何解决静态代理中的缺点呢?答案是可以使用动态代理方式</p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author xiangbin
 * @version 1.0
 * @Date Created in 2018年07月10日 12:40
 * @since 1.0
 */
public class UserDaoProxy implements IUserDao{
    private IUserDao userDao;

    public UserDaoProxy(IUserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public void save() {
        System.out.println("====调用前=======");
        userDao.save();
        System.out.println("=====调用后======");
    }
}
