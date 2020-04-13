package dao;

import entity.User;

public interface UserDao {

    int deleteByPrimaryKey(Long userId);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

}