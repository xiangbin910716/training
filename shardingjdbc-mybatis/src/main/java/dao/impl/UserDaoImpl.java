package dao.impl;

import dao.UserDao;
import entity.User;
import org.mybatis.spring.SqlSessionTemplate;

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
public class UserDaoImpl implements UserDao {

    private SqlSessionTemplate sqlSession;
    @Override
    public int deleteByPrimaryKey(Long userId) {
        return sqlSession.delete("dao.UserDao.deleteByPrimaryKey", userId);
    }


    @Override
    public int insertSelective(User record) {
        return sqlSession.insert("dao.UserDao.insertSelective", record);
    }

    @Override
    public User selectByPrimaryKey(Long userId) {
        return sqlSession.selectOne("dao.UserDao.selectByPrimaryKey", userId);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return sqlSession.update("dao.UserDao.updateByPrimaryKeySelective", record);
    }


    public SqlSessionTemplate getSqlSession() {
        return sqlSession;
    }

    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }
}
