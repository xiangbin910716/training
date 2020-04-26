package main;

import datasource.ShardingDataSource;
import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Random;
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
public class ShardingJdbcTest {

    private static Logger logger = Logger.getLogger(ShardingJdbcTest.class.getName());

    public static void main(String[] args) {
        try {
            ShardingDataSource shardingDataSource = new ShardingDataSource();
            DataSource dataSource = shardingDataSource.getShardingDataSource();
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            Random random = new Random(47);
            for (int i = 1; i < 20; i++) {
                StringBuffer sql = new StringBuffer("insert into t_user(user_name, sex, reg_date) values (");
//                sql.append("" + i);
//                sql.append(new SnowflakeShardingKeyGenerator().generateKey().toString());
                sql.append("'");
                sql.append(random.nextInt(i)).append("'");
                sql.append(",'").append(i % 2 == 0 ? "f" : "m").append("'");
                sql.append(",'").append("2020-01-15").append("'");
                sql.append(")");
                logger.info(sql.toString());
                statement.execute(sql.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
