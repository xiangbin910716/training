package main;

import datasource.ShardingDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

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
public class ShardingJdbcSelect {
    private static Logger logger = Logger.getLogger(ShardingJdbcSelect.class.getName());
    public static void main(String[] args) {
        try {
            ShardingDataSource shardingDataSource = new ShardingDataSource();
            DataSource dataSource = shardingDataSource.getShardingDataSource();
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from t_user where user_name = ?");
            statement.setString(1, "用户9");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                System.out.println(resultSet.getString("user_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
