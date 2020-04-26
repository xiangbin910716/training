package algorithm;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

public class PreciseModuloShardingDatabaseAlgorithm implements PreciseShardingAlgorithm<String> {

    @Override
    public String doSharding(final Collection<String> databaseNames, final PreciseShardingValue<String> shardingValue) {
        for (String each : databaseNames) {
            if (each.endsWith(Long.parseLong(shardingValue.getValue()) % 2 + "")) {
                return each;
            }
        }
        throw new UnsupportedOperationException();
    }
}
