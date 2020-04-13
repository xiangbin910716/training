package service.impl;

import dao.UserDao;
import entity.User;
import service.UserService;

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
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Override
    public int deleteByPrimaryKey(Long userId) {
        return userDao.deleteByPrimaryKey(userId);
    }

    @Override
    public int insertSelective(User record) {
        return userDao.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Long userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userDao.updateByPrimaryKeySelective(record);
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
