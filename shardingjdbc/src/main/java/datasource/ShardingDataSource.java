package datasource;

import algorithm.PreciseModuloShardingDatabaseAlgorithm;
import common.DataSourceUtil;
import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 数据分片
 */
public class ShardingDataSource {

    public DataSource getShardingDataSource() throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(getUserTableRuleConfiguration());
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id", "ds${user_id % 2}"));
//        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id", "t_user${user_id % 2}"));
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_name", new PreciseModuloShardingDatabaseAlgorithm()));
        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(), shardingRuleConfig, new Properties());
    }

    private static KeyGeneratorConfiguration getKeyGeneratorConfiguration() {
        KeyGeneratorConfiguration result = new KeyGeneratorConfiguration("SNOWFLAKE", "user_id");
        return result;
    }

    public TableRuleConfiguration getUserTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration("t_user", "ds${0..1}.t_user${0..1}");
        result.setKeyGeneratorConfig(getKeyGeneratorConfiguration());
        return result;
    }

    public Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>();
        result.put("ds0", DataSourceUtil.createDataSource("ds0"));
        result.put("ds1", DataSourceUtil.createDataSource("ds1"));
        return result;
    }

}
