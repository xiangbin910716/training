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
 * @Date Created in 2018年07月10日 12:39
 * @since 1.0
 */
public class UserDao implements IUserDao {

    @Override
    public void save() {
        System.out.println("======保存数据========");
    }
}
