package service;

import entity.User;

public interface UserService {
    int deleteByPrimaryKey(Long userId);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);
}
